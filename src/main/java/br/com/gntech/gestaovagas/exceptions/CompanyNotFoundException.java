package br.com.gntech.gestaovagas.exceptions;

public class CompanyNotFoundException extends RuntimeException {

  public CompanyNotFoundException(String message) {
    super(message);
  }

  public CompanyNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public CompanyNotFoundException(Throwable cause) {
    super(cause);
  }
}
