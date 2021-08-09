package org.jdkstack.jdkjson.core;

/**
 * .
 *
 * <p>.
 *
 * @author admin
 */
public class JsonRuntimeException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public JsonRuntimeException(String message) {
    super(message);
  }

  public JsonRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }

  public JsonRuntimeException(Throwable cause) {
    super(cause);
  }

  protected JsonRuntimeException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
