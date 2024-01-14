package com.productservice.productservice.controllers;


import com.productservice.productservice.dtos.FakeStoreProductDto;
import com.productservice.productservice.dtos.SelfProductDto;
import com.productservice.productservice.exception.ProductNotExistsException;
import com.productservice.productservice.models.Category;
import com.productservice.productservice.models.Product;
import com.productservice.productservice.services.FakeStoreProductService;
import com.productservice.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private RestTemplate restTemplate;

    private ProductService productService;
    @Autowired
    public ProductController(RestTemplate restTemplate,
                             @Qualifier("SelfProductService") ProductService productService) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }


    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() throws ProductNotExistsException{

        ResponseEntity<List<Product>> products = new ResponseEntity<>(
                productService.getAllProducts(),
                HttpStatus.OK);
        return  products;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id) throws ProductNotExistsException {
        return new ResponseEntity<>(productService.getSingleProduct(id),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable("id") Long id,
            @RequestBody Product product){
        return new ResponseEntity<>(productService.updateProduct(id,product),HttpStatus.OK);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Product> replaceProduct (
            @PathVariable("id") Long id,
            @RequestBody SelfProductDto productDto)throws ProductNotExistsException{
        Product product = convertSelfProductDtotoProduct(productDto);
        return new ResponseEntity<>(productService.replaceProduct(id,product) , HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Product> addNewProduct(@RequestBody SelfProductDto selfProduct){
        Product product = convertSelfProductDtotoProduct(selfProduct);
        return new ResponseEntity<>(productService.addNewProduct(product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") Long id){
        return  new ResponseEntity<>(productService.deleteProduct(id) ,HttpStatus.OK);
    }

    private Product convertSelfProductDtotoProduct(SelfProductDto selfProduct){
        Product p = new Product();
        p.setDescription(selfProduct.getDescription());
        p.setTitle(selfProduct.getTitle());
        p.setPrice(selfProduct.getPrice());
        Category c = new Category();
        c.setName(selfProduct.getCategory());
        p.setCategory(c);
        p.setImageUrl(selfProduct.getImage());
        return p;

    }


}
