import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class AlgorithmsTest {

    @org.junit.Test
    public void genetic() throws Exception {

        int successNumber = 0;
        Random random = new Random();
        int testsNumber = 10;
        for(int j = 0; j < testsNumber; j++) {
            ArrayList<Item> items = new ArrayList<>();
            int bagSize = random.nextInt(10000);
            for (int i = 0; i < bagSize / 20; i++) {
                items.add(new Item(random.nextInt(1000), random.nextInt(20)));
            }
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
            }
            if (greedy != null) {
                for (Item aGreedy : greedy) {
                    greedyCost += aGreedy.cost;
                    greedyWeight += aGreedy.weight;
                }
            }

            assertTrue(geneticWeight <= bagSize);
            assertTrue(greedyWeight <= bagSize);

            if (geneticCost >= greedyCost)
                successNumber++;
        }
        assertTrue(successNumber > testsNumber / 2);

    }

}