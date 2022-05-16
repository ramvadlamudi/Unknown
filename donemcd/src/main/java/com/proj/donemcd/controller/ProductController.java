package com.proj.donemcd.controller;

import com.proj.donemcd.dto.ProductDto;
import com.proj.donemcd.dto.Response;
import com.proj.donemcd.dto.StudentDto;
import com.proj.donemcd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProductController {
   /* @Autowired(required=true)
    private ProductService productService;

    @PostMapping("/createProduct")
    public ResponseEntity<?> createProductDetails(@RequestBody ProductDto productDto){

        System.out.println("enter createProductDetails "+productDto);
        productService.saveProductService(productDto);
        return ResponseEntity.ok(new Response(0,"created"));
    }*/

   /* @GetMapping("/getProduct/{id}")
    public ResponseEntity<?> getProductDetailsById(@PathVariable int id ) {

        System.out.println("enter getProductDetailsById "+id );
        Optional<ProductDto> productDtoOptional = productService.getProductDetailsById(id);

        return new ResponseEntity<>(productDtoOptional.get(), HttpStatus.OK);
    }*/
}
