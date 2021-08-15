package org.jdkstack.jdkjson.core.pointer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jdkstack.jdkjson.core.exception.JsonRuntimeException;

public class JsonPointerImpl {

  public JsonPointerImpl() {
    //
  }

  public Object evaluate(final Object document) {
    Object current = document;
    String[] tokens = new String[0];
    for (int idx = 0; idx < tokens.length; ++idx) {
      final String token = tokens[idx];
      if (tokens.length == 1) {
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
      if (current == null) {
        error(idx, "Missing field \"" + token + "\"");
      }
    }
    return current;
  }

  public static void test(String jsonPointer) {
    String[] tokens = jsonPointer.split("/", -1);
    if (!"".equals(tokens[0])) {
      throw new JsonRuntimeException("Json Pointer 错误.");
    }
    // 将~0,~1 替换成~
    for (int i = 1; i < tokens.length; i++) {
      String token = tokens[i];
      StringBuilder reftoken = new StringBuilder(20);
      for (int j = 0; j < token.length(); j++) {
        char ch = token.charAt(j);
        if (ch == '~' && j < token.length() - 1) {
          char ch1 = token.charAt(j + 1);
          if (ch1 == '0') {
            ch = '~';
            j++;
          } else if (ch1 == '1') {
            ch = '/';
            j++;
          }
        }
        reftoken.append(ch);
      }
      tokens[i] = reftoken.toString();
    }
  }

  public static void parse(String path) throws IllegalArgumentException {
    StringBuilder reftoken = new StringBuilder(25);
    List<String> result = new ArrayList<>();
    for (int i = 0; i < path.length(); ++i) {
      char c = path.charAt(i);
      if (i == 0) {
        if (c != '/') throw new IllegalArgumentException("Missing leading slash");
        continue;
      }
      switch (c) {
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
        case '/':
          result.add(reftoken.toString());
          reftoken = new StringBuilder(25);
          break;
        default:
          reftoken.append(c);
          break;
      }
    }
    result.add(reftoken.toString());
  }

  private void error(int atToken, String message) {
    //
  }

  public static void main(String[] args) {

    long start = System.currentTimeMillis();
    for (int i = 0; i < 20000000; i++) {
      //  test("/abc/dddd/a~1b/ad~0ddd");
      //parse("/abc/dddd/a~1b/ad~0ddd");
       //JsonPointerV1.path("/abc/dddd/a~1b/ad~0ddd");
    }
    long end = System.currentTimeMillis();
    throw new JsonRuntimeException(String.valueOf(end - start));
  }
}
