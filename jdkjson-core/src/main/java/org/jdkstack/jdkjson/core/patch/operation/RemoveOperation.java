package org.jdkstack.jdkjson.core.patch.operation;

public final class RemoveOperation extends AbstractOperation {

  @Override
  public Object handle(Object object) {
    return "remove";
  }
}
