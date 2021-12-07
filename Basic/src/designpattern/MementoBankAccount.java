package designpattern;

// Memento: keep snapshots of changes
// consume memory
// another approach: Command patter (only keeps commands)

class BankMemento {
    private final int value; // value we want keep in memento (snapshot)

    public BankMemento(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

class BankAccountMMT {
    private int value;
    private final String name;

    public BankAccountMMT(String name) {
        this.name = name;
    }

    public BankMemento deposit(int value) {
        this.value += value;
        return new BankMemento(this.value); // always create new memento
    }

    public void restore(BankMemento memento) {
        this.value = memento.getValue();
    }

    @Override
    public String toString() {
        return "Account: " + name + " has balance " + this.value;
    }
}
public class MementoBankAccount {
    public static void main(String[] args) {
        BankAccountMMT account = new BankAccountMMT("John");
        BankMemento mm1 = account.deposit(1000);
        BankMemento mm2 = account.deposit(200);
        account.deposit(300);
        System.out.println(account);

        account.restore(mm1);
        System.out.println(account);

        account.restore(mm2);
        System.out.println(account);
    }
}
