package org.jdkstack.jdkjson.examples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jdkstack.jdkjson.core.exception.JsonRuntimeException;
import org.jdkstack.jdkjson.core.pointer.JsonPointerV1;

/**
 * .
 *
 * <p>.
 *
 * @author admin
 */
public final class Examples {

  private Examples() {}

  public static void main(String... args) {
    List<Object> foo = new ArrayList<>();
    foo.add("bar");
    foo.add("baz");
    Map<String, Object> testData = new HashMap<>();
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
    // 第一个字符必须是@或者$ 第二个字符必须是{或者[ 第三个字符  最后一个字符不能是.
    //List<Integer> authors = JsonPath.read(Constants.LIST, "$.store.book[*].author");

    long start = System.currentTimeMillis();
    for (int i = 0; i < Constants.LOOP; i++) {
      // "" "/foo/0"  "/" "/a~1b"  "/c%d"  "/e^f" "/g|h"  "/i\\j"  "/k\"l"   "/ "  "/m~0n"
      JsonPointerV1 jsonPointerV1 = new JsonPointerV1("/foo/0");
      jsonPointerV1.path();
      Object value = jsonPointerV1.value(testData);
    }
    long end = System.currentTimeMillis();
    throw new JsonRuntimeException(String.valueOf(end - start));
  }
}
