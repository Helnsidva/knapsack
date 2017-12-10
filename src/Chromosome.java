import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Chromosome {

    List<Integer> genes;
    final int size;
    int cost = 0;

    Chromosome (List<Integer> argGenes) {
        genes = argGenes;
        size = argGenes.size();
    }

    void mutation() {

        Random random = new Random();
        int numb;
        for (int i = 0; i < size / 10; i++) {
            numb = random.nextInt(size);
            if ((genes.get(numb) == 0)) {
                genes.set(numb, 1);
            } else {
                genes.set(numb, 0);
            }
        }

    }

    Chromosome selection(Chromosome secondChromosome) {

        List<Integer> secondGenes = secondChromosome.genes;
        List<Integer> newGenes = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (this.genes.get(i).equals(secondGenes.get(i)))
                newGenes.add(this.genes.get(i));
            else {
                Random random = new Random();
                newGenes.add(random.nextInt(2));
            }
        }
        return new Chromosome(newGenes);

    }

}
