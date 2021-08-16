package org.jdkstack.jdkjson.core.reader.value;

import java.util.HashMap;
import java.util.Map;
import org.jdkstack.jdkjson.api.reader.value.Value;

/**
 * 反序列化静态内存池.
 *
 * <p>存储每一个数据类型对象的实现类.
 *
 * @author admin
 */
public class JsonDeserialisation {
  /** 静态内存池 . */
  private final Map<Class<?>, Value> deserialisation = new HashMap<>();

  public JsonDeserialisation() {
    init();
  }

  private void init() {
    deserialisation.put(int[].class, new CommonValue());
    deserialisation.put(Integer[].class, new CommonValue());
    deserialisation.put(short[].class, new CommonValue());
    deserialisation.put(Short[].class, new CommonValue());
    deserialisation.put(long[].class, new CommonValue());
    deserialisation.put(Long[].class, new CommonValue());
    deserialisation.put(byte[].class, new CommonValue());
    deserialisation.put(Byte[].class, new CommonValue());
    deserialisation.put(char[].class, new CommonValue());
    deserialisation.put(Character[].class, new CommonValue());
    deserialisation.put(float[].class, new CommonValue());
    deserialisation.put(Float[].class, new CommonValue());
    deserialisation.put(double[].class, new CommonValue());
    deserialisation.put(Double[].class, new CommonValue());
    deserialisation.put(boolean[].class, new CommonValue());
    deserialisation.put(Boolean[].class, new CommonValue());
  }

  /**
   * 从反序列化内存池中获取对应的对象.
   *
   * <p>获取反序列化对象.
   *
   * @param cls cls.
   * @return Value value.
   * @author admin
   */
  public Value getValue(Class<?> cls) {
    return deserialisation.get(cls);
  }
}
