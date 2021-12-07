// Prototype: Create new object by copying an existing object
// Use Cloneable interface to implement prototype dp
// It is not recommend to use Cloneable interface:
// - Cloneable is a weak interface without any function
// - Need to implement clone method (or other) of all objects
package designpattern;

import java.util.Arrays;

class AddressCl implements Cloneable {
    public String city, street;

    public AddressCl(String city, String street) {
        this.city = city;
        this.street = street;
    }

    public AddressCl clone() {
        return new AddressCl(city, street);
    }

    @Override
    public String toString() {
        return city + " : " + street;
    }
}

class PersonCl implements Cloneable {
    String[] name;
    AddressCl address;

    public PersonCl(String[] name, AddressCl address) {
        this.name = name;
        this.address = address;
    }

    public PersonCl clone() {
        return new PersonCl(this.name.clone(), this.address.clone());
    }

    @Override
    public String toString() {
        return "Person: " + Arrays.toString(name) + ", address: " +
                address.toString();
    }
}

public class PrototypeCloneable {
    public static void main(String[] args) {
        PersonCl john = new PersonCl(new String[]{"John", "Work"},
                new AddressCl("Seoul", "Gangnam"));
        PersonCl jane = john.clone();
        jane.name[0] = "Jane";
        jane.address.city = "Hawaii";
        jane.address.street = "Beach";

        System.out.println(john);
        System.out.println(jane);
    }
}
