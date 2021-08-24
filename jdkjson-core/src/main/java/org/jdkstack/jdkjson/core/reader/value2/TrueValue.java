package org.jdkstack.jdkjson.core.reader.value2;

import org.jdkstack.jdkjson.api.reader.value2.Value;

public class TrueValue implements Value {

  @Override
  public Object deserialisation() {
    return true;
  }
}
