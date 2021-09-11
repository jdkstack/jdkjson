package org.jdkstack.jdkjson.core.patch;

import org.jdkstack.jdkjson.api.patch.Patch;

/**
 * json to patch,将源json文档和目标json文档之间的不同部分,生成patch json文档. 源json+目标json=patch json.
 *
 * <p>官方: 无.
 *
 * <p>规范: 无.
 *
 * @author admin
 */
public final class Json2Patch implements Patch {

  private Json2Patch() {
    //
  }

  @Override
  public Object apply(Object object) {
    return null;
  }
}
