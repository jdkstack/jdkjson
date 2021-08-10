package org.jdkstack.jdkjson.core.reader.verson1;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import org.jdkstack.jdkjson.core.common.Ascii;
import org.jdkstack.jdkjson.core.exception.JsonRuntimeException;

/**
 * .
 *
 * <p>.
 *
 * @author admin
 */
public final class DefaultJsonReaderV1 {

  private DefaultJsonReaderV1() {
    //
  }

  /**
   * 需要跳过的字符.
   *
   * <p>json字符串map.
   *
   * @param sequence json字符.
   * @param ai ai.
   * @author admin
   */
  public static void skip(final String sequence, final AtomicInteger ai) {
    // 字符串总长度,循环到-1.
    int length = sequence.length();
    // 循环退出的标识.
    boolean flag = true;
    // 循环处理每一个字符.
    while (ai.get() < length && flag) {
      // 获取当前字符.
      char c = sequence.charAt(ai.get());
      // 处理每一个字符.
      switch (c) {
          // 跳过字符.
        case '\t':
        case '\r':
        case '\n':
        case ' ':
          // 位置+1,代表跳过当前字符.
          ai.getAndIncrement();
          break;
        default:
          // 遇到其他字符,停止循环.
          flag = false;
          break;
      }
    }
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
  public static Number number(final String sequence, final AtomicInteger ai) {
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
      if (Ascii.ASCII_44 == c) {
        // 字符, 代表数字结束, 停止循环.
        break;
      }
      // 如果是. 则是小数.
      if (Ascii.ASCII_46 == c) {
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

  /**
   * 返回一个字符串.
   *
   * <p>json字符串(key或者字符串value).
   *
   * @param sequence json字符串序列.
   * @param ai json字符串序列当前需要处理的字符位置.
   * @return String String.
   * @author admin
   */
  public static String stringValue(final String sequence, final AtomicInteger ai) {
    // 字符串需要移动一位,从"下一位开始算起.
    ai.incrementAndGet();
    // 记录"后的字符开始位置.
    int start = ai.get();
    // json字符串的长度.
    int length = sequence.length();
    // json字符串的表示.
    String stringValue = null;
    // 退出循环的标识.
    boolean flag = true;
    // 处理每一个字符.
    while (ai.get() < length && flag) {
      // 获取当前位置的字符.
      final char c = sequence.charAt(ai.get());
      // 位置+1.
      ai.incrementAndGet();
      // 转义字符 \ .
      if (Ascii.ASCII_92 == c) {
        char next = sequence.charAt(ai.get());
        // 跳过转义字符.
        escape(next, ai);
        // " ,如果当前字符是双引号,则截取start和当前位置之间的字符.
      }
      if (Ascii.ASCII_34 == c) {
        // 返回这个区间的字符串.
        stringValue = sequence.substring(start, ai.get() - 1);
        // 将循环退出标识设置成false.
        flag = false;
      }
    }
    return stringValue;
  }

  /**
   * 返回一个字符串.
   *
   * <p>json字符串(key或者字符串value).
   *
   * @param c json字符.
   * @param ai json字符串序列当前需要处理的字符位置.
   * @author admin
   */
  private static void escape(final char c, final AtomicInteger ai) {
    switch (c) {
        // 对如下字符进行转义处理.
      case '"':
      case '\\':
      case '/':
      case 'b':
      case 'f':
      case 'n':
      case 'r':
      case 't':
      case 'u':
        // 位置+1,跳过这个字符,当作普通字符对待即可.
        ai.incrementAndGet();
        break;
        // 其他字符正常处理.
      default:
        break;
    }
  }

  /**
   * 校验key value 之间的冒号.
   *
   * <p>json字符串(key和value之间必须是冒号).
   *
   * @param sequence json字符.
   * @param ai json字符串序列当前需要处理的字符位置.
   * @author admin
   */
  public static void colon(final String sequence, final AtomicInteger ai) {
    // 先跳过字符.
    skip(sequence, ai);
    // 之后获取一个字符.
    final char c = sequence.charAt(ai.get());
    // 如果这个在字符不是:.
    if (c != Ascii.ASCII_58) {
      throw new JsonRuntimeException("json不合法.");
    }
    // 位置+1.
    ai.getAndIncrement();
  }
}
