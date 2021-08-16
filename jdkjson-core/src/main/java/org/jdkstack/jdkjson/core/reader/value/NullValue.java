package org.jdkstack.jdkjson.core.reader.value;

import org.jdkstack.jdkjson.api.writer.value.Value;

public class NullValue implements Value {

  @Override
  public void exe(Object obj, StringBuilder sb) {
    sb.append(obj);
  }
}
