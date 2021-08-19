package org.jdkstack.jdkjson.core.patch.operation;

public class MoveOperation extends AbstractOperation {

  @Override
  public Object handle(Object object) {
    return "move";
  }
}
