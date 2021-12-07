package designpattern;

// define skeleton in ABSTRACT class

abstract class GameTPL {
    protected final int playerNo;
    protected int curPlayer;

    public GameTPL(int playerNo) {
        this.playerNo = playerNo;
    }

    public void run() {
        start();
        while (!haveWinner()) {
            takeTurn();
        }

        System.out.println("Player " + getWinningPlayer() + " won.");
    }

    protected abstract void start();
    protected abstract boolean haveWinner();
    protected abstract void takeTurn();
    protected abstract int getWinningPlayer();
}

class Chess extends GameTPL {
    private final int maxTurn;
    private int turn;

    public Chess(int maxTurn) {
        super(2);
        this.maxTurn = maxTurn;
        turn = 0;
    }

    @Override
    protected void start() {
        System.out.println("Starting a game: Player " + curPlayer + "'s turn.");
    }

    @Override
    protected boolean haveWinner() {
        return turn >= maxTurn;
    }

    @Override
    protected void takeTurn() {
        curPlayer = (curPlayer + 1) % playerNo;
        System.out.println("Player " + curPlayer + "'s turn");
        turn++;
    }

    @Override
    protected int getWinningPlayer() {
        return curPlayer;
    }
}
public class TemplateGame {
    public static void main(String[] args) {
        Chess chess = new Chess(5);
        chess.run();
    }
}
