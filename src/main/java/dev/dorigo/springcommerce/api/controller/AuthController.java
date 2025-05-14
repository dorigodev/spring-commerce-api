package dev.dorigo.springcommerce.api.controller;


import dev.dorigo.springcommerce.api.controller.request.LoginRequestDTO;
import dev.dorigo.springcommerce.api.controller.request.UserRequest;
import dev.dorigo.springcommerce.api.controller.response.LoginResponseDTO;
import dev.dorigo.springcommerce.api.controller.response.UserResponse;
import dev.dorigo.springcommerce.api.domain.User.User;
import dev.dorigo.springcommerce.api.infra.security.TokenService;
import dev.dorigo.springcommerce.api.mapper.UserMapper;
import dev.dorigo.springcommerce.api.repository.UserRepository;
import dev.dorigo.springcommerce.api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserService userService;
    private final UserRepository userRepository;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        try {
            var userAndPass = new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.password());
            var auth = authenticationManager.authenticate(userAndPass);
            var user = (User) auth.getPrincipal();
            var token = tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRequest request) {
        if(this.userRepository.findByEmail(request.email()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        User savedUser = userService.save(UserMapper.toUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponse(savedUser));
    }
}
