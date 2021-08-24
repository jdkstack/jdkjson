package org.jdkstack.jdkjson.core.reader.verson2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jdkstack.jdkjson.api.reader.verson2.JsonReader;
import org.jdkstack.jdkjson.core.common.AsciiV1;

/**
 * .
 *
 * <p>.
 *
 * @author admin
 */
public abstract class AbstractJsonReaderV4 implements JsonReader {
  /** 待处理的json字符串. */
  protected final String sequence;
  /** 待处理的json字符串长度. */
  protected final int length;
  /** 正在处理的字符的位置,默认值0. */
  protected int index;

  protected AbstractJsonReaderV4(final String sequenceParam) {
    // json字符串序列.
    this.sequence = sequenceParam;
    // 字符串总长度,循环到-1.
    this.length = sequenceParam.length();
  }

  /**
   * json所有value值.
   *
   * <p>json字符串map.
   *
   * @return Object Object.
   * @author admin
   */
  public Object value() {
    // 获取当前字符.
    final char c = sequence.charAt(index);
    // 返回对象表示.
    Object obj;
    // 处理每一个字符.
    switch (c) {
        // { .
      case AsciiV1.ASCII_123:
        // 值是对象.
        obj = object();
        break;
        // [ .
      case AsciiV1.ASCII_91:
        // 值是数组.
        obj = array();
        break;
        // " .
      case AsciiV1.ASCII_34:
        // 值是字符串.
        obj = stringValue();
        break;
        // / .
      case AsciiV1.ASCII_47:
        obj = comment();
        break;
        // F .
      case AsciiV1.ASCII_70:
        // f .
      case AsciiV1.ASCII_102:
        index += AsciiV1.ASCII_5;
        // 值是false.
        obj = false;
        break;
        // T .
      case AsciiV1.ASCII_84:
        // t.
      case AsciiV1.ASCII_116:
        // 值是true.
        index += AsciiV1.ASCII_4;
        obj = true;
        break;
        // N .
      case AsciiV1.ASCII_78:
        // n .
      case AsciiV1.ASCII_110:
        index += AsciiV1.ASCII_4;
        // 值是null.
        obj = null;
        break;
      default:
        obj = number();
        break;
    }
    return obj;
  }

  /**
   * 返回一个对象.
   *
   * <p>json对象.
   *
   * @return Map Map. Guava : (int) ((float) expectedSize / 0.75F + 1.0F);
   * @author admin
   */
  public Map<String, Object> object() {
    //  创建一个map代表对象.
    Map<String, Object> obj = new HashMap<>();
    // 循环退出的标识.
    boolean flag = true;
    // 如果小于json字符串的最大长度.
    while (index < length && flag) {
      // 跳过所有空白.
      skip();
      // 获取一个字符.
      final char c = sequence.charAt(index);
      // 判断字符是什么?
      switch (c) {
          // { .
        case AsciiV1.ASCII_123:
          // 字符串需要移动一位,从"下一位开始算起.
          // , .
        case AsciiV1.ASCII_44:
          // , 表示还有其他的key:value对,移动一位字符.
          index++;
          // 将状态由1->2,表示有新的key:value对需要解析.
          break;
          // " .
        case AsciiV1.ASCII_34:
          handle(obj);
          break;
          // / .
        case AsciiV1.ASCII_47:
          // 如果是 / 则可能是注释.
          value();
          break;
          // } .
        case AsciiV1.ASCII_125:
          // } , 表示当前的对象解析完毕,移动一位字符.
          index++;
          // 对象解析完成.
          // 结束循环.
          flag = false;
          break;
          // 其他字符.
        default:
          error();
          break;
      }
    }
    return obj;
  }

  private void handle(Map<String, Object> obj) {
    // 对象中的key,必须是字符串.
    final String key = stringValue();
    // 处理注释.
    comment();
    // : ,对象的key和value之间必须是:,否则异常.
    colon();
    // 对象中的value,可能是7种类型的某一种,此处是递归方式解析.
    // 将key和value添加到对象中.
    Object value = value();
    obj.put(key, value);
    // 状态由0->1,表示这个key:value对解析完毕.
  }

  /**
   * 返回一个字符串.
   *
   * <p>json字符串(key或者字符串value).
   *
   * @param c json字符.
   * @author admin
   */
  public void escape(final char c) {
    switch (c) {
      case '"':
      case '\\':
      case '/':
      case 'b':
      case 'f':
      case 'n':
      case 'r':
      case 't':
      case 'u':
        index++;
        break;
      default:
        break;
    }
  }

  /**
   * 处理注释.
   *
   * <p>目前并不完整,有bug..
   *
   * @return String 注释.
   * @author admin
   */
  @Override
  public String comment() {
    // 如果当前字符是/,则判断下一位是不是/ 或者 *.
    final char c = sequence.charAt(index);
    // 下一个位置的字符.  移动到下一个位置(并不是++).
    final char cnext = sequence.charAt(index + 1);
    // 注释的表示.
    String comment = null;
    // 下一位是/,则是单行注释.
    if (AsciiV1.ASCII_47 == c && AsciiV1.ASCII_47 == cnext) {
      comment = singleLine();
    }
    // 下一位是* 则是多行注释.
    if (AsciiV1.ASCII_47 == c && AsciiV1.ASCII_42 == cnext) {
      comment = multiLine();
    }
    return comment;
  }

  public String multiLine() {
    // 记录/后的字符开始位置.
    index++;
    int start = index;
    boolean flag = true;
    String comment = null;
    while (index < length && flag) {
      // 如果当前字符是/,则判断下一位是不是/ 或者 *.
      final int next = sequence.charAt(index);
      // 下一位是*和/ 多行注释结尾.
      if (AsciiV1.ASCII_42 == next && AsciiV1.ASCII_47 == sequence.charAt(index + 1)) {
        comment = sequence.substring(start, index - 1);
        flag = false;
      }
      // 跳过多行注释的/.
      index++;
    }
    return comment;
  }

  public String singleLine() {
    // 记录/后的字符开始位置.
    index++;
    int start = index;
    boolean flag = true;
    String comment = null;
    while (index < length && flag) {
      // 如果当前字符是/,则判断下一位是不是/ 或者 *.
      final int next = sequence.charAt(index);
      // 下一位是* 则是多行注释.
      if ('\n' == next || ',' == next || ':' == next) {
        //
        comment = sequence.substring(start, index - 1);
        flag = false;
      }
      index++;
    }
    return comment;
  }

  /**
   * 校验key value 之间的冒号.
   *
   * <p>json字符串(key和value之间必须是冒号).
   *
   * @author admin
   */
  @Override
  public void colon() {
    skip();
    final char c = sequence.charAt(index);
    // : .
    if (AsciiV1.ASCII_58 != c) {
      error();
    }
    index++;
  }

  /**
   * .
   *
   * <p>.
   *
   * @author admin
   */
  public abstract void skip();

  /**
   * .
   *
   * <p>.
   *
   * @author admin
   */
  public abstract void error();

  /**
   * .
   *
   * <p>.
   *
   * @return List List.
   * @author admin
   */
  public abstract List<Object> array();

  /**
   * .
   *
   * <p>.
   *
   * @return String String.
   * @author admin
   */
  public abstract String stringValue();

  /**
   * .
   *
   * <p>.
   *
   * @return Number Number.
   * @author admin
   */
  public abstract Number number();
}
