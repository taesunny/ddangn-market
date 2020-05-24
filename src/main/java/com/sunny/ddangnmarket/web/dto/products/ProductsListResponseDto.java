package com.sunny.ddangnmarket.web.dto.products;

import com.sunny.ddangnmarket.domain.products.Products;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProductsListResponseDto {
    private Long id;
    private String title;
    private String content;
    private Long userId;
    private int price;
    private String category;
    private String region;
    private String imageFilePath;
    private String status;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public ProductsListResponseDto(Products entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.userId = entity.getUserId();
        this.price = entity.getPrice();
        this.category = entity.getCategory().getText();
        this.region = entity.getRegion();
        this.status = entity.getStatus().getText();
        this.imageFilePath = entity.getImageFilePath();
        this.createdTime = entity.getCreatedDate();
        this.updatedTime = entity.getModifiedDate();
    }
}
