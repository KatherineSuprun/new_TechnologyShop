package com.example.mainapp.dto;

import com.example.mainapp.entity.Image;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDto {

    private String id;

    private String username;

    private String email;

    private Image avatar;

    private Boolean isExpired;

    private Boolean isActive;

    private LocalDateTime registeredOn;

    private String password;

}
