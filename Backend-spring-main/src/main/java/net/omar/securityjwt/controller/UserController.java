package net.omar.securityjwt.controller;

import net.omar.securityjwt.dto.LoginRequestDTO;
import net.omar.securityjwt.dto.AuthenticationResponseDTO;
import net.omar.securityjwt.dto.RegisterRequestDTO;
import net.omar.securityjwt.service.UserService;
import net.omar.securityjwt.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/auth")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        System.out.println(request.toString());
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody LoginRequestDTO request) {
        System.out.println("Request is here --->" + request.toString());
        AuthenticationResponseDTO responseDTO = userService.login(request);
        System.out.println("Response is here --->" + responseDTO.toString());
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUser());
    }

}
