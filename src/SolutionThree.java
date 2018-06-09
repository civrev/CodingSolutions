import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Scanner;

public class SolutionThree {

    //Solved using a hashtable and array of hashsets

    private static final int SIZE = 12;
    private static Hashtable<String, Integer> table = new Hashtable();
    private static HashSet<String>[] setArray = new HashSet[SIZE];
    private static HistEntry[] entries;
    private static int maxLength = 0;

    public static void main(String[] args) throws FileNotFoundException {

        //initialize HashSets in Array
        for(int i = 0; i < SIZE; i++){
            setArray[i] = new HashSet<String>();
        }

        //read input into HashTable and HashSet array
        readInput();

        //place very frequent words in array and sort them
        entries = new HistEntry[setArray[SIZE - 1].size()];

        int index = 0;

        for(String word : setArray[SIZE - 1]){
            entries[index] = new HistEntry(word, table.get(word));
            index++;
        }

        Arrays.sort(entries);

        //write output to file
        writeOutput();

    }

    private static void readInput() throws FileNotFoundException {
        //read input from file into HashTable and HashSet array
        File file = new File("input.txt");
        Scanner scan = new Scanner(file);
        scan.useDelimiter("[^a-zA-Z]");

        //The HashTable has both the word and the frequency
        //The position in the HashSet array is equal to the word frequency
        //If the word is very frequent (greater or equal to HashSet array size)
        //the it is placed in the last spot
        while(scan.hasNext()){
            String word = scan.next();

            if(validate(word)){

                //not case sensitive, display in lowercase
                word = word.toLowerCase();

                //compare the length of this word to max length so far (for output formatting)
                if(word.length() > maxLength)
                    maxLength = word.length();

                if(table.containsKey(word)){
                    //if it is already in the table, increase it by one and move it in HasHSet array if needed
                    int num = table.get(word);
                    table.put(word, num+1);
                    if(num < SIZE - 1) {
                        setArray[num].remove(word);
                        setArray[num+1].add(word);
                    }
                }else{
                    //otherwise add it a place it at 1
                    table.put(word, 1);
                    setArray[1].add(word);
                }
            }
        }

        //done reading input
        scan.close();
    }

    private static void writeOutput() throws FileNotFoundException {
        //write histogram to file
        PrintWriter writer = new PrintWriter("output.txt");

        for(HistEntry entry : entries){
            writer.println(entry);
        }

        for(int i = SIZE - 2; i >= 0; i--){
            HashSet<String> temp = setArray[i];

            for(String word : temp){
                writer.println(new HistEntry(word, i));
            }
        }

        //done writing file
        writer.close();
    }

    public static boolean validate(String word) {
        //Checks a token picked up by the delimiter to see if it is a valid word
        //if it is valid it will start with a letter
        if (word.length() == 0)
            return false;

        char let = word.charAt(0);
        return (let >= 'A' && let <= 'Z') || (let >= 'a' && let <= 'z');
    }

    static class HistEntry implements  Comparable<HistEntry>{
        //Helper class used for sorting and formatting the output

        String word;
        int value;

        public HistEntry(String word, int value) {
            this.word = word;
            this.value = value;
        }

        public int compareTo(HistEntry entry) {
            if (entry == null)
                return 1;
            else
                return entry.value - value;
        }

        //formatted the output here
        public String toString() {

            //using StringBuilder over String
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
