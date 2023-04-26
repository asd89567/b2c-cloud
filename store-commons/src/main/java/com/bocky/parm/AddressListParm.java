package com.bocky.parm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddressListParm {

    @JsonProperty("user_id")
    @NotNull
    private Integer userId;
}
