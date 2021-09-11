package org.jdkstack.jdkjson.jmh;

import java.util.List;
import java.util.Map;
import org.jdkstack.jdkjson.api.writer.version2.JsonWriter;

/**
 * 每次序列化都新建一个对象.
 *
 * <p>单个类实现业务逻辑.
 *
 * @author admin
 */
public class JsonWriterV4 implements JsonWriter {

  /**
   * 序列化.
   *
   * <p>map.
   *
   * @param map map.
   * @return String String.
   * @author admin
   */
  public String map2serialize(Map<String, Object> map) {
    return object(map);
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
  public String list2serialize(final Iterable<Object> list) {
    return array(list);
  }

  /**
   * 序列化.
   *
   * <p>list|map对象.
   *
   * @return String String.
   * @author admin
   */
  public Object serialize(Object obj) {
    return value(obj);
  }

  /**
   * json所有value值.
   *
   * <p>json字符串map.
   *
   * @return Object Object.
   * @author admin
   */
  public Object value(Object obj) {
    //
    Object current = obj;
    if (current instanceof String) {
      //
      current = stringValue(current);
    } else if (current instanceof Map) {
      //
      current = object((Map) current);
    } else if (current instanceof List) {
      //
      current = array((List) current);
    } else {
      //
    }
    // 如果不是特殊的对象,直接返回普通5个值即可.
    return current;
  }

  public String stringValue(Object current) {
    return "\"" + current + "\"";
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
      String key = stringValue(entry.getKey());
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

  public String array(Iterable<Object> current) {
    // 创建一个list代表数组.
    StringBuilder sb = new StringBuilder();
    // 数组的开始.
    sb.append("[");
    boolean first = true;
    // 循环处理数组中的每一个元素.
    for (Object obj : current) {
      // 数组的元素可能是7种值中的某一种,此处是递归方式解析.
      Object value = value(obj);
      if (first) {
        // 第一个元素前面是""空字符串.
        first = false;
      } else {
        // 第一个元素开始,到最后一个元素之前,增加一个逗号.
        sb.append(",");
      }
      // 将json value放入数组中.
      sb.append(value);
    }
    // 数组的结束.
    sb.append("]");
    return sb.toString();
  }
}
