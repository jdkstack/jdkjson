package org.jdkstack.jdkjson.examples;

import org.jdkstack.jdkjson.core.exception.JsonRuntimeException;
import org.jdkstack.jdkjson.core.reader.verson1.JsonReaderV1;
import org.jdkstack.jdkjson.core.reader.verson2.JsonReaderV2;

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
    long start = System.currentTimeMillis();
    for (int i = 0; i < Constants.LOOP; i++) {
      JsonReaderV2 jsonParserV2 = new JsonReaderV2(Constants.MAP);
      jsonParserV2.deserialize();
      JsonReaderV1.deserializeLru(Constants.MAP);
    }
    long end = System.currentTimeMillis();
    throw new JsonRuntimeException(String.valueOf(end - start));
  }
}
