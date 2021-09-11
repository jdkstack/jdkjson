package org.jdkstack.jdkjson.patch.operation;

public class CopyOperation extends AbstractOperation {

  @Override
  public Object handle(Object object) {
    return "copy";
  }
}
