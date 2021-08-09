package org.jdkstack.jdkjson.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.jdkstack.jdkjson.core.cache.LruV1;

/**
 * Json反序列化第1版.
 *
 * <p>采用静态方法方式解析(需要json校验处理).
 *
 * <p>json5: https://spec.json5.org/.
 *
 * @author admin
 */
public final class JsonReaderV1 {
  /** LRU缓存类. */
  private static final LruV1<String, Object> LRUV1 = new LruV1<>(100);

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
    // 创建一个公用的位置对象.
    AtomicInteger ai = new AtomicInteger(0);
    // 查询LRU缓存是否存在.
    Object obj = LRUV1.get(sequence);
    // 不存在.
    if (obj == null) {
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
    return array(sequence, ai);
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
    return object(sequence, ai);
  }

  /**
   * json所有value值.
   *
   * <p>json字符串map.
   *
   * @param sequence json字符.
   * @param ai ai.
   * @return Object Object.
   * @author admin
   */
  public static Object value(final String sequence, final AtomicInteger ai) {
    // 获取当前字符.
    final char c = sequence.charAt(ai.get());
    // 返回对象表示.
    Object obj;
    // 处理每一个字符.
    switch (c) {
        // 代表对象开始.
      case '{':
        // 值是对象.
        obj = object(sequence, ai);
        break;
        // 代表数组开始.
      case '[':
        // 值是数组.
        obj = array(sequence, ai);
        break;
        // 代表字符串开始.
      case '"':
        // 值是字符串.
        obj = DefaultJsonReaderV1.stringValue(sequence, ai);
        break;
        // 代表 false开始.
      case 'F':
      case 'f':
        ai.getAndAdd(Ascii.ASCII_5);
        // 值是false.
        obj = false;
        break;
        // 代表 true开始.
      case 'T':
      case 't':
        // 值是true.
        ai.getAndAdd(Ascii.ASCII_4);
        obj = true;
        break;
        // 代表null开始.
      case 'N':
      case 'n':
        ai.getAndAdd(Ascii.ASCII_4);
        // 值是null.
        obj = null;
        break;
        // 代表数字开始.
      default:
        obj = DefaultJsonReaderV1.number(sequence, ai);
        break;
    }
    return obj;
  }

  /**
   * 返回一个对象.
   *
   * <p>json对象.
   *
   * @param sequence json字符串序列.
   * @param ai json字符串序列当前需要处理的字符位置.
   * @return Map Map.
   * @author admin
   */
  public static Map<String, Object> object(final String sequence, final AtomicInteger ai) {
    // 位置增加1.
    ai.getAndIncrement();
    // json字符串序列的长度.
    int length = sequence.length();
    // 创建一个json对象的表示.
    Map<String, Object> obj = new HashMap<>();
    // 循环退出的标识.
    boolean flag = true;
    // 循环处理字符串序列的每一个字符.
    while (ai.get() < length && flag) {
      // 跳过一些不想处理的字符,包括换行,空白符等.
      DefaultJsonReaderV1.skip(sequence, ai);
      // 获取当前位置的字符.
      final char c = sequence.charAt(ai.get());
      // 处理每一个字符.
      switch (c) {
          // 字符串开始.
        case '"':
          // 对象中的key.
          final String key = DefaultJsonReaderV1.stringValue(sequence, ai);
          // : .
          DefaultJsonReaderV1.colon(sequence, ai);
          // 对象中的value(可能是多种对象中的一种).
          final Object value = value(sequence, ai);
          // 添加.
          obj.put(key, value);
          break;
          // 代表存在下一个key value对象.
        case ',':
          ai.getAndIncrement();
          break;
          // 代表对象结束.
        case '}':
          ai.getAndIncrement();
          // 结束循环.
          flag = false;
          break;
          // 其他字符.
        default:
          break;
      }
    }
    return obj;
  }

  /**
   * 返回一个数组.
   *
   * <p>json数组.
   *
   * @param sequence json字符串序列.
   * @param ai json字符串序列当前需要处理的字符位置.
   * @return List List.
   * @author admin
   */
  public static List<Object> array(final String sequence, final AtomicInteger ai) {
    // 位置+1.
    ai.getAndIncrement();
    // json字符串长度.
    int length = sequence.length();
    // json数组的对象表示.
    List<Object> arr = new ArrayList<>();
    // 退出循环的标识.
    boolean flag = true;
    // 循环处理每一个字符.
    while (ai.get() < length && flag) {
      // 跳过不需要处理的字符.
      DefaultJsonReaderV1.skip(sequence, ai);
      // 获取当前位置的字符.
      final char c = sequence.charAt(ai.get());
      // 处理每一个字符.
      switch (c) {
          // 代表存在下一个数组元素.
        case ',':
          ai.getAndIncrement();
          break;
          // 数组结束.
        case ']':
          ai.getAndIncrement();
          flag = false;
          break;
          // 其他字符.
        default:
          Object value = value(sequence, ai);
          arr.add(value);
          break;
      }
    }
    return arr;
  }
}
