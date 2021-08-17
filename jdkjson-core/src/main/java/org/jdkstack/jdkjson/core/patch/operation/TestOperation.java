package org.jdkstack.jdkjson.core.patch.operation;

public final class TestOperation extends AbstractOperation {
  private String value;
  private String from;

  @Override
  public Object handle(Object object) {
    return null;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }
}
