package org.jdkstack.jdkjson.core.writer.version2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jdkstack.jdkjson.core.writer.value.JsonSerialisation;
import org.jdkstack.jdkjson.api.writer.value.Value;

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
      value.exe(obj, sb);
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

  public static void main(String[] args) {
    Map<String, Object> map = new HashMap<>();
    Map<String, Object> map1 = new HashMap<>();
    List list1 = new ArrayList();
    list1.add("123");
    list1.add(null);
    list1.add(false);
    Map<String, Object> map3 = new HashMap<>();
    map3.put("xxx", new ArrayList<>());
    list1.add(map3);
    List<String> list3 = new ArrayList<>();
    list3.add("222");
    list1.add(list3);
    map1.put("f", list1);
    map1.put("f1", null);
    map.put("f", map1);

    long start = System.currentTimeMillis();

    for (int i = 0; i < 10000000; i++) {
      // String s = JSONValue.toJSONString(map);
      String serialize = JsonWriterV2.map2serialize(map);
    }
    long end = System.currentTimeMillis();
    System.out.println(end - start);

    /*try {
      Thread.sleep(999999);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }*/
  }
}
