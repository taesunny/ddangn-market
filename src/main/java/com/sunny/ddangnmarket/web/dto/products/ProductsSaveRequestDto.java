package com.sunny.ddangnmarket.web.dto.products;

import com.sunny.ddangnmarket.domain.products.Category;
import com.sunny.ddangnmarket.domain.products.Products;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class ProductsSaveRequestDto {
    private String title;
    private String content;
    private int price;
    private String category;
    private String region;

    @Setter
    private String imageFilePath;

    @Builder
    public ProductsSaveRequestDto(String title, String content, int price, String category, String region) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.category = category;
        this.region = region;
    }

    public Products toEntity(Long userId) {
        return Products.builder()
                .title(title)
                .content(content)
                .userId(userId)
                .price(price)
                .category(Category.get(category))
                .region(region)
                .imageFilePath(imageFilePath)
                .build();
    }
}
