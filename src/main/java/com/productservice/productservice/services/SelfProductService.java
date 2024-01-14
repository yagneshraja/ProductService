package com.productservice.productservice.services;

import com.productservice.productservice.dtos.FakeStoreProductDto;
import com.productservice.productservice.exception.ProductNotExistsException;
import com.productservice.productservice.models.Category;
import com.productservice.productservice.models.Product;
import com.productservice.productservice.repositories.CategoryRepository;
import com.productservice.productservice.repositories.ProductRepository;
import org.hibernate.boot.archive.scan.spi.ClassDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service("SelfProductService")
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;


    @Autowired
    public SelfProductService(ProductRepository productRepository,
                              CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Product getSingleProduct(Long id) throws ProductNotExistsException{
        Optional<Product> optionalProduct =  this.productRepository.findById(id);

        if(optionalProduct.isEmpty()){
            throw new ProductNotExistsException("No Product with id : "+id+" found");
        }

        Product product = optionalProduct.get();

        return product;
    }

    @Override
    public List<Product> getAllProducts() throws ProductNotExistsException {
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()){
            throw new ProductNotExistsException("No products found");
        }
        return products;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
        // Same as replace product but it replaces the original product
    }

    @Override
    public Product replaceProduct(Long id, Product product) throws ProductNotExistsException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new ProductNotExistsException("No Product with id : "+id+" found");
        }
        Product product1 = optionalProduct.get();
        if (product.getTitle() != null) {
            product1.setTitle(product.getTitle());
        }

        if (product.getDescription() != null) {
            product1.setDescription(product.getDescription());
        }
        if (product.getImageUrl() != null) {
            product1.setImageUrl(product.getImageUrl());
        }

        return productRepository.save(product1);
    }

    @Override
    public Product addNewProduct(Product product) {
        Optional<Category> optionalCategory = categoryRepository.findCategoryByName(product.getCategory().getName());

        if(optionalCategory.isEmpty()) {
            Category category = new Category();
            category.setName(product.getCategory().getName());
            categoryRepository.save(category);
            product.setCategory(category);
        }else{
            product.setCategory(optionalCategory.get());
        }

        return productRepository.save(product);
    }

    @Override
    public Boolean deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(!optionalProduct.isEmpty()){
            productRepository.delete(optionalProduct.get());
            return true;
        }
        else {
            return false;
        }

    }
}
