package com.example.usermanagement.service.impl;

import com.example.usermanagement.Exception.AuthenticationException;
import com.example.usermanagement.Exception.BadRequestException;
import com.example.usermanagement.Repository.UserRepo;
import com.example.usermanagement.dto.*;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.service.JwtService;
import com.example.usermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseDto  signUp(UserDto userDto) {
        try{
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

            return new ResponseDto("00",
                    "Success",
                    null);

        }catch (Exception ex){
            log.error("Bad Request");
            throw new BadRequestException();
        }


    }

    @Override
    public ResponseDto login(LoginDto loginDto) {
        if (!userRepo.existsByEmail(loginDto.getEmail())){
            return new ResponseDto("02",
                    "No such user exists! ",
                    null);
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()

                    )
            );

        }catch (Exception e){
            log.error("Invalid Credentials");
            throw new AuthenticationException();
        }


        User user = userRepo.findByEmail(loginDto.getEmail()).orElseThrow();
        String toke = jwtService.generateToke(user);
        TokenDto tokenDto=new TokenDto(toke);
        if (toke==null){
            return new ResponseDto("03",
                    "Invalid Credentials",
                    null);

        }
        return new ResponseDto("00",
                "Success",
                tokenDto);

    }
}
