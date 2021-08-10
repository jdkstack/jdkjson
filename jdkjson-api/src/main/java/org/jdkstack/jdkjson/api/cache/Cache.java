package org.jdkstack.jdkjson.api.cache;

/**
 * 缓存接口.
 *
 * <p>LRU缓存接口.
 *
 * @param  <K> k.
 * @param  <V> v.
 * @author admin
 */
public interface Cache<K, V> {

  /**
   * 使用key获取value.
   *
   * @param key key/
   * @return V v.
   * @author admin
   */
  V get(K key);

  /**
   * 保存key和value.
   *
   * @param key key.
   * @param value value.
   * @author admin
   */
  void put(K key, V value);
}
