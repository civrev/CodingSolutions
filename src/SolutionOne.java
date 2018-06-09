import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class SolutionOne {

    //Solved Using a HashTable

    static Hashtable<String, Integer> table = new Hashtable();

    static int maxLength = 0;

    public static void main(String[] args) throws FileNotFoundException {

        //reads input into a hash table
        readInput();

        //now to sort the elements in the hash table
        //load into array
        HashEntry[] entries = new HashEntry[table.size()];

        int index = 0;

        for(String word : table.keySet()){
            entries[index] = new HashEntry(word, table.get(word));
            index++;
        }

        //sort it
        Arrays.sort(entries);


        //print out solution
        PrintWriter writer = new PrintWriter("output.txt");
        for(HashEntry entry : entries){
            writer.println(entry);
        }
        writer.close();


    }

    public static void readInput() throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scan = new Scanner(file);

        //use scanner with multiple delimiters
        scan.useDelimiter("[^a-zA-Z]");

        //read input to hash table
        while(scan.hasNext()) {
            String word = scan.next();

            //check if it is in fact a word
            if(valid(word)){

                //if it is a word, check if it is in the hash table
                word = word.toLowerCase();

                //compare the length of this word to max length so far (for output formatting)
                if(word.length() > maxLength)
                    maxLength = word.length();

                if(table.containsKey(word)){
                    //if in table, increase current value by one
                    table.put(word, table.get(word) + 1);
                }else{
                    //otherwise add it to table with frequency 1
                    table.put(word, 1);
                }

            }
        }

        //done reading input
        scan.close();
    }

    public static boolean valid(String word){
        //it is a word if all characters are A->Z or a->z
        //banking on the delimiter to filter out individual tokens
        //so I only check the first character for validity

        if(word.length() == 0)
            return false;

        char let = word.charAt(0);
        return (let >= 'A' && let <= 'Z') || (let >= 'a' && let <= 'z');
    }

    //helper class
    static class HashEntry implements Comparable<HashEntry> {
        String word;
        int value;

        public HashEntry(String word, int value) {
            this.word = word;
            this.value = value;
        }

        public int compareTo(HashEntry entry) {
            if (entry == null)
                return 1;
            else
                return entry.value - value;
        }

        //formatted the output here
        public String toString() {

            //uses StringBuilder to make long strings
            StringBuilder output = new StringBuilder();

            //format for even histogram
            for(int i = 0; i < maxLength - word.length(); i++){
                output.append(' ');
            }

            output.append(word);
            output.append(" | ");

            for (int i = 0; i < value; i++) {
                output.append('=');
            }

            output.append(" (");
            output.append(value);
            output.append(')');

            return output.toString();
        }
    }
}
