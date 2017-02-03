import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;

/**
 * Created by Anton Skudarnov on 01.02.2017.
 */
public class Main {
    public static void main(String[] args) throws IOException {

       FileReader reader = new FileReader("input.txt");
       ShuntingYard parser;
       parser = new ShuntingYard(reader);
       parser.parseExpression();
       parser.calculateExpression();


    }

}
