package designpattern;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.Arrays;

class PersonSr implements Serializable {
    String[] name;
    String street;

    public PersonSr(String[] name, String street) {
        this.name = name;
        this.street = street;
    }

    @Override
    public String toString() {
        return "Person: " + Arrays.toString(name) + ", street : " + street;
    }
}

public class PrototypeSerialization {
    public static void main(String[] args) {
        PersonSr john = new PersonSr(new String[]{"John", "Dear"}, "Florida");

        PersonSr jane = SerializationUtils.roundtrip(john);
        jane.name[0] = "Jane";
        jane.street = "Long Beach";

        System.out.println(john);
        System.out.println(jane);
    }
}
