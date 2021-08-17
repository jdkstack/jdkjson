package org.jdkstack.jdkjson.core.writer.version2;

import java.util.List;
import java.util.Map;
import org.jdkstack.jdkjson.api.writer.value.Value;
import org.jdkstack.jdkjson.core.writer.value.JsonSerialisation;

/**
 * .
 *
 * <p>.
 *
 * @author admin
 */
public class JsonWriterV2 {

  private static final JsonSerialisation JSON_SERIALISATION = new JsonSerialisation();

  /**
   * 序列化.
   *
   * <p>map.
   *
   * @return String String.
   * @author admin
   */
  public static String serialize(final Object value) {
    StringBuilder sb = new StringBuilder();
    value(value, sb);
    return sb.toString();
  }

  /**
   * 序列化.
   *
   * <p>map.
   *
   * @return String String.
   * @author admin
   */
  public static String map2serialize(final Map<String, Object> value) {
    StringBuilder sb = new StringBuilder();
    object(value, sb);
    return sb.toString();
  }

  /**
   * 序列化.
   *
   * <p>map.
   *
   * @return String String.
   * @author admin
   */
  public static String list2serialize(final List<Object> value) {
    StringBuilder sb = new StringBuilder();
    array(value, sb);
    return sb.toString();
  }

  /**
   * json所有value值.
   *
   * <p>json字符串map.
   *
   * @author admin
   */
  public static void value(final Object obj, final StringBuilder sb) {
    // json value值很可能是null的,比如数组的元素或者对象的value.
    if (obj == null) {
      // 如果是空,直接拼接即可.
      sb.append(obj);
    } else {
      // 获取当前值的class运行时类型,采用此方式的原因是有很大的性能提升.
      Class<?> clz = obj.getClass();
      // 从缓存中获取具体执行类.
      Value value = JSON_SERIALISATION.getValue(clz);
      // 执行具体json value值的业务逻辑.
      value.serialisation(obj, sb);
    }
  }

  /**
   * 处理字符串.
   *
   * <p>map.
   *
   * @author admin
   */
  public static void stringValue(final Object current, final StringBuilder sb) {
    sb.append("\"").append(current).append("\"");
  }

  /**
   * 处理对象.
   *
   * <p>map.
   *
   * @author admin
   */
  public static void object(final Map<?, ?> map, final StringBuilder sb) {
    //  创建一个map代表对象.
    sb.append("{");
    //
    boolean first = true;
    //
    for (Map.Entry<?, ?> entry : map.entrySet()) {
      //
      if (first) {
        first = false;
      } else {
        sb.append(",");
      }
      //
      // 对象中的key,必须是字符串.
      stringValue(entry.getKey(), sb);
      // key:value对之间必须是冒号.
      sb.append(":");
      // 对象中的value,可能是7种类型的某一种,此处是递归方式解析.
      // 将key和value添加到对象中.
      value(entry.getValue(), sb);
    }
    //
    sb.append("}");
  }

  /**
   * 处理数组.
   *
   * <p>map.
   *
   * @author admin
   */
  public static void array(final Iterable<Object> list, final StringBuilder sb) {
    // 创建一个list代表数组.
    sb.append("[");
    //
    boolean first = true;
    //
    for (Object obj : list) {
      // 将元素添加到数组中.
      if (first) {
        first = false;
      } else {
        sb.append(",");
      }
      // 数组的元素可能是7种值中的某一种,此处是递归方式解析.
      value(obj, sb);
      //
    }
    //
    sb.append("]");
  }
}
