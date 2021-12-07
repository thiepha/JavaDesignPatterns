// part of SOLID principles
package designpattern;

interface ViolatedPrinter {
    void print();
    void scan();
    void fax();
}

class JustPrinter implements ViolatedPrinter {
    @Override
    public void print() {
        // implement
    }

    // just printer only can print, cannot do scan, fax
    public void scan() {
        // what should we do here?
        // - just ignore: difficult for users
        // - throw new Exception(): need to add throws to function, interface
        // what if we cannot access interface source code??
    }

    public void fax() {
        //
    }
}

// Interface Segregation: device interface into smaller interfaces
interface Printer {
    void print();
}

interface Scanner {
    void scan();
}

class BetterJustPrinter implements Printer {
    @Override
    public void print() {
        // implement
    }
}

class Photocopier implements Printer, Scanner {
    @Override
    public void print() {
        //
    }

    @Override
    public void scan() {
        //
    }
}
public class InterfaceSegregation {
    public static void main(String[] args) {
        //
    }
}
