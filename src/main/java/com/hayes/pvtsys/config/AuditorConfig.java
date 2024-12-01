package com.hayes.pvtsys.config;

import com.hayes.pvtsys.dto.UserDto;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class AuditorConfig implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = ((UserDto) authentication.getPrincipal()).getPvtUser().getUserName();
        return Optional.of(userName);
    }
}
