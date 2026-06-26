package org.example;

import java.time.Duration;
import java.util.concurrent.Executors;

/** Java 21 (2024, LTS) パターンマッチングの拡張 仮想スレッド */
public class Java21 {

    static void main(String[] args) {
        Java21 java21 = new Java21();
        java21.patternMatching();
        java21.virtualThreads();
    }

    // パターンマッチングの拡張 型・構造・条件で分岐
    private void patternMatching() {
        Object obj = "hello";
        String result =
                switch (obj) {
                    case Integer i -> "整数: " + i;
                    case String s -> "文字列: " + s;
                    case null -> "null";
                    default -> "その他";
                };
        System.out.println(result);
    }

    // 仮想スレッド
    private void virtualThreads() {
        Thread vt =
                Thread.ofVirtual()
                        .start(
                                () -> {
                                    System.out.println("仮想スレッド！");
                                });

        // 大量生成も余裕（100万スレッドも可能）
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 1_000_000; i++) {
                executor.submit(() -> {
                            Thread.sleep(Duration.ofSeconds(1));
                            return "done";
                        });
            }
        }
    }
}
