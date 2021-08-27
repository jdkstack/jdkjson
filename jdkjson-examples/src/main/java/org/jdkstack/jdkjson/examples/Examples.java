package org.jdkstack.jdkjson.examples;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.jdkstack.jdkjson.core.exception.JsonRuntimeException;
import org.jdkstack.jdkjson.core.reader.verson2.JsonReaderV2;

/**
 * .
 *
 * <p>.
 *
 * @author admin
 */
public final class Examples {

  private Examples() {
    //
  }

  public static void main(String... args) {
    StringBuilder sb = new StringBuilder(Constants.SIZE);
    String file = "conf\\twitter.json";
    try (BufferedReader br =
        new BufferedReader(
            new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
      // 先读取一行.
      String line = br.readLine();
      // 如果第一行不为空.
      while (line != null) {
        // 拼接当前行.
        sb.append(line);
        // 读取下一行. System.lineSeparator
        line = br.readLine();
      }
    } catch (IOException e) {
      throw new JsonRuntimeException("", e);
    }
    String msg = sb.toString();
    long start = System.currentTimeMillis();
    for (int i = 0; i < Constants.LOOP; i++) {
      JsonReaderV2 jsonReaderV2 = new JsonReaderV2(msg);
      jsonReaderV2.deserialize2Map();
    }
    long end = System.currentTimeMillis();
    throw new JsonRuntimeException(String.valueOf(end - start));
  }
}
