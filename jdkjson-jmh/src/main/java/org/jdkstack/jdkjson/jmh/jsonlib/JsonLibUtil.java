package org.jdkstack.jdkjson.jmh.jsonlib;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * .
 *
 * <p>.
 *
 * @author admin
 */
public final class JsonLibUtil {

  private JsonLibUtil() {
    //
  }

  public static String bean2JsonMap(Object obj) {
    JSONObject jsonObject = JSONObject.fromObject(obj);
    return jsonObject.toString();
  }

  public static String bean2JsonList(Object obj) {
    JSONArray jsonObject = JSONArray.fromObject(obj);
    return jsonObject.toString();
  }

  public static JSONArray json2BeanList(String jsonStr) {
    return JSONArray.fromObject(jsonStr);
  }

  public static JSONObject json2BeanMap(String jsonStr) {
    return JSONObject.fromObject(jsonStr);
  }
}
