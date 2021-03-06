package org.jdkstack.jdkjson.core.writer.version1;

import java.util.List;
import java.util.Map;
import org.jdkstack.jdkjson.api.writer.value1.Value;
import org.jdkstack.jdkjson.api.writer.version2.JsonWriter;
import org.jdkstack.jdkjson.core.writer.value1.BaseValue;

/**
 * Json序列化版本1,静态方法方式实现.
 *
 * <p>多个类实现业务逻辑.
 *
 * @author admin
 */
public final class JsonWriterV1 extends BaseValue implements JsonWriter {

  private JsonWriterV1() {
    //
  }

  /**
   * 序列化.
   *
   * <p>map.
   *
   * @return String String.
   * @author admin
   */
  public static String serialize(final Object value) {
    // 默认性能最佳.
    StringBuilder sb = new StringBuilder();
    value(value, sb);
    return sb.toString();
  }

  /**
   * 序列化.
   *
   * <p>map.
   *
   * @return String String.
   * @author admin
   */
  public static String map2serialize(final Map<String, Object> map) {
    // 默认性能最佳.
    StringBuilder sb = new StringBuilder();
    Value value = JSON_SERIALISATION.getValue(Map.class);
    value.serialisation(map, sb);
    return sb.toString();
  }

  /**
   * 序列化.
   *
   * <p>map.
   *
   * @return String String.
   * @author admin
   */
  public static String list2serialize(final List<Object> list) {
    // 默认性能最佳.
    StringBuilder sb = new StringBuilder();
    Value value = JSON_SERIALISATION.getValue(List.class);
    value.serialisation(list, sb);
    return sb.toString();
  }
}
