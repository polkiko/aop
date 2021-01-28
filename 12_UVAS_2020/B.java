import java.io.BufferedInputStream;
import java.util.Scanner;

public class B {

    static int cases, horas, encendidos, horasEst, horasTotal;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new BufferedInputStream(System.in, 4 * 1024))) {
            cases = sc.nextInt();
            sc.nextLine();


            while(cases > 0){
                horas = sc.nextInt();
                encendidos = sc.nextInt();
                horasEst = sc.nextInt();

                horasTotal = encendidos * horasEst;

                if (horasTotal == horas){
                    System.out.println("AMBAS");
                } else if (horasTotal > horas){
                    System.out.println("HORAS");
                } else {
                    System.out.println("ENCENDIDOS");
                }
                cases--;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
            System.exit(1);
        }
    }
}
