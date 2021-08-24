package org.jdkstack.jdkjson.core.reader.value1;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import org.jdkstack.jdkjson.core.common.AsciiV1;

public class CommonValue extends BaseValue {

  @Override
  public Object deserialisation(String sequence, AtomicInteger ai) {
    return number(sequence, ai);
  }

  /**
   * json 数字.
   *
   * <p>json数字.
   *
   * @param sequence json字符.
   * @param ai ai.
   * @return Number Number.
   * @author admin
   */
  public Number number(final String sequence, final AtomicInteger ai) {
    skip(sequence, ai);
    // json 字符串的长度.
    int length = sequence.length();
    // 数字是不是小数.
    boolean contains = true;
    // 开始位置.
    final int start = ai.get();
    // 循环处理每一个字符.
    while (ai.get() < length) {
      // 获取当前字符.
      final char c = sequence.charAt(ai.get());
      // 如果是,则退出循环.
      if (AsciiV1.ASCII_44 == c) {
        // 字符, 代表数字结束, 停止循环.
        break;
      }
      // 如果是. 则是小数.
      if (AsciiV1.ASCII_46 == c) {
        // 字符. 代表小数.
        contains = false;
      }
      // 位置需要+1.
      ai.incrementAndGet();
    }
    // 字符串数字.
    String substring = sequence.substring(start, ai.get());
    Number number;
    // 采用最大范围对象.
    if (contains) {
      // 如果是整数.
      number = new BigInteger(substring);
    } else {
      // 如果是小数.
      number = new BigDecimal(substring);
    }
    return number;
  }
}
