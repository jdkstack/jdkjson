package org.jdkstack.jdkjson.core.writer.value1;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jdkstack.jdkjson.api.writer.value1.Value;

/**
 * 序列化静态内存池.
 *
 * <p>存储每一个数据类型对象的实现类.
 *
 * @author admin
 */
public class JsonSerialisationV1 {
  /** 静态内存池 . */
  private final Map<Class<?>, Value> serialisationV1 = new HashMap<>();

  public JsonSerialisationV1() {
    init1();
  }

  private void init1() {
    serialisationV1.put(String.class, new StringValue());
    serialisationV1.put(List.class, new ListValue());
    serialisationV1.put(Map.class, new MapValue());
    serialisationV1.put(ArrayList.class, new ListValue());
    serialisationV1.put(HashMap.class, new MapValue());
    serialisationV1.put(Integer.class, new CommonValue());
    serialisationV1.put(Double.class, new CommonValue());
    serialisationV1.put(Long.class, new CommonValue());
    serialisationV1.put(BigInteger.class, new CommonValue());
    serialisationV1.put(BigDecimal.class, new CommonValue());
    serialisationV1.put(Short.class, new CommonValue());
    serialisationV1.put(Boolean.class, new CommonValue());
    serialisationV1.put(Object.class, new CommonValue());
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
    return serialisationV1.get(cls);
  }
}
