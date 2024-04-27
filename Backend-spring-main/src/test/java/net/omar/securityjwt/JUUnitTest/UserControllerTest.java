package net.omar.securityjwt.JUUnitTest;

import net.omar.securityjwt.controller.UserController;
import net.omar.securityjwt.dto.AuthenticationResponseDTO;
import net.omar.securityjwt.dto.LoginRequestDTO;
import net.omar.securityjwt.dto.RegisterRequestDTO;
import net.omar.securityjwt.service.UserService;
import net.omar.securityjwt.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testRegister() {
        // Given
        RegisterRequestDTO request = new RegisterRequestDTO(); // Assuming you have a constructor or builder method
        AuthenticationResponseDTO responseDTO = new AuthenticationResponseDTO(); // Assuming you have a constructor or builder method
        when(userService.register(request)).thenReturn(responseDTO);

        // When
        ResponseEntity
                <AuthenticationResponseDTO> response = userController.register(request);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    public void testLogin() {
        // Given
        LoginRequestDTO request = new LoginRequestDTO(); // Assuming you have a constructor or builder method
        AuthenticationResponseDTO responseDTO = new AuthenticationResponseDTO(); // Assuming you have a constructor or builder method
        when(userService.login(request)).thenReturn(responseDTO);

        // When
        ResponseEntity
                <AuthenticationResponseDTO> response = userController.login(request);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    public void testGetAllUsers() {
        // Given
        List<User> userList = new ArrayList<>(); // Assuming you have some users to return
        when(userService.getAllUser()).thenReturn(userList);

        // When
        ResponseEntity<List<User>> response = userController.getAllUsers();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());
    }
}
