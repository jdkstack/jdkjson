package org.jdkstack.jdkjson.jmh.jsoniter;

import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;

/**
 * .
 *
 * <p>.
 *
 * @author admin
 */
public final class JsoniterUtil {

  private JsoniterUtil() {
    //
  }

  public static String bean2Json(Object obj) {
    return JsonStream.serialize(obj);
  }

  public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
    return JsonIterator.deserialize(jsonStr, objClass);
  }
}
