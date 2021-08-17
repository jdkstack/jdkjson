package org.jdkstack.jdkjson.core.reader.value;

import java.util.Map;
import org.jdkstack.jdkjson.api.reader.value.Value;

public class MapValue implements Value {

  @Override
  public void deserialisation(Object obj, StringBuilder sb) {
    if (obj instanceof Map) {
      sb.append(obj);
    }
  }
}
