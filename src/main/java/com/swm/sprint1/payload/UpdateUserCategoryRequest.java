package com.swm.sprint1.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Setter
@Getter
public class UpdateUserCategoryRequest {

    @NotEmpty
    List<String> categories;
}

