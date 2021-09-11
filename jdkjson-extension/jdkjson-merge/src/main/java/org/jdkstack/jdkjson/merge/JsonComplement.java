package org.jdkstack.jdkjson.merge;

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
