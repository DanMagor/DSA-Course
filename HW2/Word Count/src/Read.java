import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Anton Skudarnov on 22.02.2017.
 */

/**
 * That class read data from input file
 */
public class Read {
    private Scanner sc; //scanner for reading
    private String filename;
    Read() throws FileNotFoundException {
        filename = "input.txt";
        sc = new Scanner(new File(filename));
    }

    /**
     *
     * @param name input filename
     * @throws FileNotFoundException
     */
    Read(String name) throws FileNotFoundException {
        try {
            filename = name;
            sc = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Methods separates line and places each word/number in LinkedList cell.
     * @return LinkedList with words/numbers
     */
    public LinkedList readline(){
        String inputString = sc.nextLine();
            LinkedList data = new LinkedList();
            if (!inputString.equals("")) {     //check that line is not empty. Need in some case
                for (String s : inputString.split(" ")) { //separate words/numbers
                    data.add(s);
                }
            }

        return data;
    }
    public LinkedList readOnlyWords(){
        String inputString = sc.nextLine().toLowerCase();
        LinkedList data = new LinkedList();
        if (!inputString.equals("")) {     //check that line is not empty. Need in some case
            for (String s : inputString.split("[^a-zA-Z]+")) { //separate words/numbers
                data.add(s);
            }
        }
        return data;
    }


    /**
     *
     * @return Amount of not blank lines in input file.
     * @throws FileNotFoundException
     */

    public int amountOfLines() throws FileNotFoundException {
        int i=0;
        while(sc.hasNextLine()){
            i++;
            sc.nextLine();
        }
        sc = new Scanner(new File(filename));
        return i;
    }
    /**
     *
     * @return Amount of words in input file.
     * @throws FileNotFoundException
     */
    public int amountOfWords() throws FileNotFoundException {
        int i=0;
        while(sc.hasNext()){
            i++;
            sc.next();
        }
        sc = new Scanner(new File(filename));
        return i;
    }




}

