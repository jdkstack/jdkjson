package org.jdkstack.jdkjson.jmh.fastjson;

import com.alibaba.fastjson.JSON;

/**
 * .
 *
 * <p>.
 *
 * @author admin
 */
public final class FastJsonUtil {

  private FastJsonUtil() {
    //
  }

  public static String bean2Json(Object obj) {
    return JSON.toJSONString(obj);
  }

  public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
    return JSON.parseObject(jsonStr, objClass);
  }
}
