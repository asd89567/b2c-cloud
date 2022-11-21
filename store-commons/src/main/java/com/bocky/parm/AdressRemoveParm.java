package com.bocky.parm;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AdressRemoveParm {

    @NotBlank
    private Integer id;
}
