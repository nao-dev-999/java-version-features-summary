package org.example;

import java.util.List;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

/**
 * Java 24 (2024)
 * Stream中間操作の拡張
 */
public class Java24 {

    static void main(String[] args) {
        Java24 java24 = new Java24();
        java24.streamGatherers();
    }

    // 独自のStream中間操作を定義できる
    // 例：n個ずつのウィンドウ処理
    private void streamGatherers() {
        Stream.of(1, 2, 3, 4, 5).gather(Gatherers.windowSliding(3)).forEach(System.out::println);
    }
}
