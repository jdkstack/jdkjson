package org.jdkstack.jdkjson.jmh.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;

public class JacksonUtil {

  private static ObjectMapper mapper = new ObjectMapper();

  private JacksonUtil() {
    //
  }

  public static String bean2Json(Object obj) {
    try {
      mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
      return mapper.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("", e);
    }
  }

  public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
    try {
      return mapper.readValue(jsonStr, objClass);
    } catch (IOException e) {
      throw new RuntimeException("", e);
    }
  }
}
