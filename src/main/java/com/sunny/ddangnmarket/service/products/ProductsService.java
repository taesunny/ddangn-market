package com.sunny.ddangnmarket.service.products;

import com.sunny.ddangnmarket.domain.products.Category;
import com.sunny.ddangnmarket.domain.products.Products;
import com.sunny.ddangnmarket.domain.products.ProductsRepository;
import com.sunny.ddangnmarket.service.S3Service;
import com.sunny.ddangnmarket.web.dto.products.ProductsListResponseDto;
import com.sunny.ddangnmarket.web.dto.products.ProductsResponseDto;
import com.sunny.ddangnmarket.web.dto.products.ProductsSaveRequestDto;
import com.sunny.ddangnmarket.web.dto.products.ProductsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductsService {
    private final ProductsRepository productsRepository;

    @Transactional
    public Long save(ProductsSaveRequestDto requestDto, Long userId) {
        // TODO: add category validation
        return productsRepository.save(requestDto.toEntity(userId)).getId();
    }

    @Transactional
    public Long update(Long id, ProductsUpdateRequestDto requestDto) {
        // TODO: add category validation
        Products products = productsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No post. id=" + id));

        products.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getPrice());

        return id;
    }

    @Transactional
    public Long updateStatus(Long id, String statusQeuryString) {
        // TODO: add category validation
        Products products = productsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No post. id=" + id));

        products.updateStatus(statusQeuryString);

        return id;
    }

    @Transactional
    public void delete(Long id) {
        Products products = productsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No post. id=" + id));

        productsRepository.delete(products);
    }

    @Transactional(readOnly = true) // faster by using readOnly
    public List<ProductsListResponseDto> findAllDesc() {
        return productsRepository.findAllDesc().stream()
                .map(ProductsListResponseDto::new)
                .collect(Collectors.toList());
    }

    public ProductsResponseDto findById(Long id) {
        Products entity = productsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No post. id=" + id));

        return new ProductsResponseDto(entity);
    }
}
