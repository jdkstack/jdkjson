package org.jdkstack.jdkjson.core.writer.version1;

import java.util.Map;
import org.jdkstack.jdkjson.core.common.Ascii;

/**
 * .
 *
 * <p>.
 *
 * @author admin
 */
public class JsonWriterV1 {

  /**
   * 序列化.
   *
   * <p>map.
   *
   * @param map map.
   * @return String String.
   * @author admin
   */
  public static String map2serialize(Map<String, Object> map) {
    StringBuilder buf = new StringBuilder(Ascii.ASCII_64);
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

  /**
   * 序列化.
   *
   * <p>list.
   *
   * @param list list 对象.
   * @return String String.
   * @author admin
   */
  public static String list2serialize(final Iterable<Object> list) {
    StringBuilder buf = new StringBuilder(Ascii.ASCII_64);
    buf.append('[');
    String separator = "";
    for (Object o : list) {
      buf.append(separator);
      if (o instanceof String) {
        buf.append('"');
        buf.append(o);
        buf.append('"');
      } else {
        // 需要递归处理.
        buf.append(o);
      }
      separator = ",";
    }
    buf.append(']');
    return buf.toString();
  }

  /**
   * 序列化.
   *
   * <p>list|map对象.
   *
   * @return String String.
   * @author admin
   */
  public String serialize() {
    return null;
  }
}
