package org.jdkstack.jdkjson.core.reader.value1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ListValue extends BaseValue {

  @Override
  public Object deserialisation(String sequence, AtomicInteger ai) {
    return array(sequence, ai);
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
  public List<Object> array(final String sequence, final AtomicInteger ai) {
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
      skip(sequence, ai);
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
