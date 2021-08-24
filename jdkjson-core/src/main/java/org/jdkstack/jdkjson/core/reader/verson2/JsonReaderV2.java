package org.jdkstack.jdkjson.core.reader.verson2;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jdkstack.jdkjson.core.cache.LruV1;
import org.jdkstack.jdkjson.core.common.AsciiV1;
import org.jdkstack.jdkjson.core.exception.JsonRuntimeException;
import org.jdkstack.jdkjson.core.reader.Constants;

/**
 * Json反序列化第2版,采用非静态方法方式解析.
 *
 * <p>多个类实现业务逻辑.
 *
 * <p>ECMA json5(对RFC规范的扩展): https://spec.json5.org/.
 *
 * <p>RFC json: https://datatracker.ietf.org/doc/rfc8259/.
 *
 * <p>https://www.json.org/json-en.html/.
 *
 * @author admin
 */
public class JsonReaderV2 extends AbstractJsonReaderV2 {

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
  public List<Object> deserialize2List() {
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
  public Map<String, Object> deserialize2Map() {
    return object();
  }

  /**
   * 使用LRU算法优化反序列化.
   *
   * <p>json字符串list|map.
   *
   * @return Object Object.
   * @author admin
   */
  public List<Object> deserialize2ListLru() {
    // 查询LRU缓存是否存在.
    List<Object> obj = LRUV1_LIST.get(sequence);
    // 不存在.
    if (obj == null) {
      // 解析json字符串,返回对象array.
      List<Object> value = array();
      // 放入LRU缓存中.
      LRUV1_LIST.put(sequence, value);
      // 赋值当前对象object.
      obj = value;
    }
    return obj;
  }

  /**
   * 使用LRU算法优化反序列化.
   *
   * <p>json字符串list|map.
   *
   * @return Object Object.
   * @author admin
   */
  public Map<String, Object> deserialize2MapLru() {
    // 查询LRU缓存是否存在.
    Map<String, Object> obj = LRUV1_MAP.get(sequence);
    // 不存在.
    if (obj == null) {
      // 解析json字符串,返回对象object.
      Map<String, Object> value = object();
      // 放入LRU缓存中.
      LRUV1_MAP.put(sequence, value);
      // 赋值当前对象object.
      obj = value;
    }
    return obj;
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
    final StringBuilder sb = new StringBuilder(AsciiV1.ASCII_64);
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
    throw new JsonRuntimeException(sb.toString());
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

  /**
   * 返回一个数组.
   *
   * <p>json数组.
   *
   * @return List List.
   * @author admin
   */
  @Override
  public List<Object> array() {
    // 创建一个list代表数组.
    List<Object> arr = new ArrayList<>();
    // 退出循环的标识.
    boolean flag = true;
    // 如果小于json字符串的最大长度.
    while (index < length && flag) {
      // 跳过所有空白.
      skip();
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

  /**
   * 返回一个字符串.
   *
   * <p>json字符串(key或者字符串value).
   *
   * @return String String.
   * @author admin
   */
  @Override
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
        // 跳过转义字符.
        escape(next);
      }
      // " ,如果当前字符是双引号,则截取start和当前位置之间的字符.
      if (AsciiV1.ASCII_34 == c) {
        // 返回这个区间的字符串.
        stringValue = sequence.substring(start, index - 1);
        // 将循环退出标识设置成false.
        flag = false;
      }
    }
    return stringValue;
  }

  /**
   * json 数字.
   *
   * <p>json数字.
   *
   * @return Number Number.
   * @author admin
   */
  @Override
  public Number number() {
    skip();
    final int start = index;
    while (index < length) {
      final char c = sequence.charAt(index);
      if (AsciiV1.ASCII_44 == c) {
        // 字符, 代表数字结束, 停止循环.
        break;
      }
      index++;
    }
    // 字符串数字. 比StringBuilder高效1倍.
    String substring = sequence.substring(start, index);
    Number number;
    // 如果包含'.',循环的时候处理,性能还是差一点?.
    if (substring.contains(Constants.FULL_STOP)) {
      number = new BigDecimal(substring);
    } else {
      number = new BigInteger(substring);
    }
    return number;
  }
}
