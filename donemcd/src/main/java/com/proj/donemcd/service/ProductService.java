package com.proj.donemcd.service;

import com.proj.donemcd.dto.ProductDto;
import com.proj.donemcd.dto.StudentDto;

import java.util.Optional;

public interface ProductService {

    void saveProductService(ProductDto productDto);
    Optional<ProductDto> getProductDetailsById(int id);

}
