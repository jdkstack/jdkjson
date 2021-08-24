package org.jdkstack.jdkjson.core.writer.value1;

import java.util.List;

public class ListValue extends BaseValue {

  @Override
  public void serialisation(Object obj, StringBuilder sb) {
    array((List) obj, sb);
  }

  /**
   * 处理数组.
   *
   * <p>map.
   *
   * @author admin
   */
  public void array(final Iterable<Object> list, final StringBuilder sb) {
    // 创建一个list代表数组.
    sb.append("[");
    //
    boolean first = true;
    //
    for (Object obj : list) {
      // 将元素添加到数组中.
      if (first) {
        first = false;
      } else {
        sb.append(",");
      }
      // 数组的元素可能是7种值中的某一种,此处是递归方式解析.
      value(obj, sb);
      //
    }
    //
    sb.append("]");
  }
}
