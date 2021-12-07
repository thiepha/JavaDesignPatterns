package designpattern;

public class SingletonInnerStatic {
    private SingletonInnerStatic() {
        System.out.println("Inside constructor");
    }

    private static class Inner {
        private static final SingletonInnerStatic INSTANCE = new SingletonInnerStatic();
    }

    public static SingletonInnerStatic getInstance() {
        return Inner.INSTANCE;
    }

    // Demo
    public static void main(String[] args) {
        SingletonInnerStatic singleton = SingletonInnerStatic.getInstance();
        SingletonInnerStatic singleton2 = SingletonInnerStatic.getInstance();
        System.out.println(singleton == singleton2);
    }
}
