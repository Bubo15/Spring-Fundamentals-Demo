package com.shop.dealershop.anotations.impl;

import com.shop.dealershop.anotations.IsExistModel;
import com.shop.dealershop.service.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsBrandAlreadyExist implements ConstraintValidator<IsExistModel, String> {

    private final BrandService brandService;

    @Autowired
    public IsBrandAlreadyExist(BrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return this.brandService.getBrandByName(s) == null;
    }
}
