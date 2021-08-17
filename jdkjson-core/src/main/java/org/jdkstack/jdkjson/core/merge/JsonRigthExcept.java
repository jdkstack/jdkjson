package org.jdkstack.jdkjson.core.merge;

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

  public void rightExcept(final Map<String, Object> source, final Map<String, Object> target) {
    //
  }

  public void rightExcept(final List<Object> source, final List<Object> target) {
    //
  }

  public void rightExcept(final Map<String, Object> source, final List<Object> target) {
    //
  }

  public void rightExcept(final List<Object> source, final Map<String, Object> target) {
    //
  }
}
