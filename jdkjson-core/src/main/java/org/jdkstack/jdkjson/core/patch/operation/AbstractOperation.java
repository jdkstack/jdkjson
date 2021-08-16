package org.jdkstack.jdkjson.core.patch.operation;

public abstract class AbstractOperation implements Operation {
  protected String op;
  protected String path;

  @Override
  public Object apply(Object object) {
    return handle(object);
  }

  public abstract Object handle(Object object);
}
