package br.com.coltran.farmacinhapp.validators;

import br.com.coltran.farmacinhapp.validators.abstractions.DtNasctoUsuario;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DtNasctoUsuarioValidator implements ConstraintValidator<DtNasctoUsuario, LocalDate> {


    @Override
    public void initialize(DtNasctoUsuario constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null) return true;
        LocalDate now = LocalDate.now();
        if(value.isAfter(now)) return false;
        LocalDate idadeMinima = now.minusYears(6);
        return value.isBefore(idadeMinima) || value.isEqual(idadeMinima);
    }
}
