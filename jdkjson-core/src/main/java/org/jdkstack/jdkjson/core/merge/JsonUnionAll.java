package org.jdkstack.jdkjson.core.merge;

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

  public void unionAll(final Map<String, Object> source, final Map<String, Object> target) {
    //
  }

  public void unionAll(final List<Object> source, final List<Object> target) {
    //
  }

  public void unionAll(final Map<String, Object> source, final List<Object> target) {
    //
  }

  public void unionAll(final List<Object> source, final Map<String, Object> target) {
    //
  }
}
