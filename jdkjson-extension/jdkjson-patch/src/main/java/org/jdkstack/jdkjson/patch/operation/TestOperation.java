package org.jdkstack.jdkjson.patch.operation;

public final class TestOperation extends AbstractOperation {

  @Override
  public Object handle(Object object) {
    return "test";
  }
}
