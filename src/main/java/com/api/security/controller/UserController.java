package com.api.security.controller;

import com.api.security.model.domain.UserModel;
import com.api.security.model.dto.LoginDto;
import com.api.security.model.dto.RegisterDto;
import com.api.security.repository.UserRepository;
import com.api.security.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public UserController(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @GetMapping("/me")
    public String me() {
        return "Hello world!\nIt's me being developed!";
    }

    @GetMapping("/user")
    public String user(){
        return "Hello world!\nIt's me USER, being developed!";
    }

    @GetMapping("/admin")
    public String admin(){
        return "Hello world!\nIt's me ADMIN, being developed!";
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginDto loginDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        var token = tokenService.generateToken((UserModel) authentication.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterDto registerDto) {
        if (userRepository.findByUsername(registerDto.getUsername()) != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.getPassword());
        var newUser = new UserModel(registerDto.getUsername(), encryptedPassword, registerDto.getEmail(), registerDto.getRole());
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(newUser));
    }
}
