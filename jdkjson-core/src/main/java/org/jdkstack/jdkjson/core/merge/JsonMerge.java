package org.jdkstack.jdkjson.core.merge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * json patch merge.
 *
 * <p>官方: http://jsonpatch.com/
 *
 * <p>规范: https://www.rfc-editor.org/rfc/rfc7396.txt
 *
 * @author admin
 */
public class JsonMerge implements Merge {
  // 交集(公共部分)
  public void intersection(final Map<String, Object> source, final Map<String, Object> target) {
    //
  }

  public void intersection(final List<Object> source, final List<Object> target) {
    //
  }

  public void intersection(final List<Object> source, final Map<String, Object> target) {
    //
  }

  public void intersection(final Map<String, Object> source, final List<Object> target) {
    //
  }

  // 右差集.
  public void rightIntersection(
      final Map<String, Object> source, final Map<String, Object> target) {
    //
  }

  public void rightIntersection(final List<Object> source, final List<Object> target) {
    //
  }

  public void rightIntersection(final Map<String, Object> source, final List<Object> target) {
    //
  }

  public void rightIntersection(final List<Object> source, final Map<String, Object> target) {
    //
  }

  // 左差集.
  public void leftIntersection(final Map<String, Object> source, final Map<String, Object> target) {
    //
  }

  public void leftIntersection(final List<Object> source, final List<Object> target) {
    //
  }

  public void leftIntersection(final Map<String, Object> source, final List<Object> target) {
    //
  }

  public void leftIntersection(final List<Object> source, final Map<String, Object> target) {
    //
  }

  // 并集.
  public void unionAll(final Map<String, Object> source, final Map<String, Object> target) {
    //
  }

  public void unionAll(final List<Object> source, final List<Object> target) {
    //
  }

  public void unionAll(final Map<String, Object> source, final List<Object> target) {
    //
  }

  public void unionAll(final List<Object> source, final Map<String, Object> target) {
    //
  }

  // 去重并集(去重公共部分后的全部).
  public void union(final Map<String, Object> source, final Map<String, Object> target) {
    //
  }

  public void union(final List<Object> source, final List<Object> target) {
    //
  }

  public void union(final Map<String, Object> source, final List<Object> target) {
    //
  }

  public void union(final List<Object> source, final Map<String, Object> target) {
    //
  }

  // 补集.
  public void complement(final Map<String, Object> source, final Map<String, Object> target) {
    //
  }

  public void complement(final List<Object> source, final List<Object> target) {
    //
  }

  public void complement(final Map<String, Object> source, final List<Object> target) {
    //
  }

  public void complement(final List<Object> source, final Map<String, Object> target) {
    //
  }

  public static void main(String... args) {

    JsonMerge jsonMergePatch = new JsonMerge();
    jsonMergePatch.intersection(new ArrayList<>(), new HashMap<>());
    jsonMergePatch.intersection(new HashMap<>(), new ArrayList<>());
    // A系统账号
    List<String> accountList1 =
        Arrays.asList("11111111111", "22222222222", "33333333333", "44444444444", "77777777777");
    // B系统账号
    List<String> accountList2 =
        Arrays.asList("55555555555", "11111111111", "22222222222", "66666666666");

    // 账号交集
    List<String> intersectionAccount =
        accountList1.stream().filter(accountList2::contains).collect(Collectors.toList());
    System.out.println("交集:" + intersectionAccount.toString());

    // 账号去重并集
    List<String> deduplicationUnionAccount =
        Stream.of(accountList1, accountList2)
            .flatMap(List::stream)
            .distinct()
            .collect(Collectors.toList());
    System.out.println("去重并集:" + deduplicationUnionAccount.toString());

    // A系统账号账号差集(accountList1 - accountList2)
    List<String> subtractionAccount1 =
        accountList1.stream()
            .filter(account -> !accountList2.contains(account))
            .collect(Collectors.toList());
    System.out.println("A系统账号差集:" + subtractionAccount1.toString());

    // B系统账号差集(accountList2 - accountList1)
    List<String> subtractionAccount2 =
        accountList2.stream()
            .filter(account -> !accountList1.contains(account))
            .collect(Collectors.toList());
    System.out.println("B系统账号差集:" + subtractionAccount2.toString());

    // 账号去重并集
    List<String> deduplicationUnionAccount22 =
        Stream.of(accountList1, accountList2)
            .flatMap(List::stream)
            .distinct()
            .collect(Collectors.toList());

    // 账号交集
    List<String> intersectionAccount22 =
        accountList1.stream().filter(accountList2::contains).collect(Collectors.toList());

    // 账号补集(并集-交集)
    List<String> complementAccount =
        deduplicationUnionAccount22.stream()
            .filter(account -> !intersectionAccount22.contains(account))
            .collect(Collectors.toList());
    System.out.println("补集：" + complementAccount.toString());
  }
}
