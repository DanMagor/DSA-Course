import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Locale;

/**
 * Created by Anton Skudarnov on 04.02.2017.
 * The Main class of problem
 */
public class Main {


    public static void main(String[] args) throws IOException {
        MonteCarlo mc = new MonteCarlo();
        try {
            mc.ReadPoints();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String output = String.format(Locale.US,"%.2f",mc.calculateSquare()); //I use locale cause  FileWriter
        FileWriter writer = new FileWriter("output.txt");       //writes double with comma
        writer.write(output);
        writer.close();

    }
}
