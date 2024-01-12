package com.productservice.productservice.exception;

import com.productservice.productservice.models.Product;

public class ProductNotExistsException extends Exception {

    public ProductNotExistsException(String message){
        super(message);
    }

}
