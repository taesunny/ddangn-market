package com.sunny.ddangnmarket.service.comments;

import com.sunny.ddangnmarket.domain.products.Comments;
import com.sunny.ddangnmarket.domain.products.CommentsRepository;
import com.sunny.ddangnmarket.web.dto.comments.CommentsListResponseDto;
import com.sunny.ddangnmarket.web.dto.comments.CommentsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentsService {
    private final CommentsRepository commentsRepository;

    @Transactional
    public Long save(CommentsSaveRequestDto requestDto, String userId, String userEmail, Long productId) {
        return commentsRepository.save(requestDto.toEntity(userId, userEmail, productId)).getId();
    }

    @Transactional
    public void delete(Long id) {
        Comments comment = commentsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No comment. id=" + id));

        commentsRepository.delete(comment);
    }

    @Transactional
    public void deleteAllByProductId(Long productId) {
        commentsRepository.deleteAllByProductId(productId);
    }

    @Transactional(readOnly = true) // faster by using readOnly
    public List<CommentsListResponseDto> findAllByProductIdDesc(Long productId) {
        return commentsRepository.findAllByProductIdOrderByCreatedDateDesc(productId).stream()
                .map(CommentsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true) // faster by using readOnly
    public boolean hasComment(Long id) {
        Comments comment = commentsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No comment. id=" + id));

        return true;
    }
}
