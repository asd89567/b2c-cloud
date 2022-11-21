package com.bocky.parm;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserLoginParm {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
