package org.jdkstack.jdkjson.jmh.hutooljson;

import cn.hutool.json.JSONUtil;

/**
 * .
 *
 * <p>.
 *
 * @author admin
 */
public class HutoolJsonUtil {

  private HutoolJsonUtil() {
    //
  }

  public static String bean2Json(Object obj) {
    return JSONUtil.toJsonStr(obj);
  }

  public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
    return JSONUtil.toBean(jsonStr, objClass, false);
  }
}
