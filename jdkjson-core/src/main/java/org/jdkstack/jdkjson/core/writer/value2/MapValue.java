package org.jdkstack.jdkjson.core.writer.value2;

import java.util.Map;

public class MapValue extends BaseValue {

  @Override
  public Object serialisation(Object obj) {
    return object((Map) obj);
  }

  public String object(Map<String, Object> current) {
    //  创建一个map代表对象.
    StringBuilder sb = new StringBuilder();
    // 对象的开始.
    sb.append("{");
    boolean first = true;
    // 循环处理对象中的每一个key:value对.
    for (Map.Entry<String, Object> entry : current.entrySet()) {
      if (first) {
        // 第一个key:value对前面是""空字符串.
        first = false;
      } else {
        //  第一个key:value开始,到最后一个key:value之前,增加一个逗号.
        sb.append(",");
      }
      // 对象中的key,必须是字符串.
      String key = "\"" + entry.getKey() + "\"";
      sb.append(key);
      // key:value之间必须是冒号.
      sb.append(":");
      Object value = value(entry.getValue());
      // 对象中的value,可能是7种类型的某一种,此处是递归方式解析.
      sb.append(value);
    }
    // 对象的结束.
    sb.append("}");
    return sb.toString();
  }
}
