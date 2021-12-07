package designpattern;

import java.util.HashMap;
import java.util.function.Consumer;

class Event<T> {
    private int count = 0;
    private final HashMap<Integer, Consumer<T>> handlers = new HashMap<>();

    public Subscription addHandler(Consumer<T> consumer) {
        int idx = count;
        handlers.put(count++, consumer);
        return new Subscription(this, idx);
    }

    public void fire(T args) {
        for (Consumer<T> consumer : handlers.values()) {
            consumer.accept(args);
        }
    }

    public void remove(Subscription sub) {
        handlers.remove(sub.getIdx());
    }

    public class Subscription implements AutoCloseable {
        private final int idx;
        private final Event<T> event;

        public Subscription(Event<T> event, int idx) {
            this.event = event;
            this.idx = idx;
        }

        @Override
        public void close() {
            event.remove(this);
        }

        public int getIdx() {
            return idx;
        }
    }
}

class ChangeEvent {
    public String type;
    public int value;

    public ChangeEvent(String type, int value) {
        this.type = type;
        this.value = value;
    }
}

class PersonEvent {
    private int age;
    public Event<ChangeEvent> event;

    public PersonEvent() {
        event = new Event<>();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (this.age == age) return;
        this.age = age;
        event.fire(new ChangeEvent("age", this.age));
    }

    public Event<ChangeEvent>.Subscription subscribe(Consumer<ChangeEvent> consumer) {
        return event.addHandler(consumer);
    }
}

public class ObserverEventSubscription {
    public static void main(String[] args) {
        PersonEvent person = new PersonEvent();
        Event<ChangeEvent>.Subscription sub = person.subscribe(
                x -> {
                    System.out.println("Person's " + x.type + " changed to " + x.value);
                }
        );

        person.setAge(20);
        person.setAge(21);
        person.setAge(22);
        sub.close();
        person.setAge(23);
    }
}
