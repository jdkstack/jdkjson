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

  private static final long serialVersionUID = 1L;
  /** . */
  private final ReentrantLock lock = new ReentrantLock();
  /** lru最大缓存容量. */
  private final int limit;

  /**
   * 扩容因子和容量需要手动计算.
   *
   * <p>如果容量50(阈值64),扩容因子0.75f,则>48时开始扩容.
   *
   * <p>如果容量100(阈值128),扩容因子0.75f,则>96开始扩容.
   *
   * <p>例子: 容量100,因子0.8125f,阈值等于104.容量50,因子0.8125f,阈值等于52.
   *
   * <p>扩容算法比较粗,需要重新考虑.
   *
   * @param limitParam limitParam.
   * @author admin
   */
  public LruV2(final int limitParam) {
    super(limitParam, Constants.LOADFACTOR, true);
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

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @param key key.
   * @return V v.
   * @author admin
   */
  @Override
  public V get(final Object key) {
    lock.lock();
    try {
      return super.get(key);
    } finally {
      lock.unlock();
    }
  }

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @param key key.
   * @param value value.
   * @return V v.
   * @author admin
   */
  @Override
  public V put(final K key, final V value) {
    lock.lock();
    try {
      return super.put(key, value);
    } finally {
      lock.unlock();
    }
  }

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @param key key.
   * @return V v.
   * @author admin
   */
  @Override
  public V remove(final Object key) {
    lock.lock();
    try {
      return super.remove(key);
    } finally {
      lock.unlock();
    }
  }
}
