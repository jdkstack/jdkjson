package org.jdkstack.jdkjson.core.reader.value1;

import java.util.concurrent.atomic.AtomicInteger;
import org.jdkstack.jdkjson.api.reader.value1.Value;
import org.jdkstack.jdkjson.core.common.AsciiV1;

public class FalseValue implements Value {

  @Override
  public Object deserialisation(String sequence, AtomicInteger ai) {
    ai.getAndAdd(AsciiV1.ASCII_5);
    return false;
  }
}
