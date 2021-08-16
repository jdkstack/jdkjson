package org.jdkstack.jdkjson.core.reader.value;

public class BooleanValue implements Value {

  @Override
  public void exe(Object obj, StringBuilder sb) {
    sb.append(obj);
  }
}
