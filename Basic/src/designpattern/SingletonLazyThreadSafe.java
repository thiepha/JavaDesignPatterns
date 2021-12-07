package designpattern;

// Lazy: create instance when users request it
// Threadsafe
public class SingletonLazyThreadSafe {
    private SingletonLazyThreadSafe() {
        System.out.println("Inside single constructor");
    }

    private static SingletonLazyThreadSafe instance;

    // we can use synchronized on function but it is not good for performance
    // public static synchronized SingletonLazyThreadSafe getInstance() {
    public static SingletonLazyThreadSafe getInstance() {
        // use double-checking (old technique)
        if (instance == null) {
            synchronized (SingletonStaticBlock.class) {
                if (instance == null) {
                    instance = new SingletonLazyThreadSafe();
                }
            }
        }

        return instance;
    }

    // demo
    public static void main(String[] args) {
        SingletonLazyThreadSafe singleton = SingletonLazyThreadSafe.getInstance();

    }
}