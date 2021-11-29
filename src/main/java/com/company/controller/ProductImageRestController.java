package com.company.controller;

import com.company.dto.ProductImageDTO;
import com.company.service.ProductImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/v1/productimage")
public class ProductImageRestController {

    private final ProductImageService productImageService;

    public ProductImageRestController(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }

    @PostMapping("/add")
    public ResponseEntity<ProductImageDTO> uploadProductImage(
            @RequestPart(value = "id", required = false) Long id,
            @RequestPart(value = "files", required = false) MultipartFile multipartFile) {

        return ResponseEntity.ok(productImageService.uploadProductImage(id, multipartFile));
    }

    @PostMapping("/addimages")
    public ResponseEntity<List<ProductImageDTO>> uploadProductImages(
            @RequestPart(value = "id", required = false) Long id,
            @RequestPart(value = "files", required = false) MultipartFile[] multipartFiles) {

        return ResponseEntity.ok(productImageService.uploadProductImages(id, multipartFiles));
    }

    @GetMapping("/getimage/{id}")
    public void getProductImage() {

    }

    @GetMapping("/getimages")
    public void getAllProductImage() {

    }

    @PutMapping("/updateimage")
    public void updateProductImage() {

    }

    @DeleteMapping("deleteimage")
    public void deleteProductImage() {

    }

    @DeleteMapping("deleteimages")
    public void deleteProductImages() {

    }
}
