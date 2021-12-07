package designpattern;

// Chain of responsibility: apply an action on chain of objects

class CreatureCOR {
    protected final String name;
    protected int attack, defense;

    public CreatureCOR(String name) {
        this.name = name;
        attack = defense = 1;
    }

    public CreatureCOR(String name, int attack, int defense) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
    }

    @Override
    public String toString() {
        return "Creature " + name + " has attack " + attack +
                " and defense " + defense;
    }
}

class CreatureModifier {
    protected final CreatureCOR creature;
    private CreatureModifier next;

    public CreatureModifier(CreatureCOR creature) {
        this.creature = creature;
    }

    public void add(CreatureModifier next) {
        if (this.next != null) {
            this.next.add(next);
        } else {
            this.next = next;
        }
    }

    public void handle() {
        if (next != null) next.handle();
    }
}

class DoubleAttackModifier extends CreatureModifier {
    public DoubleAttackModifier(CreatureCOR creature) {
        super(creature);
    }

    @Override
    public void handle() {
        System.out.println("Doubling creature " + creature.name + "'s attack...");
        creature.attack *= 2;
        super.handle();
    }
}

class UpgradeDefenseModifier extends CreatureModifier {
    public UpgradeDefenseModifier(CreatureCOR creature) {
        super(creature);
    }

    @Override
    public void handle() {
        System.out.println("Upgrade creature " + creature.name + "'s defense...");
        creature.defense += 5;
        super.handle();
    }
}

class NoBonusModifier extends CreatureModifier {
    public NoBonusModifier(CreatureCOR creature) {
        super(creature);
    }

    @Override
    public void handle() {
        // do nothing
        System.out.println("No bonus...");
    }
}

public class ChainOfResponsibilityMethod {
    public static void main(String[] args) {
        CreatureCOR creature = new CreatureCOR("goblin", 2, 3);
        System.out.println(creature);

        CreatureModifier root = new CreatureModifier(creature);

        //
        //root.add(new NoBonusModifier(creature));
        //
        root.add(new DoubleAttackModifier(creature));
        root.add(new UpgradeDefenseModifier(creature));

        root.handle();
        System.out.println(creature);
    }
}
