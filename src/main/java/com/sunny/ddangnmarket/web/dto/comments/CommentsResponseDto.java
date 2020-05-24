package com.sunny.ddangnmarket.web.dto.comments;

import com.sunny.ddangnmarket.domain.products.Comments;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentsResponseDto {
    private Long id;
    private String content;
    private Long userId;
    private String userEmail;
    private Long productId;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public CommentsResponseDto(Comments entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.userId = entity.getUserId();
        this.userEmail = entity.getUserEmail();
        this.productId = entity.getUserId();
        this.createdTime = entity.getCreatedDate();
        this.updatedTime = entity.getModifiedDate();
    }
}
