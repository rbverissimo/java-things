package br.com.coltran.farmacinhapp.security.validators.abstractions;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

@NotBlank(message = "A senha não pode estar em branco")
@Size(min = 6, message = "A senha deve conter ao menos 6 caractéres")
@Pattern(regexp = ".*[a-zA-Z].*", message = "A senha deve conter ao menos uma letra")
@Pattern(regexp = ".*\\d.*", message = "A senha deve conter ao menos um dígito numérico")
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {})
public @interface ValidPassword {

    String message() default "O formato da senha é inválido.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
