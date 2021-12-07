package dp.observer;

import java.util.ArrayList;
import java.util.List;

public class Loan implements Subject {
    private List<Observer> observerList = new ArrayList<>();
    private String type;
    private float interest;
    private String bank;

    Loan(String type, float interest, String bank) {
        this.type = type;
        this.interest = interest;
        this.bank = bank;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
        notifyObservers();
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    @Override
    public void registerObserver(Observer ob) {
        observerList.add(ob);
    }

    public void removeObserver(Observer ob) {
        observerList.remove(ob);
    }

    public void notifyObservers() {
        for (Observer ob : observerList) {
            ob.update(interest);
        }
    }
}
