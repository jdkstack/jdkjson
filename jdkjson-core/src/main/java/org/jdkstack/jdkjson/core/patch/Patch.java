package org.jdkstack.jdkjson.core.patch;

public interface Patch {
  void remove();

  void replace();

  void add();

  void move();

  void copy();

  void test();
}
