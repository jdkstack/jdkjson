package org.jdkstack.jdkjson.core.reader.verson1;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.jdkstack.jdkjson.core.reader.value1.BaseValue;

/**
 * Json反序列化第1版,采用静态方法方式解析.
 *
 * <p>多个类实现业务逻辑.
 *
 * <p>ECMA json5(对RFC规范的扩展): https://spec.json5.org/.
 *
 * <p>RFC json: https://datatracker.ietf.org/doc/rfc8259/.
 *
 * <p>https://www.json.org/json-en.html/.
 *
 * @author admin
 */
public final class JsonReaderV1 extends BaseValue {

  private JsonReaderV1() {
    //
  }

  /**
   * 使用LRU算法优化反序列化.
   *
   * <p>json字符串list|map.
   *
   * @param sequence json字符.
   * @return Object Object.
   * @author admin
   */
  public static Object deserializeLru(final String sequence) {
    // 查询LRU缓存是否存在.
    Object obj = LRUV1.get(sequence);
    // 不存在.
    if (obj == null) {
      // 创建一个公用的位置对象.
      AtomicInteger ai = new AtomicInteger(0);
      // 解析json字符串,返回对象object.
      Object value = value(sequence, ai);
      // 放入LRU缓存中.
      LRUV1.put(sequence, value);
      // 赋值当前对象object.
      obj = value;
    }
    return obj;
  }

  /**
   * 使用LRU算法优化反序列化.
   *
   * <p>json字符串list|map.
   *
   * @param sequence json字符.
   * @return Object Object.
   * @author admin
   */
  public static Map<String, Object> deserialize2MapLru(final String sequence) {
    // 查询LRU缓存是否存在.
    Map<String, Object> obj = LRUV1_MAP.get(sequence);
    // 不存在.
    if (obj == null) {
      // 创建一个公用的位置对象.
      AtomicInteger ai = new AtomicInteger(0);
      // 解析json字符串,返回对象object.
      Map<String, Object> value =
          (Map) JSON_DESERIALISATION.getValue('{').deserialisation(sequence, ai);
      // 放入LRU缓存中.
      LRUV1_MAP.put(sequence, value);
      // 赋值当前对象object.
      obj = value;
    }
    return obj;
  }

  /**
   * 使用LRU算法优化反序列化.
   *
   * <p>json字符串list|map.
   *
   * @param sequence json字符.
   * @return Object Object.
   * @author admin
   */
  public static List<Object> deserialize2ListLru(final String sequence) {
    // 查询LRU缓存是否存在.
    List<Object> obj = LRUV1_LIST.get(sequence);
    // 不存在.
    if (obj == null) {
      // 创建一个公用的位置对象.
      AtomicInteger ai = new AtomicInteger(0);
      // 解析json字符串,返回对象object.
      List<Object> value = (List) JSON_DESERIALISATION.getValue('[').deserialisation(sequence, ai);
      // 放入LRU缓存中.
      LRUV1_LIST.put(sequence, value);
      // 赋值当前对象object.
      obj = value;
    }
    return obj;
  }

  /**
   * 反序列化.
   *
   * <p>json字符串list|map.
   *
   * @param sequence json字符.
   * @return Object Object.
   * @author admin
   */
  public static Object deserialize(final String sequence) {
    // 创建一个公用的位置对象.
    AtomicInteger ai = new AtomicInteger(0);
    // 返回匹配的对象.
    return value(sequence, ai);
  }

  /**
   * 反序列化.
   *
   * <p>json字符串list.
   *
   * @param sequence json字符.
   * @return Object Object.
   * @author admin
   */
  public static Object deserialize2List(final String sequence) {
    // 创建一个公用的位置对象.
    AtomicInteger ai = new AtomicInteger(0);
    // 返回数组的表示List.
    return JSON_DESERIALISATION.getValue('[').deserialisation(sequence, ai);
  }

  /**
   * 反序列化.
   *
   * <p>json字符串map.
   *
   * @param sequence json字符.
   * @return Object Object.
   * @author admin
   */
  public static Object deserialize2Map(final String sequence) {
    // 创建一个公用的位置对象.
    AtomicInteger ai = new AtomicInteger(0);
    // 返回对象的表示Map.
    return JSON_DESERIALISATION.getValue('{').deserialisation(sequence, ai);
  }
}
