package com.productservice.productservice.services;

import com.productservice.productservice.dtos.FakeStoreProductDto;
import com.productservice.productservice.exception.ProductNotExistsException;
import com.productservice.productservice.models.Category;
import com.productservice.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements  ProductService{

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

    @Override
    public Product getSingleProduct(Long id) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product addNewProduct(Product product) {
        return null;
    }

    @Override
    public Boolean deleteProduct(Long id) {
        return false;
    }
}
