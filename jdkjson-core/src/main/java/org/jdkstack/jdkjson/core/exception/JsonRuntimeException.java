package org.jdkstack.jdkjson.core.exception;

/**
 * .
 *
 * <p>.
 *
 * @author admin
 */
public class JsonRuntimeException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public JsonRuntimeException(final String message) {
    super(message);
  }

  public JsonRuntimeException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public JsonRuntimeException(final Throwable cause) {
    super(cause);
  }

  protected JsonRuntimeException(
      final String message,
      Throwable cause,
      final boolean enableSuppression,
      final boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
