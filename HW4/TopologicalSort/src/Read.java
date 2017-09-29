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
    public LinkedList<String> readline(){
        String inputString = sc.nextLine();
        LinkedList data = new LinkedList();
        if (!inputString.equals("")) {     //check that line is not empty. Need in some case
            for (String s : inputString.split(" ")) { //separate words/numbers
                data.add(s);
            }
        }

        return data;
    }
    public LinkedList<String> readline(String separator){
        String inputString = sc.nextLine();
        LinkedList data = new LinkedList();
        if (!inputString.equals("")) {     //check that line is not empty. Need in some case
            for (String s : inputString.split(separator)) { //separate words/numbers
                data.add(s);
            }
        }

        return data;
    }

    public String readString(){
        String inputString = sc.nextLine();
        return inputString;
    }



    public int amountOfLines() throws FileNotFoundException {
        int i=0;
        while(sc.hasNextLine()){
            i++;
            sc.nextLine();
        }
        sc = new Scanner(new File(filename));
        return i;
    }

    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }

    public int readInt(){
        return sc.nextInt();
    }

    public double readDouble(){
        return sc.nextDouble();
    }
    public long readLong(){
        return sc.nextLong();
    }




}

