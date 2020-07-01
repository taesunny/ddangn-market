package com.sunny.ddangnmarket.web.dto.comments;

import com.sunny.ddangnmarket.domain.products.Category;
import com.sunny.ddangnmarket.domain.products.Comments;
import com.sunny.ddangnmarket.domain.products.Products;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class CommentsSaveRequestDto {
    private String content;

    @Builder
    public CommentsSaveRequestDto(Long productId, String content) {
        this.content = content;
    }

    public Comments toEntity(String userId, String userEmail, Long productId) {
        return Comments.builder()
                .content(content)
                .userId(userId)
                .userEmail(userEmail)
                .productId(productId)
                .build();
    }
}
