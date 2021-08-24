package org.jdkstack.jdkjson.core.writer.value2;

import org.jdkstack.jdkjson.api.writer.value2.Value;

public class StringValue implements Value {

  @Override
  public Object serialisation(Object obj) {
    return "\"" + obj + "\"";
  }
}
