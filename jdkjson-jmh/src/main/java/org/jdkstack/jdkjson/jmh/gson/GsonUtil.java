package org.jdkstack.jdkjson.jmh.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * .
 *
 * <p>.
 *
 * @author admin
 */
public final class GsonUtil {

  private static final Gson GSON = new GsonBuilder().create();

  private GsonUtil() {
    //
  }

  public static String bean2Json(Object obj) {
    return GSON.toJson(obj);
  }

  public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
    return GSON.fromJson(jsonStr, objClass);
  }

  public static String jsonFormatter(String uglyJsonStr) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonParser jp = new JsonParser();
    JsonElement je = jp.parse(uglyJsonStr);
    return gson.toJson(je);
  }
}
