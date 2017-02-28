import java.io.IOException;

/**
 * Main class of program. It contains main part of algorithm.
 */
public class Main {
    private static final String[] stoplist = {"a", "the", "in", "at", "to", "on", "not", "for",
            "s", "d", "re", "is", "are", "am", "has", "I", "we", "you"};



    public static void main(String[] args) throws IOException {
        Hashtable<String, Integer> hashtable = new Hashtable();
        Read read = new Read();
        int amountOfLines = read.amountOfLines();
        for (int i = 0; i < amountOfLines; i++) {
            for (Object temp : read.readOnlyWords()) {          //read text from file and add it in hashtable
                String word = (String) temp;

                if (hashtable.contains(word)) {
                    int count = hashtable.get(word) + 1;
                    hashtable.put(word, count);             //if there is word then add 1 to value
                } else
                    hashtable.put(word, 1);  //else just put word in hashtable
            }
        }

        String resultStrings;
        //find most frequent word
        //part with stoplist
        int max = 0;
        String maxWord = "no word";
        for (HashEntry<String, Integer> temp : hashtable.values()) {
            if (temp.value > max) {
                max = temp.value;
                maxWord = temp.key;
            }
        }

        resultStrings = maxWord + " " + Integer.toString(max);


        //part without stoplist
        max = 0;
        maxWord = "no word";
        for (String temp : stoplist) {
            hashtable.remove(temp);
        }
        for (HashEntry<String, Integer> temp : hashtable.values()) {
            if (temp.value > max) {
                max = temp.value;
                maxWord = temp.key;
            }
        }

        resultStrings = resultStrings + "\n" + maxWord + " " + Integer.toString(max);
        Write write = new Write();
        write.writeString(resultStrings);  //write the result in output file

    }


}

