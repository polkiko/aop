/*
 *
 * 10099. The Tourist Guide
 *
 * @author Jesús Jerez
 * */

import java.io.BufferedInputStream;
import java.util.*;

class Main {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new BufferedInputStream(System.in, 4 * 1024))) {
            MapCity map = new MapCity();
            int nScenario = 1;
            while(sc.hasNext()){
                MapCity.N = sc.nextInt(); // number of cities
                MapCity.R = sc.nextInt(); // number of road segments

                if ((MapCity.N != 0) && (MapCity.R != 0)) {
                    map.read(sc);
                    System.out.println("Scenario #" + nScenario);
                    System.out.println("Minimum Number of Trips = " + map.solve());
                    System.out.println();
                    nScenario++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
            System.exit(1);
        }
    }
}

class MapCity {

    static int N, R, S, D, T;
    static int[][] connections = new int[99999][3];
    PriorityQueue<PartialSolution> frontier = new PriorityQueue<>();

    public void read(Scanner sc) {
        for (int i = 0; i < R*2; i=i+2) {
            connections[i][0] = sc.nextInt();
            connections[i][1] = sc.nextInt();
            connections[i][2] = sc.nextInt() - 1;
            connections[i + 1][0] = connections[i][1];
            connections[i + 1][1] = connections[i][0];
            connections[i + 1][2] = connections[i][2];
        }
        S = sc.nextInt(); // starting city
        D = sc.nextInt(); // destination city
        T = sc.nextInt(); // tourists
    }

    public int solve() {
        PartialSolution firstStatus = new PartialSolution(MapCity.S, 0);
        firstStatus.visitados[MapCity.S] = true;
        return backtracking(firstStatus);
    }

    public int backtracking(PartialSolution firstStatus) {
        frontier.clear();
        frontier.add(firstStatus);
        PartialSolution current;
        while (!frontier.isEmpty()) {
            current = frontier.poll();
            if (current.isValid() && current.isFinal()) {
                return current.maxTrips;
            } else if (current.isValid()) {
                List<PartialSolution> nextones = current.successors();
                Iterator<PartialSolution> cursor = nextones.iterator();
                while (cursor.hasNext()) {
                    frontier.add(cursor.next());
                    cursor.remove();
                }
            }
        }
        return 0;
    }
}

class PartialSolution implements Comparable {

    int node;
    int cost;
    int maxTrips;
    boolean[] visitados;

    public PartialSolution(int node, int cost) {
        this.node = node;
        this.cost = cost;
        this.visitados = new boolean[MapCity.N + 1];
    }

    public boolean isValid() {
        return true;
    }

    public boolean isFinal() {
        return node == MapCity.D;
    }

    public List<PartialSolution> successors() {
        ArrayList<PartialSolution> nextones = new ArrayList<>();
        for (int i = 0; i < MapCity.R*2; i++) {
            if (node == MapCity.connections[i][0] && !visitados[MapCity.connections[i][1]]) {
                PartialSolution next = new PartialSolution(MapCity.connections[i][1], MapCity.connections[i][2]);
                if (MapCity.N >= 0) System.arraycopy(this.visitados, 0, next.visitados, 0, MapCity.N);
                next.visitados[MapCity.connections[i][1]] = true;
                int nTrips = (int) Math.ceil((double) MapCity.T / MapCity.connections[i][2]);
                next.maxTrips = Math.max(this.maxTrips, nTrips);
                nextones.add(next);
            }
        }
        return nextones;
    }

    public int cost() {
        return (int) Math.ceil((double) MapCity.T / this.cost);
    }

    public int compareTo(Object o1) {
        return Integer.compare(this.cost(), ((PartialSolution)o1).cost());
    }
}
