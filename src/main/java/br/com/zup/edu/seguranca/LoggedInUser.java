package br.com.zup.edu.seguranca;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class LoggedInUser {

    public String getUsername() {
        return (String) principal().getClaims().get("preferred_username");
    }

    private Jwt principal() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return (Jwt) securityContext
                .getAuthentication().getPrincipal();
    }

}
