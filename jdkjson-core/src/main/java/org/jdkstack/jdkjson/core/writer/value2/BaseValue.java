package org.jdkstack.jdkjson.core.writer.value2;

import org.jdkstack.jdkjson.api.writer.value2.Value;

public class BaseValue implements Value {

  protected static final JsonSerialisation JSON_SERIALISATION = new JsonSerialisation();

  @Override
  public Object serialisation(Object obj) {
    return value(obj);
  }

  /**
   * json所有value值.
   *
   * <p>json字符串map.
   *
   * @return Object Object.
   * @author admin
   */
  public Object value(Object obj) {
    // json value值很可能是null的,比如数组的元素或者对象的value.
    if (obj == null) {
      // 如果是空,直接拼接即可.
      return null;
    } else {
      // 获取当前值的class运行时类型,采用此方式的原因是有很大的性能提升.
      Class<?> clz = obj.getClass();
      // 从缓存中获取具体执行类.
      Value value = JSON_SERIALISATION.getValue(clz);
      // 执行具体json value值的业务逻辑.
      return value.serialisation(obj);
    }
  }
}
