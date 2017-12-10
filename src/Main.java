import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class Main {

    public static void main(String[] args) {

        ArrayList<Item> items = new ArrayList<>();
        int bagSize = 20;
        items.add(new Item(4, 10));
        items.add(new Item(3, 1));
        items.add(new Item(2, 0));
        items.add(new Item(9, 11));
        items.add(new Item(10, 4));
        items.add(new Item(2, 7));
        items.add(new Item(0, 10));
        items.add(new Item(1, 2));
        Chromosome genetic = Algorithms.genetic(bagSize, items);
        List<Item> greedy = Algorithms.greedy(bagSize, items);

        int geneticCost = 0;
        int greedyCost = 0;
        int geneticWeight = 0;
        int greedyWeight = 0;
        if (genetic != null) {
            for (int i = 0; i < genetic.genes.size(); i++) {
                if (genetic.genes.get(i) == 1) {
                    geneticCost += items.get(i).cost;
                    geneticWeight += items.get(i).weight;
                }
            }
            System.out.println("genetic algorithm: ");
            System.out.println("cost: " + geneticCost);
            System.out.println("weight: " + geneticWeight);
            System.out.println("items: ");
            for (int i = 0; i < genetic.size; i++) {
                if (genetic.genes.get(i) == 1) {
                    System.out.println("cost: " + items.get(i).cost + " weight: " + items.get(i).weight);
                }
            }
        }
        else {
            System.out.println("genetic algorithm didn't find solution");
        }
        if (greedy != null) {
            for (Item aGreedy : greedy) {
                greedyCost += aGreedy.cost;
                greedyWeight += aGreedy.weight;
            }
            System.out.println("------------------");
            System.out.println("greedy algorithm: ");
            System.out.println("cost: " + greedyCost);
            System.out.println("weight: " + greedyWeight);
            System.out.println("items: ");
            for (int i = 0; i < greedy.size(); i++) {
                System.out.println("cost: " + greedy.get(i).cost + " weight: " + greedy.get(i).weight);
            }
        }
        else {
            System.out.println("greedy algorithm didn't find solution");
        }

    }

}
