package com.sunny.ddangnmarket.web.dto.categories;

import lombok.Getter;

@Getter
public class CategoriesListResponseDto {
    private String name;

    public CategoriesListResponseDto(String name) {
        this.name = name;
    }
}
