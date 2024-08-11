package com.ltp.gradesubmission.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotBlankValidator implements ConstraintValidator<NotBlank, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {

    return value != null;
  }
}
