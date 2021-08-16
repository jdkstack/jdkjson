package org.jdkstack.jdkjson.core.reader.value;

import org.jdkstack.jdkjson.api.writer.value.Value;
import org.jdkstack.jdkjson.core.writer.version2.JsonWriterV2;

public class StringValue implements Value {

  @Override
  public void exe(Object obj, StringBuilder sb) {
    JsonWriterV2.stringValue(obj, sb);
  }
}
