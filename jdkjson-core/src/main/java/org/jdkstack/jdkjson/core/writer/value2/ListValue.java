package org.jdkstack.jdkjson.core.writer.value2;

import java.util.List;

public class ListValue extends BaseValue {

  @Override
  public Object serialisation(Object obj) {
    return array((List) obj);
  }

  public String array(Iterable<Object> current) {
    // 创建一个list代表数组.
    StringBuilder sb = new StringBuilder();
    // 数组的开始.
    sb.append("[");
    boolean first = true;
    // 循环处理数组中的每一个元素.
    for (Object obj : current) {
      // 数组的元素可能是7种值中的某一种,此处是递归方式解析.
      Object value = value(obj);
      if (first) {
        // 第一个元素前面是""空字符串.
        first = false;
      } else {
        // 第一个元素开始,到最后一个元素之前,增加一个逗号.
        sb.append(",");
      }
      // 将json value放入数组中.
      sb.append(value);
    }
    // 数组的结束.
    sb.append("]");
    return sb.toString();
  }
}
