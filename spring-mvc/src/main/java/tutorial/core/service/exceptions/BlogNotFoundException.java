package tutorial.core.service.exceptions;

public class BlogNotFoundException extends RuntimeException {

  public BlogNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public BlogNotFoundException(String message) {
    super(message);
  }

  public BlogNotFoundException() {
  }
}
