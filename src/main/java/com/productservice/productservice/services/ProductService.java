package com.productservice.productservice.services;

import com.productservice.productservice.exception.*;

import com.productservice.productservice.dtos.FakeStoreProductDto;
import com.productservice.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long id) throws ProductNotExistsException;

    List<Product> getAllProducts() throws ProductNotExistsException;

    Product updateProduct(Long id, Product product);

    Product replaceProduct(Long id, Product product) throws ProductNotExistsException;

    Product addNewProduct(Product product);

    Boolean deleteProduct(Long id);
}
