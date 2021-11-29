package com.company.service;

import com.company.dto.ProductImageDTO;
import com.company.dto.converter.ProductImageDTOConverter;
import com.company.model.Product;
import com.company.model.ProductImage;
import com.company.repository.ProductImageRepository;
import com.company.util.FileUploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductImageService {

    private String UPLOAD = "src\\main\\productimg";

    private final ProductImageRepository productImageRepository;
    private final FileUploadUtil fileUploadUtil;
    private final ProductImageDTOConverter productImageDTOConverter;
    private final ProductService productService;

    public ProductImageService(
            ProductImageRepository productImageRepository, FileUploadUtil fileUploadUtil,
            ProductImageDTOConverter productImageDTOConverter, ProductService productService) {
        this.productImageRepository = productImageRepository;
        this.fileUploadUtil = fileUploadUtil;
        this.productImageDTOConverter = productImageDTOConverter;
        this.productService = productService;
    }

    public ProductImageDTO uploadProductImage(Long id, MultipartFile multipartFile) {

        Product product = productService.findProductById(id);

        ProductImage productImage = new ProductImage();
        productImage.setImageUrl(multipartFile.getOriginalFilename());
        productImage.setProductId(product);
        fileUploadUtil.saveFile(UPLOAD, multipartFile);
        return productImageDTOConverter.convert(productImageRepository.save(productImage));
    }

    public List<ProductImageDTO> uploadProductImages(Long id, MultipartFile[] multipartFiles) {
        Product product = productService.findProductById(id);

        List<ProductImage> productImageList = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            fileUploadUtil.saveFile(UPLOAD, multipartFile);
            ProductImage productImage = new ProductImage(multipartFile.getOriginalFilename(), product);
            productImageList.add(productImage);
        }

        return productImageDTOConverter.convert(productImageRepository.saveAll(productImageList));
    }

}
