package designpattern;

import java.io.File;
import java.util.function.Supplier;

// static block is used in case the constructor can throw exception
public class SingletonStaticBlock {
    private SingletonStaticBlock() throws Exception {
        System.out.println("Inside constructor");
        File.createTempFile(".", ".");
    }

    public static SingletonStaticBlock instance;

    static {
        try {
            instance = new SingletonStaticBlock();
        } catch (Exception ex) {
            System.out.println("Failed to create singleton");
        }
    }

    public static SingletonStaticBlock getInstance() {
        return instance;
    }
}

class SBDemo {
    public static void main(String[] args) {
        SingletonStaticBlock singleton = SingletonStaticBlock.getInstance();
    }
}

class SingletonTester
{
    public static boolean isSingleton(Supplier<Object> func)
    {
        Object obj1 = func.get();
        Object obj2 = func.get();
        return obj1 == obj2;
    }
}
