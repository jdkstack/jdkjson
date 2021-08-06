package org.jdkstack.jdkjson.jmh.jsonlib;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonLibUtil {

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

  @SuppressWarnings("unchecked")
  public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
    return (T) JSONObject.toBean(JSONObject.fromObject(jsonStr), objClass);
  }
}
