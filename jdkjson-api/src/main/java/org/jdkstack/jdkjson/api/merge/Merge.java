package org.jdkstack.jdkjson.api.merge;

import java.util.List;
import java.util.Map;

public interface Merge {

  void merge(final Map<String, Object> source, final Map<String, Object> target);

  void merge(final List<Object> source, final List<Object> target);

  void merge(final Map<String, Object> source, final List<Object> target);

  void merge(final List<Object> source, final Map<String, Object> target);
}
