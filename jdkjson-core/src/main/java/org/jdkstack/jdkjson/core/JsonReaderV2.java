package org.jdkstack.jdkjson.core;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// json5: https://spec.json5.org/
public class JsonReaderV2 extends AbstractJsonParser {
  /** 正在处理的字符的位置. */
  private int index = 0;
  /** 待处理的json字符串. */
  private final String sequence;
  /** 待处理的json字符串长度. */
  private final int length;
  /** 换行的第一个字符的位置. */
  private int position = 0;
  /** 当前行号,当前处理第几行. */
  private int line = 1;

  public JsonReaderV2(final String sequence) {
    this.sequence = sequence;
    // 字符串总长度,循环到-1.
    this.length = sequence.length();
  }

  // 反序列化json->map|list.
  public Object deserialize() {
    return value();
  }

  // 反序列化json->list.
  public Object deserialize2List() {
    return array();
  }

  // 反序列化json->map.
  public Object deserialize2Map() {
    return object();
  }

  // value七种值.
  public Object value() {
    final int c = sequence.charAt(index);
    switch (c) {
        // { .
      case 123:
        // 值是对象.
        return object();
        // [ .
      case 91:
        // 值是数组.
        return array();
        // " .
      case 34:
        // 值是字符串.
        return string();
        // / .
      case 47:
        return comment();
        // F .
      case 70:
        // f .
      case 102:
        index += 5;
        // 值是false.
        return false;
        // T .
      case 84:
        // t.
      case 116:
        // 值是true.
        index += 4;
        return true;
        // N .
      case 78:
        // n .
      case 110:
        index += 4;
        // 值是null.
        return null;
      default:
        return number();
    }
  }

  // 对象
  private Map<String, Object> object() {
    //  创建一个map代表对象.
    Map<String, Object> obj = new HashMap<>();
    // 如果小于json字符串的最大长度.
    while (index < length) {
      // 跳过所有空白.
      skip();
      // 获取一个字符.
      final int c = sequence.charAt(index);
      // 判断字符是什么?
      switch (c) {
          // { .
        case 123:
          // 字符串需要移动一位,从"下一位开始算起.
          index++;
          // 初始状态为0.
          int state = 0;
          break;
          // " .
        case 34:
          // 对象中的key,必须是字符串.
          final String key = string();
          // 处理注释.
          comment();
          // : ,对象的key和value之间必须是:,否则异常.
          colon();
          // 对象中的value,可能是7种类型的某一种,此处是递归方式解析.
          final Object value = value();
          // 将key和value添加到对象中.
          obj.put(key, value);
          // 状态由0->1,表示这个key:value对解析完毕.
          state = 1;
          break;
          // , .
        case 44:
          // , 表示还有其他的key:value对,移动一位字符.
          index++;
          // 将状态由1->2,表示有新的key:value对需要解析.
          state = 2;
          break;
          // / .
        case 47:
          // 如果是 / 则可能是注释.
          Object comment = value();
          System.out.println(comment);
          break;
          // } .
        case 125:
          // } , 表示当前的对象解析完毕,移动一位字符.
          index++;
          // 对象解析完成.
          state = -1;
          // 返回当前对象.
          return obj;
        default:
          error(line, position + 1, index, sequence);
          break;
      }
    }
    return null;
  }

  // 数组.
  private List<Object> array() {
    // 创建一个list代表数组.
    List<Object> arr = new ArrayList<>();
    // 如果小于json字符串的最大长度.
    while (index < length) {
      // 跳过所有空白.
      skip();
      // 获取一个字符.
      final int c = sequence.charAt(index);
      // 判断字符是什么?
      switch (c) {
          // [ .
        case 91:
          // 移动一位字符.
          index++;
          // 初始状态为0.
          int state = 0;
          break;
          // , .
        case 44:
          index++;
          state = 2;
          // 数组还有其他的元素等待解析.
          break;
          // / .
        case 47:
          // 如果是 / 则可能是注释.
          Object comment = value();
          System.out.println(comment);
          break;
          // ] .
        case 93:
          index++;
          // 数组解析完毕,返回数组对象.
          return arr;
        default:
          // 状态由0->1,表示这个数组的元素解析完毕.
          state = 1;
          // 数组的元素可能是7种值中的某一种,此处是递归方式解析.
          Object value = value();
          // 将元素添加到数组中.
          arr.add(value);
          break;
      }
    }
    return null;
  }

