package com.tenten.hackathon.controller;

import com.tenten.hackathon.dto.ResponseDto;
import com.tenten.hackathon.dto.SignInDto;
import com.tenten.hackathon.dto.SignUpDto;
import com.tenten.hackathon.dto.SingInResponseDto;
import com.tenten.hackathon.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class AuthController {
    @Autowired AuthService authService;
    @PostMapping("/signIn")
    public ResponseDto<SingInResponseDto> signIn(@RequestBody SignInDto request){
        ResponseDto<SingInResponseDto> result = authService.signIn(request);
        return result;
    }
    @PostMapping("/signUp")
    public ResponseDto<?> signUp(@RequestBody SignUpDto request){
        ResponseDto<?> result = authService.signUp(request);
        return result;
    }
}
