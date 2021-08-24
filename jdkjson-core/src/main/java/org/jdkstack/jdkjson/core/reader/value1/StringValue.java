package org.jdkstack.jdkjson.core.reader.value1;

import org.jdkstack.jdkjson.api.reader.value.Value;

public class StringValue implements Value {

  @Override
  public void deserialisation(Object obj, StringBuilder sb) {
    if (obj instanceof String) {
      sb.append(obj);
    }
  }
}
