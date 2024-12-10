package microviaje;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableDiscoveryClient
@EnableMongoRepositories(basePackages = "microviaje.repository.mongodb")
@EnableJpaRepositories(basePackages = "microviaje.repository.jpa")
public class MicroservicioViajeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicioViajeApplication.class, args);


    }

}
