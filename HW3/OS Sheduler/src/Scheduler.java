import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Anton Skudarnov on 21.03.2017.
 */
public class Scheduler {

    int MAX_TIME = 120000;
    protected Read read;
    private int height;
    private int width;

    BinaryHeap<Integer, Integer> queue;

    Scheduler(String filename) throws FileNotFoundException {
        read = new Read("input.csv");
        queue = new BinaryHeap<>();
    }

    private String[][] readData() throws FileNotFoundException {

        height = read.amountOfLines();
        LinkedList line = read.readline();
        width = line.size();
        String[][] data = new String[height][width];
        for (int i = 0; i < height; i++) {
            int j = 0;
            for (Object temp : line) {
                data[i][j] = temp.toString();
                j++;
            }
            if (i != height - 1) line = read.readline();
        }
        return data;
    }

    public void start() throws IOException {
        String[][] data = readData();
        int endTime = 0;
        int currentEvent = 1; //"1" because there is row with Titles
        String resultString = "";
        int nextEvent = 2;  //"2" the same reason.
        while(currentEvent<height) {
           queue.removeMax();
           endTime = endTime + Integer.parseInt(data[currentEvent][2]);
           while (nextEvent < height && Integer.parseInt(data[nextEvent][1]) < endTime) {
               queue.insert(Integer.parseInt(data[nextEvent][3]), nextEvent);
               nextEvent++;
           }

           if (endTime <= MAX_TIME) {
               resultString = data[currentEvent][0];
           } else break;
           if(queue.isEmpty()){
               currentEvent = nextEvent;
           }else
           currentEvent = queue.max().getValue();


       }

       Write write = new Write("output.txt");

       write.writeString(resultString);
    }


}
