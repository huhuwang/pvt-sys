package com.hayes.pvtsys.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Random;


@Component
public class AuditorConfig implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        String[] names = {"Hayes", "Lina", "Edward"};
        Random random = new Random();
        int randomNumber = random.nextInt(3);
        return Optional.of(names[randomNumber]);
    }
}
