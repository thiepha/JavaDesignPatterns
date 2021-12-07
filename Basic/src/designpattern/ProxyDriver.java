package designpattern;

class Driver {
    public int age;

    public Driver(int age) {
        this.age = age;
    }
}

class Car {
    protected final Driver driver;

    public Car(Driver driver) {
        this.driver = driver;
    }

    public void drive() {
        System.out.println("Car is driven by driver with age " + driver.age);
    }
}

class CarProxy extends Car {
    public CarProxy(Driver driver) {
        super(driver);
    }

    @Override
    public void drive() {
        if (driver.age > 16) {
            System.out.println("Car is driver by a driver with age " + driver.age);
        } else {
            System.out.println("Driver is too young to drive");
        }
    }
}

public class ProxyDriver {
    public static void main(String[] args) {
        Driver driver = new Driver(10);
        Car car = new Car(driver);
        car.drive();
        CarProxy carProxy = new CarProxy(driver);
        carProxy.drive();
    }
}
