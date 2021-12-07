package designpattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class BankAccount {
    private int balance;
    private int overDraftLimit = -500;

    public void deposit(int amount) {
        balance += amount;
        System.out.println("Deposit: " + amount + ", balance: " + balance);
    }

    public boolean withdraw(int amount) {
        if (balance - amount >= overDraftLimit) {
            balance -= amount;
            System.out.println("Withdraw: " + amount + ", balance: " + balance);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "BankAccount: balance: " + balance;
    }
}

interface Command {
    void call();
    void undo();
}

enum Action {
    DEPOSIT,
    WITHDRAW
}

class BankAccountCommand implements Command {
    private BankAccount account;
    private int amount;
    private boolean succeeded;
    private Action action;

    @Override
    public void call() {
        if (action == Action.DEPOSIT) {
            succeeded = true;
            account.deposit(amount);
        } else { // WITHDRAW
            succeeded = account.withdraw(amount);
        }
    }

    @Override
    public void undo() {
        if (action == Action.DEPOSIT) {
            succeeded = account.withdraw(amount);
        } else {
            if (succeeded) {
                account.deposit(amount);
            }
        }
    }

    public BankAccountCommand(BankAccount account, Action action, int amount) {
        this.account = account;
        this.action = action;
        this.amount = amount;
    }
}

public class CommandBankAccount {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        System.out.println(account);

        List<BankAccountCommand> commands = new ArrayList<>();
        commands.add(new BankAccountCommand(account, Action.DEPOSIT, 100));
        commands.add(new BankAccountCommand(account, Action.WITHDRAW, 1000));
        commands.add(new BankAccountCommand(account, Action.DEPOSIT, 500));
        commands.add(new BankAccountCommand(account, Action.WITHDRAW, 1000));

        for (Command c : commands) {
            c.call();
            System.out.println(account);
        }

        Collections.reverse(commands);
        for (Command c : commands) {
            c.undo();
            System.out.println(account);
        }
    }
}
