package com.tenten.hackathon.service;

import com.tenten.hackathon.dto.PatchUserDto;
import com.tenten.hackathon.dto.PatchUserResponseDto;
import com.tenten.hackathon.dto.ResponseDto;
import com.tenten.hackathon.entity.UserEntity;
import com.tenten.hackathon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ResponseDto<PatchUserResponseDto> patchUser(@RequestBody PatchUserDto dto, String userEmail) {

        UserEntity userEntity = null;
        double userTotalWeight = dto.getUserTotalWeight();

        try {
            userEntity = userRepository.findByUserEmail(userEmail);
            if (userEntity == null) {
                return ResponseDto.setFailed("Dose Not Exist User");
            }
            userEntity.setUserTotalWeight(userTotalWeight);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("DB Error");
        }
        userEntity.setUserPassword("");

        PatchUserResponseDto patchUserResponseDto = new PatchUserResponseDto(userEntity);

        return ResponseDto.setSuccess("Success", patchUserResponseDto);
    }

}

