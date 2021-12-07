package designpattern;

class Person {
    // address
    public String street, postcode, city;

    // employment
    public String company, position;
    public int income;

    @Override
    public String toString() {
        return "Person { " +
                "streetAddress = '" + street + '\'' +
                ", postcode = '" + postcode + '\'' +
                ", city = '" + city + '\'' +
                ", companyName = '" + company + '\'' +
                ", position = '" + position + '\'' +
                ", annualIncome = " + income +
                " }";
    }
}

// Builder facade
class PersonBuilder {
    protected Person person;

    public PersonBuilder() {
        person = new Person();
        System.out.println("Inside PersonBuilder constructor");
    }

    public AddressBuilder lives() {
        return new AddressBuilder(person);
    }

    public EmploymentBuilder works() {
        return new EmploymentBuilder(person);
    }

    public Person build() {
        return person;
    }
}

class AddressBuilder extends PersonBuilder {
    public AddressBuilder(Person person) {
        this.person = person;
        System.out.println("Inside AddressBuilder");
    }
    public AddressBuilder at(String street) {
        person.street = street;
        return this;
    }

    public AddressBuilder in(String city) {
        person.city = city;
        return this;
    }

    public AddressBuilder withPostcode(String postcode) {
        person.postcode = postcode;
        return this;
    }
}

class EmploymentBuilder extends PersonBuilder {
    public EmploymentBuilder(Person person) {
        this.person = person;
        System.out.println("Inside EmploymentBuilder");
    }

    public EmploymentBuilder at(String company) {
        person.company = company;
        return this;
    }

    public EmploymentBuilder asA(String position) {
        person.position = position;
        return this;
    }

    public EmploymentBuilder earning(int income) {
        person.income = income;
        return this;
    }
}

public class BuilderFacet {
    public static void main(String[] args) {
        PersonBuilder pb = new PersonBuilder();
        Person person = pb
                .lives()
                    .at("123 London Road")
                    .in("London")
                    .withPostcode("SW12BC")
                .works()
                    .at("Fabrikam")
                    .asA("Engineer")
                    .earning(123000)
                .build();
        System.out.println(person);
    }
}
