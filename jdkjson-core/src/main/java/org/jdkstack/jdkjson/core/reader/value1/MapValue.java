package org.jdkstack.jdkjson.core.reader.value1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MapValue extends BaseValue {

  @Override
  public Object deserialisation(String sequence, AtomicInteger ai) {
    return object(sequence, ai);
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
  public Map<String, Object> object(final String sequence, final AtomicInteger ai) {
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
      skip(sequence, ai);
      // 获取当前位置的字符.
      final char c = sequence.charAt(ai.get());
      // 处理每一个字符.
      switch (c) {
          // 字符串开始.
        case '"':
          // 对象中的key.
          final String key = stringValue(sequence, ai);
          // : .
          colon(sequence, ai);
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
}
