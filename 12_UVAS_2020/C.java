import java.io.BufferedInputStream;
import java.util.Scanner;

public class C {

    static float a, b, horaA, horaB, resta, aguja2, min;
    static int cases, horaEntA, total, mitad;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new BufferedInputStream(System.in, 4 * 1024))) {
            cases = sc.nextInt();
            sc.nextLine();
            while (cases > 0){
                a = sc.nextFloat();
                b = sc.nextFloat();
                horaEntA = (int) a * 3 / 90;
                horaA = a * 3 / 90;

                resta = horaA - horaEntA;
                min = resta * 60;
                aguja2 = min * 90 / 15;

                if ((Math.round(aguja2 * 100.0) / 100.0) == b){
                    System.out.println(String.format("%02d", horaEntA) + ":" + String.format("%02d", Math.round(min)));
                } else {
                    horaEntA = (int) b * 3 / 90;
                    horaA = b * 3 / 90;

                    resta = horaA - horaEntA;
                    min = resta * 60;
                    aguja2 = min * 90 / 15;

                    if ((Math.round(aguja2 * 100.0) / 100.0) == a){
                        System.out.println(String.format("%02d", horaEntA) + ":" + String.format("%02d", Math.round(min)));
                    } else {
                        System.out.println("ERROR");
                    }
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
