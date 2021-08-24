package org.jdkstack.jdkjson.core.reader.value1;

import java.util.HashMap;
import java.util.Map;
import org.jdkstack.jdkjson.api.reader.value1.Value;

/**
 * 反序列化静态内存池.
 *
 * <p>存储每一个数据类型对象的实现类.
 *
 * @author admin
 */
public class JsonDeserialisation {
  /** 静态内存池 . */
  private final Map<Character, Value> deserialisation = new HashMap<>();

  public JsonDeserialisation() {
    init();
  }

  private void init() {
    deserialisation.put('{', new MapValue());
    deserialisation.put('[', new ListValue());
    deserialisation.put('"', new StringValue());
    deserialisation.put('F', new FalseValue());
    deserialisation.put('f', new FalseValue());
    deserialisation.put('T', new TrueValue());
    deserialisation.put('t', new TrueValue());
    deserialisation.put('N', new NullValue());
    deserialisation.put('n', new NullValue());
    deserialisation.put('c', new CommonValue());
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
  public Value getValue(Character c) {
    return deserialisation.get(c);
  }
}
