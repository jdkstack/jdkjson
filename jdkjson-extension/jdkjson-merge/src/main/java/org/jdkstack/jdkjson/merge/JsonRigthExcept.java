package org.jdkstack.jdkjson.merge;

import java.util.List;
import java.util.Map;

/**
 * 源json和目标json合并,以目标json作为基准,取两个json的公共部分,即右差集.
 *
 * <p>.
 *
 * @author admin
 */
public class JsonRigthExcept extends AbstractMerge {

  @Override
  public void execute(final Map<String, Object> source, final Map<String, Object> target) {
    //
  }

  @Override
  public void execute(final List<Object> source, final List<Object> target) {
    //
  }

  @Override
  public void execute(final Map<String, Object> source, final List<Object> target) {
    //
  }

  @Override
  public void execute(final List<Object> source, final Map<String, Object> target) {
    //
  }
}
