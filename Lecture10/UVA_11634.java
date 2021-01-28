/*
 *
 * 11634. Generate Random Numbers
 *
 * @author Jesús Jerez
 * */

import java.io.BufferedInputStream;
import java.util.Scanner;

public class Main {

    static int[] numerosVistos = new int[1000];
    static int length, count;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new BufferedInputStream(System.in, 4 * 1024))) {
            String valor;
            int a0;
            while ((a0 = sc.nextInt()) != 0) {

                count = 0;
                while (!esta(a0)) {
                    numerosVistos[count] = a0;
                    a0 *= a0;
                    if(lengthOf(a0) < 8){
                        valor = String.format("%08d", a0);
                    } else {
                        valor = String.valueOf(a0);
                    }
                    a0 = Integer.parseInt(valor.substring(2,6));
                    count++;
                }

                System.out.println(count);

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
            System.exit(1);
        }
    }

    private static int lengthOf(int a0) {
        // Se puede saber el tamaño si se va reduciendo por potencias de 2 a partir del número maximo posible
        // Como en este caso será de 8, nos vale con 2^0, 2^1, 2^2 (1+2+4 = 7 + 1 = 8)
        length = 1;
        if (a0 >= 10000) {
            length += 4;
            a0 /= 10000;
        }
        if (a0 >= 100) {
            length += 2;
            a0 /= 100;
        }
        if (a0 >= 10) {
            length += 1;
        }
        return length;
    }

    private static boolean esta(int a0) {
        for (int i = 0; i < count; i++) {
            if (a0 == numerosVistos[i]){
                return true;
            }
        }
        return false;
    }
}
