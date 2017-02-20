import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Anton Skudarnov on 06.02.2017.
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
       /* Hashtable compnaies = new Hashtable();
        compnaies.put("Google","United States");
        compnaies.put("Nokia","Finland");
        compnaies.put("Sony", "Japan");

        System.out.println(compnaies.get("Google"));
        System.out.println(compnaies.containsKey("Google"));
        System.out.println(compnaies.containsValue("Japan"));
        Enumeration enumeration = compnaies.elements();
        while(enumeration.hasMoreElements()){
            System.out.println(enumeration.nextElement());
        }
        System.out.println(compnaies.isEmpty());
        System.out.println(compnaies.size());
        Enumeration hastableValuesEnum = compnaies.elements();

        Scanner scanner = new Scanner(new FileReader("dictionary.txt"));
        scanner.useLocale(Locale.US);
        String inputString;
        Hashtable dictionary = new Hashtable();
        int i = 0;
        while(scanner.hasNextLine()){
            inputString = scanner.nextLine();
            dictionary.put(i,inputString);
            i++;
        }
        scanner = new Scanner(new FileReader("gp.txt"));
        scanner.useLocale(Locale.US);
        Hashtable text = new Hashtable();
        i = 0;
        while(scanner.hasNext()){
            inputString = scanner.next();
            if(!dictionary.containsValue(inputString) && !text.containsValue(inputString)){
                System.out.println(inputString);
                text.put(i,inputString);
                i++;
            }
        }
        System.out.println(i);
    } */
        Hashtable words = new Hashtable();
        Scanner scanner = new Scanner(new File("input.txt"));
        while (scanner.hasNext()) {
            String inputString = scanner.next();
            char chars[] = inputString.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            words.put(sorted,inputString);
        }
        System.out.println(words.size());
        Enumeration enumeration = words.elements();
        while(enumeration.hasMoreElements()){
            System.out.println(enumeration.nextElement());
        }

    }

}
