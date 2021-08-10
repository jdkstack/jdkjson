package org.jdkstack.jdkjson.jmh.jsonparser;

import org.jdkstack.jdkjson.core.reader.verson2.JsonReaderV2;

/**
 * .
 *
 * <p>.
 *
 * @author admin
 */
public final class JsonParserUtil {

  private JsonParserUtil() {
    //
  }

  public static Object json2Bean(String jsonStr) {
    JsonReaderV2 jsonParserV2 = new JsonReaderV2(jsonStr);
    return jsonParserV2.deserialize();
  }

  public static Object json2BeanList(String jsonStr) {
    JsonReaderV2 jsonParserV2 = new JsonReaderV2(jsonStr);
    return jsonParserV2.deserialize2List();
  }

  public static Object json2BeanMap(String jsonStr) {
    JsonReaderV2 jsonParserV2 = new JsonReaderV2(jsonStr);
    return jsonParserV2.deserialize2Map();
  }
}
