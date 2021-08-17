package org.jdkstack.jdkjson.core.merge;

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

  abstract void merge(final Map<String, Object> source, final Map<String, Object> target);

  abstract void merge(final List<Object> source, final List<Object> target);

  abstract void merge(final Map<String, Object> source, final List<Object> target);

  abstract void merge(final List<Object> source, final Map<String, Object> target);
}
