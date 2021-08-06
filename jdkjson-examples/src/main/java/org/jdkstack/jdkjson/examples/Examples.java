package org.jdkstack.jdkjson.examples;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.ReadContext;
import org.jdkstack.jdkjson.core.JsonWriterV1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jayway.jsonpath.JsonPath.using;

public class Examples {

  public static void main(String[] args) {
    long s = System.currentTimeMillis();
    String json =
        "{\"age\":24,\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"friends\":[{\"age\":24,\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"小明\"},{\"age\":24,\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"Tony\"},{\"age\":24,\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"陈小二\"}],\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"邵同学\"}";
    // for (int i = 0; i < 2000000; i++) {
    // JsonReaderV2 jsonStateMachine = new JsonReaderV2(json);
    // jsonStateMachine.deserialize2Map();
    // }

    Map<String, Object> map = new HashMap<>();
    map.put("age", 24);
    map.put("coat", "Nike");
    map.put("true", true);
    map.put("null1", null);

    List<Object> list = new ArrayList<>();
    list.add(123);
    list.add("ddddddd");
    list.add(true);
    list.add(null);
    String s1 = JsonWriterV1.list2serialize(list);
    System.out.println(s1);

    // List<Integer> authors = JsonPath.read(json, "$.friends[*].age");

    Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);

    List<Integer> authors1 = JsonPath.read(document, "$.friends[*].age");
    List<Integer> authors2 = JsonPath.read(document, "$.friends[*].age");

    ReadContext ctx = JsonPath.parse(json);

    List<String> authorsOfBooksWithISBN = ctx.read("$.friends[*].age");
    Configuration conf = Configuration.builder().options(Option.AS_PATH_LIST).build();

    List<String> pathList = using(conf).parse(json).read("$..age");
    long e = System.currentTimeMillis();
    System.out.println(e - s);
  }
}
