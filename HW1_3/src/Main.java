import java.io.*;
import java.util.Scanner;

/**
 * Created by Anton Skudarnov on 05.02.2017.
 */
public class Main {


    public static void main(String[] args) throws IOException {

       BitSet<Long> bitset = new BitSet();
       ArrayList<Long> testNumbers = new ArrayList();  //ArrayList of numbers for test.
        try {
            readLines(bitset,testNumbers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = new String();
        for (int i = 0; i<testNumbers.count;i++){
            if(bitset.isConsist(testNumbers.get(i))){   //tests for each number in ArrayList

                result = result + "true ";}
            else
            result = result + "false ";
        }
        writeLines(result);



    }


    /**
     * Read two lines from input file and add new elements in set and in Arraylist
     * @param bitset set of elements
     * @param testNumbers ArrayList of numbers for test.
     * @throws IOException
     */
    public static void readLines(BitSet bitset, ArrayList testNumbers) throws IOException {
        Scanner sc = new Scanner(new File("input.txt"));
        String numberString = sc.nextLine();
        if (!numberString.equals("")) {     //check that first line is not empty
            for (String s : numberString.split(" ")) { //separate numbers
                bitset.add(Long.parseLong(s));

            }
        }
        numberString = sc.nextLine();
        if(!numberString.equals("")) {   //check that second line is not empty
            for (String s : numberString.split(" ")) { //separate numbers
                testNumbers.add(Long.parseLong(s));

            }
        }
    }



    public static void writeLines(String resut) throws IOException {
        FileWriter writer = new FileWriter("output.txt");
        writer.write(resut);
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
