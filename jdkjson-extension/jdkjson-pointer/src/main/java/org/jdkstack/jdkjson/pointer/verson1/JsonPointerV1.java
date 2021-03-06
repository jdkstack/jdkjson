package org.jdkstack.jdkjson.pointer.verson1;

import org.jdkstack.jdkjson.core.common.AsciiV1;
import org.jdkstack.jdkjson.core.exception.JsonRuntimeException;

/**
 * Json Pointer第1版.
 *
 * <p>用json pointer操作json.
 *
 * <p>RFC json pointer: https://www.rfc-editor.org/rfc/rfc6901.txt
 *
 * <pre>
 *   1.The key words:[RFC2119]
 *   2.一个字符串语法(ABNF[RFC5234]):用于JSON中确定一个特定的值.
 *   3.语法:Unicode字符串[RFC4627].包含零个或多个字符,并且由左斜杠'/'分割. 例如:/abc/w1.
 *   reference token为abc和w1.  /不是reference token,而是分隔符.
 *   '~' 和'/' 是语法的关键字符,需要转换成 ~0和~1.
 *   4.
 *   5.
 *   json pointer ABNF 语法:
 *   json-pointer    = *( "/" reference-token )   0或者多个/
 *   reference-token = *( unescaped / escaped )   0或者多个转义,非转义字符
 *   unescaped       = %x00-2E / %x30-7D / %x7F-10FFFF ; %x2F ('/') and %x7E ('~') are excluded from 'unescaped'
 *   不需要转义的字符.
 *   escaped         = "~" ( "0" / "1" ) ; representing '~' and '/', respectively  需要转义的字符.
 *   逻辑:
 *   1.首先对json pointer path解码,循环处理path的每一个字符. ~1转换/ 和~0转换~,~01转换~1(只转换一次,不会再次转换~1为/)
 *   2.如果当前是对象.
 *   3.如果当前是数组.
 * </pre>
 *
 * @author admin
 */
public class JsonPointerV1 extends AbstractJsonPointerV1 {

  public JsonPointerV1(final String path) {
    super(path);
  }

  /**
   * 对json pointer中的token进行解码(不包括前缀/).
   *
   * <p>json pointer可能存在被编码的/ ~,因为这是特殊的字符.
   *
   * @param token json pointer token.
   * @return String String.
   * @author admin
   */
  @Override
  public String decode(String token) {
    // json pointer长度.
    final int len = token.length();
    // 初始化容量需要再次测试(是否应该分配对象?).
    final StringBuilder sb = new StringBuilder(len);
    // 当前被处理的json pointer字符位置.
    int index = 0;
    // 循环处理每一个字符.
    while (index < len) {
      // 获取一个字符.
      char c = token.charAt(index);
      // 位置+1,检查当前是不是最后一个字符.
      int i = index + 1;
      // 如果当前字符满足'~',并且不是最后一个字符.
      if (c == '~' && i < len) {
        // 获取下一个字符.
        final char next = token.charAt(i);
        // 如果是0.
        if (next == '0') {
          // 用~ 替换~0.
          c = '~';
          // 位置+1(跳过~).
          index++;
        }
        // 如果是1
        if (next == '1') {
          // 用/ 替换~1.
          c = '/';
          // 位置+1(跳过/).
          index++;
        }
      }
      // 不满足上面条件,添加原始字符,如果满足条件,添加替换字符.
      sb.append(c);
      // 跳过当前字符的位置,下一次循环处理下一个字符.
      index++;
    }
    // 返回被解码的json poninter.
    return sb.toString();
  }

  /**
   * 对json pointer中的token进行编码(不包括前缀/).
   *
   * <p>json pointer可能存在/ ~,因为这是特殊的字符,需要转换成~1和~0(不包括token分隔符/).
   *
   * @param token json pointer token.
   * @return String String.
   * @author admin
   */
  @Override
  public String encode(String token) {
    //    // 初始化容量需要再次测试.
    final StringBuilder sb = new StringBuilder(25);
    // json pointer长度.
    final int len = token.length();
    // 当前被处理的json pointer字符位置.
    int index = 0;
    // 循环处理每一个字符.
    while (index < len) {
      // 获取一个字符.
      final char c = token.charAt(index);
      // 如果当前字符满足'~'.
      if (c == '~') {
        // 编码成~0.
        sb.append("~0");
      } else if (c == '/') {
        // 编码成~1.
        sb.append("~1");
      } else {
        // 不处理.
        sb.append(c);
      }
      // 处理下一个字符.
      index++;
    }
    // 返回编码后的json pointer.
    return sb.toString();
  }

