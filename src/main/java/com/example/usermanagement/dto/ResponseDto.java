package com.example.usermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private String responseCode;
    private String responseMsg;
    private Object  content;

}