  // 字符串.
  private String string() {
    // 字符串需要移动一位,从"下一位开始算起.
    index++;
    // 记录"后的字符开始位置.
    int start = index;
    while (index < length) {
      final int c = sequence.charAt(index);
      index++;
      // 转义字符 \ .
      if (92 == c) {
        char next = sequence.charAt(index);
        // 跳过转义字符.
        escape(next);
        // " ,如果当前字符是双引号,则截取start和当前位置之间的字符.
      } else if (34 == c) {
        // 返回这个区间的字符串.
        return sequence.substring(start, index - 1);
      }
    }
    error(line, position + 1, index, sequence);
    throw new RuntimeException("");
  }

  // 数字.
  private Number number() {
    boolean contains = true;
    final int start = index;
    while (index < length) {
      final int c = sequence.charAt(index);
      if (44 == c) {
        // 字符, 代表数字结束, 停止循环.
        break;
      } else if (46 == c) {
        // 字符. 代表小数.
        contains = false;
      }
      index++;
    }
    // 字符串数字.
    String substring = sequence.substring(start, index);
    if (contains) {
      return new BigInteger(substring);
    } else {
      return new BigDecimal(substring);
    }
  }

  // 冒号.
  private void colon() {
    skip();
    final int n = sequence.charAt(index);
    // : .
    if (58 != n) {
      error(line, position + 1, index, sequence);
    }
    index++;
  }

  // 转义.
  private void escape(final int c) {
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

  // 跳过字符.
  public void skip() {
    // 字符串总长度,循环到-1.
    int length = sequence.length();
    while (index < length) {
      // 拿到字符后.
      char c = sequence.charAt(index);
      switch (c) {
        case '\n':
          // 当前行号+1.
          line++;
          // 只要换行,记录当前的位置.
          position = index;
        case '\t':
        case '\r':
        case ' ':
          index++;
          break;
        default:
          return;
      }
    }
  }

  // 注释,目前并不完整,有bug.
  private String comment() {
    // 如果当前字符是/,则判断下一位是不是/ 或者 *.
    final int c = sequence.charAt(index);
    // 下一位是/,则是单行注释.
    if (47 == c && 47 == sequence.charAt(index++)) {
      // 记录/后的字符开始位置.
      index++;
      int start = index;
      while (index < length) {
        // 如果当前字符是/,则判断下一位是不是/ 或者 *.
        final int next = sequence.charAt(index);
        // 下一位是* 则是多行注释.
        if ('\n' == next || ',' == next || ':' == next) {
          //
          return sequence.substring(start, index - 1);
        }
        index++;
      }
      // 下一位是* 则是多行注释.
    } else if (47 == c && 42 == sequence.charAt(index++)) {
      // 记录/后的字符开始位置.
      index++;
      int start = index;
      while (index < length) {
        // 如果当前字符是/,则判断下一位是不是/ 或者 *.
        final int next = sequence.charAt(index);
        // 下一位是* 则是多行注释.
        if (42 == next) {
          final int next1 = sequence.charAt(index++);
          if (47 == next1) {
            return sequence.substring(start, index - 1);
          }
        }
        index++;
      }
    }
    return "";
  }

  // 打印错误.
  private void error(int row, int begin, int index, String string) {
    StringBuilder sb = new StringBuilder();
    sb.append("Parse error at row: ").append(row).append(", column: ").append(index - begin + 1);
    sb.append("\r\n");
    int n = begin;
    while (n < string.length()) {
      char c = string.charAt(n);
      if (10 != c) {
        sb.append(string.charAt(n++));
      } else {
        break;
      }
    }
    sb.append("\r\n");
    sb.append("-".repeat(Math.max(0, (index - begin))));
    sb.append("^");
    throw new RuntimeException("\r\n" + sb);
  }
}
