package org.example;

/** Java 14 (2020) switch式の導入 text blockの導入 */
public class Java14 {

    static void main(String[] args) {
        Java14 java14 = new Java14();
        java14.switchExpression();
        java14.textBlock();
    }

    // switch式の導入
    private void switchExpression() {
        String day = "MONDAY";
        String result =
                switch (day) {
                    case "MONDAY", "FRIDAY", "SUNDAY" -> "6am";
                    case "TUESDAY" -> "7am";
                    case "THURSDAY", "SATURDAY" -> "8am";
                    case "WEDNESDAY" -> "9am";
                    default -> throw new IllegalStateException("Invalid day: " + day);
                };
        System.out.println(result);
    }

    // text blockの導入
    private void textBlock() {
        String html =
                """
                <html>
                    <body>
                        <p>Hello, World</p>
                    </body>
                </html>
                """;
        System.out.println(html);
    }
}
