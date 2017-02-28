import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
/**
 * Created by Anton Skudarnov on 22.02.2017.
 */

/**
 * The main class of the program.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Write writer = new Write();
        writer.writeInt(HelpRiki.findColumn());

    }
}
