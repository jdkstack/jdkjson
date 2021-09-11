package org.jdkstack.jdkjson.core.reader.deserialisation;

import java.util.HashMap;
import java.util.Map;
import org.jdkstack.jdkjson.api.reader.value1.Value;
import org.jdkstack.jdkjson.core.reader.value1.CommonValue;
import org.jdkstack.jdkjson.core.reader.value1.FalseValue;
import org.jdkstack.jdkjson.core.reader.value1.ListValue;
import org.jdkstack.jdkjson.core.reader.value1.MapValue;
import org.jdkstack.jdkjson.core.reader.value1.NullValue;
import org.jdkstack.jdkjson.core.reader.value1.StringValue;
import org.jdkstack.jdkjson.core.reader.value1.TrueValue;

/**
 * 反序列化静态内存池.
 *
 * <p>存储每一个数据类型对象的实现类.
 *
 * @author admin
 */
public class JsonDeserialisationV1 {
  /** 静态内存池 . */
  private final Map<Character, Value> deserialisationV1 = new HashMap<>();

  public JsonDeserialisationV1() {
    init1();
  }

  private void init1() {
    deserialisationV1.put('{', new MapValue());
    deserialisationV1.put('[', new ListValue());
    deserialisationV1.put('"', new StringValue());
    deserialisationV1.put('F', new FalseValue());
    deserialisationV1.put('f', new FalseValue());
    deserialisationV1.put('T', new TrueValue());
    deserialisationV1.put('t', new TrueValue());
    deserialisationV1.put('N', new NullValue());
    deserialisationV1.put('n', new NullValue());
    deserialisationV1.put('c', new CommonValue());
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
    return deserialisationV1.get(c);
  }
}
