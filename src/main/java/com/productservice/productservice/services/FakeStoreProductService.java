package com.productservice.productservice.services;

import com.productservice.productservice.dtos.FakeStoreProductDto;
import com.productservice.productservice.models.Category;
import com.productservice.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements  ProductService{

    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(long id) {

        FakeStoreProductDto fakeStoreProduct =
                restTemplate.getForObject(
                        "https://fakestoreapi.com/products/"+id,
                        FakeStoreProductDto.class);

        return convertFakeStoreProductToProduct(fakeStoreProduct);
    }


    @Override
    public Product updateProduct(FakeStoreProductDto productDto) {
        // if data is persistent then we will fetch the product using id and update
        // the contents of that product with fakeStore product Dto and return the product

        return convertFakeStoreProductToProduct(productDto);

    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImageUrl());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto,
                FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class,
                restTemplate.getMessageConverters());
        FakeStoreProductDto response =  restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        return convertFakeStoreProductToProduct(response);

    }
    @Override
    public Product addNewProduct(FakeStoreProductDto productDto) {
        return convertFakeStoreProductToProduct(productDto);
    }

    @Override
    public FakeStoreProductDto[] getAllProducts() {
        FakeStoreProductDto[] products = restTemplate.getForObject(
                "https://fakestoreapi.com/products",FakeStoreProductDto[].class);
        return products;
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
