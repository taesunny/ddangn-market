package com.sunny.ddangnmarket.domain.products;

import com.sunny.ddangnmarket.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Products extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String region;

    @Column(nullable = true) // TODO: change it to true
    private Long userId;

    @Column(nullable = false)
    private int price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(columnDefinition = "TEXT")
    private String imageFilePath;

    @Builder
    public Products(String title, String content, Long userId, int price, Category category, String region, String imageFilePath) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.price = price;
        this.category = category;
        this.region = region;
        this.imageFilePath = imageFilePath;
        this.status = Status.SELLING;
    }

    public void update(String title, String content, int price) {
        this.title = title;
        this.content = content;
        this.price = price;
    }

    public void updateStatus(String statusQueryString) {
        this.status = Status.getFromQueryString(statusQueryString);
    }
}
