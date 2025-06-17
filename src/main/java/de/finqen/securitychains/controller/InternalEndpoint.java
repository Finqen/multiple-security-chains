package de.finqen.securitychains.controller;

import de.finqen.securitychains.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(path = {"/internal"})
public class InternalEndpoint {

    @GetMapping(value = "/token", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getInternalToken() {
        return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body("TOKEN123");
    }

    @GetMapping(value = "/user")
    public ResponseEntity<UserDto> getInternalUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(
                new UserDto().toBuilder()
                        .username(authentication.getName())
                        .type("internal")
                        .roles(authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                        .build());
    }
}