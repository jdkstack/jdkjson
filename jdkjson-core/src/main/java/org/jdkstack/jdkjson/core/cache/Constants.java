package org.jdkstack.jdkjson.core.cache;

/**
 * This is a class description.
 *
 * <p>Another description after blank line.
 *
 * @author admin
 */
public final class Constants {
  /** 这个值可以防止hashmap进行一次扩容(阈值是初始容量+). */
  public static final float LOADFACTOR = 0.8125F;
  /** 这个值可以防止hashmap进行一次扩容(阈值是初始容量的2倍). */
  public static final float LOADFACTOR2 = 1.5625F;

  private Constants() {
    //
  }
}
