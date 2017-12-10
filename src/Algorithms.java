import java.util.*;

class Algorithms {

    public static class GreedyComparator implements Comparator<Item> {
        @Override
        public int compare(Item o1, Item o2) {
            return Double.compare((double) o2.cost / (double) o2.weight, (double) o1.cost / (double) o1.weight);
        }
    }

    public static class GeneticComparator implements Comparator<Chromosome> {
        @Override
        public int compare(Chromosome o1, Chromosome o2) {
            return o2.cost - o1.cost;
        }
    }

    private static List<Chromosome> mutation(List<Chromosome> population) {

        if (population.isEmpty())
            return null;
        List<Chromosome> newGeneration = new ArrayList<>();
        newGeneration.addAll(population);
        for (int i = 0; i < population.size() / 10; i++) {

            Random random = new Random();
            Chromosome mutationChromosome = newGeneration.get(random.nextInt(newGeneration.size()));
            mutationChromosome.mutation();

        }
        return newGeneration;

    }

    private static List<Chromosome> selection(List<Chromosome> population, int capacity, ArrayList<Item> items) {

        if (population.isEmpty())
            return null;
        for (Chromosome aPopulation : population) {

            int cost = 0;
            int weight = 0;
            for (int j = 0; j < aPopulation.genes.size(); j++) {
                if (aPopulation.genes.get(j) == 1) {
                    cost += items.get(j).cost;
                    weight += items.get(j).weight;
                }
            }
            if (weight > capacity)
                cost = capacity - weight;
            aPopulation.cost = cost;

        }

        population.sort(new GeneticComparator());
        return population.subList(0, population.size() / 2 + population.size() % 2);

    }

    private static List<Chromosome> crossing(List<Chromosome> population) {

        if (population.isEmpty())
            return null;
        List<Chromosome> selectedPopulation = new ArrayList<>();
        int populationSize = population.size() - population.size() % 2;
        for (int i = 0; i < populationSize; i++) {
            Random random = new Random();
            Chromosome firstChromosome = population.get(random.nextInt(populationSize));
            Chromosome secondChromosome = population.get(random.nextInt(populationSize));
            Chromosome newChr = firstChromosome.selection(secondChromosome);
            selectedPopulation.add(newChr);
        }
        return selectedPopulation;

    }

    private static List<Chromosome> generatePopulation(int size) {

        List<Chromosome> generatedChromosomes = new ArrayList<>();
        int number = 2 * size;
        while (generatedChromosomes.size() != number) {
            List<Integer> generate = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                Random random = new Random();
                generate.add(random.nextInt(2));
            }
            for (Chromosome i: generatedChromosomes) {
                if (i.genes.equals(generate)) {
                    generate = null;
                    break;
                }
            }
            if (generate != null)
                generatedChromosomes.add(new Chromosome(generate));

        }
        return generatedChromosomes;

    }

    static Chromosome genetic(int capacity, ArrayList<Item> items) {

        if (items.isEmpty())
            return null;
        int itemsNumber = items.size();
        List<Chromosome> newGeneration = generatePopulation(itemsNumber);
        for(int i = 0; i < 2 * itemsNumber; i++) {

            List<Chromosome> selectedPopulation = selection(newGeneration, capacity, items);

            List<Chromosome> crossedPopulation = crossing(selectedPopulation);

            List<Chromosome> resultPopulation = new ArrayList<>();
            resultPopulation.addAll(selectedPopulation);
            resultPopulation.addAll(crossedPopulation);

            newGeneration = mutation(resultPopulation);
        }

        List<Chromosome> bestWay = selection(newGeneration, capacity, items);
        return bestWay.get(0);

    }

    static List<Item> greedy(int capacity, ArrayList<Item> items) {

        if(items.isEmpty())
            return null;
        List<Item> result = new ArrayList<>();
        result.addAll(items);
        result.sort(new GreedyComparator());
        int resultCapacity = 0;
        for(int i = 0; i < result.size(); i++) {
            if(result.get(i).weight + resultCapacity > capacity) {
                result.remove(i);
                i--;
                continue;
            }
            resultCapacity += result.get(i).weight;
        }
        return result;

    }

}
