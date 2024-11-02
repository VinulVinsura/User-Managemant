package com.example.usermanagement.service;

import com.example.usermanagement.dto.ResponseDto;
import com.example.usermanagement.dto.UserDto;

public interface UserService {
    ResponseDto signUp(UserDto userDto);
}
