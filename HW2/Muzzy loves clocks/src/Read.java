import java.io.File;
import java.io.FileNotFoundException;
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

    Read() throws FileNotFoundException {
        sc = new Scanner(new File("input.txt"));
    }


    /**
     * Methods separates line and places each word/number in LinkedList cell.
     * @return LinkedList with words/numbers
     */
    public LinkedList readline(){
        String inputString = sc.nextLine();
        LinkedList data = new LinkedList();

        if (!inputString.equals("")) {     //check that line is not empty. Need in some case
            for (String s :inputString.split(" ")) { //separate words/numbers
                data.add(s);
            }
        }
        return data;
    }



}

