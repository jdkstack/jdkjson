package org.jdkstack.jdkjson.core.pointer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jdkstack.jdkjson.core.common.Ascii;
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
public class JsonPointerV1 implements Pointer {

  private static final List<String> tokens = new ArrayList<>();

  /*
    private final RefToken[] tokens;

    public static final JsonPointerV1 ROOT = new JsonPointerV1(new RefToken[] {});

    public JsonPointerV1(RefToken[] tokens) {
      this.tokens = tokens;
    }

    public JsonPointerV1(List<RefToken> tokens) {
      this.tokens = tokens.toArray(new RefToken[0]);
    }

    public static JsonPointerV1 parse(String path) throws IllegalArgumentException {
      StringBuilder reftoken = null;
      List<RefToken> result = new ArrayList<RefToken>();

      for (int i = 0; i < path.length(); ++i) {
        char c = path.charAt(i);

        // Require leading slash
        if (i == 0) {
          if (c != '/') throw new IllegalArgumentException("Missing leading slash");
          reftoken = new StringBuilder();
          continue;
        }

        switch (c) {
            // Escape sequences
          case '~':
            switch (path.charAt(++i)) {
              case '0':
                reftoken.append('~');
                break;
              case '1':
                reftoken.append('/');
                break;
              default:
                throw new IllegalArgumentException(
                    "Invalid escape sequence ~" + path.charAt(i) + " at index " + i);
            }
            break;

            // New reftoken
          case '/':
            result.add(new RefToken(reftoken.toString()));
            reftoken.setLength(0);
            break;

          default:
            reftoken.append(c);
            break;
        }
      }

      if (reftoken == null) return ROOT;

      result.add(RefToken.parse(reftoken.toString()));
      return new JsonPointerV1(result);
    }

    public boolean isRoot() {
      return tokens.length == 0;
    }

    JsonPointerV1 append(String field) {
      RefToken[] newTokens = Arrays.copyOf(tokens, tokens.length + 1);
      newTokens[tokens.length] = new RefToken(field);
      return new JsonPointerV1(newTokens);
    }

    JsonPointerV1 append(int index) {
      return append(Integer.toString(index));
    }

    int size() {
      return tokens.length;
    }

    public String toString() {
      StringBuilder sb = new StringBuilder();
      for (RefToken token : tokens) {
        sb.append('/');
        sb.append(token);
      }
      return sb.toString();
    }

    public List<RefToken> decompose() {
      return Arrays.asList(tokens.clone());
    }

    public RefToken get(int index) throws IndexOutOfBoundsException {
      if (index < 0 || index >= tokens.length)
        throw new IndexOutOfBoundsException("Illegal index: " + index);
      return tokens[index];
    }

    public RefToken last() {
      if (isRoot()) throw new IllegalStateException("Root pointers contain no reference tokens");
      return tokens[tokens.length - 1];
    }

    public JsonPointerV1 getParent() {
      return isRoot() ? this : new JsonPointerV1(Arrays.copyOf(tokens, tokens.length - 1));
    }

    private void error(int atToken, String message) {
      //
    }

    public Object evaluate(final Object document) {
      Object current = document;
      for (int idx = 0; idx < tokens.length; ++idx) {
        final RefToken token = tokens[idx];
        if (current instanceof List) {
          List map = (List) current;
          current = map.get(token.getIndex());
        } else if (current instanceof Map) {
          Map map = (Map) current;
          current = map.get(token.getField());
        } else {
          throw new JsonRuntimeException("");
        }
        if (current == null) {
          error(idx, "Missing field \"" + token.getField() + "\"");
        }
      }
      return current;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      JsonPointerV1 that = (JsonPointerV1) o;

      // Probably incorrect - comparing Object[] arrays with Arrays.equals
      return Arrays.equals(tokens, that.tokens);
    }

    @Override
    public int hashCode() {
      return Arrays.hashCode(tokens);
    }

    static class RefToken {
      private String decodedToken;
      private transient Integer index = null;

      public RefToken(String decodedToken) {
        if (decodedToken == null) throw new IllegalArgumentException("Token can't be null");
        this.decodedToken = decodedToken;
      }

      private static final Pattern DECODED_TILDA_PATTERN = Pattern.compile("~0");
      private static final Pattern DECODED_SLASH_PATTERN = Pattern.compile("~1");

      private static String decodePath(Object object) {
        String path = object.toString(); // see http://tools.ietf.org/html/rfc6901#section-4
        path = DECODED_SLASH_PATTERN.matcher(path).replaceAll("/");
        return DECODED_TILDA_PATTERN.matcher(path).replaceAll("~");
      }

      private static final Pattern ENCODED_TILDA_PATTERN = Pattern.compile("~");
      private static final Pattern ENCODED_SLASH_PATTERN = Pattern.compile("/");

      private static String encodePath(Object object) {
        String path = object.toString(); // see http://tools.ietf.org/html/rfc6901#section-4
        path = ENCODED_TILDA_PATTERN.matcher(path).replaceAll("~0");
        return ENCODED_SLASH_PATTERN.matcher(path).replaceAll("~1");
      }

      private static final Pattern VALID_ARRAY_IND = Pattern.compile("-|0|(?:[1-9][0-9]*)");

      public static RefToken parse(String rawToken) {
        if (rawToken == null) throw new IllegalArgumentException("Token can't be null");
        return new RefToken(decodePath(rawToken));
      }

      public boolean isArrayIndex() {
        if (index != null) return true;
        Matcher matcher = VALID_ARRAY_IND.matcher(decodedToken);
        if (matcher.matches()) {
          index = matcher.group().equals("-") ? LAST_INDEX : Integer.parseInt(matcher.group());
          return true;
        }
        return false;
      }

      public int getIndex() {
        if (!isArrayIndex()) throw new IllegalStateException("Object operation on array target");
        return index;
      }

      public String getField() {
        return decodedToken;
      }

      @Override
      public String toString() {
        return encodePath(decodedToken);
      }

      @Override
      public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RefToken refToken = (RefToken) o;

        return decodedToken.equals(refToken.decodedToken);
      }

      @Override
      public int hashCode() {
        return decodedToken.hashCode();
      }
    }

  */
  // static final int LAST_INDEX = Integer.MIN_VALUE;

