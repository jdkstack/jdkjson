package org.jdkstack.jdkjson.core;

import java.util.List;
import java.util.Map;

public class JsonWriterV1 extends AbstractJsonParser {

  // 序列化map|list->json.
  public String serialize() {
    return null;
  }

  // 序列化map->json.
  public static String map2serialize(Map<String, Object> map) {
    StringBuilder buf = new StringBuilder(100);
    buf.append('{');
    String separator = "";
    for (Map.Entry<String, Object> entry : map.entrySet()) {
      buf.append(separator);
      String key = entry.getKey();
      Object value = entry.getValue();
      if (value instanceof String) {
        buf.append('"');
        buf.append(key);
        buf.append('"');
        buf.append(":");
        buf.append('"');
        buf.append(value);
        buf.append('"');
      } else {
        buf.append('"');
        buf.append(key);
        buf.append('"');
        buf.append(":");
        // 需要递归处理.
        buf.append(value);
      }
      separator = ",";
    }
    buf.append('}');
    return buf.toString();
  }

  // 序列化list->json.
  public static String list2serialize(List<Object> list) {
    StringBuilder buf = new StringBuilder(100);
    buf.append('[');
    String separator = "";
    for (int i = 0; i < list.size(); i++) {
      buf.append(separator);
      Object value = list.get(i);
      if (value instanceof String) {
        buf.append('"');
        buf.append(value);
        buf.append('"');
      } else {
        // 需要递归处理.
        buf.append(value);
      }
      separator = ",";
    }
    buf.append(']');
    return buf.toString();
  }
}
