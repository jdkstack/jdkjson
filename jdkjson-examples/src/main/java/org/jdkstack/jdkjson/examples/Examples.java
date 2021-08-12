package org.jdkstack.jdkjson.examples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;
import org.jdkstack.jdkjson.core.exception.JsonRuntimeException;
import org.jdkstack.jdkjson.core.pointer.JsonPointerImpl;
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
    // String json1 = "{\"aa\":\"1\",\"a1a\":\"1\"}";
    // String json2 = "{\"aa\":\"999\",\"bb\":\"2\"}";
    // JsonValue j1 = JsonUtil.toJson(json1);
    // JsonValue j2 = JsonUtil.toJson(json2);
    // JsonMergePatch jsonMergePatch = new JsonMergePatchImpl(j2);
    // JsonValue diff = jsonMergePatch.apply(j1);
    // String patchJson = diff.toString();
    // JsonPointerImpl jsonPointer=new JsonPointerImpl("/a~0a/!10");
    // System.out.println(patchJson);
    Map<String, Object> map = new HashMap<>();
    map.put("description", "This test data is provided by the RFC6901 document!");
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
    map.put("testData", testData);
    // JsonPointerImpl jsonPointer = new JsonPointerImpl("/foo/0");
    // jsonPointer.parse("/foo/0");
    // Object evaluate = jsonPointer.evaluate(testData);

    // Object evaluate1 = JsonPointer.parse("/foo").evaluate(testData);
    // Object evaluate2 = JsonPointer.parse("/foo/0").evaluate(testData);
    // Object evaluate3 = JsonPointer.parse("/").evaluate(testData);
    //Object evaluate4 = JsonPointerV1.parse("/a~1b").evaluate(testData);
    // Object evaluate5 = JsonPointer.parse("/c%d").evaluate(testData);
    // Object evaluate6 = JsonPointer.parse("/e^f").evaluate(testData);
    // Object evaluate7 = JsonPointer.parse("/g|h").evaluate(testData);
    // Object evaluate8 = JsonPointer.parse("/i\\j").evaluate(testData);
    // Object evaluate9 = JsonPointer.parse("/k\"l").evaluate(testData);
    // Object evaluate10 = JsonPointer.parse("/ ").evaluate(testData);
    // Object evaluate11 = JsonPointer.parse("/m~0n").evaluate(testData);

    // List<Integer> authors = JsonPath.read(Constants.LIST, "$.store.book[*].author^");
    // 第一个字符必须是@或者$
    // 第二个字符必须是{或者[
    // 第三个字符

    // 最后一个字符不能是.
    long start = System.currentTimeMillis();

    // 200W,使用LRU缓存(解析一条).
    // for (int i = 0; i < Constants.LOOP; i++) {
    // JsonReaderV2 jsonParserV2 = new JsonReaderV2(Constants.MAP);
    // jsonParserV2.deserializeLru();
    // JsonReaderV1.deserializeLru(Constants.MAP);
    // JsonPointerImpl jsonPointer = new JsonPointerImpl();
    // jsonPointer.parse("/foo/0");
    // }

    // 200W,解析所有.
    for (int i = 0; i < Constants.LOOP; i++) {
      // JsonReaderV2 jsonParserV2 = new JsonReaderV2(Constants.MAP);
      // jsonParserV2.deserialize();
      // JsonReaderV1.deserialize(Constants.MAP);
      // JsonPointerImpl jsonPointer = new JsonPointerImpl();
      // jsonPointer.test("/foo/0");
      //JsonPointerImpl jsonPointer = new JsonPointerImpl();
      //jsonPointer.parse("/~");

      final StringBuilder token = new StringBuilder(200);
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
      token.append("122");
    }

    //
    JsonObject jsonObject = JsonObject.EMPTY_JSON_OBJECT;
   JsonValue jsonValue = Json.createPointer("/~01").getValue(jsonObject);
    long end = System.currentTimeMillis();
    throw new JsonRuntimeException(String.valueOf(end - start));
  }
}
