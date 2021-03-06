package org.jdkstack.jdkjson.jmh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.jdkstack.jdkjson.core.exception.JsonRuntimeException;
import org.jdkstack.jdkjson.core.writer.version1.JsonWriterV1;
import org.jdkstack.jdkjson.core.writer.version2.JsonWriterV2;
import org.jdkstack.jdkjson.jmh.jsoniter.JsoniterUtil;
import org.jdkstack.jdkjson.jmh.jsonsmart.JsonSmartUtil;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * How run this?
 *
 * <p>java -jar target/benchmarks.jar ".*JdkLogBenchmark.*" -f 1 -i 10 -wi 20 -bm sample -tu ns .
 *
 * @author admin
 */
@State(Scope.Benchmark)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
public class JsonSerializeBenchmark {
  private final Map<String, Object> map = new HashMap<>();
  private final List<Object> list = new ArrayList<>();

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @param args args.
   * @author admin
   */
  public static void main(final String... args) {
    final Options opt =
        new OptionsBuilder()
            .include(JsonSerializeBenchmark.class.getSimpleName())
            .threads(10)
            .forks(1)
            .build();
    try {
      new Runner(opt).run();
    } catch (final RunnerException e) {
      // Conversion into unchecked exception is also allowed.
      throw new JsonRuntimeException("", e);
    }
  }

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @author admin
   */
  @Setup(Level.Trial)
  public void setup() {
    List<Object> list1 = new ArrayList<>();
    list1.add("123");
    list1.add(false);
    Map<String, Object> map3 = new HashMap<>();
    map3.put("xxx", new ArrayList<>());
    list1.add(map3);
    List<String> list3 = new ArrayList<>();
    list3.add("222");
    list1.add(list3);
    Map<String, Object> map1 = new HashMap<>();
    map1.put("f", list1);
    map.put("f", map1);
    //
    map.put("age", Constants.AGE);
    map.put("coat", "Nike");
    map.put("true", true);
    map.put("null1", null);

    list.add(Constants.AGE);
    list.add("123123");
    list.add(true);
    list.add(map);
  }

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @author admin
   */
  @TearDown(Level.Trial)
  public void down() {
    //
  }

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @author admin
   */
  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public void jsoniterList() {
    JsoniterUtil.bean2Json(list);
  }

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @author admin
   */
  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public void jsonSmartList() {
    JsonSmartUtil.bean2Json(list);
  }

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @author admin
   */
  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public void jsoniterMap() {
    JsoniterUtil.bean2Json(map);
  }

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @author admin
   */
  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public void jsonSmartMap() {
    JsonSmartUtil.bean2Json(map);
  }

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @author admin
   */
  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public void jsonWriterV1List() {
    JsonWriterV1.list2serialize(list);
  }

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @author admin
   */
  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public void jsonWriterV1Map() {
    JsonWriterV1.map2serialize(map);
  }

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @author admin
   */
  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public void jsonWriterV2List() {
    JsonWriterV2 jsonWriterV2 = new JsonWriterV2();
    jsonWriterV2.list2serialize(list);
  }

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @author admin
   */
  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public void jsonWriterV2Map() {
    JsonWriterV2 jsonWriterV2 = new JsonWriterV2();
    jsonWriterV2.map2serialize(map);
  }

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @author admin
   */
  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public void jsonWriterV3List() {
    JsonWriterV3.list2serialize(list);
  }
  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @author admin
   */
  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public void jsonWriterV3Map() {
    JsonWriterV3.map2serialize(map);
  }

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @author admin
   */
  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public void jsonWriterV4List() {
    JsonWriterV4 jsonWriterV2 = new JsonWriterV4();
    jsonWriterV2.list2serialize(list);
  }
  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @author admin
   */
  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public void jsonWriterV4Map() {
    JsonWriterV4 jsonWriterV2 = new JsonWriterV4();
    jsonWriterV2.map2serialize(map);
  }
}
