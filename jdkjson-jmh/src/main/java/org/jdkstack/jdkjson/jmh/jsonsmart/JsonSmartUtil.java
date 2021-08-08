package org.jdkstack.jdkjson.jmh.jsonsmart;

import net.minidev.json.JSONValue;

public class JsonSmartUtil {
  public static String bean2Json(Object obj) {
    return JSONValue.toJSONString(obj);
  }

  public static Object json2Bean(String jsonStr) {
    return JSONValue.parse(jsonStr);
  }
}
