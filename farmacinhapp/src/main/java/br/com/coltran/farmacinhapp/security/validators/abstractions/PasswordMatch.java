package br.com.coltran.farmacinhapp.security.validators.abstractions;

import br.com.coltran.farmacinhapp.security.validators.PasswordMatchValidador;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchValidador.class)
public @interface PasswordMatch {

    String message() default "As senhas não conferem";
    Class<?> [] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
