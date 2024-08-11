package com.ltp.gradesubmission.validation;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BirthDateValidator implements ConstraintValidator<BirthDate, LocalDate> {

  @Override
  public boolean isValid(LocalDate date, ConstraintValidatorContext context) {

    return LocalDate.now().isAfter(date) ;
  }
}
