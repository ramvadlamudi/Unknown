package com.proj.donemcd.service;


import com.proj.donemcd.dto.ProductDto;
import com.proj.donemcd.model.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ProdcutServiceImpl implements ProductService{

    /*@Autowired
    private ProdcutRepository prodcutRepository;*/

    @Override
    public void saveProductService(ProductDto productDto) {

        Product product = buildProduct(productDto);
        System.out.println("enter product "+product);
       // prodcutRepository.save(product);
    }

    @Override
    public Optional<ProductDto> getProductDetailsById(int id) {
       // Optional<Product> optionalproduct ; //prodcutRepository.findById(id);
       /* if(optionalproduct.isPresent()) {
            ProductDto productDto = convertProduct(optionalproduct.get());
            return Optional.of(productDto);
        }*/
        return Optional.empty();
    }

    private Product buildProduct(ProductDto productDto) {
        return Product.builder().id(productDto.getId()).prodcuctName(productDto.getProdcuctName())
                .productDescription(productDto.getProductDescription()).build();
    }

    private ProductDto convertProduct(Product product) {
        return ProductDto.builder().id(product.getId()).prodcuctName(product.getProdcuctName())
                .productDescription(product.getProductDescription())
                .build();

    }
}
