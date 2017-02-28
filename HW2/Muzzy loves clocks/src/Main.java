import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
/**
 * Created by Anton Skudarnov on 22.02.2017.
 */


/**
 * The main class of program.
 */
public class Main {

    public static void main(String[] args) throws IOException {
	    Read reader = new Read();
	    Write writer = new Write();
        LinkedList firstLine = reader.readline();
        LinkedList secondLine = reader.readline();

        writer.writeInt(Muzzy.calculateMinutes(firstLine,secondLine));

    }
}
