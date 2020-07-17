package com.swm.sprint1.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;

@Getter
@NoArgsConstructor
public class GetUserResponse {
    private String name;
    private Long id;
    private List<String> categories;

    public GetUserResponse(Long id, String name, List<String> categories) {
        this.id=id;
        this.name=name;
        this.categories=categories;
    }
}
