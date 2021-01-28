import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class E {


    static int cases, cont, min;
    static String fila;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new BufferedInputStream(System.in, 4 * 1024))) {
            cases = sc.nextInt();
            sc.nextLine();

            while (cases > 0){
                min = 0;
                fila = sc.nextLine();
                cont = 0;
                while (cont < fila.length()-1){
                    if (fila.charAt(cont) == fila.charAt(cont+1)){
                        min+=2;
                    } else {
                        min++;
                    }
                    cont+=2;
                }

                System.out.println(min);
                cases--;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
            System.exit(1);
        }
    }
}
