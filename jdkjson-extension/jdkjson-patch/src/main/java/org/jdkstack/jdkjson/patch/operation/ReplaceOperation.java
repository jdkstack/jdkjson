package org.jdkstack.jdkjson.patch.operation;

public final class ReplaceOperation extends AbstractOperation {

  @Override
  public Object handle(Object object) {
    return "replace";
  }
}
