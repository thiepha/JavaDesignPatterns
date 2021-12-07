package dp.observer;

public class Internet implements Observer {
    public void update(float interest) {
        System.out.println("Internet: got new interest: " + interest);
    }
}
