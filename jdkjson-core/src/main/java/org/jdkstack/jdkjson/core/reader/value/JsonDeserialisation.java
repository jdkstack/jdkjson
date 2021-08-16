package org.jdkstack.jdkjson.core.reader.value;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    deserialisation.put(int[].class, new BooleanValue());
    deserialisation.put(Integer[].class, new BooleanValue());
    deserialisation.put(short[].class, new BooleanValue());
    deserialisation.put(Short[].class, new BooleanValue());
    deserialisation.put(long[].class, new BooleanValue());
    deserialisation.put(Long[].class, new BooleanValue());
    deserialisation.put(byte[].class, new BooleanValue());
    deserialisation.put(Byte[].class, new BooleanValue());
    deserialisation.put(char[].class, new BooleanValue());
    deserialisation.put(Character[].class, new BooleanValue());
    deserialisation.put(float[].class, new BooleanValue());
    deserialisation.put(Float[].class, new BooleanValue());
    deserialisation.put(double[].class, new BooleanValue());
    deserialisation.put(Double[].class, new BooleanValue());
    deserialisation.put(boolean[].class, new BooleanValue());
    deserialisation.put(Boolean[].class, new BooleanValue());
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
