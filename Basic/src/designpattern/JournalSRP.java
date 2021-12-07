package designpattern;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

// Single Response Principle: one class should have only one responsibility
public class JournalSRP {
    private final List<String> entries;

    public JournalSRP() {
        entries = new ArrayList<>();
    }

    public void add(String entry) {
        entries.add(entry);
    }

    public void remove(int idx) {
        entries.remove(idx);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entries);
    }
}

class Persistence {
    public void saveToFile(JournalSRP jr, String fileName, boolean override) throws FileNotFoundException {
        if (override || new File(fileName).exists()) {
            try (PrintStream out = new PrintStream(fileName)) {
                out.println(jr.toString());
            }
        }
    }
}

class Demo {
    public static void main(String[] args) throws Exception {
        JournalSRP jr = new JournalSRP();
        jr.add("Today is monday");
        jr.add("Lets do it.");
        Persistence ps = new Persistence();
        ps.saveToFile(jr, "./monday.txt", true);
    }
}
