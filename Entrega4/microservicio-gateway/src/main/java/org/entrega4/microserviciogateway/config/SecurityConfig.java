package org.entrega4.microserviciogateway.config;

import org.entrega4.microserviciogateway.security.AuthotityConstant;
import org.entrega4.microserviciogateway.security.jwt.JwtFilter;
import org.entrega4.microserviciogateway.security.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final TokenProvider tokenProvider;

    public SecurityConfig( TokenProvider tokenProvider ) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain( final HttpSecurity http ) throws Exception {
        http
            .csrf( AbstractHttpConfigurer::disable );
        http
            .sessionManagement( s -> s.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) );
        http
            .securityMatcher("/api/**" )
            .authorizeHttpRequests( authz -> authz
                    .requestMatchers(HttpMethod.POST, "/api/authenticate").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/user").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/cuentas").authenticated()
                    .requestMatchers(HttpMethod.GET, "/api/cuentas").hasAuthority( AuthotityConstant._ADMIN )
                    .requestMatchers( "/api/usuarios").authenticated()
                    .requestMatchers( "/api/monopatines").hasAuthority( AuthotityConstant._ADMIN )
                    .requestMatchers( "/api/paradas").hasAuthority( AuthotityConstant._ADMIN )
                    .requestMatchers("/api/reportes").hasAuthority( AuthotityConstant._ADMIN )
                    .requestMatchers("/api/tarifas").hasAuthority( AuthotityConstant._ADMIN )
                    .requestMatchers("/api/mantenimientos").hasAuthority( AuthotityConstant._ADMIN )
                    .requestMatchers("/api/pausas").hasAuthority( AuthotityConstant._ADMIN )
                    .requestMatchers("/api/viajes").hasAuthority( AuthotityConstant._ADMIN )
                    .requestMatchers("/api/mantenimientos/**").hasAuthority( AuthotityConstant._ADMIN )
                    .requestMatchers("/api/usuarios/**").hasAuthority( AuthotityConstant._ADMIN )
                    .requestMatchers("/api/cuentas/**").hasAuthority( AuthotityConstant._ADMIN )
                    .requestMatchers("/api/monopatines/monopatinesCercanos/**").hasAuthority( AuthotityConstant._ADMIN )
                    .requestMatchers("/api/monopatines/**").hasAuthority( AuthotityConstant._ADMIN )
                    .requestMatchers("/api/paradas/monopatines/**").permitAll()
                    .requestMatchers("/api/paradas/**").hasAuthority( AuthotityConstant._ADMIN )
                    .requestMatchers("/api/tarifas/**").hasAuthority( AuthotityConstant._ADMIN )
                    .requestMatchers("/api/reportes/**").hasAuthority( AuthotityConstant._ADMIN )
                    .requestMatchers("/api/viajes/**").hasAuthority( AuthotityConstant._ADMIN )
                    .anyRequest().authenticated()
            )
            .httpBasic( Customizer.withDefaults() )
            .addFilterBefore( new JwtFilter( this.tokenProvider ), UsernamePasswordAuthenticationFilter.class );
        return http.build();
    }

}
