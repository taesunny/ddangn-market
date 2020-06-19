package com.sunny.ddangnmarket.web.dto.products;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductsUpdateRequestDto {
    private String title;
    private String content;
    private String category;
    private int price;

    @Builder
    public ProductsUpdateRequestDto(String title, String content, String category, int price) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.price = price;
    }
}
