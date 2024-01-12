package com.productservice.productservice.controllers;


import com.productservice.productservice.dtos.FakeStoreProductDto;
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
    public ResponseEntity<FakeStoreProductDto[]> getAllProdcts(){

        FakeStoreProductDto[] products =  productService.getAllProducts();
        return  new ResponseEntity<>(products, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") long id) {
        Product p =  productService.getSingleProduct(id);
        return  new ResponseEntity<>(p,HttpStatus.OK);
    }

    @PostMapping("")
    public Product addNewProduct(@RequestBody FakeStoreProductDto productDto){
        return productService.addNewProduct(productDto);
    }


    @PatchMapping("")
    public Product updateProduct(@RequestBody FakeStoreProductDto productDto){
        System.out.println(productDto.getId());
        return productService.updateProduct(productDto);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(
            @PathVariable("id") Long id,
            @RequestBody FakeStoreProductDto productDto){
        Product p = convertFakeStoreProductToProduct(productDto);
        return productService.replaceProduct(id,p);
    }

    private Product convertFakeStoreProductToProduct(FakeStoreProductDto fakeStoreProduct){

        Product p = new Product();
        p.setId(fakeStoreProduct.getId());
        p.setDescription(fakeStoreProduct.getDescription());
        p.setTitle(fakeStoreProduct.getTitle());
        p.setPrice(fakeStoreProduct.getPrice());
        Category c = new Category();
        c.setName(fakeStoreProduct.getCategory());
        p.setCategory(c);
        p.setImageUrl(fakeStoreProduct.getImage());
        return p;
    }



}
