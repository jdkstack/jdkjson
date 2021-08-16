package org.jdkstack.jdkjson.core.writer.value;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jdkstack.jdkjson.api.writer.value.Value;

/**
 * 序列化静态内存池.
 *
 * <p>存储每一个数据类型对象的实现类.
 *
 * @author admin
 */
public class JsonSerialisation {
  /** 静态内存池 . */
  private final Map<Class<?>, Value> serialisation = new HashMap<>();

  public JsonSerialisation() {
    serialisation.put(String.class, new StringValue());
    serialisation.put(List.class, new ListValue());
    serialisation.put(Map.class, new MapValue());
    serialisation.put(ArrayList.class, new ListValue());
    serialisation.put(HashMap.class, new MapValue());
    serialisation.put(Integer.class, new NumberValue());
    serialisation.put(Double.class, new NumberValue());
    serialisation.put(Long.class, new NumberValue());
    serialisation.put(BigInteger.class, new NumberValue());
    serialisation.put(BigDecimal.class, new NumberValue());
    serialisation.put(Short.class, new NumberValue());
    serialisation.put(Boolean.class, new BooleanValue());
  }

  /**
   * 从序列化内存池中获取对应的对象.
   *
   * <p>获取序列化对象.
   *
   * @param cls cls.
   * @return Value value.
   * @author admin
   */
  public Value getValue(Class<?> cls) {
    return serialisation.get(cls);
  }
}
