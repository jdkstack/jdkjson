package org.jdkstack.jdkjson.core.writer.value;

import java.util.List;
import org.jdkstack.jdkjson.api.writer.value.Value;
import org.jdkstack.jdkjson.core.writer.version2.JsonWriterV2;

public class ListValue implements Value {

  @Override
  public void serialisation(Object obj, StringBuilder sb) {
    JsonWriterV2.array((List) obj, sb);
  }
}