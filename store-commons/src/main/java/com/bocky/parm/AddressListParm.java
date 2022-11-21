package com.bocky.parm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddressListParm {

    @NotBlank
    @JsonProperty("user_id ")
    private Integer userId;
}
