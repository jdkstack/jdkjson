package org.jdkstack.jdkjson.core.reader.deserialisation;

import java.util.HashMap;
import java.util.Map;
import org.jdkstack.jdkjson.api.reader.value2.Value;
import org.jdkstack.jdkjson.core.reader.value2.CommonValue;
import org.jdkstack.jdkjson.core.reader.value2.FalseValue;
import org.jdkstack.jdkjson.core.reader.value2.ListValue;
import org.jdkstack.jdkjson.core.reader.value2.MapValue;
import org.jdkstack.jdkjson.core.reader.value2.NullValue;
import org.jdkstack.jdkjson.core.reader.value2.StringValue;
import org.jdkstack.jdkjson.core.reader.value2.TrueValue;

/**
 * 反序列化静态内存池.
 *
 * <p>存储每一个数据类型对象的实现类.
 *
 * @author admin
 */
public class JsonDeserialisationV2 {
  /** 静态内存池 . */
  private final Map<Character, Value> deserialisationV2 = new HashMap<>();

  public JsonDeserialisationV2() {
    init2();
  }

  private void init2() {
    deserialisationV2.put('{', new MapValue());
    deserialisationV2.put('[', new ListValue());
    deserialisationV2.put('"', new StringValue());
    deserialisationV2.put('F', new FalseValue());
    deserialisationV2.put('f', new FalseValue());
    deserialisationV2.put('T', new TrueValue());
    deserialisationV2.put('t', new TrueValue());
    deserialisationV2.put('N', new NullValue());
    deserialisationV2.put('n', new NullValue());
    deserialisationV2.put('c', new CommonValue());
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
    return deserialisationV2.get(c);
  }
}
