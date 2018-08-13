// https://open.kattis.com/problems/tarifa

import java.util.Scanner;

public class tarifa {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int cap = scan.nextInt();
        int n = scan.nextInt();
        int extra = 0;
        for(int i=0; i<n; i++){
            int x = scan.nextInt();
            extra = extra + cap - x;
        }
        System.out.println(extra+cap);
    }

}
