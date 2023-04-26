package com.bocky.parm;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AdressRemoveParm {

    @NotNull
    private Integer id;
}
