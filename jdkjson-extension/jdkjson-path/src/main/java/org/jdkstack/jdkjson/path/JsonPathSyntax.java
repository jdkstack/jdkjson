package org.jdkstack.jdkjson.path;

/**
 * .
 *
 * <p> java:S1068 .
 *
 * @author admin
 */
@SuppressWarnings("java:S1068")
public final class JsonPathSyntax {
  /** the root object. */
  private static final char ROOT = '$';
  /** the current object. */
  private static final char CURRENT = '@';

  private static final char OPEN_SQUARE_BRACKET = '[';
  private static final char CLOSE_SQUARE_BRACKET = ']';
  private static final char OPEN_PARENTHESIS = '(';
  private static final char CLOSE_PARENTHESIS = ')';
  private static final char OPEN_BRACE = '{';
  private static final char CLOSE_BRACE = '}';
  private static final char WILDCARD = '*';
  private static final char PERIOD = '.';
  private static final char SPACE = ' ';
  private static final char TAB = '\t';
  private static final char CR = '\r';
  private static final char LF = '\n';
  private static final char BEGIN_FILTER = '?';
  private static final char COMMA = ',';
  private static final char SPLIT = ':';
  private static final char MINUS = '-';
  private static final char SINGLE_QUOTE = '\'';
  private static final char DOUBLE_QUOTE = '"';

  private JsonPathSyntax() {
    //
  }
}
