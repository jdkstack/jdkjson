package org.jdkstack.jdkjson.patch.operation;

public class MoveOperation extends AbstractOperation {

  @Override
  public Object handle(Object object) {
    return "move";
  }
}
