package org.jdkstack.jdkjson.core.patch;

import java.util.List;
import java.util.Map;

/**
 * json patch.
 *
 * <p>官方: http://jsonpatch.com/
 *
 * <p>规范: https://www.rfc-editor.org/rfc/rfc6902.txt
 *
 * @author admin
 */
public class JsonPatch implements Patch {

  @Override
  public void remove() {
    //
  }

  @Override
  public void replace() {
    //
  }

  @Override
  public void add() {}

  @Override
  public void move() {
    //
  }

  @Override
  public void copy() {
    //
  }

  @Override
  public void test() {
    //
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
