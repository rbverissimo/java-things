package br.com.coltran.farmacinhapp.validators.abstractions;

import br.com.coltran.farmacinhapp.validators.DtNasctoUsuarioValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DtNasctoUsuarioValidator.class)
public @interface DtNasctoUsuario {

    String message() default "A data de nascimento do usuário não pode estar no futuro e o mesmo deve ter pelo menos 6 anos de idade.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
