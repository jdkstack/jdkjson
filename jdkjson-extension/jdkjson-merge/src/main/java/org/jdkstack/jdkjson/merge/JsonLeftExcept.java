package org.jdkstack.jdkjson.merge;

import java.util.List;
import java.util.Map;

/**
 * 源json和目标json合并,以源json作为基准,取两个json的公共部分,即左差集.
 *
 * <p>.
 *
 * @author admin
 */
public class JsonLeftExcept extends AbstractMerge {

  @Override
  public void execute(final Map<String, Object> source, final Map<String, Object> target) {
    //
    throw new UnsupportedOperationException("左差集1.");
  }

  @Override
  public void execute(final List<Object> source, final List<Object> target) {
    //
    throw new UnsupportedOperationException("左差集2.");
  }

  @Override
  public void execute(final Map<String, Object> source, final List<Object> target) {
    //
    throw new UnsupportedOperationException("左差集3.");
  }

  @Override
  public void execute(final List<Object> source, final Map<String, Object> target) {
    //
    throw new UnsupportedOperationException("左差集4.");
  }
}
