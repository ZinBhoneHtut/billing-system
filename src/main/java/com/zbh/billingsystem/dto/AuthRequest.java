package com.zbh.billingsystem.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AuthRequest {
    @NotNull(message = "* Username cannot be empty")
    private String username;
    @NotNull(message = "* Password cannot be empty")
    private String password;
}
