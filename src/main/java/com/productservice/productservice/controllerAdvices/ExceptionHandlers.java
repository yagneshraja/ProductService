package com.productservice.productservice.controllerAdvices;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import com.productservice.productservice.exception.ProductNotExistsException;
import com.productservice.productservice.dtos.ExceptionDto;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(ProductNotExistsException.class)
    public ResponseEntity<ExceptionDto> handleProductNotExistException(ProductNotExistsException exception){
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage(exception.getMessage());
        dto.setDetail("Check the product id, it probably does not exist");
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }





}
