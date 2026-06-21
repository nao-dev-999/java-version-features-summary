package org.example;

/** Java 17 (2021, LTS) Sealedクラスの導入 */
public class Java17 {

    static void main(String[] args) {
        Java17 java17 = new Java17();
        java17.sealedClass();
    }

    // sealedクラスの導入
    // sealedクラスは、継承を制限するクラスで、どのクラスが継承できるかを明示的に指定することができる
    // sealedクラスは、非公開のサブクラスを持つことができる
    sealed interface Shape permits Circle, Rectangle, Triangle {}

    record Circle(double radius) implements Shape {}

    record Rectangle(double w, double h) implements Shape {}

    record Triangle(double base, double height) implements Shape {}

    private void sealedClass() {
        Shape shape = new Circle(5.0);
        // Switchと組み合わせると強力！
        double area =
                switch (shape) {
                    case Circle c -> Math.PI * c.radius() * c.radius();
                    case Rectangle r -> r.w() * r.h();
                    case Triangle t -> 0.5 * t.base() * t.height();
                };
        System.out.println(area);
    }
}
