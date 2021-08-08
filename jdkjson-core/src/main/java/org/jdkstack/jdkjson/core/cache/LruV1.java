package org.jdkstack.jdkjson.core.cache;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自定义实现Lru算法.
 *
 * <p>Lru 版本1 .
 *
 * @param <K> k.
 * @param <V> v.
 * @author admin
 */
public class LruV1<K, V> implements Cache<K, V> {
  /** . */
  private final ReentrantLock lock = new ReentrantLock();
  /** . */
  private final Map<K, V> map = new ConcurrentHashMap<>();
  /** . */
  private final Deque<K> queue = new LinkedList<>();
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
  public LruV1(final int limitParam) {
    this.limit = limitParam;
  }

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @param key key.
   * @param value value.
   * @author admin
   */
  @Override
  public void put(final K key, final V value) {
    // 添加key和value.
    V oldValue = map.put(key, value);
    // 如果多次添加同一个key. 则考虑将这个key放到最前面(插入时排序).
    if (null != oldValue) {
      // 如果有添加过.
      removeFirst(key);
    } else {
      // 如果没有添加过.
      addFirst(key);
    }
    // 判断map容量是否超过限制.
    if (map.size() > limit) {
      // 删除一个map元素,并从队列中删除最后一个元素.
      map.remove(removeLast());
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
  public V get(final K key) {
    V value = map.get(key);
    // 如果缓存存在.
    if (null != value) {
      // 则考虑将这个key放到最前面(获取时排序).
      removeFirst(key);
    }
    return value;
  }

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @param key key.
   * @author admin
   */
  private void addFirst(final K key) {
    lock.lock();
    try {
      // 在列表首添加元素.
      queue.addFirst(key);
    } finally {
      lock.unlock();
    }
  }

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @return K .
   * @author admin
   */
  private K removeLast() {
    lock.lock();
    try {
      // 删除最后一个元素.
      return queue.removeLast();
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
   * @author admin
   */
  private void removeFirst(final K key) {
    lock.lock();
    try {
      // 找到对应的key,并删除.
      queue.removeFirstOccurrence(key);
      // 重新添加key到最前面.
      queue.addFirst(key);
    } finally {
      lock.unlock();
    }
  }
}
