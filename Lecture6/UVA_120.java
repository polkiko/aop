/*
 *
 * 120. Stacks of Flapjacks
 *
 * @author Jesús Jerez
 * */

import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.Scanner;



class Main {

    static int length;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new BufferedInputStream(System.in, 4 * 1024))) {
            while (sc.hasNext()) {
                int [] pancakes = read(sc);
                int [] pancakesOrdered = getOrdered(pancakes);
                int i = length -1;
                while(i >= 0){
                    if (pancakes[i] != pancakesOrdered[i]) {
                        int pos = getPosition(pancakes, pancakesOrdered[i]);
                        if (pos == length){
                            flip(pancakes, (length - i));
                            System.out.print((length - i) + " ");
                        } else {
                            flip(pancakes, pos);
                            System.out.print(pos + " ");
                            if (pancakes[i] != pancakesOrdered[i]){
                                flip(pancakes, (length - i));
                                System.out.print((length - i) + " ");
                            }
                        }
                    }
                    i--;
                }
                System.out.println("0");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
            System.exit(1);
        }
    }

    private static int getPosition(int[] pancakes, int c) {
        int pos = 1;
        for (int i = length-1; i >= 0; i--) {
            if (pancakes[i] == c){
                break;
            }
            pos++;
        }
        return pos;
    }

    private static int[] getOrdered(int[] pancakes) {
        int [] pancakesOrdered = new int[pancakes.length];
        System.arraycopy(pancakes, 0, pancakesOrdered, 0, pancakes.length);
        Arrays.sort(pancakesOrdered);
        return pancakesOrdered;
    }

    private static void flip(int[] pancakes, int i) {
        int nSwaps = (length - (i)) / 2;
        for (int j = 0; j <= nSwaps; j++) {
            int tmp = pancakes[length-i-j];
            pancakes[length-i-j] = pancakes[j];
            pancakes[j] = tmp;
        }
    }

    private static int[] read(Scanner sc) {
        length = 0;
        String line = sc.nextLine();
        System.out.println(line);
        String[] parts = line.split(" ");
        int [] result = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            result[i] = Integer.parseInt(parts[i]);
            length++;
        }
        return result;
    }
}
