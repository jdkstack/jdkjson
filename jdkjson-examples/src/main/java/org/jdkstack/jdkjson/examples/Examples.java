package org.jdkstack.jdkjson.examples;

import org.jdkstack.jdkjson.core.JsonReaderV2;
import org.jdkstack.jdkjson.core.cache.LruV1;
import org.jdkstack.jdkjson.core.cache.LruV2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Examples {

  public static void main(String[] args) {
    long s = System.currentTimeMillis();
    String json =
        "{\"age\":24,\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"friends\":[{\"age\":24,\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"小明\"},{\"age\":24,\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"Tony\"},{\"age\":24,\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"陈小二\"}],\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"邵同学\"}";
    // for (int i = 0; i < 2000000; i++) {
    // JsonReaderV2 jsonStateMachine = new JsonReaderV2(json);
    // jsonStateMachine.deserialize2Map();
    // }
    StringBuilder sb = new StringBuilder(500);
    try (BufferedReader br =
        new BufferedReader(
            new InputStreamReader(
                new FileInputStream("C:\\Users\\admin\\Desktop\\json.txt"),
                StandardCharsets.UTF_8))) {
      String line = null;
      while ((line = br.readLine()) != null) {
        sb.append(line).append("\n");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    String msg = sb.toString();

    JsonReaderV2 jsonStateMachine = new JsonReaderV2(msg);

    jsonStateMachine.deserialize();
    String arr =
        "[{\"age\":24,\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"小明\"},{\"age\":24,\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"Tony\"},{\"age\":24,\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"陈小二\"}]\n";
    /*
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
    String s1 = JsonWriterV1.list2serialize(list);*/
    // System.out.println(s1);

    // List<Integer> authors = JsonPath.read(arr, "$[*].age");

    // Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);

    // List<Integer> authors1 = JsonPath.read(document, "$.friends[*].age");
    // List<Integer> authors2 = JsonPath.read(document, "$.friends[*].age");

    // ReadContext ctx = JsonPath.parse(json);

    // List<String> authorsOfBooksWithISBN = ctx.read("$.friends[*].age");
    // Configuration conf = Configuration.builder().options(Option.AS_PATH_LIST).build();

    // List<String> pathList = using(conf).parse(json).read("$..age");

    LruV1<String, String> lruV1 = new LruV1<>(100);
    LruV2<String, String> lruV2 = new LruV2<>(100);
    for (int i = 0; i < 1000; i++) {
      lruV1.put(i + "", i + "");
      lruV2.put(i + "", i + "");
    }
    lruV1.put(991 + "", 991+ "");
    String s1 = lruV1.get(985 + "");

    String s2 = lruV2.get(986 + "");
    long e = System.currentTimeMillis();
    System.out.println(e - s);
  }
}
