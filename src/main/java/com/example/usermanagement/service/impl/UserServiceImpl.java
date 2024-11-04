package com.example.usermanagement.service.impl;

import com.example.usermanagement.Repository.UserRepo;
import com.example.usermanagement.dto.ResponseDto;
import com.example.usermanagement.dto.UserDto;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.service.JwtService;
import com.example.usermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseDto  signUp(UserDto userDto) {
        if (userRepo.existsByEmail(userDto.getEmail())){
            return new ResponseDto("04",
                    "User Already Exists",
                    null);

        }
        User user=new User(null,
                userDto.getEmail(),
                userDto.getFirstName(),
                userDto.getLastName(),
                passwordEncoder.encode(userDto.getPassword()));
        User saveUser = userRepo.save(user);
        String toke = jwtService.generateToke(saveUser);

        if (saveUser==null){
            return new ResponseDto("06",
                    "BadRequest",
            null);


        }
         return new ResponseDto("00",
                "Success",
                null);

    }
}
