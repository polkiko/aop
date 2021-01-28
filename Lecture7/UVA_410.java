/*
 *
 * 410. Station Balance
 *
 * @author Jesús Jerez
 * */

import java.io.BufferedInputStream;
import java.util.*;

class Main {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new BufferedInputStream(System.in, 4 * 1024))) {
            int C, S, count = 0;
            int[] masses;
            int[][] camaras;
            while (sc.hasNext()) {
                count++;
                C = sc.nextInt(); // Chambers
                S = sc.nextInt(); // Specimens

                masses = new int[S];
                for (int i = 0; i < S; i++){
                    masses[i] = sc.nextInt();
                }

                Arrays.sort(masses);

                camaras = new int[C][2];

                int cont = S;
                int j = 0;
                while(j < C) {
                    if (cont > 0){
                        camaras[j][0] = masses[cont-1];
                    }
                    j++;
                    cont--;
                }
                j--;
                while (j >= 0) {
                    if (cont > 0){
                        camaras[j][1] = masses[cont-1];
                    }
                    cont--;
                    j--;
                }


                System.out.println("Set #" + count);
                for (int i = 0; i < C; i++){
                    System.out.print(" " + i + ":");
                    if(camaras[i][0] != 0){
                        System.out.print(" " + camaras[i][0]);
                    }
                    if(camaras[i][1] != 0){
                        System.out.print(" " + camaras[i][1]);
                    }
                    System.out.println();
                }

                double AM = 0, sum, imbalance = 0;

                for (int i = 0; i < S; i++){
                    AM += masses[i];
                }
                AM = AM / C;

                for (int i = 0; i < C; i++) {
                    sum = camaras[i][0] + camaras[i][1] - AM;
                    if(sum < 0){
                        sum = sum * (-1);
                    }
                    imbalance += sum;
                }

                String string = String.format(Locale.US, "%.5f", imbalance);
                System.out.println("IMBALANCE = " + string + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
            System.exit(1);
        }
    }
}
