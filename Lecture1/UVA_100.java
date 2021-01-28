/*
*
* 100. The 3n + 1 problem
*
* @author Jesús Jerez
* */

import java.util.Scanner;

class Main {

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {

            int i = sc.nextInt();
            int j = sc.nextInt();
            int min = Math.min(i, j);
            int max = Math.max(i, j);
            int max_cycle = 1;

            while (min <= max) {
                int n = min;
                int cycle_length = 1; // At least it will do 1 cycle

                while (n != 1) {
                    n = ((n % 2) != 0) ? (3 * n) + 1 :  n / 2;
                    cycle_length++;
                }

                max_cycle = Math.max(cycle_length, max_cycle);
                min++;
            }

            System.out.println(i + " " + j + " " + max_cycle);
        }
    }
}
