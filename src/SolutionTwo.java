import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Scanner;

public class SolutionTwo {

    //Solved Using a HashTable working together with MaxHeap

    static Hashtable<String, Integer> table = new Hashtable();
    static int maxLength = 0;

    public static void main(String[] args) throws FileNotFoundException{

        //reads from text file into Hash Table and Max Heap, and writes output to text file

        MaxHeap heap = new MaxHeap();

        File file = new File("input.txt");
        Scanner scan = new Scanner(file);
        scan.useDelimiter("[^a-zA-Z]");

        //read input to HashTable and Heap
        while(scan.hasNext()){
            String word = scan.next();

            if(validate(word)){
                //if it is a word, check if it is in the hash table
                word = word.toLowerCase();

                //compare the length of this word to max length so far (for output formatting)
                if(word.length() > maxLength)
                    maxLength = word.length();

                //if it is in the has table, modify its value in the heap
                if(table.containsKey(word)){
                    heap.modify(table.get(word), 1);
                }else {
                    table.put(word, 0);
                    heap.insert(word);
                }
            }
        }

        //done reading input
        scan.close();

        //write solution to file
        PrintWriter writer = new PrintWriter("output.txt");
        int size = heap.size();
        for(int i = 0; i < size; i++){
            writer.println(heap.remove());
        }

        writer.close();

    }

    public static boolean validate(String word){
        //it is a word if all characters are A->Z or a->z
        //banking on the delimiter to filter out individual tokens
        //so I only check the first character for validity

        if(word.length() == 0)
            return false;

        char let = word.charAt(0);
        return (let >= 'A' && let <= 'Z') || (let >= 'a' && let <= 'z');
    }

    //Array based MaxHeap
    static class MaxHeap{

        private Node[] heap;
        private int size;

        public MaxHeap(){
            heap = new Node[64];
            size = 0;
        }

        //Nodes in MaxHeap
        class Node{
            int freq;
            String key;

            public  Node(String key){
                this.key = key;
                freq = 1;
            }

            //formatted the output here
            public String toString() {

                //uses StringBuilder to make long strings
                StringBuilder output = new StringBuilder();

                //format for even histogram
                for(int i = 0; i < maxLength - key.length(); i++){
                    output.append(' ');
                }

                output.append(key);
                output.append(" | ");

                for (int i = 0; i < freq; i++) {
                    output.append('=');
                }

                output.append(" (");
                output.append(freq);
                output.append(')');

                return output.toString();
            }
        }

        //Methods for MaxHeap **********************************

        //makes the array bigger
        private void expand(){
            Node[] temp = new Node[heap.length * 2];

            for(int i = 0; i < heap.length; i++){
                temp[i] = heap[i];
            }

            heap = temp;
        }

        //getter for size
        public int size(){ return size;}

        public void insert(String key){
            //insert node into heap
            Node node = new Node(key);

            //add node to next available position
            //since this is a max heap, and all values are inserted as 1
            //no need to upheap, new values will always be a leaf
            heap[size] = node;

            //update hashtable with position of key in heap
            table.put(key, size);

            //increase size
            size++;

            //check to see if array is full, increase if it is
            if(size == heap.length){
                expand();
            }
        }

        public void modify(int pos, int amount){
            Node node = heap[pos];

            node.freq += amount;

            Node parent = heap[parentOf(pos)];

            while(parent.freq < node.freq){
                heap[pos] = parent;
                table.put(parent.key, pos);
                heap[parentOf(pos)] = node;
                pos = parentOf(pos);
                parent = heap[parentOf(pos)];
            }

            table.put(node.key, pos);
        }

        private void downheap(){

            int pos = 0;
            Node node = heap[size - 1];
            heap[size - 1] = null;
            heap[0] = node;

            Node left = heap[leftOf(pos)];
            Node right = heap[rightOf(pos)];

            //move Node down to keep MaxHeap ordered if needed
            while(left != null && (node.freq < left.freq || (right != null && node.freq < right.freq))){
                if(right == null || left.freq > right.freq){
                    //swap with left
                    heap[pos] = left;
                    table.put(left.key, pos);
                    heap[leftOf(pos)] = node;
                    pos = leftOf(pos);
                }else {
                    //swap with right
                    heap[pos] = right;
                    table.put(right.key, pos);
                    heap[rightOf(pos)] = node;
                    pos = rightOf(pos);
                }
                if(inBounds(leftOf(pos)) && inBounds(rightOf(pos))) {
                    left = heap[leftOf(pos)];
                    right = heap[rightOf(pos)];
                }else{
                    left = null;
                }
            }

            table.put(node.key, pos);
        }

        public Node remove(){
            //returns the root of the MaxHeap, and removes it from the heap
            //returns null if heap is empty
            if(size != 0){
                Node first = heap[0];

                if(size != 1)
                    downheap();

                table.remove(first.key);

                size--;
                return first;
            }else{
                return null;
            }
        }

        //returns the position of the parent of a given position in the heap
        private int parentOf(int pos){
            int num;
            if (pos % 2 == 0)
                num =  (pos - 2) / 2;
            else
                num =  (pos - 1) / 2;
            return num > 0 ? num : 0;
        }

        //left and right indices for a given position
        private int leftOf(int pos){ return (pos * 2) + 1; }
        private int rightOf(int pos){ return (pos * 2) + 2; }

        //check if a position is in the array
        private boolean inBounds(int pos){ return pos >= 0 && pos < heap.length;}

    }
}
