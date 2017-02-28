import javax.sound.sampled.Line;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;

/**
 * Created by Anton Skudarnov on 22.02.2017.
 */

/**
 * That class solves "Muzzy loves clocks". It contains main parts of algorithm.
 */
public class Muzzy {


    /**
     * That method contains main part of algorithm. I used dynamic programming for solving that problem.
     * @param firstLine linked list of data from text file. It contains first line with time and cost of time.
     * @param secondLine Linked List of data (in that case 1 number). It contains second line from text
     *                                                                            file (amount of money)
     * @return The result of computation. The maximum of possible amount of time, which Muzzy can buy.
     */
    public static int calculateMinutes(LinkedList firstLine, LinkedList secondLine) {
        int arraySize = firstLine.size() / 2;
        int[] time = new int[arraySize];
        int[] cost = new int[arraySize];
        parseLine(firstLine,time,cost);

        Double temp = Double.parseDouble(secondLine.peekFirst().toString())*100;
        int money = temp.intValue();
        int[][]clocks = new int[arraySize+1][money+1];

        for(int i = 0; i<arraySize;i++){
                clocks[i][0]  = 0;
            for(int j = 0; j<money;j++){
                clocks[0][j] = 0;
            }
        }
        for(int i = 1; i<arraySize+1;i++){
            for(int j = 1; j<money+1;j++){
                if (cost[i-1]<=j) {
                    clocks[i][j] = Integer.max(clocks[i-1][j],clocks[i-1][j-cost[i-1]]+time[i-1]);
                }
                else
                {
                    clocks[i][j] = clocks[i-1][j];
                }
            }
        }

        return clocks[arraySize][money];



    }

    /**
     *  That method separates time from cost of time
     * @param firstLine contains time and the cost of each time
     * @param time is array for time data
     * @param cost is array for cost of time data
     */
    private static void parseLine(LinkedList firstLine, int[] time, int[] cost){

        int iterator = 0;
        for (Object obj : firstLine) {
            String temp = obj.toString();
            if (iterator % 2 == 0) {
                parseTime(temp,time,iterator);
            } else {
                Double d = Double.parseDouble(temp)*100;

                cost[iterator / 2] = d.intValue();
            }
            iterator++;
        }
    }

    /**
     *
     * @param temp contains a time in 00:00 (or 0:00) format
     * @param time is array for time data
     * @param iterator needs for correct indexation in array of time
     */
    private static void parseTime(String temp, int[] time, int iterator){
            int h; //amount of hours
            int min; //amount of minutes
        if (temp.length() == 4) {
            h = Integer.parseInt(temp.substring(0,1)) * 60;  //this part separates hours from minutes
            min = Integer.parseInt(temp.substring(2));
            time[iterator / 2] = h + min;         // if/else statement needs for different types of input data
        } else {
            h = h = Integer.parseInt(temp.substring(0,2)) * 60;
            min = Integer.parseInt(temp.substring(3));
            time[iterator / 2] = h + min;
        }
    }
}
