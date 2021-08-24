package org.jdkstack.jdkjson.core.writer.value1;

import java.util.Map;

public class MapValue extends BaseValue {

  @Override
  public void serialisation(Object obj, StringBuilder sb) {
    object((Map) obj, sb);
  }

  /**
   * 处理对象.
   *
   * <p>map.
   *
   * @author admin
   */
  public void object(final Map<?, ?> map, final StringBuilder sb) {
    //  创建一个map代表对象.
    sb.append("{");
    //
    boolean first = true;
    //
    for (Map.Entry<?, ?> entry : map.entrySet()) {
      //
      if (first) {
        first = false;
      } else {
        sb.append(",");
      }
      // 对象中的key,必须是字符串.
      sb.append("\"").append(entry.getKey()).append("\"");
      // key:value对之间必须是冒号.
      sb.append(":");
      // 对象中的value,可能是7种类型的某一种,此处是递归方式解析.
      // 将key和value添加到对象中.
      value(entry.getValue(), sb);
    }
    //
    sb.append("}");
  }
}
