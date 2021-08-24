package org.jdkstack.jdkjson.core.reader.value1;

import java.util.List;
import org.jdkstack.jdkjson.api.reader.value.Value;

public class ListValue implements Value {

  @Override
  public void deserialisation(Object obj, StringBuilder sb) {
    if (obj instanceof List) {
      sb.append(obj);
    }
  }
}
