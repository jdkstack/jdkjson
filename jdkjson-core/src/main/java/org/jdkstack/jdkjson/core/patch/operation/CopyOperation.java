package org.jdkstack.jdkjson.core.patch.operation;

public class CopyOperation extends AbstractOperation {

  @Override
  public Object handle(Object object) {
    return "copy";
  }
}
