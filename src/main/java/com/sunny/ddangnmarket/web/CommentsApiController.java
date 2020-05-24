package com.sunny.ddangnmarket.web;

import com.sunny.ddangnmarket.domain.products.Status;
import com.sunny.ddangnmarket.security.CurrentUser;
import com.sunny.ddangnmarket.security.UserPrincipal;
import com.sunny.ddangnmarket.service.S3Service;
import com.sunny.ddangnmarket.service.comments.CommentsService;
import com.sunny.ddangnmarket.service.products.ProductsService;
import com.sunny.ddangnmarket.web.dto.comments.CommentsListResponseDto;
import com.sunny.ddangnmarket.web.dto.comments.CommentsSaveRequestDto;
import com.sunny.ddangnmarket.web.dto.products.ProductsListResponseDto;
import com.sunny.ddangnmarket.web.dto.products.ProductsResponseDto;
import com.sunny.ddangnmarket.web.dto.products.ProductsSaveRequestDto;
import com.sunny.ddangnmarket.web.dto.products.ProductsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class CommentsApiController {
    private final CommentsService commentsService;

    @PostMapping(value = "/products/{productId}/comments")
    @PreAuthorize("hasRole('USER')")
    public Long save(@PathVariable Long productId, @RequestBody CommentsSaveRequestDto requestDto, @CurrentUser UserPrincipal userPrincipal) {
        return commentsService.save(requestDto, userPrincipal.getId(), userPrincipal.getEmail(), productId);
    }

    @GetMapping("/products/{productId}/comments")
    public List<CommentsListResponseDto> findByProductId(@PathVariable Long productId) {
        return commentsService.findAllByProductIdDesc(productId);
    }

    @DeleteMapping("/products/{productId}/comments/{commentsId}")
    @PreAuthorize("hasRole('USER')")
    public Long delete(@PathVariable Long productId, @PathVariable Long commentsId) {
        commentsService.delete(commentsId);

        return commentsId;
    }
}
