package com.shop.dealershop.anotations;

import com.shop.dealershop.anotations.impl.IsModelAlreadyExist;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint( validatedBy = IsModelAlreadyExist.class)
@Documented
public @interface IsExistModel {

    String message() default "{org.hibernate.validator.constraints.IsExistModel.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
