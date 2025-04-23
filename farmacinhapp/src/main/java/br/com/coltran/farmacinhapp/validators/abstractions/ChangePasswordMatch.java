package br.com.coltran.farmacinhapp.validators.abstractions;

import br.com.coltran.farmacinhapp.validators.ChangePasswordMatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ChangePasswordMatchValidator.class)
public @interface ChangePasswordMatch {

    String message() default "As novas senhas informadas não conferem.";
    Class<?> [] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
