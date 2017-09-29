import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.DoubleSummaryStatistics;

public class Main {



    public static void main(String[] args) throws IOException {
        Read read = new Read("input.txt");
        Write write = new Write("output.txt");
        AVLTree<Double,Integer> tree = new AVLTree<>();
        long seed = (long) read.readDouble();
        MyRandom random = new MyRandom(seed);
        int i = 1;
        double number = random.nextDouble();
        while(!tree.containsKey(number)){
            tree.put(number,i);
            i++;
            number = random.nextDouble();
        }
        int result = i - tree.getValue(number);
        write.writeInt(result);






    }
}
