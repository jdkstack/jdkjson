package org.jdkstack.jdkjson.core.reader.value1;

import java.util.concurrent.atomic.AtomicInteger;

public class StringValue extends BaseValue {

  @Override
  public Object deserialisation(String sequence, AtomicInteger ai) {
    return stringValue(sequence, ai);
  }
}