  /**
   * 对json pointer中的token进行解码(不包括前缀/).
   *
   * <p>json pointer可能存在被编码的/ ~,因为这是特殊的字符.
   *
   * @param token json pointer token.
   * @return String String.
   * @author admin
   */
  public static String decode(String token) {
    // 初始化容量需要再次测试(是否应该分配对象?).
    final StringBuilder sb = new StringBuilder(25);
    // json pointer长度.
    final int length = token.length();
    // 当前被处理的json pointer字符位置.
    int index = 0;
    // 循环处理每一个字符.
    while (index < length) {
      // 获取一个字符.
      char c = token.charAt(index);
      // 位置+1,检查当前是不是最后一个字符.
      int i = index + 1;
      // 如果当前字符满足'~',并且不是最后一个字符.
      if (c == '~' && i < length) {
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
  private static String encode(String token) {
    //    // 初始化容量需要再次测试.
    final StringBuilder sb = new StringBuilder(25);
    // json pointer长度.
    final int length = token.length();
    // 当前被处理的json pointer字符位置.
    int index = 0;
    // 循环处理每一个字符.
    while (index < length) {
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
  public static int arrayIndex(String token) {
    int length = token.length();
    // 当前被处理的json pointer字符位置.
    int index = 0;
    // 初始化状态0.
    int state = 0;
    // 返回的索引下标,默认返回-1,代表返回整个数组.
    int arrayIndex = -1;
    // 循环停止标识.
    boolean flag = true;
    // 循环处理每一个字符.
    while (index < length && flag) {
      // 获取一个字符.
      final char c = token.charAt(index);
      // 处理每一个字符.
      switch (c) {
          // - .
        case Ascii.ASCII_45:
          switch (state) {
              // 第一个字符是-,那么后面不能出现任何字符,流程结束.
            case 0:
              // 状态设置成非法.
              state = -1;
              // 数组索引设置成-1.
              arrayIndex = -1;
              break;
              // 其他状态都是非法的. 包括正常状态1,因为-不允许出现在(1-9)后面.
            default:
              // 非法.
              flag = false;
              // 状态非法.
              state = -1;
              // 一旦非法,数组索引重置-1.
              arrayIndex = -1;
              break;
          }
          break;
          // 0 .
        case Ascii.ASCII_48:
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
        case Ascii.ASCII_49:
        case Ascii.ASCII_50:
        case Ascii.ASCII_51:
        case Ascii.ASCII_52:
        case Ascii.ASCII_53:
        case Ascii.ASCII_54:
        case Ascii.ASCII_55:
        case Ascii.ASCII_56:
        case Ascii.ASCII_57:
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
   * @param path json pointer path.
   * @author admin
   */
  public static void path(String path) {
    int index = 0;
    int length = path.length();
    int state = 0;
    StringBuilder sb = new StringBuilder(25);
    // 循环处理每一个字符.
    while (index < length) {
      char c = path.charAt(index);
      // 处理每一个字符.
      switch (c) {
        case '~':
          int i = index + 1;
          // 如果当前字符满足'~',并且不是最后一个字符.
          if (i < length) {
            // 获取下一个字符.
            final char next = path.charAt(i);
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
            tokens.add("");
            state = 1;
          } else if (state == 1) {
            tokens.add(sb.toString());
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
    tokens.add(sb.toString());
  }

  /**
   * json pointer从json对象中查询.
   *
   * <p>json pointer从json对象中查询.
   *
   * @param json json.
   * @return Object json value.
   * @author admin
   */
  public static Object value(final Object json) {
    Object current = json;
    for (int idx = 0; idx < tokens.size(); ++idx) {
      final String token = tokens.get(idx);
      if (tokens.size() == 1) {
        return current;
      }
      if ("".equals(token)) {
        continue;
      }
      if (current instanceof List) {
        List map = (List) current;
        current = map.get(Integer.parseInt(token));
      } else if (current instanceof Map) {
        Map map = (Map) current;
        current = map.get(token);
      } else {
        throw new JsonRuntimeException("");
      }
    }
    return current;
  }

  public static void main(String[] args) {
    Map<String, Object> testData = new HashMap<>();
    List<Object> foo = new ArrayList<>();
    foo.add("bar");
    foo.add("baz");
    testData.put("foo", foo);
    testData.put("", 0);
    testData.put("a/b", 1);
    testData.put("c%d", 2);
    testData.put("e^f", 3);
    testData.put("i\\j", 5);
    testData.put("g|h", 4);
    testData.put("k\"l", 6);
    testData.put(" ", 7);
    testData.put("m~n", 8);

    path("/");
    Object value = value(testData);
    path("/foo/0");
    path("/");
    path("/a~1b");
    path("/c%d");
    path("/e^f");
    path("/g|h");
    path("/i\\j");
    path("/k\"l");
    path("/ ");
    path("/m~0n");

    path("/abc/dddd/a~1b/ad~0ddd");
    String encode = encode("/~~~////~01~");

    System.out.println(encode);

    String decode = decode(encode);

    System.out.println(decode);

    long start = System.currentTimeMillis();
    for (int i = 0; i < 200000000; i++) {
      arrayIndex("21111111111111111133123123-12");
    }
    long end = System.currentTimeMillis();
    throw new JsonRuntimeException(String.valueOf(end - start));
  }
}
