package br.com.coltran.farmacinhapp.security.validators;

import br.com.coltran.farmacinhapp.security.dto.UserRegDTO;
import br.com.coltran.farmacinhapp.security.validators.abstractions.PasswordMatch;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidador implements ConstraintValidator<PasswordMatch, UserRegDTO> {

    @Override
    public void initialize(PasswordMatch passwordMatch) {
    }

    @Override
    public boolean isValid(UserRegDTO userRegDTO, ConstraintValidatorContext constraintValidatorContext) {
        return userRegDTO.getPassword().equals(userRegDTO.getPasswordConfirm());
    }
}
