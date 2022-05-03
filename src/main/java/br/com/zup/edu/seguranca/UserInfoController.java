package br.com.zup.edu.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserInfoController {

    @Autowired
    private LoggedInUser user;

    @GetMapping("/api/user-info")
    public ResponseEntity<?> getUserInfo(@AuthenticationPrincipal Jwt principal) {
        return ResponseEntity.ok(principal);
    }

    @GetMapping("/api/user-info/email-with-claims")
    private ResponseEntity<?> getEmailWithClaims(
            @AuthenticationPrincipal(expression = "claims") Map<String, Object> claims) {
        return ResponseEntity
                .ok(Map.of(
                        "email", claims.get("email"),
                        "claims", claims)
                );
    }

    @GetMapping("/api/user-info/username")
    private ResponseEntity<?> getUsername() {
        return ResponseEntity
                .ok(Map.of("username", user.getUsername()));
    }
}
