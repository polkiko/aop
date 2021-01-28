/*
*
* 10038. Jolly Jumpers
*
* @author Jesús Jerez
* */

import java.util.Scanner;
import java.util.Arrays;

class Main {

    public static boolean jolly(int[] numbers){

        int i = 0;
        int j = 1;
        boolean jolly = true;
        int[] differences = new int[numbers.length-1];

        while(i < numbers.length-1){
            int difference = Math.abs(numbers[i] - numbers[i+1]);
            differences[i] = difference;
            i++;
        }

        Arrays.sort(differences);

        while(jolly && (j < differences.length+1)){
            if(differences[j-1] != j){
                jolly = false;
            }
            j++;
        }
        return jolly;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()){
            int length = sc.nextInt();
            if(length == 1){
                sc.nextInt();
                System.out.println("Jolly");
            }else{
                int[] numbers = new int[length];

                for (int i = 0; i < length; i++) {
                    numbers[i] = sc.nextInt();
                }

                if(jolly(numbers)){
                    System.out.println("Jolly");
                } else {
                    System.out.println("Not jolly");
                }
            }
        }
    }
}
