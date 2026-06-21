package org.example;

import java.util.Optional;

/** Java 8 (2014) ラムダ式の導入 Stream APIの導入 Optionalクラスの導入 */
public class Java8 {

    static void main(String[] args) {
        Java8 java8 = new Java8();
        java8.lambdaExpression();
        java8.streamApi();
        java8.optionalClass();
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
}
