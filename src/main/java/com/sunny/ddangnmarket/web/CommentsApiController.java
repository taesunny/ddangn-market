package com.sunny.ddangnmarket.web;

import com.sunny.ddangnmarket.service.comments.CommentsService;
import com.sunny.ddangnmarket.util.KeyCloakUtils;
import com.sunny.ddangnmarket.web.dto.comments.CommentsListResponseDto;
import com.sunny.ddangnmarket.web.dto.comments.CommentsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class CommentsApiController {
    private final CommentsService commentsService;

    @PostMapping(value = "/products/{productId}/comments")
//    @PreAuthorize("hasRole('USER')")
    public Long save(@PathVariable Long productId, @RequestBody CommentsSaveRequestDto requestDto, HttpServletRequest request) {
        return commentsService.save(requestDto, KeyCloakUtils.getUserId(request), KeyCloakUtils.getUserEmail(request), productId);
    }

    @GetMapping("/products/{productId}/comments")
    public List<CommentsListResponseDto> findByProductId(@PathVariable Long productId) {
        return commentsService.findAllByProductIdDesc(productId);
    }

    @DeleteMapping("/products/{productId}/comments/{commentsId}")
//    @PreAuthorize("hasRole('USER')")
    public Long delete(@PathVariable Long productId, @PathVariable Long commentsId) {
        commentsService.delete(commentsId);

        return commentsId;
    }
}
