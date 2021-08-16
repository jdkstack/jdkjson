package org.jdkstack.jdkjson.core.reader.value;

import java.util.List;
import org.jdkstack.jdkjson.core.writer.value.Value;
import org.jdkstack.jdkjson.core.writer.version2.JsonWriterV2;

public class ListValue implements Value {

  @Override
  public void exe(Object obj, StringBuilder sb) {
    JsonWriterV2.array((List) obj, sb);
  }
}
