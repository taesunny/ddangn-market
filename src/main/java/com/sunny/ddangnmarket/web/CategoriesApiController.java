package com.sunny.ddangnmarket.web;

import com.sunny.ddangnmarket.service.categories.CategoriesService;
import com.sunny.ddangnmarket.service.products.ProductsService;
import com.sunny.ddangnmarket.web.dto.categories.CategoriesListResponseDto;
import com.sunny.ddangnmarket.web.dto.products.ProductsResponseDto;
import com.sunny.ddangnmarket.web.dto.products.ProductsSaveRequestDto;
import com.sunny.ddangnmarket.web.dto.products.ProductsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CategoriesApiController {

    private final CategoriesService categoriesService;

    @GetMapping("/api/v1/categories")
    public List<CategoriesListResponseDto> getList() {
        return categoriesService.findAll();
    }
}
