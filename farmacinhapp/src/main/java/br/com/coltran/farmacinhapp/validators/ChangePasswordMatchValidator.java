package br.com.coltran.farmacinhapp.validators;

import br.com.coltran.farmacinhapp.controllers.api.dto.ChangePassword;
import br.com.coltran.farmacinhapp.validators.abstractions.ChangePasswordMatch;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ChangePasswordMatchValidator implements ConstraintValidator<ChangePasswordMatch, ChangePassword> {

    @Override
    public void initialize(ChangePasswordMatch changePasswordMatch) {
    }

    @Override
    public boolean isValid(ChangePassword changePassword, ConstraintValidatorContext constraintValidatorContext) {
        return changePassword.getNovaSenha().equals(changePassword.getNovaSenhaConfirm());
    }
}
