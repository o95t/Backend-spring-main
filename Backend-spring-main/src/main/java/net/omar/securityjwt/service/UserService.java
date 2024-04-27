package net.omar.securityjwt.service;

import net.omar.securityjwt.auth.AuthenticationService;
import net.omar.securityjwt.dto.AuthenticationResponseDTO;
import net.omar.securityjwt.dto.LoginRequestDTO;
import net.omar.securityjwt.dto.RegisterRequestDTO;
import net.omar.securityjwt.user.User;
import net.omar.securityjwt.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    UserRepository repository;

    public AuthenticationResponseDTO register(RegisterRequestDTO request) {
        AuthenticationResponseDTO responseDTO = authenticationService.register(request);
        int id = repository.findByEmail(request.getEmail()).get().getId();
        request.setId(id);
        return responseDTO;
    }

    public AuthenticationResponseDTO login(LoginRequestDTO request) {
        AuthenticationResponseDTO responseDTO = authenticationService.authenticate(request);
        return responseDTO;
    }

    public List<User> getAllUser(){
        return repository.findAll();
    }
}
