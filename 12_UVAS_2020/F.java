import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class F {


    static int cantidad;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new BufferedInputStream(System.in, 4 * 1024))) {
            while (sc.hasNextInt()){
                cantidad = sc.nextInt();
                if (cantidad == 0){
                    //break;
                } else {
                    String binario = Integer.toBinaryString(cantidad);
                    String binaryString = invertir(binario);
                    int decimal = Integer.parseInt(binaryString,2);
//                    System.out.println("Recibido: " + cantidad);
//                    System.out.println("Binario:  " + binario);
//                    System.out.println("Invertido: " + decimal);
                    if ((cantidad % 2) == 0){
                        System.out.println("SI");
                    }else{
                        System.out.println("NO");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
            System.exit(1);
        }
    }

    private static String invertir(String binario){
        StringBuilder invertido = new StringBuilder();
        for (int i = 0; i < binario.length(); i++){
            invertido.append(binario.charAt(binario.length()-1-i));
            //numeros[i] = binario.charAt(binario.length()-1-i) -48;
        }
        return invertido.toString();
    }
}
