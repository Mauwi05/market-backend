package com.tecnm.merida.market_backend.security;

import com.tecnm.merida.market_backend.persistence.crud.ClienteCrudRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initDefaultClientPassword(ClienteCrudRepository clienteCrudRepository,
                                                       PasswordEncoder passwordEncoder) {
        return args -> clienteCrudRepository.findByCorreoElectronico("lionel@messi.com")
                .ifPresent(cliente -> {
                    if (cliente.getPassword() == null || cliente.getPassword().isBlank()) {
                        cliente.setPassword(passwordEncoder.encode("1234"));
                        clienteCrudRepository.save(cliente);
                    }
                });
    }
}
