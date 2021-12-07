// Use constructor to copy object
package designpattern;

import java.util.Arrays;

class AddressCC {
    public String city, street;

    public AddressCC(String city, String street) {
        this.city = city;
        this.street = street;
    }

    public AddressCC(AddressCC address) {
        this(address.city, address.street);
    }

    @Override
    public String toString() {
        return city + " : " + street;
    }
}

class PersonCC {
    String[] name;
    AddressCC address;

    public PersonCC(String[] name, AddressCC address) {
        this.name = name;
        this.address = address;
    }

    public PersonCC(PersonCC person) {
        this(Arrays.copyOf(person.name, person.name.length),
                new AddressCC(person.address));
    }

    @Override
    public String toString() {
        return "Person: " + Arrays.toString(name) + ", address: " +
                address.toString();
    }
}
public class PrototypeCopyConstructor {
    public static void main(String[] args) {
        PersonCC john = new PersonCC(new String[]{"John", "Work"},
                new AddressCC("Seoul", "Gangnam"));
        PersonCC jane = new PersonCC(john);
        jane.name[0] = "Jane";
        jane.address.city = "Hawaii";
        jane.address.street = "Beach";

        System.out.println(john);
        System.out.println(jane);
    }
}
