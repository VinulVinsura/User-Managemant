package com.example.usermanagement.controller;

import com.example.usermanagement.dto.ResponseDto;
import com.example.usermanagement.dto.UserDto;
import com.example.usermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup") //add user
    public ResponseEntity<ResponseDto>signUp(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.signUp(userDto));
    }

//    @PostMapping("login")
//    public ResponseEntity<ResponseDto> login(@RequestBody )
}
