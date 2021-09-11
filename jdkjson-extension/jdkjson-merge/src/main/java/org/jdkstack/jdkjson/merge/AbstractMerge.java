package org.jdkstack.jdkjson.merge;

import java.util.List;
import java.util.Map;
import org.jdkstack.jdkjson.api.merge.Merge;

/**
 * json patch merge.
 *
 * <p>官方: http://jsonpatch.com/
 *
 * <p>规范: https://www.rfc-editor.org/rfc/rfc7396.txt
 *
 * @author admin
 */
public abstract class AbstractMerge implements Merge {

  @Override
  public void merge(final Map<String, Object> source, final Map<String, Object> target) {
    // 1. 两个map取去重并集如何做?
    // 2. value = 7个值.字符串,数字,true,false,null,{},[].
    // 3. 如果是字符串,数字,true,false,null直接合并即可. 如果是{},需要递归比较. 如果是[],也需要递归比较.
    // 4.
    // 5.
    execute(source, target);
  }

  @Override
  public void merge(final List<Object> source, final List<Object> target) {
    // 1. 两个list取去重并集如何做?
    // 2. 元素的值 = 7个值.字符串,数字,true,false,null,{},[].
    // 3. 如果是字符串,数字,true,false,null直接合并即可. 如果是{},需要递归比较. 如果是[],也需要递归比较.
    // 4.
    // 5.
    execute(source, target);
  }

  @Override
  public void merge(final Map<String, Object> source, final List<Object> target) {
    // 1.
    execute(source, target);
  }

  @Override
  public void merge(final List<Object> source, final Map<String, Object> target) {
    // 1.
    execute(source, target);
  }

  abstract void execute(final Map<String, Object> source, final Map<String, Object> target);

  abstract void execute(final List<Object> source, final List<Object> target);

  abstract void execute(final Map<String, Object> source, final List<Object> target);

  abstract void execute(final List<Object> source, final Map<String, Object> target);
}
