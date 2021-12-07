package dp.observer;

public class Newspaper implements Observer {
    public void update(float interest) {
        System.out.println("Newspaper: got new interest: " + interest);
    }
}
