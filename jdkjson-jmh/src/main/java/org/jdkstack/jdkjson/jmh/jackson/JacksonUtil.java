package org.jdkstack.jdkjson.jmh.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import org.jdkstack.jdkjson.core.exception.JsonRuntimeException;

/**
 * .
 *
 * <p>.
 *
 * @author admin
 */
public final class JacksonUtil {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  private JacksonUtil() {
    //
  }

  public static String bean2Json(Object obj) {
    try {
      MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
      return MAPPER.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      throw new JsonRuntimeException("", e);
    }
  }

  public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
    try {
      return MAPPER.readValue(jsonStr, objClass);
    } catch (IOException e) {
      throw new JsonRuntimeException("", e);
    }
  }
}
