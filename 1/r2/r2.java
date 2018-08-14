// https://open.kattis.com/problems/r2

import java.util.Scanner;

public class r2{

    static class MeanSet{
        int one, two, mean;

        public MeanSet(int one, int mean){
            this.one = one;
            this.mean = mean;
            this.two = backwardsCalcMean(one, mean);
        }

        public int backwardsCalcMean(int one, int mean){
            return (mean * 2) - one;
        }

    }

    public static void main(String[] a){
        Scanner scan = new Scanner(System.in);
        MeanSet ms = new MeanSet(scan.nextInt(), scan.nextInt());
        System.out.println(ms.two);
    }
}