  /**
   * json pointer.
   *
   * <p>json pointer
   *
   * @param token json pointer token.
   * @return String String.
   * @author admin
   */
  @Override
  public int arrayIndex(String token) {
    int len = token.length();
    // 当前被处理的json pointer字符位置.
    int index = 0;
    // 初始化状态0.
    int state = 0;
    // 返回的索引下标,默认返回-1,代表返回整个数组.
    int arrayIndex = -1;
    // 循环停止标识.
    boolean flag = true;
    // 循环处理每一个字符.
    while (index < len && flag) {
      // 获取一个字符.
      final char c = token.charAt(index);
      // 处理每一个字符.
      switch (c) {
          // - .
        case AsciiV1.ASCII_45:
          // 第一个字符是-,那么后面不能出现任何字符,流程结束.
          // 状态设置成非法.
          if (state != 0) {
            flag = false;
            // 状态非法.
            // 一旦非法,数组索引重置-1.
          }
          state = -1;
          arrayIndex = -1;
          break;
          // 0 .
        case AsciiV1.ASCII_48:
          switch (state) {
              // 第一个字符是0,那么后面不能出现任何字符,流程结束.
            case 0:
              // 状态非法.
              state = -1;
              // 数组索引设置成0.
              arrayIndex = 0;
              break;
              // 如果状态1,说明之前是合法的字符,(0可以出现在1-9之后,所以需要处理状态1的逻辑).
            case 1:
              break;
              // 其他状态全都是非法的.
            default:
              // 非法.
              flag = false;
              // 状态设置成非法.
              state = -1;
              // 一旦非法,数组索引重置-1.
              arrayIndex = -1;
              break;
          }
          break;
          // 1-9 .
        case AsciiV1.ASCII_49:
        case AsciiV1.ASCII_50:
        case AsciiV1.ASCII_51:
        case AsciiV1.ASCII_52:
        case AsciiV1.ASCII_53:
        case AsciiV1.ASCII_54:
        case AsciiV1.ASCII_55:
        case AsciiV1.ASCII_56:
        case AsciiV1.ASCII_57:
          switch (state) {
              // 第一个字符是1-9数字.
            case 0:
              // 状态设置成正常状态.
              state = 1;
              break;
              // 如果状态被设置成-1. 证明读取到了非法字符-,或者其他字符.
            case -1:
              // 循环停止.
              flag = false;
              break;
              // 不处理其他状态.
            default:
              break;
          }
          break;
          // 其他字符, A,B,C,D,E,F,G,+,*...... 都是不合法的.
        default:
          // 所有未处理的字符都是非法的,停止循环.
          flag = false;
          // 状态设置成非法.
          state = -1;
          // 一旦非法,数组索引重置-1.
          arrayIndex = -1;
          break;
      }
      // 移动当前位置+1,处理下一个字符.
      index++;
    }
    // 如果当前的状态是合法的,证明token是合法的数组索引.
    if (state != -1) {
      // 转换当前索引并赋值.
      arrayIndex = Integer.parseInt(token);
    }
    // 返回最新的数组索引.
    return arrayIndex;
  }

  /**
   * json pointer.
   *
   * <p>json pointer
   *
   * @author admin
   */
  @Override
  public void path() {
    int index = 0;
    int state = 0;
    StringBuilder sb = new StringBuilder(25);
    // 循环处理每一个字符.
    while (index < length) {
      char c = this.path.charAt(index);
      // 处理每一个字符.
      switch (c) {
        case '~':
          int i = index + 1;
          // 如果当前字符满足'~',并且不是最后一个字符.
          if (i < length) {
            // 获取下一个字符.
            final char next = this.path.charAt(i);
            // 如果是0.
            if (next == '0') {
              // 用~ 替换~0.
              c = '~';
              // 位置+1(跳过~).
              index++;
            }
            // 如果是1
            if (next == '1') {
              // 用/ 替换~1.
              c = '/';
              // 位置+1(跳过/).
              index++;
            }
          }
          sb.append(c);
          break;
        case '/':
          if (state == 0) {
            this.tokens.add("");
            state = 1;
          } else if (state == 1) {
            this.tokens.add(sb.toString());
            sb.setLength(0);
          }
          break;
        default:
          sb.append(c);
          if (state == 0) {
            throw new JsonRuntimeException("s");
          }
      }
      index++;
    }
    this.tokens.add(sb.toString());
    this.size = this.tokens.size();
  }
}
