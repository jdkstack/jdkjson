package org.jdkstack.jdkjson.core;

import org.jdkstack.jdkjson.core.cache.LruV1;

/**
 * Json反序列化第2版.
 *
 * <p>采用静态方法方式解析(需要json校验处理).
 *
 * <p>json5: https://spec.json5.org/.
 *
 * @author admin
 */
public class JsonReaderV2 extends AbstractJsonReader {
  /** LRU缓存类. */
  protected static final LruV1<String, Object> LRUV1 = new LruV1<>(100);
  /** 换行的第一个字符的位置,默认值0. */
  private int position;
  /** 当前行号,当前处理第几行,默认值第一行. */
  private int line = 1;

  /**
   * 返回一个对象.
   *
   * @param sequenceParam json字符串序列.
   * @author admin
   */
  public JsonReaderV2(final String sequenceParam) {
    super(sequenceParam);
  }

  /**
   * 使用LRU算法优化反序列化.
   *
   * <p>json字符串list|map.
   *
   * @return Object Object.
   * @author admin
   */
  public Object deserializeLru() {
    // 查询LRU缓存是否存在.
    Object obj = LRUV1.get(sequence);
    // 不存在.
    if (obj == null) {
      // 解析json字符串,返回对象object.
      Object value = value();
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
   * <p>json字符串list.
   *
   * @return Object Object.
   * @author admin
   */
  public Object deserialize2ListLru() {
    // 查询LRU缓存是否存在.
    Object obj = LRUV1.get(sequence);
    // 不存在.
    if (obj == null) {
      // 解析json字符串,返回数组List.
      Object value = array();
      // 放入LRU缓存中.
      LRUV1.put(sequence, value);
      // 赋值当前数组List.
      obj = value;
    }
    return obj;
  }

  /**
   * 使用LRU算法优化反序列化.
   *
   * <p>json字符串map.
   *
   * @return Object Object.
   * @author admin
   */
  public Object deserialize2MapLru() {
    // 查询LRU缓存是否存在.
    Object obj = LRUV1.get(sequence);
    // 不存在.
    if (obj == null) {
      // 解析json字符串,返回对象Map.
      Object value = object();
      // 放入LRU缓存中.
      LRUV1.put(sequence, value);
      // 赋值当前对象Map.
      obj = value;
    }
    return obj;
  }

  /**
   * 反序列化.
   *
   * <p>json字符串list|map.
   *
   * @return Object Object.
   * @author admin
   */
  public Object deserialize() {
    return value();
  }

  /**
   * 反序列化.
   *
   * <p>json字符串list.
   *
   * @return Object Object.
   * @author admin
   */
  public Object deserialize2List() {
    return array();
  }

  /**
   * 反序列化.
   *
   * <p>json字符串map.
   *
   * @return Object Object.
   * @author admin
   */
  public Object deserialize2Map() {
    return object();
  }

  /**
   * 处理错误.
   *
   * <p>json字符校验,异常处理.
   *
   * @author admin
   */
  @Override
  public void error() {
    // 系统换行符.
    final String lineSeparator = System.lineSeparator();
    // 异常行-异常字符的长度.
    int len = index - position;
    final StringBuilder sb = new StringBuilder(Ascii.ASCII_64);
    sb.append("Parse error at row: ").append(line).append(", ").append("column: ").append(len);
    sb.append(lineSeparator);
    // 从当前行的头-异常位置.
    for (int i = position; i < len; i++) {
      sb.append(sequence.charAt(i));
    }
    sb.append(lineSeparator);
    // 重复打印-.
    sb.append("-".repeat(Math.max(0, len)));
    sb.append("⇡").append(lineSeparator);
  }

  /**
   * 需要跳过的字符.
   *
   * <p>json字符串map.
   *
   * @author admin
   */
  @Override
  public void skip() {
    // 循环退出的标识.
    boolean flag = true;
    while (index < length && flag) {
      // 获取当前字符.
      final char c = sequence.charAt(index);
      // 处理每一个字符.
      switch (c) {
          // 如果字符是换行.
        case '\n':
          // 当前行号+1.
          line++;
          // 只要换行,记录当前的位置.
          position = index;
          // 当前位置+1.
          index++;
          break;
        case '\t':
        case '\r':
        case ' ':
          index++;
          break;
        default:
          // 遇到其他字符,停止循环.
          flag = false;
          break;
      }
    }
  }
}
