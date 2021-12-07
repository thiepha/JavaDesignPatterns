package designpattern;

import java.util.ArrayList;
import java.util.List;

class Observable<T> {
    List<Observer<T>> observers = new ArrayList<>();

    public void subscribe(Observer<T> observer) {
        observers.add(observer);
    }

    public void notify(String type, int value) {
        for (Observer<T> observer : observers) {
            observer.handle(type, value);
        }
    }
}

class PersonOSV extends Observable<PersonOSV> {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (this.age == age) return;
        this.age = age;
        notify("age", this.age);
    }
}

interface Observer<T> {
    void handle(String type, int value);
}

public class ObserverPerson implements Observer<PersonOSV> {
    @Override
    public void handle(String type, int value) {
        System.out.println("Person's " + type + " has changed to " + value);
    }
}

class DemoOSV {
    public static void main(String[] args) {
        PersonOSV person = new PersonOSV();

        ObserverPerson observer = new ObserverPerson();
        person.subscribe(observer);

        for (int i = 20; i <= 23; i++) {
            person.setAge(i);
        }
    }
}
