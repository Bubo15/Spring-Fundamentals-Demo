package com.shop.dealershop.anotations.impl;

import com.shop.dealershop.anotations.NotValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsValidValue implements ConstraintValidator<NotValue, String> {

    private String value;

    @Override
    public void initialize(NotValue annotation) {
        this.value = annotation.wrongValue();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !s.equals(this.value);
    }
}
