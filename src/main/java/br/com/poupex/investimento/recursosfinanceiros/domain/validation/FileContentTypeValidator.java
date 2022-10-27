package br.com.poupex.investimento.recursosfinanceiros.domain.validation;

import java.util.Arrays;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {

  private String[] allowedContentTypes;

  @Override
  public void initialize(final FileContentType constraint) {
    this.allowedContentTypes = constraint.allowed().split(",");
  }

  @Override
  public boolean isValid(final MultipartFile multipartFile, final ConstraintValidatorContext context) {
    return multipartFile == null || Arrays.stream(this.allowedContentTypes).toList().contains(multipartFile.getContentType());
  }

}
