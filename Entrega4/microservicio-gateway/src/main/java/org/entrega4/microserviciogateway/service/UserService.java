package org.entrega4.microserviciogateway.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.entrega4.microserviciogateway.entity.User;
import org.entrega4.microserviciogateway.repository.AuthorityRepository;
import org.entrega4.microserviciogateway.repository.UserRepository;
import org.entrega4.microserviciogateway.service.dto.user.UserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    public long saveUser( UserDTO request ) {
        final var user = new User( request.getUsername() );
        user.setPassword( passwordEncoder.encode( request.getPassword() ) );
        final var roles =  this.authorityRepository.findAllById( request.getAuthorities() );
        user.setAuthorities( roles );
        final var result = this.userRepository.save( user );
        return result.getId();
    }
}
