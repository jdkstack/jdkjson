package org.jdkstack.jdkjson.jmh;

import org.jdkstack.jdkjson.core.JsonWriterV1;
import org.jdkstack.jdkjson.jmh.fastjson.FastJsonUtil;
import org.jdkstack.jdkjson.jmh.jackson.JacksonUtil;
import org.jdkstack.jdkjson.jmh.jsoniter.JsoniterUtil;
import org.jdkstack.jdkjson.jmh.jsonlib.JsonLibUtil;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
  Map<String, Object> map = new HashMap<>();
  List<Object> list = new ArrayList<>();

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @author admin
   */
  @Setup(Level.Trial)
  public void setup() {
    //
    map.put("age", 24);
    map.put("coat", "Nike");
    map.put("true", true);
    map.put("null1", null);

    list.add(123);
    list.add("123123");
    list.add(true);
    list.add(null);
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
      throw new RuntimeException("", e);
    }
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
  public void fastJsonList() {
    FastJsonUtil.bean2Json(list);
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
  public void jacksonList() {
    JacksonUtil.bean2Json(list);
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
  public void jsonLibList() {
    JsonLibUtil.bean2JsonList(list);
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
  public void jsonWriterV2Map() {
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
  public void fastJsonMap() {
    FastJsonUtil.bean2Json(map);
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
  public void jacksonMap() {
    JacksonUtil.bean2Json(map);
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
  public void jsonLibMap() {
    JsonLibUtil.bean2JsonMap(map);
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
}
