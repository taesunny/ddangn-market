package com.sunny.ddangnmarket.domain.images;

import com.sunny.ddangnmarket.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Images extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String filePath;

    @Builder
    public Images(Long id, String title, String filePath) {
        this.id = id;
        this.title = title;
        this.filePath = filePath;
    }
}
