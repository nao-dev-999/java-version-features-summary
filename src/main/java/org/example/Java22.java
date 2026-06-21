package org.example;

import java.util.List;
import java.util.stream.Stream;

/**
 * Java 22 (2024)
 * 新しい機能の追加
 */
public class Java22 {

    static void main(String[] args) {
        Java22 java22 = new Java22();
        java22.noNameVariable();
    }

    // 無名変数
    // 使わない変数を _ で明示
    private void noNameVariable() {
        try {
            Integer.parseInt("abc");
        } catch (NumberFormatException _) { // 例外オブジェクト不要
            System.out.println("数値じゃない");
        }

        // ラムダでも
        List<String> result =
                Stream.of(1, 2, 3)
                        .map(_ -> "fixed") // 引数を使わないことが明確
                        .toList();
        result.forEach(System.out::println);
    }
}
