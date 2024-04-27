package net.omar.securityjwt.auth;

import lombok.RequiredArgsConstructor;
import net.omar.securityjwt.config.JwtService;
import net.omar.securityjwt.dto.LoginRequestDTO;
import net.omar.securityjwt.dto.AuthenticationResponseDTO;
import net.omar.securityjwt.dto.RegisterRequestDTO;
import net.omar.securityjwt.user.Address;
import net.omar.securityjwt.user.Role;
import net.omar.securityjwt.user.User;
import net.omar.securityjwt.user.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDTO register(RegisterRequestDTO request) {
        Address address = new Address(request.getStreetAddress(),request.getCity(),request.getState(),request.getZipCode());
        var user = User.builder()
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        user.setAddress(address);
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDTO.builder().token(jwtToken).build();
    }

    public AuthenticationResponseDTO authenticate(LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDTO.builder().token(jwtToken).build();
    }
}
