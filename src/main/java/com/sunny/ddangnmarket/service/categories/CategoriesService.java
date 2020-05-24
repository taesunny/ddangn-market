package com.sunny.ddangnmarket.service.categories;

import com.sunny.ddangnmarket.domain.products.Category;
import com.sunny.ddangnmarket.domain.products.Products;
import com.sunny.ddangnmarket.domain.products.ProductsRepository;
import com.sunny.ddangnmarket.web.dto.categories.CategoriesListResponseDto;
import com.sunny.ddangnmarket.web.dto.products.ProductsListResponseDto;
import com.sunny.ddangnmarket.web.dto.products.ProductsResponseDto;
import com.sunny.ddangnmarket.web.dto.products.ProductsSaveRequestDto;
import com.sunny.ddangnmarket.web.dto.products.ProductsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class CategoriesService {
    private final ProductsRepository productsRepository;

    public List<CategoriesListResponseDto> findAll() {
        return Stream.of(Category.values())
                .map(m -> new CategoriesListResponseDto(m.getText()))
                .collect(Collectors.toList());
    }
}
