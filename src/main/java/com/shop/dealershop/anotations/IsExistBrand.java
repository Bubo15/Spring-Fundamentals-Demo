package com.shop.dealershop.anotations;

        import com.shop.dealershop.anotations.impl.IsBrandAlreadyExist;

        import javax.validation.Constraint;
        import javax.validation.Payload;
        import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint( validatedBy = IsBrandAlreadyExist.class)
@Documented
public @interface IsExistBrand {

    String message() default "{org.hibernate.validator.constraints.IsExistBrand.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
