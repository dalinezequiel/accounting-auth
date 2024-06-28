package com.api.security.mapper;

import com.api.security.model.domain.UserModel;
import com.api.security.model.dto.LoginDto;
import com.api.security.model.dto.RegisterDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public static UserModel toUserModel(RegisterDto signUpDto){
        return null;
    }

    public static UserModel toUserModel(LoginDto loginDto){
        return null;
    }
}
