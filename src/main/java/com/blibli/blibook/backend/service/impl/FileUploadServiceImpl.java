package com.blibli.blibook.backend.service.impl;

import com.blibli.blibook.backend.model.entity.Product;
import com.blibli.blibook.backend.model.entity.User;
import com.blibli.blibook.backend.payload.UserPayload;
import com.blibli.blibook.backend.service.ProductService;
import com.blibli.blibook.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Service
public class FileUploadServiceImpl {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    private String projectDir = "D:/Project/blibook.backend/src/main/resources/";
    private String uploadDir = "uploads/";

    public UserPayload uploadUserPhoto(@RequestParam Integer userId,
                                       @RequestParam MultipartFile multipartFile) throws IOException {
        String photoLink = projectDir + uploadDir + "userPhoto/" + userId + ".jpg";
        File file = new File(photoLink);
        if(!file.exists()){
            file.mkdirs();
        } else {
            file.delete();
        }
        multipartFile.transferTo(file);
        User updateUser = userService.findFirstByUserId(userId);
        updateUser.setUserPhotoLink(photoLink);
        userService.save(updateUser);
        return new UserPayload(updateUser.getUserName(), updateUser.getUserEmail(), updateUser.getUserPhotoLink());
    }

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
        Product updateProduct = productService.findFirstByProductId(productId);
        updateProduct.setProductPhotoLink(photoLink);
        return productService.save(updateProduct);
    }

}
