// https://open.kattis.com/problems/timeloop

import java.util.Scanner;

public class timeloop{
    public static void main(String[] a){
        Scanner scan = new Scanner(System.in);
        int times = scan.nextInt();
        String message = "Abracadabra";
        
        for(int i = 1; i <= times; i++){
            System.out.println(i + " " + message);
        }
    }
}
