import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Anton Skudarnov on 22.02.2017.
 */

/**
 * That class solves problem. It contains main part of algorithm.
 */
public class HelpRiki {

    private static int height; //height of table
    private static int width; //width of table

    /**
     *  The main part of algorithm.
     * @return The number of time column
     * @throws FileNotFoundException
     */
    public static int findColumn() throws IOException {
        double[][] data = readData();  //data table from input file
        sortData(data);      // sort columns

        int counter = 0;  // it helps check the result
        for (int i = 0; i<width;i++){
            double difference = data[1][i] - data[0][i]; //difference of first two elements
            for (int j = 0; j<height-1;j++){
                if (data[j][i] == 0 ) break;
                if (data[j+1][i] - data[j][i] < 10E-4) break; //check that there is some difference. If not then it's not right column
                if (data[j+1][i] - data[j][i] - difference <10E-5) //check that difference is the same
                    counter++;
                else break;
            }
            if (counter == height-1) return i;  //if all differences are right then it's answer column
            else counter = 0;
        }
        return -1;  //Output in the case that there is no right column
    }

    /**
     *
     * @return array with data from the input table.
     * @throws FileNotFoundException
     */
    private static double[][] readData() throws FileNotFoundException {
        Read read = new Read("input.csv");
        height = read.amountOfLines();
        LinkedList line = read.readline();
        width = line.size();
        double[][] data = new double[height][width];
        for (int i = 0; i < height; i++) {
            int j = 0;
            for (Object temp : line) {
                data[i][j] = Double.parseDouble(temp.toString());
                j++;
            }
            if (i != height - 1) line = read.readline();
        }
        return data;
    }

    /**
     *
     * @param data is input array.
     * @return sorted array.
     */
    private static double[][] sortData(double[][] data) throws IOException {
        for (int i = 0; i < width; i++) {
            double temp[] = new double[height];
            for (int j = 0; j < height; j++)
                temp[j] = data[j][i];
            MergeSort.sort(temp, 0, height - 1);
            for (int j = 0; j < height; j++)
                data[j][i] = temp[j];
        }

        return data;
    }
}

