package org.jdkstack.jdkjson.core.reader.value2;

import java.util.List;
import java.util.Map;
import org.jdkstack.jdkjson.api.reader.value2.Value;
import org.jdkstack.jdkjson.core.cache.LruV1;
import org.jdkstack.jdkjson.core.reader.Constants;

public class BaseValue implements Value {

  /** LRU缓存类. */
  protected static final LruV1<String, Object> LRUV1 = new LruV1<>(Constants.CAPACITY);
  /** LRU缓存类. */
  protected static final LruV1<String, Map<String, Object>> LRUV1_MAP =
      new LruV1<>(Constants.CAPACITY);
  /** LRU缓存类. */
  protected static final LruV1<String, List<Object>> LRUV1_LIST = new LruV1<>(Constants.CAPACITY);

  @Override
  public Object deserialisation() {
    return null;
  }
}
