import java.io.*;

class Emp implements Serializable {
    private final static long serialversionUID = 1287738473L;
    transient int a; // not serialize
    static int b; // not serialize
    String name;
    int age;

    // Default constructor
    public Emp(String name, int age, int a, int b) {
        System.out.println("Creating new Emp: " + name + ", age: " + age + ", a: " + a + ", b: " + b);
        this.name = name;
        this.age = age;
        this.a = a;
        this.b = b;
    }
}
public class SerializationTest {
    static void printData(Emp e) {
        System.out.println("Employee: name: " + e.name + "; age: " + e.age + "; a: " + e.a + ", b: " + e.b);
    }
    public static void main(String[] args) {
        Emp before = new Emp("Aloha", 100, 80, 81);
        printData(before);

        String fileName = "seri.txt";

        // Serialization
        try {
            FileOutputStream fo = new FileOutputStream(fileName);
            ObjectOutputStream oo = new ObjectOutputStream(fo);

            // save object to file
            oo.writeObject(before);

            oo.close();
            fo.close();
            System.out.println("Object is serialized");

            before.a = 1;
            before.b = 2;
        } catch (Exception ex) {
            System.out.println("Serialization error: " + ex.toString());
        }

        // Deserialization
        try {
            FileInputStream fi = new FileInputStream(fileName);
            ObjectInputStream oi = new ObjectInputStream(fi);

            Emp after = (Emp)oi.readObject();

            oi.close();
            fi.close();

            System.out.println("Object is deserialized");
            printData(after);
        } catch (Exception ex) {
            System.out.println("Deserialization error: " + ex.toString());
        }

        Emp e2 = new Emp("Emp2", 2, 12, 20);
        printData(e2);
        printData(before);
    }
}
