package org.jdkstack.jdkjson.core.writer.value1;

import org.jdkstack.jdkjson.api.writer.value1.Value;

public class StringValue implements Value {

  @Override
  public void serialisation(Object obj, StringBuilder sb) {
    sb.append("\"").append(obj).append("\"");
  }
}
