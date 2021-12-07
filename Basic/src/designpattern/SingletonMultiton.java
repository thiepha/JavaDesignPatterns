// multiton: limit number of instances created
package designpattern;

import java.util.HashMap;

// only 3 instances (max) available (1 for each type)
enum Subsystem {
    PRIMARY,
    AUX,
    FALLBACK
}

class PrinterM {
    private PrinterM() {
        System.out.println("Inside constructor");
    }

    // Use hashmap to store instances
    private static final HashMap<Subsystem, PrinterM> instances = new HashMap<>();

    public static PrinterM getInstance(Subsystem ss) {
        if (instances.containsKey(ss)) {
            return instances.get(ss);
        }

        PrinterM instance = new PrinterM();
        instances.put(ss, instance);
        return instance;
    }
}

public class SingletonMultiton {
    public static void main(String[] args) {
        PrinterM printer1 = PrinterM.getInstance(Subsystem.PRIMARY);
        PrinterM printer2 = PrinterM.getInstance(Subsystem.AUX);
        PrinterM printer3 = PrinterM.getInstance(Subsystem.AUX);
        System.out.println(printer2 == printer3);
    }
}
