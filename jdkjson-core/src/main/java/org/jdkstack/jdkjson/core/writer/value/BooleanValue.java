package org.jdkstack.jdkjson.core.writer.value;

import org.jdkstack.jdkjson.api.writer.value.Value;

public class BooleanValue implements Value {

  @Override
  public void serialisation(Object obj, StringBuilder sb) {
    sb.append(obj);
  }
}
