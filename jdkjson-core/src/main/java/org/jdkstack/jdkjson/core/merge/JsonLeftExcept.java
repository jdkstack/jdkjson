package org.jdkstack.jdkjson.core.merge;

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
  public void merge(final Map<String, Object> source, final Map<String, Object> target) {
    // 1.
  }

  @Override
  public void merge(final List<Object> source, final List<Object> target) {
    // 1.
  }

  @Override
  public void merge(final Map<String, Object> source, final List<Object> target) {
    // 1.
  }

  @Override
  public void merge(final List<Object> source, final Map<String, Object> target) {
    // 1.
  }

  public void leftExcept(final Map<String, Object> source, final Map<String, Object> target) {
    //
  }

  public void leftExcept(final List<Object> source, final List<Object> target) {
    //
  }

  public void leftExcept(final Map<String, Object> source, final List<Object> target) {
    //
  }

  public void leftExcept(final List<Object> source, final Map<String, Object> target) {
    //
  }
}
