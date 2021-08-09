package org.jdkstack.jdkjson.jmh;

import org.jdkstack.jdkjson.core.JsonReaderV1;
import org.jdkstack.jdkjson.jmh.jsonparser.JsonParserUtil;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
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

import java.util.Random;
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
public class JsonDeserializeBenchmark {

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
            .include(JsonDeserializeBenchmark.class.getSimpleName())
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
  public void jsonParserV1() {
    String list =
            "[{\"age\":"
                    + new Random().nextInt(100)
                    + ",\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"小明\"},{\"age\":24,\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"Tony\"},{\"age\":24,\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"陈小二\"}]";

    JsonReaderV1.deserialize(list);
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
  public void jsonWriterV2() {
    String list =
        "[{\"age\":"
            + new Random().nextInt(100)
            + ",\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"小明\"},{\"age\":24,\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"Tony\"},{\"age\":24,\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"陈小二\"}]";

    JsonParserUtil.json2Bean(list);
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
    String map =
        "{\"age\":"
            + new Random().nextInt(100)
            + ",\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"friends\":[{\"age\":24,\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"小明\"},{\"age\":24,\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"Tony\"},{\"age\":24,\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"陈小二\"}],\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"邵同学\"}";

    JsonParserUtil.json2BeanMap(map);
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
  public void jsonParserV1Map() {
    String map =
            "{\"age\":"
                    + new Random().nextInt(100)
                    + ",\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"friends\":[{\"age\":24,\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"小明\"},{\"age\":24,\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"Tony\"},{\"age\":24,\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"陈小二\"}],\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"邵同学\"}";

    JsonReaderV1.deserialize2Map(map);
  }

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @author admin
   */
  /*  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public void jsoniterMap() {
    JsoniterUtil.json2Bean(map, Map.class);
  }*/

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @author admin
   */
  /*  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public void fastJsonMap() {
    FastJsonUtil.json2Bean(map, Map.class);
  }*/

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @author admin
   */
  /*  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public void jacksonMap() {
    JacksonUtil.json2Bean(map, Map.class);
  }*/

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @author admin
   */
  /*  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public void jsonLibMap() {
    JsonLibUtil.bean2JsonMap(map);
  }*/

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @author admin
   */
  /*
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void jsonSmartMap() {
      JsonSmartUtil.json2Bean(map);
    }
  */

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
  public void jsonParserV1List() {
    String list =
        "[{\"age\":"
            + new Random().nextInt(100)
            + ",\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"小明\"},{\"age\":24,\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"Tony\"},{\"age\":24,\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"陈小二\"}]";

    JsonReaderV1.deserialize2List(list);
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
    String list =
        "[{\"age\":"
            + new Random().nextInt(100)
            + ",\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"小明\"},{\"age\":24,\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"Tony\"},{\"age\":24,\"clothes\":{\"coat\":\"Nike\",\"trousers\":\"adidas\",\"shoes\":\"安踏\"},\"fullName\":{},\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"name\":\"陈小二\"}]";

    JsonParserUtil.json2BeanList(list);
  }

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @author admin
   */
  /*
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void jsoniterList() {
      JsoniterUtil.json2Bean(list, List.class);
    }
  */

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @author admin
   */
  /*
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void fastJsonList() {
      FastJsonUtil.json2Bean(list, List.class);
    }
  */

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @author admin
   */
  /*
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void jacksonList() {
      JacksonUtil.json2Bean(list, List.class);
    }
  */

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @author admin
   */
  /*
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void jsonLibList() {
      JsonLibUtil.bean2JsonList(list);
    }
  */

  /**
   * This is a method description.
   *
   * <p>Another description after blank line.
   *
   * @author admin
   */
  /*  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public void jsonSmartList() {
    JsonSmartUtil.json2Bean(list);
  }*/
}
