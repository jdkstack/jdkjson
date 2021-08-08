package org.jdkstack.jdkjson.core.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * LinkedHashMap实现Lru算法(线程安全).
 *
 * <p>Lru 版本2 .
 *
 * @param <K> k.
 * @param <V> v.
 * @author admin
 */
public class LruV2<K, V> extends LinkedHashMap<K, V> {
  /** . */
  private final ReentrantLock lock = new ReentrantLock();
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

  @Override
  public V get(Object key) {
    lock.lock();
    try {
      return super.get(key);
    } finally {
      lock.unlock();
    }
  }

  @Override
  public V put(K key, V value) {
    lock.lock();
    try {
      return super.put(key, value);
    } finally {
      lock.unlock();
    }
  }

  @Override
  public V remove(Object key) {
    lock.lock();
    try {
      return super.remove(key);
    } finally {
      lock.unlock();
    }
  }
}
