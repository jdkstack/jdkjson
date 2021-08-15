package org.jdkstack.jdkjson.core.path.verson1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jdkstack.jdkjson.core.common.AsciiV1;

/**
 * .
 *
 * <p>.
 *
 * @author admin
 */
public class JsonPathV1 {
  /** 待处理的json字符串. */
  protected final String sequence;
  /** 待处理的json字符串长度. */
  protected final int length;
  /** 正在处理的字符的位置,默认值0. */
  protected int index;

  protected JsonPathV1(final String sequenceParam) {
    // json path字符串序列.
    this.sequence = sequenceParam;
    // 字符串总长度,循环到-1.
    this.length = sequenceParam.length();
  }

  public List<Object> array() {
    // 创建一个list代表数组.
    List<Object> arr = new ArrayList<>();
    // 退出循环的标识.
    boolean flag = true;
    // 如果小于json字符串的最大长度.
    while (index < length && flag) {
      // 获取一个字符.
      final char c = sequence.charAt(index);
      // 判断字符是什么?
      switch (c) {
          // [ .
        case AsciiV1.ASCII_91:
          // 移动一位字符.
          // , .
        case AsciiV1.ASCII_44:
          index++;
          // 数组还有其他的元素等待解析.
          break;
          // / .
        case AsciiV1.ASCII_47:
          // 如果是 / 则可能是注释.
          value();
          break;
          // ] .
        case AsciiV1.ASCII_93:
          index++;
          // 数组解析完毕,返回数组对象.
          flag = false;
          break;
          // 其他字符.
        default:
          // 状态由0->1,表示这个数组的元素解析完毕.
          // 数组的元素可能是7种值中的某一种,此处是递归方式解析.
          Object value = value();
          // 将元素添加到数组中.
          arr.add(value);
          break;
      }
    }
    return arr;
  }

  private Object value() {
    // 循环退出的标识.
    boolean flag = true;
    // 返回对象表示.
    Object obj = null;
    // 循环处理字符串序列的每一个字符.
    while (index < length && flag) {
      // 获取当前位置的字符.
      final char c = sequence.charAt(index);
      // 处理每一个字符.
      switch (c) {
          // { .     {            }  对象.
        case AsciiV1.ASCII_123:
          // 值是对象.
          obj = object();
          break;
          // [ .    [            ]  数组.
        case AsciiV1.ASCII_91:
          // 值是数组.
          obj = array();
          break;
          // $ json path 开始.
          // case '$':
          // 检查root是对象还是数组.
          // root();
          //  break;
          /*        case '@':
            break;
          case '[':
            break;
          case '.':
            break;
          case '*':
            flag = false;
            break;*/
          // ^ json path 结束.
          // case '^':
          //  break;
          // 其他字符.
        default:
          obj = null;
          break;
      }
      index++;
    }
    return obj;
  }

  private Object root() {
    // 获取当前位置($下一个)的下一个字符.
    final char c = sequence.charAt(index + 1);
    // 返回对象表示.
    Object obj;
    // 处理每一个字符.
    switch (c) {
        // { .     {            }  对象.
      case AsciiV1.ASCII_123:
        // 值是对象.
        obj = object();
        break;
        // [ .    [            ]  数组.
      case AsciiV1.ASCII_91:
        // 值是数组.
        obj = array();
        break;
      default:
        obj = null;
        break;
    }
    return obj;
  }

  /**
   * 返回一个对象.
   *
   * <p>json对象.
   *
   * @return Map Map.
   * @author admin
   */
  public Map<String, Object> object() {
    //  创建一个map代表对象.
    Map<String, Object> obj = new HashMap<>();
    // 循环退出的标识.
    boolean flag = true;
    // 如果小于json字符串的最大长度.
    while (index < length && flag) {
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
          // ' .
        case AsciiV1.ASCII_39:
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
          break;
      }
    }
    return obj;
  }

  private void handle(Map<String, Object> obj) {
    // 对象中的key,必须是字符串.
    final String key = stringValue();
    // : ,对象的key和value之间必须是:,否则异常.
    colon();
    // 对象中的value,可能是7种类型的某一种,此处是递归方式解析.
    // 将key和value添加到对象中.
    Object value = value();
    obj.put(key, value);
    // 状态由0->1,表示这个key:value对解析完毕.
  }

  public void colon() {

    final char c = sequence.charAt(index);
    // : .
    if (AsciiV1.ASCII_58 != c) {}

    index++;
  }

  public String stringValue() {
    // 字符串需要移动一位,从"下一位开始算起.
    index++;
    // 记录"后的字符开始位置.
    int start = index;
    // json字符串的表示.
    String stringValue = null;
    // 退出循环的标识.
    boolean flag = true;
    while (index < length && flag) {
      final char c = sequence.charAt(index);
      index++;
      // 转义字符 \ .
      if (AsciiV1.ASCII_92 == c) {
        char next = sequence.charAt(index);
        //
      }
      // "或者' ,如果当前字符是双引号或者',则截取start和当前位置之间的字符.
      if (AsciiV1.ASCII_34 == c || AsciiV1.ASCII_39 == c) {
        // 返回这个区间的字符串.
        stringValue = sequence.substring(start, index - 1);
        // 将循环退出标识设置成false.
        flag = false;
      }
    }
    return stringValue;
  }
}
