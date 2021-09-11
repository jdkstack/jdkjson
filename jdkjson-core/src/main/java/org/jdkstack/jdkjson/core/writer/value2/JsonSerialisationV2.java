package org.jdkstack.jdkjson.core.writer.value2;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jdkstack.jdkjson.api.writer.value2.Value;

/**
 * 序列化静态内存池.
 *
 * <p>存储每一个数据类型对象的实现类.
 *
 * @author admin
 */
public class JsonSerialisationV2 {
  /** 静态内存池 . */
  private final Map<Class<?>, Value> serialisationV2 = new HashMap<>();

  public JsonSerialisationV2() {
    init2();
  }

  private void init2() {
    serialisationV2.put(String.class, new StringValue());
    serialisationV2.put(List.class, new ListValue());
    serialisationV2.put(Map.class, new MapValue());
    serialisationV2.put(ArrayList.class, new ListValue());
    serialisationV2.put(HashMap.class, new MapValue());
    serialisationV2.put(Integer.class, new CommonValue());
    serialisationV2.put(Double.class, new CommonValue());
    serialisationV2.put(Long.class, new CommonValue());
    serialisationV2.put(BigInteger.class, new CommonValue());
    serialisationV2.put(BigDecimal.class, new CommonValue());
    serialisationV2.put(Short.class, new CommonValue());
    serialisationV2.put(Boolean.class, new CommonValue());
    serialisationV2.put(Object.class, new CommonValue());
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
    return serialisationV2.get(cls);
  }
}
