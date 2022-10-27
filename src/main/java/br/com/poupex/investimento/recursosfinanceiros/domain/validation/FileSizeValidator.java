package br.com.poupex.investimento.recursosfinanceiros.domain.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.val;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

  private DataSize maxSize;

  @Override
  public void initialize(FileSize constraintAnnotation) {
    this.maxSize = DataSize.parse(constraintAnnotation.max());
  }

  @Override
  public boolean isValid(final MultipartFile value, final ConstraintValidatorContext context) {
    return value == null || value.getSize() <= maxSize.toBytes();
  }

}
