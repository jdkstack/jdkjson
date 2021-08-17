package org.jdkstack.jdkjson.core.merge;

import java.util.List;
import java.util.Map;

/**
 * 源json和目标json合并,取两个json的公共部分,即交集.
 *
 * <p>.
 *
 * @author admin
 */
public class JsonIntersection extends AbstractMerge {

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

  public void intersection(final Map<String, Object> source, final Map<String, Object> target) {
    //
  }

  public void intersection(final List<Object> source, final List<Object> target) {
    //
  }

  public void intersection(final List<Object> source, final Map<String, Object> target) {
    //
  }

  public void intersection(final Map<String, Object> source, final List<Object> target) {
    //
  }
}
