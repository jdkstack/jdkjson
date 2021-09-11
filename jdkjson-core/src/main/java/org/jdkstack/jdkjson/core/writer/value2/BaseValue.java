package org.jdkstack.jdkjson.core.writer.value2;

import java.util.List;
import java.util.Map;
import org.jdkstack.jdkjson.api.writer.value2.Value;
import org.jdkstack.jdkjson.core.cache.LruV1;
import org.jdkstack.jdkjson.core.reader.Constants;
import org.jdkstack.jdkjson.core.writer.serialisation.JsonSerialisationV2;

public class BaseValue implements Value {

  protected static final JsonSerialisationV2 JSON_SERIALISATION = new JsonSerialisationV2();
  /** LRU缓存类. */
  protected static final LruV1<Object, String> LRUV1 = new LruV1<>(Constants.CAPACITY);
  /** LRU缓存类. */
  protected static final LruV1<Map<String, Object>, String> LRUV1_MAP =
      new LruV1<>(Constants.CAPACITY);
  /** LRU缓存类. */
  protected static final LruV1<List<Object>, String> LRUV1_LIST = new LruV1<>(Constants.CAPACITY);

  @Override
  public Object serialisation(Object obj) {
    return value(obj);
  }

  /**
   * json所有value值.
   *
   * <p>json字符串map.
   *
   * @return Object Object.
   * @author admin
   */
  public Object value(Object obj) {
    // json value值很可能是null的,比如数组的元素或者对象的value.
    if (obj == null) {
      // 如果是空,直接拼接即可.
      return null;
    } else {
      // 获取当前值的class运行时类型,采用此方式的原因是有很大的性能提升.
      Class<?> clz = obj.getClass();
      // 从缓存中获取具体执行类.
      Value value = JSON_SERIALISATION.getValue(clz);
      // 执行具体json value值的业务逻辑.
      return value.serialisation(obj);
    }
  }
}
