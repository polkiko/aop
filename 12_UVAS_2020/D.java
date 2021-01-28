import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class D {


    static int cases, nCalendarios, perdidos;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new BufferedInputStream(System.in, 4 * 1024))) {
            cases = sc.nextInt();
            sc.nextLine();

            while (cases > 0){
                nCalendarios = sc.nextInt();
                sc.nextLine();
                int[] years = new int[nCalendarios];
                for (int i = 0; i < nCalendarios; i++){
                    years[i] = sc.nextInt();
                }

                Arrays.sort(years);

                perdidos =  years[nCalendarios-1] - years[0] - (nCalendarios-1);
                System.out.println(perdidos);
                cases--;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
            System.exit(1);
        }
    }
}
