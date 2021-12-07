// to use builder with extended class

package designpattern;

class Person1 {
    public String name;
    public String position;

    @Override
    public String toString() {
        return "Person { " +
                "name = '" + name + '\'' +
                ", position = '" + position + '\'' +
                " }";
    }
}

class PersonBuilder1<T extends PersonBuilder1> {
    protected Person1 person;

    public PersonBuilder1() {
        person = new Person1();
    }

    public T withName(String name) {
        person.name = name;
        return self();
    }

    public T self() {
        return (T) this;
    }

    public Person1 build() {
        return person;
    }
}

class EmployeeBuilder extends PersonBuilder1<EmployeeBuilder> {
    public EmployeeBuilder worksAs(String position) {
        person.position = position;
        return self();
    }

    @Override
    public EmployeeBuilder self() {
        return this;
    }
}

public class BuilderEmployee {
    public static void main(String[] args) {
        EmployeeBuilder eb = new EmployeeBuilder();
        Person1 person = eb.withName("Steve")
                .worksAs("Super")
                .build();
        System.out.println(person);
    }
}
