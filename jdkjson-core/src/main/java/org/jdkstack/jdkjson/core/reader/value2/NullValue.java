package org.jdkstack.jdkjson.core.reader.value2;

import org.jdkstack.jdkjson.api.reader.value2.Value;

public class NullValue implements Value {

  @Override
  public Object deserialisation() {
    return null;
  }
}
