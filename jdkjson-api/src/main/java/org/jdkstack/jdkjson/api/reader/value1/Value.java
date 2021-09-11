package org.jdkstack.jdkjson.api.reader.value1;

import java.util.concurrent.atomic.AtomicInteger;

public interface Value {
  Object deserialisation(final String sequence, final AtomicInteger ai);
}
