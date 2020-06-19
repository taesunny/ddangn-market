package com.sunny.ddangnmarket.domain.products;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductsRepositoryTest {

    @Autowired
    ProductsRepository postsRepository;

    @AfterEach
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void saveAndFindPost() {
        // given
        String title = "sunny's test post title";
        String content = "sunny's test post content";

        postsRepository.save(Products.builder()
                .title(title)
                .category(Category.BABY)
                .content(content)
                .build());

        // when
        List<Products> productsList = postsRepository.findAll();

        // then
        Products product = productsList.get(0);

        assertEquals(product.getTitle(), title);
        assertEquals(product.getCategory(), Category.BABY);
        assertEquals(product.getContent(), content);
    }

    @Test
    public void registerBaseTimeEntity() {
        //given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        postsRepository.save(Products.builder()
                .title("title")
                .category(Category.BABY)
                .content("content")
                .build());

        //when
        List<Products> productsList = postsRepository.findAll();

        //then
        Products products = productsList.get(0);

        System.out.println(">>>>>>>> createdDate=" + products.getCreatedDate()
                + ", modifiedDate=" + products.getModifiedDate());

        assertTrue(products.getCreatedDate().isAfter(now));
        assertTrue(products.getModifiedDate().isAfter(now));
    }
}
