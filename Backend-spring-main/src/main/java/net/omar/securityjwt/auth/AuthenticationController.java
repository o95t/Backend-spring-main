package net.omar.securityjwt.auth;

import lombok.RequiredArgsConstructor;
import net.omar.securityjwt.dto.LoginRequestDTO;
import net.omar.securityjwt.dto.AuthenticationResponseDTO;
import net.omar.securityjwt.dto.RegisterRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
