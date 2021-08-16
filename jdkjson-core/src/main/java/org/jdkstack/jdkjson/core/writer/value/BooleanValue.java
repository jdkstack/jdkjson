package org.jdkstack.jdkjson.core.writer.value;

public class BooleanValue implements Value{

  @Override
  public void exe(Object obj, StringBuilder sb) {
    sb.append(obj);
  }
}
