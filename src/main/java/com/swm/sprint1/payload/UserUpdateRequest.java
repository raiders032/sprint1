package com.swm.sprint1.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UserUpdateRequest {

    @NotBlank
    private String name;
}
