package de.finqen.securitychains.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/api"})
public class ApiEndpoint {

    @GetMapping(value = "/user")
    public String getUser(@AuthenticationPrincipal Jwt jwt) {
        return "User: " + jwt.getSubject();
    }

    @GetMapping(value = "/user/token", produces = MediaType.TEXT_PLAIN_VALUE)
    @PreAuthorize("isFullyAuthenticated()")
    public ResponseEntity<String> getUserToken() {
        return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body("USER_TOKEN123");
    }

}
