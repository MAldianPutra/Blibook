package com.blibli.blibook.backend.service.impl;

import com.blibli.blibook.backend.model.entity.Product;
import com.blibli.blibook.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileUploadServiceImpl {

    @Autowired
    private ProductService productService;

    private String projectDir = "D:/Project/blibook.backend/src/main/resources/";
    private String uploadDir = "uploads/";

    public Product uploadProductPhoto(@RequestParam Integer productId,
                                      @RequestParam MultipartFile multipartFile) throws IOException {
        String photoLink = projectDir + uploadDir + "productPhoto/" + productId + ".jpg";
        File file = new File(photoLink);
        if(!file.exists()){
            file.mkdirs();
        } else {
            file.delete();
        }
        multipartFile.transferTo(file);
        Product updateProduct = productService.findProductById(productId);
        updateProduct.setProductPhotoLink(photoLink);
        return productService.save(updateProduct);
    }

    public Product uploadProductItem(@RequestParam Integer productId,
                                     @RequestParam MultipartFile multipartFile) throws IOException {
        String itemLink = projectDir + uploadDir + "productItem/" + productId + ".pdf";
        File file = new File(itemLink);
        if(!file.exists()){
            file.mkdirs();
        } else {
            file.delete();
        }
        multipartFile.transferTo(file);
        Product updateProduct = productService.findProductById(productId);
        updateProduct.setProductItemLink(itemLink);
        productService.save(updateProduct);
        return updateProduct;
    }

}
