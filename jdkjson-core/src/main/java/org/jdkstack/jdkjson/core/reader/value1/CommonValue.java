package org.jdkstack.jdkjson.core.reader.value1;

import org.jdkstack.jdkjson.api.reader.value.Value;

public class CommonValue implements Value {

  @Override
  public void deserialisation(Object obj, StringBuilder sb) {
    sb.append(obj);
  }
}
