package com.productservice.productservice.services;

import com.productservice.productservice.dtos.FakeStoreProductDto;
import com.productservice.productservice.models.Product;

public interface ProductService {

    public Product getSingleProduct(long id);

    public Product updateProduct( FakeStoreProductDto productDto);

    public Product replaceProduct(Long id, Product product);

    public Product addNewProduct(FakeStoreProductDto productDto);

    public FakeStoreProductDto[] getAllProducts();
}
