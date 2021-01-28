/*
*
* 1112. Mice and Maze
*
* @author Jesús Jerez
* */

import java.io.BufferedInputStream;
import java.util.*;

class Main {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new BufferedInputStream(System.in, 4 * 1024))) {
            int cases = sc.nextInt();
            Maze maze = new Maze();
            for(int i = 0; i < cases; i++) {
                maze.read(sc);
                System.out.println(maze.solve());
                if(i < cases-1){ // Delete last line
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
            System.exit(1);
        }
    }
}

class Maze{

    static int N, T, E, M;
    static int[][] connections = new int[1000][3];
    Stack<PartialSolution> frontier = new Stack<>();

    public void read(Scanner sc) {
        // Arrays.fill();
        N = sc.nextInt(); // n cells
        E = sc.nextInt(); // exit cell
        T = sc.nextInt(); // count-down timer
        M = sc.nextInt(); // connections
        for(int i = 0; i < M; i++){
            connections[i][0] = sc.nextInt();
            connections[i][1] = sc.nextInt();
            connections[i][2] = sc.nextInt();
        }
    }

    public int solve(){
        int nMice = 0;
        for(int i = 1; i <= N; i++) {
            PartialSolution firstStatus = new PartialSolution(i, 0);
            if(backtracking(firstStatus)){
                nMice += 1;
            }
        }
        return nMice;
    }

    public boolean backtracking(PartialSolution firstStatus) {
        frontier.clear();

        frontier.add(firstStatus);

        PartialSolution current;
        boolean arrives = false;
        while (!frontier.isEmpty() && !arrives) {
            current = frontier.pop();
            if (current.isValid() && current.isFinal()) {
                arrives = true;
            } else if(current.isValid()) {
                List<PartialSolution> nextones = current.successors();
                Iterator<PartialSolution> cursor = nextones.iterator();
                while (cursor.hasNext()) {
                    frontier.push(cursor.next());
                    cursor.remove();
                }
            }
        }
        return arrives;
    }
}

class PartialSolution {

    public int node;
    public int cost;

    public PartialSolution(int nodo, int coste){
        this.node = nodo;
        this.cost = coste;
    }

    public boolean isValid(){
        return cost <= Maze.T;
    }

    public boolean isFinal(){
        return node == Maze.E;
    }

    public List<PartialSolution> successors() {
        List<PartialSolution> nextones = new ArrayList<>();
        for (int i = 0; i < Maze.M; i++){
            if (Maze.connections[i][0] == node){
                PartialSolution newStatus = new PartialSolution(Maze.connections[i][1], cost + Maze.connections[i][2]);
                nextones.add(newStatus);
            }
        }
        return nextones;
    }
}
