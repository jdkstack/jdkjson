package org.jdkstack.jdkjson.core.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LinkedHashMap实现Lru算法.
 *
 * <p>Lru 版本2 .
 *
 * @param <K> k.
 * @param <V> v.
 * @author admin
 */
public class LruV2<K, V> extends LinkedHashMap<K, V> {

  /** lru最大缓存容量. */
  private final int limit;

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @param limitParam limitParam.
   * @author admin
   */
  public LruV2(final int limitParam) {
    super(limitParam, 0.75f, true);
    this.limit = limitParam;
  }

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @param eldest eldest.
   * @return boolean b.
   * @author admin
   */
  @Override
  public boolean removeEldestEntry(final Map.Entry<K, V> eldest) {
    return size() > limit;
  }
}
