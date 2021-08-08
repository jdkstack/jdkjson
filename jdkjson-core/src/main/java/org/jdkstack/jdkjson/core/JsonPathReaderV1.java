package org.jdkstack.jdkjson.core;

public class JsonPathReaderV1 {
  /** 正在处理的字符的位置. */
  private int index = 0;
  /** 待处理的json字符串. */
  private final String sequence;
  /** 待处理的json字符串长度. */
  private final int length;
  /** 换行的第一个字符的位置. */
  private int position = 0;
  /** 当前行号,当前处理第几行. */
  private int line = 1;

  public JsonPathReaderV1(final String sequence) {
    this.sequence = sequence;
    // 字符串总长度,循环到-1.
    this.length = sequence.length();
  }

  // .
  public Object value() {
    final int c = sequence.charAt(index);
    switch (c) {
      case '$':
      case '.':
      case '*':
      case '[':
      case ']':
      case ',':
      case '-':
      case '@':
      case '?':
      case '(':
      case ')':
      case '<':
      case '=':
      case '>':
      case '!':
      case '~':
      case '/':
      case '\'':
      case ':':
      default:
        return null;
    }
  }
}
