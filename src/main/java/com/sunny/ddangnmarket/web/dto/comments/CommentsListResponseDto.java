package com.sunny.ddangnmarket.web.dto.comments;

import com.sunny.ddangnmarket.domain.products.Comments;
import com.sunny.ddangnmarket.domain.products.Products;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentsListResponseDto {
    private Long id;
    private String content;
    private String userId;
    private String userEmail;
    private Long productId;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public CommentsListResponseDto(Comments entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.userId = entity.getUserId();
        this.userEmail = entity.getUserEmail();
        this.productId = entity.getProductId();
        this.createdTime = entity.getCreatedDate();
        this.updatedTime = entity.getModifiedDate();
    }
}
