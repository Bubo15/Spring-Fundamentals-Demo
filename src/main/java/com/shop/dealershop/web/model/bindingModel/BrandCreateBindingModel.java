package com.shop.dealershop.web.model.bindingModel;

import com.shop.dealershop.anotations.IsExistBrand;

import javax.validation.constraints.NotEmpty;

public class BrandCreateBindingModel {
    private String name;

    public BrandCreateBindingModel() {
    }

    @NotEmpty(message = "Brand can't be empty")
    @IsExistBrand(message = "Brand already exist")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
