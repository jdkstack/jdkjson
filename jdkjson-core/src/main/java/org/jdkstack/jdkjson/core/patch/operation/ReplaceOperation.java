package org.jdkstack.jdkjson.core.patch.operation;

public final class ReplaceOperation extends AbstractOperation {

  @Override
  public Object handle(Object object) {
    return "replace";
  }
}
