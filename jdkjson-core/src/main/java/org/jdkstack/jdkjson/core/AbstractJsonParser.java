package org.jdkstack.jdkjson.core;

import org.jdkstack.jdkjson.api.JsonParser;
import org.jdkstack.jdkjson.core.cache.LruV1;
import org.jdkstack.jdkjson.core.cache.LruV2;

public abstract class AbstractJsonParser implements JsonParser {

  protected static final LruV1<String, Object> LRUV2 = new LruV1<>(100);

  protected static final LruV2<String, Object> LRUV1 = new LruV2<>(100);
}
