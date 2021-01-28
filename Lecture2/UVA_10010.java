/*
*
* 10010. Where's Waldorf
*
* @author Jesús Jerez
* */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Main {

    final static Scanner sc = new Scanner(System.in);

    public static char[][] getSoup(int rows, int columns){
        String[] letters = new String[rows];
        for(int i = 0; i < rows; i++){
            letters[i] = sc.nextLine().toLowerCase();
        }

        char[][] soup = new char[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++) {
                soup[i][j] = letters[i].charAt(j);
            }
        }
        return soup;
    }

    public static List<String> getWords(int numberOfWords){
        sc.nextLine();
        List<String> words = new ArrayList<String>();
        for(int i = 0; i < numberOfWords; i++){
            words.add(sc.nextLine().toLowerCase());
        }
        return words;
    }

    private static boolean searchWord(int[] pos, String word, int movrow, int movcolumn, char[][] soup) {

        int movs = 0;
        int row = pos[0];
        int column = pos[1];

        String wordSoup = "";
        while(movs < word.length() && row < soup.length && column < soup[0].length && row >= 0 && column >= 0){
            wordSoup += soup[row][column];
            row += movrow;
            column += movcolumn;
            movs++;
        }

        return word.equals(wordSoup);
    }

    public static void main(String[] args) {

        while (sc.hasNextInt()){
            int cases = sc.nextInt();
            while(cases > 0){

                int rows = sc.nextInt();
                int columns = sc.nextInt();
                sc.nextLine();

                char[][] soup = getSoup(rows, columns);
                List<String> words = getWords(sc.nextInt());

                for(int w = 0; w < words.size(); w++) {
                    char firstLetter = words.get(w).charAt(0);
                    List<int[]> posiblePos = new ArrayList<int[]>();

                    for(int i = 0; i < soup.length; i++){
                        for(int j = 0; j < soup[i].length; j++){
                            if(soup[i][j] == firstLetter ){
                                int[] currentPos = {i,j};
                                posiblePos.add(currentPos);
                            }
                        }
                    }
                    boolean found = false;
                    int i = 0;
                    int[] position = new int[2];
                    while(i < posiblePos.size()) {
                        if (searchWord(posiblePos.get(i), words.get(w), 0, 1, soup)){ // Horizontal Right
                            position = posiblePos.get(i);
                            break;
                        }
                        if (searchWord(posiblePos.get(i), words.get(w), 0, -1, soup)){ // Horizontal Left
                            position = posiblePos.get(i);
                            break;
                        }
                        if (searchWord(posiblePos.get(i), words.get(w), 1, 0, soup)){ // Vertical Down
                            position = posiblePos.get(i);
                            found = true;
                            break;
                        }
                        if (searchWord(posiblePos.get(i), words.get(w), -1, 0, soup)){ // Vertical Up
                            position = posiblePos.get(i);
                            found = true;
                            break;
                        }
                        if (searchWord(posiblePos.get(i), words.get(w), -1, 1, soup)){ // Diagonal up right
                            position = posiblePos.get(i);
                            found = true;
                            break;
                        }
                        if (searchWord(posiblePos.get(i), words.get(w), -1, -1, soup)){ // Diagonal up left
                            position = posiblePos.get(i);
                            found = true;
                            break;
                        }
                        if (searchWord(posiblePos.get(i), words.get(w), 1, 1, soup)){ // Diagonal down right
                            position = posiblePos.get(i);
                            found = true;
                            break;
                        }
                        if (searchWord(posiblePos.get(i), words.get(w), 1, -1, soup)){ // Diagonal down left
                            position = posiblePos.get(i);
                            found = true;
                            break;
                        }
                        i++;
                    }
                    int x = position[0] + 1;
                    int y = position[1] + 1;
                    System.out.println(x + " " + y);

                }
                if (cases != 1) {
                    System.out.println("");
                }
                cases--;

            }
        }
    }


}
