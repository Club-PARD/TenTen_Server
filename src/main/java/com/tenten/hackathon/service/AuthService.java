package com.tenten.hackathon.service;

import com.tenten.hackathon.dto.ResponseDto;
import com.tenten.hackathon.dto.SignInDto;
import com.tenten.hackathon.dto.SignUpDto;
import com.tenten.hackathon.dto.SingInResponseDto;
import com.tenten.hackathon.entity.UserEntity;
import com.tenten.hackathon.repository.UserRepository;
import com.tenten.hackathon.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired UserRepository userRepository;
    @Autowired TokenProvider tokenProvider;

    public ResponseDto<?> signUp(SignUpDto dto) {
        String userEmail = dto.getUserEmail();
        String userPassword = dto.getUserPassword();
        String userPasswordCheck = dto.getUserPasswordCheck();

        // email 중복 확인
        try {
            if (userRepository.existsById(userEmail))
                return ResponseDto.setFailed("Existed Email!");
        } catch (Exception error) {
            return ResponseDto.setFailed("Data Base Error!");
        }

        // 비밀번호가 서로 다르면 failed response 반환!
        if (!userPassword.equals(userPasswordCheck))
            return ResponseDto.setFailed("Password does not matched!");

        // UserEntity 생성
        UserEntity userEntity = new UserEntity(dto);

        // UserRepository를 이용해서 데이터베이스에 Entity 저장!!
        try {
            userRepository.save(userEntity);
        } catch (Exception error) {
            return ResponseDto.setFailed("Data Base Error!");
        }

        // 성공시 success response 반환
        return ResponseDto.setSuccess("Sign Up Success!", null);
    }

    public ResponseDto<SingInResponseDto> signIn(SignInDto dto) {

        String userEmail = dto.getUserEmail();
        String userPassword = dto.getUserPassword();
        try {
            boolean existed = userRepository.existsByUserEmailAndUserPassword(userEmail, userPassword);
            if (!existed) return ResponseDto.setFailed("Sign In Informaion Does Not Match");
        } catch (Exception error) {
            return ResponseDto.setFailed("Database Error");
        }

        UserEntity userEntity = null;
        try {
            userEntity = userRepository.findById(userEmail).get();
        } catch (Exception error) {
            return ResponseDto.setFailed("Database Error");
        }

        userEntity.setUserPassword("");

        String token = tokenProvider.create(userEmail);
        int exprTime = 3600000;

        SingInResponseDto signInResponseDto = new SingInResponseDto(token, exprTime, userEntity);
        return ResponseDto.setSuccess("Sign In Success", signInResponseDto);
    }
}
