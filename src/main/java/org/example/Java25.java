package org.example;

// Java 25：モジュールごとに一括import
// java.baseモジュール全体をimport
import module java.base;

public class Java25 {
    static void main(String[] args) {
        try {
            new PositiveNumber(-1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        Java25 java25 = new Java25();
        java25.handleRequest(new User("Alice", 30));
    }

    // Java 25：super()の前にロジックを書ける
    static class PositiveNumber extends Number {
        PositiveNumber(int value) {
            if (value <= 0) throw new IllegalArgumentException("正の数のみ");
            super(); // バリデーション後にsuper()を呼べる
        }
        @Override
        public int intValue() { return 0;}
        @Override
        public long longValue() { return 0;}
        @Override
        public float floatValue() { return 0;}
        @Override
        public double doubleValue() { return 0;}
    }

    // Scoped Values（JEP 487）正式化
    // ThreadLocalの後継・Java 21でプレビューだったものが正式に
    record User(String name, int age) {}
    static final ScopedValue<User> CURRENT_USER = ScopedValue.newInstance();
    void handleRequest(User user) {
        ScopedValue
                .where(CURRENT_USER, user)  // 値をバインド
                .run(this::processOrder); // スコープ内で有効。リクエストスコープにしたい場合、filterで使えばよい。
        // スコープを抜けると自動的に消える・remove()不要！
    }
    void processOrder() {
        User user = CURRENT_USER.get(); // スコープ内なら取得可能
        System.out.println(user);
    }
}
