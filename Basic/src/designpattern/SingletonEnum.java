package designpattern;

import java.io.*;

// enum: singleton, threadsafe, reflection, serialization: ok
// disadvantages: cannot do inheritance, fields do not save during serialization
enum Singleton {
    INSTANCE;

    Singleton() {
        value = 50;
    }

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
public class SingletonEnum {
    static void saveToFile(Singleton obj, String fileName) throws Exception {
        try (FileOutputStream fileOutput = new FileOutputStream(fileName);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput)) {
            objectOutput.writeObject(obj);
        }
    }

    static Singleton readFromFile(String fileName) throws Exception {
        try (FileInputStream fileInput = new FileInputStream(fileName);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput)) {
            return (Singleton) objectInput.readObject();
        }
    }
    public static void main(String[] args) throws Exception {
        String fileName = "serialize.obj";

//        Singleton singleton = Singleton.INSTANCE;
//        singleton.setValue(10);
//        saveToFile(singleton, fileName);

        Singleton singleton1 = readFromFile(fileName);
        System.out.println(singleton1.getValue());
    }
}
