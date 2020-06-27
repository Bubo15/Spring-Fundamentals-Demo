package com.shop.dealershop.anotations.impl;

import com.shop.dealershop.anotations.IsExistModel;
import com.shop.dealershop.service.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsModelAlreadyExist implements ConstraintValidator<IsExistModel, String> {

    private final ModelService modelService;

    @Autowired
    public IsModelAlreadyExist(ModelService modelService) {
        this.modelService = modelService;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return this.modelService.getModelByName(s) == null;
    }
}
