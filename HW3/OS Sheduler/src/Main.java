import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Scheduler scheduler = new Scheduler("input.csv");
        scheduler.start();
    }
}
