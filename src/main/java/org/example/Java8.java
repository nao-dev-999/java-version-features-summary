package org.example;

import java.sql.Timestamp;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.*;

/**
 * Java 8 (2014) ラムダ式の導入 Stream APIの導入 Optionalクラスの導入 java.util.functionパッケージの導入 java.timeパッケージの導入
 */
public class Java8 {

    static void main(String[] args) {
        Java8 java8 = new Java8();
        java8.functionalInterface();
        java8.lambdaExpression();
        java8.streamApi();
        java8.optionalClass();
        java8.timeApi();
    }

    // FunctionalInterfaceの例
    private void functionalInterface() {
        Function<String, Integer> function = String::length;
        System.out.println(function.apply("Hello"));

        Function<Integer, String> intToStr = i -> "数値: " + i;
        intToStr.apply(42); // "数値: 42"

        // Consumer<T>：T を受け取り何も返さない（副作用）
        Consumer<String> print = System.out::println;
        print.accept("Hello"); // Hello

        Consumer<List<String>> clear = List::clear;

        // Supplier<T>：何も受け取らず T を返す
        Supplier<String> greeting = () -> "Hello!";
        greeting.get(); // "Hello!"

        Supplier<List<String>> newList = ArrayList::new;
        newList.get(); // 新しいArrayList

        // Predicate<T>：T を受け取り boolean を返す
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<Integer> isEven = n -> n % 2 == 0;

        isEmpty.test(""); // true
        isEven.test(4); // true

        // BiFunction<T, U, R>：T と U を受け取り R を返す
        BiFunction<String, Integer, String> repeat = String::repeat;
        // ※ Stringにrepeat(int)があるのでメソッド参照可
        BiFunction<String, String, String> concat = (a, b) -> a + b;
        concat.apply("Hello", " World"); // "Hello World"

        // BiConsumer<T, U>：T と U を受け取り何も返さない
        BiConsumer<String, Integer> biPrint = (s, n) -> System.out.println(s + ": " + n);
        biPrint.accept("年齢", 30); // 年齢: 30

        // BiPredicate<T, U>：T と U を受け取り boolean を返す
        BiPredicate<String, String> startsWith = String::startsWith;
        startsWith.test("Hello World", "Hello"); // true
    }

    // ラムダ式の導入
    private void lambdaExpression() {
        Runnable runnable = () -> System.out.println("Hello, World");
        runnable.run();
    }

    // Stream APIの導入
    private void streamApi() {
        String[] array = {"a", "b", "c", "d", "e"};
        long count = java.util.Arrays.stream(array).count();
        System.out.println(count);
    }

    // Optionalクラスの導入
    private void optionalClass() {
        String value = null;
        Optional<String> optional = Optional.ofNullable(value);
        System.out.println(optional.orElse("default value"));
    }

    private void timeApi() {
        // 役割ごとに型を使い分ける
        // Instant     　→　タイムゾーンを持たない（UTC） DBへの保存・ログ・API通信
        // ZonedDateTime → タイムゾーンをまたぐ業務ロジック
        // LocalDateTime → タイムゾーンが不要な業務ロジック
        // LocalDate  　 → 日付のみ（誕生日・営業日）
        // LocalTime 　  → 時刻のみ（営業時間・締め切り時刻）

        // エポック秒（1970-01-01T00:00:00Z）からの経過時間
        Instant instant = Instant.now();
        Instant epoch = Instant.EPOCH; // 1970-01-01T00:00:00Z

        instant.getEpochSecond(); // Unix時間（秒）
        instant.toEpochMilli(); // Unix時間（ミリ秒）

        // Instantから変換
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("Asia/Tokyo"));
        System.out.println(zonedDateTime); // 2024-01-15T23:45:00000+09:00[Asia/Tokyo]

        // DBのtimestampとのやり取りに便利
        Timestamp ts = Timestamp.from(Instant.now());
        Instant from = ts.toInstant();

        // Clockを注入してテスト可能にする
        // 本番
        LocalDate prod = LocalDate.now(Clock.systemDefaultZone());
        // テスト（固定時刻）
        LocalDate test =
                LocalDate.now(
                        Clock.fixed(
                                Instant.parse("2024-06-01T00:00:00Z"), ZoneId.of("Asia/Tokyo")));

