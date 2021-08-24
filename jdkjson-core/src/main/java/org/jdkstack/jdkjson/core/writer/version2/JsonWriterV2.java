package org.jdkstack.jdkjson.core.writer.version2;

import java.util.List;
import java.util.Map;
import org.jdkstack.jdkjson.api.writer.value2.Value;
import org.jdkstack.jdkjson.core.writer.value2.BaseValue;

/**
 * .
 *
 * <p>.
 *
 * @author admin
 */
public class JsonWriterV2 extends BaseValue {

  /**
   * 序列化.
   *
   * <p>map.
   *
   * @param map map.
   * @return String String.
   * @author admin
   */
  public String map2serialize(Map<String, Object> map) {
    Value value = JSON_SERIALISATION.getValue(Map.class);
    return (String) value.serialisation(map);
  }

  /**
   * 序列化.
   *
   * <p>list.
   *
   * @param list list 对象.
   * @return String String.
   * @author admin
   */
  public String list2serialize(final Iterable<Object> list) {
    Value value = JSON_SERIALISATION.getValue(List.class);
    return (String) value.serialisation(list);
  }

  /**
   * 序列化.
   *
   * <p>list|map对象.
   *
   * @return String String.
   * @author admin
   */
  public Object serialize(Object obj) {
    return value(obj);
  }
}
