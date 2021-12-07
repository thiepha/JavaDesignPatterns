package designpattern;


import java.io.*;

// Not good:
// - can use Reflection to get private constructor
// - use serialization: serialize and deserialize to get private constructor
public class SingletonBasic implements Serializable {
    int value;

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private static final SingletonBasic INSTANCE = new SingletonBasic();
    private SingletonBasic() {
        System.out.println("In constructor");
    }

    public static SingletonBasic getInstance() {
        return INSTANCE;
    }

    // Without this: deserialization will return different object (singleton does not work)
    protected Object readResolve() {
        return INSTANCE;
    }

}

class BSDemo {
    public static void main(String[] args) {
        SingletonBasic instance = SingletonBasic.getInstance();
        instance.setValue(10);
        System.out.println(instance.getValue());

        SingletonBasic instance2 = SingletonBasic.getInstance();
        System.out.println(instance2.getValue());
    }
}

class BSDemo2 {
    static void saveToFile(SingletonBasic obj, String fileName) throws Exception {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOut);) {
            outputStream.writeObject(obj);
        }
    }

    static SingletonBasic readFromFile(String fileName) throws Exception {
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream inputStream = new ObjectInputStream(fileIn);) {
            return (SingletonBasic) inputStream.readObject();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Using serialization to hack singleton");
        SingletonBasic singleton = SingletonBasic.getInstance();
        singleton.setValue(1);
        String fileName = "basicSingleton.obj";

        saveToFile(singleton, fileName);
        singleton.setValue(100);

        SingletonBasic singleton2 = readFromFile(fileName);
        System.out.println(singleton == singleton2);
        System.out.println(singleton.getValue());
        System.out.println(singleton2.getValue());

        SingletonBasic singleton3 = readFromFile(fileName);
        System.out.println(singleton3 == singleton2);
    }
}
