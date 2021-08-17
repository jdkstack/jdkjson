package org.jdkstack.jdkjson.core.pointer.verson1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jdkstack.jdkjson.api.pointer.Pointer;

public abstract class AbstractJsonPointerV1 implements Pointer {
  /** json pointer的path分解成token. */
  protected final List<String> tokens = new ArrayList<>();
  /** json pointer path. */
  protected final String path;
  /** json pointer path length. */
  protected final int length;
  /** json pointer path token size. */
  protected int size;

  protected  AbstractJsonPointerV1(final String path){
    this.path = path;
    this.length = path.length();
  }

  abstract String encode(String token);

  abstract String decode(String token);

  abstract int arrayIndex(String token);

  abstract void path();

  /**
   * json pointer从json对象中查询.
   *
   * <p>json pointer从json对象中查询.
   *
   * @param json json.
   * @return Object json value.
   * @author admin
   */
  @Override
  public Object value(final Object json) {
    // 当前json完整对象,复制一份对象,以后操作都在这个current对象的基础上.
    Object current = json;
    // 循环tokens,获取每一个token对象的json value.
    for (int idx = 1; idx < size; idx++) {
      // 从第一个token开始.
      final String token = tokens.get(idx);
      // 处理json数组.
      if (current instanceof List) {
        // 将json对象转换成list.
        final List<Object> list = (List<Object>) current;
        // 从数组中获取对应index的数据.
        current = list.get(arrayIndex(token));
      }
      // 处理json对象.
      if (current instanceof Map) {
        // 将json对象转换成map.
        final Map<String, Object> map = (Map<String, Object>) current;
        // 从对象中获取对应key的数据.
        current = map.get(token);
      }
      // 其他类型的数据忽略,因此当前的token也忽略处理.
    }
    return current;
  }
}
