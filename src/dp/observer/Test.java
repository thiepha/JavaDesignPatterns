package dp.observer;

public class Test {
    public static void main(String[] args) {
        Newspaper npp = new Newspaper();
        Internet itn = new Internet();

        Loan sc = new Loan("Personal", 2.0f, "Standard Chartered");
        sc.registerObserver(npp);
        sc.registerObserver(itn);
        sc.setInterest(1.5f);
    }
}
