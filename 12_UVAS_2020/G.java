import java.io.BufferedInputStream;
import java.util.Scanner;

public class G {

    //static int[] copas = new int[500];
    static int nCopas, total, max;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new BufferedInputStream(System.in, 4 * 1024))) {
            while (sc.hasNextInt()){
                nCopas = sc.nextInt();
                sc.nextLine();
                System.out.println("++ " + nCopas);
                if (nCopas != 0) {
                    total = 0;
                    for (int i = 0; i < nCopas; i++){
                        System.out.println(sc.nextInt());
                    }
                    sc.nextLine();
//                    max = 0;
//                    for(int i = 0; i < nCopas; i++){
//                        if(copas[i] > max)
//                            max = copas[i];
//                    }
//                    for (int i = 0; i < nCopas; i++){
//                        total += max - copas[i];
//                    }
//                    System.out.println(total);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
