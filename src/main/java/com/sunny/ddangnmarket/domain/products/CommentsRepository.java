package com.sunny.ddangnmarket.domain.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

//    @Query("SELECT c FROM Comments c WHERE c.productId ORDER BY c.createdDate DESC") // TODO: check desc or asce
    List<Comments> findAllByProductIdOrderByCreatedDateDesc(Long productId);

    void deleteAllByProductId(Long productId);
}
