package org.example;

import java.awt.*;

/** Java 16 (2021) recordクラスの導入 パターンマッチングの導入 */
public class Java16 {

    static void main(String[] args) {
        Java16 java16 = new Java16();
        java16.recordClass();
        java16.patternMatching();
    }

    // recordクラスの導入
    // recordクラスは、データキャリアとして使用されるクラスで、
    // フィールド、コンストラクタ、アクセサメソッド、toString、equals、hashCodeなどのメソッドを自動生成する
    private void recordClass() {
        record Person(String name, int age) {}
        Person person = new Person("John", 30);
        System.out.println(person.name());
        System.out.println(person.age());
        System.out.println(person);
    }

    // パターンマッチングの導入
    // instanceof演算子を使用して、オブジェクトの型を判定し、変数に代入することができる
    private void patternMatching() {
        Object obj = "Hello, World";
        if (obj instanceof String s) {
            System.out.println(s.toUpperCase());
        }
    }
}
