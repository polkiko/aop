import java.io.BufferedInputStream;
import java.util.Scanner;

public class A {

    static int cases, a, b, total, mitad;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new BufferedInputStream(System.in, 4 * 1024))) {
            cases = sc.nextInt();
            sc.nextLine();
            while (cases > 0){
                a = sc.nextInt();
                b = sc.nextInt();

                total = a + b;
                mitad = b / 2;

                System.out.println(a-b);
                cases--;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
            System.exit(1);
        }
    }
}
