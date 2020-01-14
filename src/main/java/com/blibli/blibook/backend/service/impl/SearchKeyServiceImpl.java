package com.blibli.blibook.backend.service.impl;

import com.blibli.blibook.backend.model.entity.Product;
import com.blibli.blibook.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SearchKeyServiceImpl {

    @Autowired
    ProductRepository productRepository;

    public List<Product> findProduct(String searchKey) {
        List<Product> products = productRepository.findByProductNameContainingAndProductStatus_ProductStatusName(
                searchKey, "AVAILABLE");
        if (products.isEmpty()) {
            Matcher matcher = Pattern.compile("\\w+").matcher(searchKey);
            StringBuilder upperSearchKey = new StringBuilder();
            while (matcher.find()) {
                upperSearchKey.append(capitalize(matcher.group()));
                if (!matcher.hitEnd()) {
                    upperSearchKey.append(" ");
                }
            }
            products = productRepository.findByProductNameContainingAndProductStatus_ProductStatusName(
                    upperSearchKey.toString(), "AVAILABLE");
            if(products.isEmpty()){
                Matcher matcher2 = Pattern.compile("\\w+").matcher(searchKey);
                StringBuilder lowerSearchKey = new StringBuilder();
                while (matcher2.find()){
                    lowerSearchKey.append(matcher2.group().toLowerCase());
                    if(!matcher2.hitEnd()){
                        lowerSearchKey.append(" ");
                    }
                }
                products = productRepository.findByProductNameContainingAndProductStatus_ProductStatusName(
                        lowerSearchKey.toString(), "AVAILABLE");
                return products;
            } else {
                return products;
            }
        } else {
            return products;
        }
    }

    private static String capitalize(String str){
        if(str == null || str.isEmpty()){
            return str;
        }
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }
}
