package com.blibli.blibook.backend.controller;


import com.blibli.blibook.backend.ApiPath;
import com.blibli.blibook.backend.model.entity.ProductLibrary;
import com.blibli.blibook.backend.service.ProductLibraryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductLibraryController {

    @Autowired
    private ProductLibraryService productLibraryService;

    @GetMapping(ApiPath.PRODUCT_LIBRARY_BY_PRODUCT_LIBRARY_ID)
    public ProductLibrary findByProductLibraryId(@PathVariable Integer productLibraryId){
        return productLibraryService.findByProductLibraryId(productLibraryId);
    }

    @GetMapping(ApiPath.PRODUCT_LIBRARY_BY_USER_ID)
    public List<ProductLibrary> findByUser(@PathVariable Integer userId){
        return productLibraryService.findByUser(userId);
    }

    @PostMapping(ApiPath.PRODUCT_LIBRARY)
    public ProductLibrary save(@RequestBody ProductLibrary productLibrary){
        return productLibraryService.save(productLibrary);
    }
}
