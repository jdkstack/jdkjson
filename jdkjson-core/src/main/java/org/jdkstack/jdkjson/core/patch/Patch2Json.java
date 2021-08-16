package org.jdkstack.jdkjson.core.patch;

import java.util.List;
import java.util.Map;
import org.jdkstack.jdkjson.api.patch.Patch;
import org.jdkstack.jdkjson.core.patch.operation.AbstractOperation;

/**
 * patch to json,将patch json文档应用到源json文档后,产生目标json文档. patch json+源json=目标json.
 *
 * <p>官方: http://jsonpatch.com/ .
 *
 * <p>规范: https://www.rfc-editor.org/rfc/rfc6902.txt .
 *
 * @author admin
 */
public class Patch2Json implements Patch {

  private List<AbstractOperation> operations;

  public Patch2Json() {
    //
  }

  @Override
  public Object apply(final Object object) {
    // 待处理的源json或者是经过patch处理的新json.
    Object obj = object;
    // 循环patch json数组.
    for (final AbstractOperation operation : operations) {
      // 调用每一个patch的apply方法,返回值为处理过后的新json.
      obj = operation.apply(obj);
    }
    // 返回最终的json,也就是最后一个patch处理过后的json.
    return obj;
  }

  public void patch(List<Map<String, String>> patchs, List<Object> source) {
    for (Map<String, String> patch : patchs) {
      // patch 方法.
      String op = patch.get("op");
      // path 需要操作什么路径(数组就是索引,对象就是key).
      String path = patch.get("path");
      // value 给path 设置的值.
      String value = patch.get("value");
      switch (op) {
        case Constants.REMOVE:
          source.remove(value);
          break;
        case Constants.ADD:
          source.add(value);
          break;
        case Constants.REPLACE:
          source.set(Integer.parseInt(path), value);
          break;
        case Constants.MOVE:
        case Constants.COPY:
        case Constants.TEST:
        default:
          break;
      }
    }
  }

  public void patch(List<Map<String, Object>> patch, Map<String, Object> map) {
    //
  }
}
