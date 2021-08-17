package org.jdkstack.jdkjson.core.pointer.verson1;

import org.jdkstack.jdkjson.api.pointer.Pointer;

public abstract class AbstractJsonPointerV1 implements Pointer {

  abstract String encode(String token);

  abstract String decode(String token);

  abstract int arrayIndex(String token);

  abstract void path();

  abstract Object value(Object json);
}
