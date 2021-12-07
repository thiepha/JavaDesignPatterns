package designpattern;

import java.util.ArrayList;
import java.util.List;

abstract class Creature
{
    protected Game game;
    protected int attack, defense;
    public abstract int getAttack();
    public abstract int getDefense();
    public abstract void query(Object source, Query query);

    public Creature(Game game, int attack, int defense) {
        this.game = game;
        this.attack = attack;
        this.defense = defense;
    }

    @Override
    public String toString() {
        return "Creature: attach: " + getAttack() + "; defense: " + getDefense();
    }
}

class Goblin extends Creature
{
    protected Goblin(Game game, int attach, int defense) {
        super(game, attach, defense);
    }

    public Goblin(Game game)
    {
        super(game, 1, 1);
    }

    @Override
    public int getAttack()
    {
        Query query = new Query(Statistic.ATTACK);
        for (Creature creature : game.creatures) {
            creature.query(this, query);
        }
        return query.result;
    }

    @Override
    public int getDefense()
    {
        Query query = new Query(Statistic.DEFENSE);
        for (Creature creature : game.creatures) {
            creature.query(this, query);
        }
        return query.result;
    }

    @Override
    public void query(Object source, Query query) {
        if (source == this) {
            if (query.statistic == Statistic.ATTACK) {
                query.result += attack;
            } else {
                query.result += defense;
            }
        } else if (query.statistic == Statistic.DEFENSE) {
            query.result++;
        }
    }
}

class GoblinKing extends Goblin
{
    public GoblinKing(Game game)
    {
        super(game, 3, 3);
    }

    @Override
    public void query(Object source, Query query) {
        if (source != this && query.statistic == Statistic.ATTACK) {
            query.result++;
        } else {
            super.query(source, query);
        }
    }
}

enum Statistic
{
    ATTACK, DEFENSE
}

class Query {
    public Statistic statistic;
    public int result;

    public Query(Statistic statistic) {
        this.statistic = statistic;
    }
}

class Game
{
    public List<Creature> creatures = new ArrayList<>();
}

public class ChainOfResponsibilityGame {
    public static void main(String[] args) {
        Game game = new Game();

        Goblin gb = new Goblin(game);
        game.creatures.add(gb);
        Goblin gb2 = new Goblin(game);
        game.creatures.add(gb2);
        GoblinKing king = new GoblinKing(game);
        game.creatures.add(king);

        System.out.println(gb);
        System.out.println(gb2);
        System.out.println(king);
    }
}
