package designpattern;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

class CreatureITR implements Iterable<Integer> {
    private final int NUM = 3; // able to add new feature easily
    private final int STRENGTH = 0, AGILITY = 1, INTELL = 2;
    private final int[] stats = new int[3];

    public int getStrength() {
        return stats[STRENGTH];
    }

    public void setStrength(int val) {
        stats[STRENGTH] = val;
    }

    public int getAgility() {
        return stats[AGILITY];
    }

    public void setAgility(int val) {
        stats[AGILITY] = val;
    }

    public int getIntell() {
        return stats[INTELL];
    }

    public void setIntell(int val) {
        stats[INTELL] = val;
    }

    // with array backed properties: following methods do not need to change
    // when new properties are added
    public int max() {
        return IntStream.of(stats).max().orElse(0);
    }

    public int sum() {
        return IntStream.of(stats).sum();
    }

    public int average() {
        return (int)IntStream.of(stats).average().orElse(0);
    }

    @Override
    public Iterator<Integer> iterator() {
        return IntStream.of(stats).iterator();
    }

    @Override
    public void forEach(Consumer<? super Integer> consumer) {
        for (int val : stats) {
            consumer.accept(val);
        }
    }

    @Override
    public Spliterator<Integer> spliterator() {
        return Arrays.spliterator(stats);
    }
}
public class IteratorCreature {
    public static void main(String[] args) {
        CreatureITR creature = new CreatureITR();
        creature.setStrength(12);
        creature.setAgility(18);
        creature.setIntell(30);

        System.out.printf("Creature has max stat of " + creature.max() +
                ", total stats of " + creature.sum() +
                ", an average stat of " + creature.average());
    }
}
