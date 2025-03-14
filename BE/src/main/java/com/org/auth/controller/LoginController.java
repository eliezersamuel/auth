package com.org.auth.controller;

import com.org.auth.dto.UserDTO;
import com.org.auth.dto.UserLoginDTO;
import com.org.auth.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping(value = "sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signup(@RequestBody @Valid UserDTO userDTO){
        return loginService.signup(userDTO);
    }

    @PostMapping(value = "sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO){
        return loginService.login(userLoginDTO);
    }
}
