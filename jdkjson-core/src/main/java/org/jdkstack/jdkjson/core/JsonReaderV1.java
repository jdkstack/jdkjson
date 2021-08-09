package org.jdkstack.jdkjson.core;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class JsonReaderV1 extends AbstractJsonParser {
  // 对象
  private static Map<String, Object> object(String sequence, AtomicInteger i) {
    i.getAndIncrement();
    int length = sequence.length();
    Map<String, Object> obj = new HashMap<>();
    int state = 0;
    boolean flag = true;
    while (i.get() < length && flag) {
      skipWhiteSpace(sequence, i);
      final int c = sequence.charAt(i.get());
      switch (c) {
        case '"':
          // 对象中的key.
          final String key = string(sequence, i);
          // : .
          colon(sequence, i);
          // 对象中的value.
          final Object value = value(sequence, i);
          // 添加.
          obj.put(key, value);
          state = 1;
          break;
        case ',':
          i.getAndIncrement();
          state = 2;
          break;
        case '}':
          i.getAndIncrement();
          // return obj;
          // 结束循环.
          flag = false;
          break;
        default:
          break;
      }
    }
    return obj;
  }

  // 数组.
  private static List<Object> array(String sequence, AtomicInteger i) {
    i.getAndIncrement();
    int length = sequence.length();
    List<Object> arr = new ArrayList<>();
    int state = 0;
    boolean flag = true;
    while (i.get() < length && flag) {
      skipWhiteSpace(sequence, i);
      final int c = sequence.charAt(i.get());
      switch (c) {
        case ',':
          i.getAndIncrement();
          state = 2;
          break;
        case ']':
          i.getAndIncrement();
          flag = false;
          // return arr;
          break;
        default:
          state = 1;
          Object value = value(sequence, i);
          arr.add(value);
          break;
      }
    }
    return arr;
  }

  // 字符串.
  private static String string(String sequence, AtomicInteger i) {
    // 字符串需要移动一位,从"下一位开始算起.
    i.incrementAndGet();
    // 记录"后的字符开始位置.
    int start = i.get();
    int length = sequence.length();
    while (i.get() < length) {
      final int c = sequence.charAt(i.get());
      i.incrementAndGet();
      // 转义字符 \ .
      if (92 == c) {
        char next = sequence.charAt(i.get());
        // 跳过转义字符.
        escape(next, i);
        // " ,如果当前字符是双引号,则截取start和当前位置之间的字符.
      } else if (34 == c) {
        // 返回这个区间的字符串.
        return sequence.substring(start, i.get() - 1);
      }
    }
    throw new RuntimeException("");
  }

  // 转义.
  private static void escape(final int c, AtomicInteger i) {
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
        i.incrementAndGet();
        break;
      default:
        break;
    }
  }

  // 冒号.
  private static void colon(String sequence, AtomicInteger i) {
    skipWhiteSpace(sequence, i);
    final int n = sequence.charAt(i.get());
    if (n != ':') {
      throw new RuntimeException("非法.");
    }
    i.getAndIncrement();
  }

  // 反序列化json->map.
  public static Object deserializeLru(String sequence) {
    AtomicInteger i = new AtomicInteger(0);
    Object obj = LRUV1.get(sequence);
    if (obj != null) {
      return obj;
    } else {
      Object value = value(sequence, i);
      LRUV1.put(sequence, value);
      return value;
    }
  }

  // 反序列化json->list.
  public static Object deserialize2ListLru(String sequence) {
    AtomicInteger i = new AtomicInteger(0);
    Object obj = LRUV1.get(sequence);
    if (obj != null) {
      return obj;
    } else {
      Object value = array(sequence, i);
      LRUV1.put(sequence, value);
      return value;
    }
  }

  // 反序列化json->map.
  public static Object deserialize2MapLru(String sequence) {
    AtomicInteger i = new AtomicInteger(0);
    Object obj = LRUV1.get(sequence);
    if (obj != null) {
      return obj;
    } else {
      Object value = object(sequence, i);
      LRUV1.put(sequence, value);
      return value;
    }
  }
  // 反序列化json->map.
  public static Object deserialize(String sequence) {
    AtomicInteger i = new AtomicInteger(0);
    return value(sequence, i);
  }

  // 反序列化json->list.
  public static Object deserialize2List(String sequence) {
    AtomicInteger i = new AtomicInteger(0);
    return array(sequence, i);
  }

  // 反序列化json->map.
  public static Object deserialize2Map(String sequence) {
    AtomicInteger i = new AtomicInteger(0);
    return object(sequence, i);
  }

  public static void skipWhiteSpace(String sequence, AtomicInteger i) {
    // 字符串总长度,循环到-1.
    int length = sequence.length();
    while (i.get() < length) {
      // 拿到字符后.
      char c = sequence.charAt(i.get());
      switch (c) {
        case '\t':
        case '\r':
        case '\n':
        case ' ':
          i.getAndIncrement();
          break;
        default:
          return;
      }
    }
  }

  // value七种值.
  public static Object value(String sequence, AtomicInteger i) {
    final int c = sequence.charAt(i.get());
    switch (c) {
      case '{':
        // 值是对象.
        return object(sequence, i);
      case '[':
        // 值是数组.
        return array(sequence, i);
      case '"':
        // 值是字符串.
        return string(sequence, i);
      case 'f':
        i.getAndAdd(5);
        // 值是false.
        return false;
      case 't':
        // 值是true.
        i.getAndAdd(4);
        return true;
      case 'n':
        i.getAndAdd(4);
        // 值是null.
        return null;
      default:
        return number(sequence, i);
    }
  }

  // 数字.
  private static Number number(String sequence, AtomicInteger i) {
    int length = sequence.length();
    boolean contains = true;
    final int start = i.get();
    while (i.get() < length) {
      final int c = sequence.charAt(i.get());
      if (44 == c) {
        // 字符, 代表数字结束, 停止循环.
        break;
      } else if (46 == c) {
        // 字符. 代表小数.
        contains = false;
      }
      i.incrementAndGet();
    }
    // 字符串数字.
    String substring = sequence.substring(start, i.get());
    if (contains) {
      return new BigInteger(substring);
    } else {
      return new BigDecimal(substring);
    }
  }
}
