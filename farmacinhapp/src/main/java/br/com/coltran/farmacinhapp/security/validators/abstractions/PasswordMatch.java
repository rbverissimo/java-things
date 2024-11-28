package br.com.coltran.farmacinhapp.security.validators.abstractions;

import br.com.coltran.farmacinhapp.security.validators.PasswordMatchValidador;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchValidador.class)
public @interface PasswordMatch {

    String message() default "As senhas não conferem";
    Class<?> [] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
