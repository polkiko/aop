/*
*
* 861. Little Bishops
*
* @author Jesús Jerez
*
* NOTA: Este código no pasa las pruebas del UVA Online Judge por TLE. La solución pasa
* por no probar con todas las posibilidades (el alfil sólo puede ocupar unas determinadas
* casillas en cada turno...)
* */
import java.io.BufferedInputStream;
import java.util.*;

class Main {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new BufferedInputStream(System.in, 4 * 1024))) {
            // La clase BufferedInputStream() nos permite reservar bytes en memoria (en este ejemplo 4KB). El tiempo
            // que tarda en hacer dicha reserva no depende de la cantidad de memoria, sino de las veces que se haga.
            // Es mejor pasarse que quedarse corto con la reserva. Si el archivo que queremos coger es más grande
            // de lo que le pedimos no pasará nada, porque la clase se encargará de volver a hacerlo.
            Chessboard chessboard = new Chessboard();
            while(sc.hasNextInt()) {
                chessboard.read(sc);
                if (Chessboard.n != 0){
                    System.out.println(chessboard.solve());
                } else {
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

class Chessboard {

    static int n, k;
    Stack<PartialSolution> frontier = new Stack<>();

    public void read(Scanner sc) {
        n = sc.nextInt(); // nxn chessboard
        k = sc.nextInt(); // k bishops
    }

    public int solve(){
        //mostrarTablero();
        int[][] a = new int[Chessboard.n][Chessboard.n];
        PartialSolution firstStatus = new PartialSolution(a, 0, Chessboard.n * Chessboard.n);
        return backtracking(firstStatus);
    }

    ArrayList<int[][]> solValid = new ArrayList<>();
    public int backtracking(PartialSolution firstStatus) {
        solValid.clear();
        int sum = 0;
        //System.out.println("Tablero de " + Chessboard.n + "x" + Chessboard.n + " y " + Chessboard.k + " bishops.");
        frontier.clear();

        frontier.add(firstStatus);

        PartialSolution current;
        while (!frontier.isEmpty()) {
            current = frontier.pop();
            //System.out.println("--> Estado con " + current.bishops + " alfiles. Y tablero: ");
            //mostrarTablero(current.board);
            if (current.isValid() && current.isFinal()) {
                if(noEsta(current.board)){
                    sum++;
                    solValid.add(current.board);
                }
                //System.out.println("Es valido!");
            } else if(current.isValid()) {
                List<PartialSolution> nextones = current.successors();
                Iterator<PartialSolution> cursor = nextones.iterator();
                while (cursor.hasNext()) {
                    frontier.push(cursor.next());
                    cursor.remove();
                }
            }
        }
        return sum;
    }

    public boolean noEsta(int[][] board) {
        boolean esIgual = true;
        for (int m = 0; m < solValid.size(); m++){

            int[][] iBoard = solValid.get(m);

            int iguales = 0;
            for(int i = 0; i < iBoard.length; i++){
                for(int j = 0; j < iBoard.length; j++){
                    if(iBoard[i][j] == board[i][j]){
                        iguales++;
                    }
                }
            }
            if(iguales == Chessboard.n * Chessboard.n){
                esIgual = false;
                break;
            }

        }
        return esIgual;
    }

    public void mostrarTablero(int[][] a){
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < a.length; j++){
                System.out.print("[" + a[i][j] + "]");
            }
            System.out.println();
        }
    }
}

class PartialSolution {

    int[][] board;// = new int[Chessboard.n][Chessboard.n]; // Create board
    int [][] temporalBoard = new int[Chessboard.n][Chessboard.n];
    int whiteCells;
    int bishops;

    public PartialSolution(int[][] board, int bishops, int whiteCells){
        this.board = board;
        cloneArray(board, this.temporalBoard);
        this.bishops = bishops; // Bishops
        this.whiteCells = whiteCells;
    }

    public boolean isValid(){
        return bishops <= Chessboard.k;
    }

    public boolean isFinal(){
        return bishops == Chessboard.k;
    }

    public List<PartialSolution> successors() {
        List<PartialSolution> nextones = new ArrayList<>();
        for (int i = 0; i < whiteCells; i++) {
            int[][] newBoard = new int[Chessboard.n][Chessboard.n];
            cloneArray(this.board, newBoard);
            insertBishop(newBoard);
            PartialSolution newStatus = new PartialSolution(newBoard, this.bishops + 1, updateWhiteCells(newBoard));
            nextones.add(newStatus);
        }
        return nextones;
    }

    boolean insertado = false;
    public void insertBishop(int[][] newBoard) {
        insertado = false;
        for(int i = 0; i < temporalBoard.length && !insertado; i++){
            for(int j = 0; j < temporalBoard[i].length && !insertado; j++){
                if(temporalBoard[i][j] == 0 || temporalBoard[i][j] == 1){ // If cell is white or black
                    newBoard[i][j] = 2;
                    temporalBoard[i][j] = 2;
                    int[] pos = {i,j};
                    updateCells(newBoard, pos);
                    insertado = true;
                }
            }
        }
    }

    public void updateCells(int[][] newBoard, int[] pos) {
        disallowCells(1, -1, newBoard, pos);
        disallowCells(1, 1, newBoard, pos);
        disallowCells(-1, 1, newBoard, pos);
        disallowCells(-1, -1, newBoard, pos);
    }

    public void disallowCells(int movRow, int movCol, int[][] newBoard, int[] pos) {
        int row = pos[0] + movRow;
        int col = pos[1] + movCol;

        while(row < Chessboard.n && col < Chessboard.n && row >= 0 && col >= 0){
            if(newBoard[row][col] == 0 || newBoard[row][col] == 1){ // If cell is white or black
                newBoard[row][col] = 3; // Disallow this cell
            }
            row += movRow;
            col += movCol;
        }
    }

    public void cloneArray(int[][] from, int[][] to) {
        for(int i = 0; i < from.length; i++){
            for(int j = 0; j < from[i].length; j++){
                to[i][j] = from[i][j];
            }
        }
    }

    public int updateWhiteCells(int[][] a) {
        int cont = 0;
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < a[i].length; j++){
                if(a[i][j] == 0 || a[i][j] == 1){ // If cell is white or black
                    cont++;
                }
            }
        }
        return cont;
    }


}