        LocalDate date = LocalDate.of(2024, 1, 15); // 2024-01-15
        LocalDate date2 = LocalDate.parse("2024-01-15"); // 文字列から

        // 情報取得
        date.getYear(); // 2024
        date.getMonth(); // JANUARY
        date.getMonthValue(); // 1
        date.getDayOfMonth(); // 15
        date.getDayOfWeek(); // MONDAY
        date.lengthOfMonth(); // 31（その月の日数）
        date.isLeapYear(); // false（うるう年か）

        // 加減算（イミュータブル：元のオブジェクトは変わらない）
        date.plusDays(10); // 2024-01-25
        date.plusMonths(2); // 2024-03-15
        date.plusYears(1); // 2025-01-15
        date.minusDays(5); // 2024-01-10

        // 比較
        LocalDate d1 = LocalDate.of(2024, 1, 1);
        LocalDate d2 = LocalDate.of(2024, 6, 1);
        d1.isBefore(d2); // true
        d1.isAfter(d2); // false
        d1.isEqual(d2); // false

        // 生成
        LocalTime now = LocalTime.now();
        LocalTime time = LocalTime.of(14, 30, 0); // 14:30:00
        LocalTime time2 = LocalTime.parse("14:30:00");

        // 情報取得
        time.getHour(); // 14
        time.getMinute(); // 30
        time.getSecond(); // 0
        time.getNano(); // 0

        // 加減算
        time.plusHours(2); // 16:30:00
        time.plusMinutes(45); // 15:15:00
        time.minusHours(1); // 13:30:00

        // 生成
        LocalDateTime datetime1 = LocalDateTime.now();
        LocalDateTime datetime2 = LocalDateTime.of(2024, 1, 15, 14, 30, 0);
        LocalDateTime datetime3 =
                LocalDateTime.of(LocalDate.of(2024, 1, 15), LocalTime.of(14, 30, 0));

        // LocalDate / LocalTime に分解
        LocalDate localdate = datetime2.toLocalDate(); // 2024-01-15
        LocalTime localtime = datetime2.toLocalTime(); // 14:30:00

        // 加減算
        datetime2.plusDays(10).plusHours(2); // 2024-01-25T16:30:00

        // 生成
        ZonedDateTime tokyo = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        ZonedDateTime utc = ZonedDateTime.now(ZoneId.of("UTC"));
        ZonedDateTime london = ZonedDateTime.now(ZoneId.of("Europe/London"));

        // LocalDateTimeにタイムゾーンを付ける
        LocalDateTime ldt = LocalDateTime.of(2024, 1, 15, 14, 30);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("Asia/Tokyo"));
        // 2024-01-15T14:30+09:00[Asia/Tokyo]

        // タイムゾーン変換
        ZonedDateTime nyTime = zdt.withZoneSameInstant(ZoneId.of("America/New_York"));
        // 2024-01-15T00:30-05:00[America/New_York]

        // 利用可能なタイムゾーン一覧
        ZoneId.getAvailableZoneIds().stream()
                .filter(z -> z.contains("Asia"))
                .sorted()
                .forEach(System.out::println);
        // Asia/Tokyo, Asia/Seoul, ...

        // Period：日付ベースの期間（年・月・日）
        LocalDate start = LocalDate.of(2020, 1, 1);
        LocalDate end = LocalDate.of(2024, 6, 15);

        Period period = Period.between(start, end);
        period.getYears(); // 4
        period.getMonths(); // 5
        period.getDays(); // 14

        // 生成
        Period threeMonths = Period.ofMonths(3);
        Period oneYear = Period.ofYears(1);
        start.plus(threeMonths); // 2020-04-01

        // Duration：時間ベースの期間（時・分・秒・ナノ秒）
        LocalTime t1 = LocalTime.of(9, 0);
        LocalTime t2 = LocalTime.of(17, 30);

        Duration duration = Duration.between(t1, t2);
        duration.toHours(); // 8
        duration.toMinutes(); // 510

        // 生成
        Duration twoHours = Duration.ofHours(2);
        Duration thirtyMin = Duration.ofMinutes(30);
    }
}
