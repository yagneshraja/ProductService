package com.productservice.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelfProductDto {
    private String title;
    private  String description;
    private double price;
    private String category;
    private String image;
}
