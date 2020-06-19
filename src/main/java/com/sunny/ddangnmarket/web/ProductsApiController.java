package com.sunny.ddangnmarket.web;

import com.sunny.ddangnmarket.domain.products.Status;
import com.sunny.ddangnmarket.security.CurrentUser;
import com.sunny.ddangnmarket.security.UserPrincipal;
import com.sunny.ddangnmarket.service.S3Service;
import com.sunny.ddangnmarket.service.comments.CommentsService;
import com.sunny.ddangnmarket.service.products.ProductsService;
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
public class ProductsApiController {
    private final S3Service s3Service;
    private final ProductsService productsService;
    private final CommentsService commentsService;

    @PostMapping(value = "/api/v1/products", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasRole('USER')")
    public Long save(@RequestPart("data") ProductsSaveRequestDto requestDto, @RequestPart("image") MultipartFile image, @CurrentUser UserPrincipal userPrincipal) throws IOException {
        // TODO: image file null check

        System.out.println("requestDto: " + requestDto);
        System.out.println("requestDto.title: " + requestDto.getTitle());
        System.out.println("image.getName: " + image.getOriginalFilename());
        System.out.println("image.getSize: " + image.getSize());

        if (image.getSize() > 5000000) {
            throw new IllegalStateException("Basic Error");
        }

        String newName = getNewImageName(image.getOriginalFilename());
        MultipartFile imageToUpload = getNewFile(newName, image);

        String imagePath = s3Service.upload(imageToUpload);
        System.out.println("s3 upload success, imagePath: " + imagePath);
        requestDto.setImageFilePath(imagePath);

        // TODO: set UserId

        // TODO: set region from user info

        return productsService.save(requestDto, userPrincipal.getId());
    }

    @PutMapping("/api/v1/products/{id}")
    @PreAuthorize("hasRole('USER')")
    public Long update(@PathVariable Long id, @RequestBody ProductsUpdateRequestDto requestDto, @RequestParam("status") String statusQeuryString) {

        System.out.println("product update statusQeuryString: " + statusQeuryString);

        if (statusQeuryString == null || "".equals(statusQeuryString)) {
            return productsService.update(id, requestDto);
        }

        if (statusQeuryString.equals(Status.SELLING.getQueryString()) || statusQeuryString.equals(Status.SOLD_OUT.getQueryString())) {
            return productsService.updateStatus(id, statusQeuryString);
        }

        return Long.valueOf(0); // temp
    }

    @GetMapping("/api/v1/products/{id}")
    public ProductsResponseDto findById(@PathVariable String id) {
        Long idLongValue = Long.valueOf(id);

        return productsService.findById(idLongValue);
    }

    @GetMapping("/api/v1/products")
    public List<ProductsListResponseDto> getList() {
        return productsService.findAllDesc();
    }

    @DeleteMapping("/api/v1/products/{id}")
    @PreAuthorize("hasRole('USER')")
    public Long delete(@PathVariable Long id) throws IOException {

        commentsService.deleteAllByProductId(id);

        ProductsResponseDto product = productsService.findById(id);
        s3Service.delete(product.getImageFilePath());

        productsService.delete(id);

        return id;
    }

    private String getNewImageName(String originalName) {
        int dotIndex = originalName.lastIndexOf(".");
        String ext = originalName.substring(dotIndex);

        SimpleDateFormat postFix = new SimpleDateFormat("yyyyMMdd-HHmmssSSS");
        int rand = (int) (Math.random() * 1000);

        return originalName.substring(0, dotIndex) + postFix.format(System.currentTimeMillis()) + "_" + rand + ext;
    }

    private MultipartFile getNewFile(String fileName, MultipartFile currentFile) {
        return new MultipartFile() {
            @Override
            public String getName() {
                return currentFile.getName();
            }

            @Override
            public String getOriginalFilename() {
                return fileName;
            }

            @Override
            public String getContentType() {
                return currentFile.getContentType();
            }

            @Override
            public boolean isEmpty() {
                return currentFile.isEmpty();
            }

            @Override
            public long getSize() {
                return currentFile.getSize();
            }

            @Override
            public byte[] getBytes() throws IOException {
                return currentFile.getBytes();
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return currentFile.getInputStream();
            }

            @Override
            public void transferTo(File file) throws IOException, IllegalStateException {

            }
        };
    }
}
