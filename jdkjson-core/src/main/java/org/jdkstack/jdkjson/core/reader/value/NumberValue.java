package org.jdkstack.jdkjson.core.reader.value;

import org.jdkstack.jdkjson.api.reader.value.Value;

public class NumberValue implements Value {

  @Override
  public void deserialisation(Object obj, StringBuilder sb) {
    sb.append(obj);
  }
}
