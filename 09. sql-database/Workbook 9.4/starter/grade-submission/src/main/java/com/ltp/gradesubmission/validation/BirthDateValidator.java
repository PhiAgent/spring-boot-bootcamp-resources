package com.ltp.gradesubmission.validation;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BirthDateValidator implements ConstraintValidator<BirthDate, LocalDate> {

  private LocalDate OLDEST_ALLOWED_DATE = LocalDate.of(1900, 01, 01);

  @Override
  public boolean isValid(LocalDate date, ConstraintValidatorContext context) {

    LocalDate currentDate = LocalDate.now();

    return currentDate.isAfter(date) && date.isAfter(OLDEST_ALLOWED_DATE);
  }
}
