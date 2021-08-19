package org.jdkstack.jdkjson.core.patch.operation;

public class AddOperation extends AbstractOperation {

  @Override
  public Object handle(Object object) {
    return "add";
  }
}
