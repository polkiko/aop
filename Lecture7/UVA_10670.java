/*
 *
 * 10670. Work Reduction
 *
 * @author Jesús Jerez
 * */

import java.io.BufferedInputStream;
import java.util.*;

public class Main {
    static int cases, N, M, L, A, B, init, next, coste;
    static String agency;
    static ArrayList<Par> agencias = new ArrayList<>();

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new BufferedInputStream(System.in, 4 * 1024))) {
            cases = sc.nextInt();
            for (int i = 1; i <= cases; i++){
                System.out.println("Case " + i);
                N = sc.nextInt(); // Starting workload
                M = sc.nextInt(); // Target workload
                L = sc.nextInt(); // Agencies
                sc.nextLine();
                agencias.clear();
                while(L > 0) { // For each agency
                    String line = sc.nextLine();
                    String[] parts = line.split(":");
                    agency = parts[0];
                    String[] valores = parts[1].split(",");
                    A = Integer.parseInt(valores[0]);
                    B = Integer.parseInt(valores[1]);
                    init = N;
                    coste = 0;
                    while(init > M){
                        next = (int) Math.floor(init / 2.0);
                        if(next >= M){ // Se puede ir dividiendo entre 2
                            if(B < A*(init-next)){
                                // Entonces debe coger la B
                                coste += B;
                            } else {
                                // Debe coger la opción A
                                coste = coste + (A*(init-next));
                            }
                            init = next;
                        } else { // Ya no se puede dividir entre 2, no queda otra que ir 1 a 1
                            coste = coste + A;
                            init--;
                        }
                    }
                    agencias.add(new Par(agency, coste));
                    L--;
                }
                // Print results
                Collections.sort(agencias);
                for (Par par: agencias){
                    System.out.println(par.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
            System.exit(1);
        }
    }

    private static int coste(int next, int b) {
        return 0;
    }
}

class Par implements Comparable {
    String name;
    int coste;

    public Par(String name, int coste) {
        this.name = name;
        this.coste = coste;
    }

    public int compareTo(Object o1) {
        int comparaOrden = Integer.compare(this.coste, ((Par) o1).coste);
        if (comparaOrden != 0) {
            return comparaOrden;
        } else {
            return this.name.compareTo(((Par) o1).name);
        }

    }

    public String toString() {
        return name + " " + coste;
    }
}
