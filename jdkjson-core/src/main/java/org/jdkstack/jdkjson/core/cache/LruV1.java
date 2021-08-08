package org.jdkstack.jdkjson.core.cache;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自定义实现Lru算法(线程安全).
 *
 * <p>Lru 版本1 .
 *
 * @param <K> k.
 * @param <V> v.
 * @author admin
 */
public class LruV1<K, V> implements Cache<K, V> {
  /** 考虑用CAS代替lock. 其他数据结构代替HashMap和ArrayDeque. */
  private final ReentrantLock lock = new ReentrantLock();
  /** 保存value. */
  private final Map<K, V> map = new HashMap<>();
  /** 保存key(保存顺序,当获取元素时,将当前元素放到最前面位置). */
  private final Deque<K> queue = new ArrayDeque<>();
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
    lock.lock();
    try {
      // 添加key和value.
      V oldValue = map.put(key, value);
      // 如果多次添加同一个key. 则考虑将这个key放到最前面(插入时排序).
      if (null != oldValue) {
        // 如果有添加过.
        // 找到对应的key,并删除.
        queue.removeFirstOccurrence(key);
        // 重新添加key到最前面.
        queue.addFirst(key);
      } else {
        // 如果没有添加过.
        queue.addFirst(key);
        // 判断map容量是否超过限制.
        if (map.size() > limit) {
          // 删除一个map元素,并从队列中删除最后一个元素.
          map.remove(queue.removeLast());
        }
      }
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
  public V get(final K key) {
    lock.lock();
    try {
      V value = map.get(key);
      // 如果缓存存在.
      if (null != value) {
        // 则考虑将这个key放到最前面(获取时排序).
        // 找到对应的key,并删除.
        queue.removeFirstOccurrence(key);
        // 重新添加key到最前面.
        queue.addFirst(key);
      }
      return value;
    } finally {
      lock.unlock();
    }
  }
}
