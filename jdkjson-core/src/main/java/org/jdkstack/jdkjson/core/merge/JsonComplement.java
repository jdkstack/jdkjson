package org.jdkstack.jdkjson.core.merge;

import java.util.List;
import java.util.Map;

/**
 * 源json和目标json合并,去掉差集部分后,剩余部分即补集.
 *
 * <p>.
 *
 * @author admin
 */
public class JsonComplement extends AbstractMerge {

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

  public void complement(final Map<String, Object> source, final Map<String, Object> target) {
    //
  }

  public void complement(final List<Object> source, final List<Object> target) {
    //
  }

  public void complement(final Map<String, Object> source, final List<Object> target) {
    //
  }

  public void complement(final List<Object> source, final Map<String, Object> target) {
    //
  }
}
