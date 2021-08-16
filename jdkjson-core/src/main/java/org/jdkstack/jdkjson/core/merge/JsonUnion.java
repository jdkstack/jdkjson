package org.jdkstack.jdkjson.core.merge;

import java.util.List;
import java.util.Map;

/**
 * 源json和目标json合并,并去重即去重并集.
 *
 * <p>.
 *
 * @author admin
 */
public class JsonUnion extends AbstractMerge {

  @Override
  public void merge(final Map<String, Object> source, final Map<String, Object> target) {
    // 1. 两个map取去重并集如何做?
    // 2. value = 7个值.字符串,数字,true,false,null,{},[].
    // 3. 如果是字符串,数字,true,false,null直接合并即可. 如果是{},需要递归比较. 如果是[],也需要递归比较.
    // 4.
    // 5.
  }

  @Override
  public void merge(final List<Object> source, final List<Object> target) {
    // 1. 两个list取去重并集如何做?
    // 2. 元素的值 = 7个值.字符串,数字,true,false,null,{},[].
    // 3. 如果是字符串,数字,true,false,null直接合并即可. 如果是{},需要递归比较. 如果是[],也需要递归比较.
    // 4.
    // 5.
  }

  @Override
  public void merge(final Map<String, Object> source, final List<Object> target) {
    // 1.
  }

  @Override
  public void merge(final List<Object> source, final Map<String, Object> target) {
    // 1.
  }

  public void union(final Map<String, Object> source, final Map<String, Object> target) {
    //
  }

  public void union(final List<Object> source, final List<Object> target) {
    //
  }

  public void union(final Map<String, Object> source, final List<Object> target) {
    //
  }

  public void union(final List<Object> source, final Map<String, Object> target) {
    //
  }
}
