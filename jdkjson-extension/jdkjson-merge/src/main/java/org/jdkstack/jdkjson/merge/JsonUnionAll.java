package org.jdkstack.jdkjson.merge;

import java.util.List;
import java.util.Map;

/**
 * 源json和目标json合并,即并集.
 *
 * <p>.
 *
 * @author admin
 */
public class JsonUnionAll extends AbstractMerge {

  @Override
  public void execute(final Map<String, Object> source, final Map<String, Object> target) {
    //
    throw new UnsupportedOperationException("并集不去重1.");
  }

  @Override
  public void execute(final List<Object> source, final List<Object> target) {
    //
    throw new UnsupportedOperationException("并集不去重2.");
  }

  @Override
  public void execute(final Map<String, Object> source, final List<Object> target) {
    //
    throw new UnsupportedOperationException("并集不去重3.");
  }

  @Override
  public void execute(final List<Object> source, final Map<String, Object> target) {
    throw new UnsupportedOperationException("并集不去重4.");
    //
  }
}
