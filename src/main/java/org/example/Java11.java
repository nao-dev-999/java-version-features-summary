package org.example;

/**
 * Java 11 (2018, LTS) StringにisBlank(), strip(), lines()などのメソッド追加 varがラムダ引数で使用可能に
 * HTTPクライアントAPI（標準化） 単一ファイルプログラムの直接実行（java Hello.java）
 */
public class Java11 {

    static void main(String[] args) {
        Java11 java11 = new Java11();
        java11.stringNewMethods();
    }

    private void stringNewMethods() {
        String text = "  Hello World  ";
        // stripメソッド
        // 先頭と末尾の空白を削除する
        System.out.println(text.strip());
        System.out.println();

        // isBlankメソッド
        // 空文字かどうかを判定する
        System.out.println(text.isBlank());
        System.out.println();

        // linesメソッド
        // 改行コードで文字列を分割し、stream<String>を返す
        String textLines = "line1\nline2\nline3";
        System.out.println(textLines.lines().count());
        textLines.lines().forEach(System.out::println);
    }
}
