import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class SopaConcurrente {

    public static List<String> words = new ArrayList<>();

    // Rows and columns sizes
    static int rows, columns;
 
    // For searching in all 8 direction
    static int[] x = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int[] y = { -1, 0, 1, -1, 1, -1, 0, 1 };

    static String getDirection(int direction) {
        switch (direction) {
            case 1:
                return "Arriba ↑";
            case 2:
                return "Diagonal Ariba Derecha ⬀";
            case 3:
                return "Izquierda ←";
            case 4:
                return "Derecha →";
            case 5:
                return "Digonal Abajo Izquierda ⬃";
            case 6:
                return "Abajo ↓";
            case 7:
                return "Diagonal Abajo Derecha ⬂";
            case 8:
                return "Diagonal Arriba Izquierda ⬁";
            default:
                return "NA";
        }
    }

    private static boolean searchInDirection(char[][] grid, int row, int col, int dir, String word, int len) {
        int k, rd = row + x[dir], cd = col + y[dir];
        for (k = 1; k < len; k++) {
            if (rd >= rows || rd < 0 || cd >= columns || cd < 0) break;

            if (grid[rd][cd] != word.charAt(k)) break;

            // Moving in particular direction
            rd += x[dir];
            cd += y[dir];
        }
        if (k == len){
            System.out.println("Palabra encontrada: " + word);
            System.out.println(getDirection(dir));
            return true;
        }
        return false;
    }

    static boolean iterativeSearch(char[][] grid, int row, int col, String word) throws InterruptedException {
        // If first character of word
        // doesn't match with
        // given starting point in grid.
        if (grid[row][col] != word.charAt(0)) return false;
 
        // Get the size og search word
        int len = word.length();

        
        List<Thread> hilosL = new ArrayList<>();
        int hilos = 15;

        for(int dir = 0; dir < 8; ++dir){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    searchInDirection(grid, row, col, Integer.parseInt(Thread.currentThread().getName()), word, len); 
                }
            },""+dir);
            t.start();
            hilosL.add(t);

            if(hilos == hilosL.size()){
                for(Thread threads : hilosL){
                    threads.join();
                }
                hilosL.clear();
            }
        }

        for(Thread threads : hilosL){
            threads.join();
        }

        return false;
    }

    static void search(char[][] grid, String word) throws InterruptedException {
        // Consider every point as starting
        // point and search given word
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (grid[row][col]==word.charAt(0)){
                    boolean result = iterativeSearch(grid, row, col, word);
                    if (result) {
                        System.out.println("Coordenadas " + row + ", " + col);
                    }
                }
            }
        }
    }

    public static char[][] readMatriz(String path) {
        try {
            // Lectura del archivo con la sopa de letras
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            boolean matrizReadEnd = false;
            List<char[]> rows = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                // Varificamos si terminamos de leer la sopa de letras
                if (data.length() == 0  && !matrizReadEnd){
                    matrizReadEnd = true;
                }

                if (!matrizReadEnd) {
                    // List<String> items = Arrays.asList(data.split(" "));
                    // sopa.add(items);
                    rows.add(data.replaceAll(" ", "").toCharArray());
                } else if(data.length() > 0) {
                // Leemos la lista de words a buscar
                    words.add(data);
                }
            }
            myReader.close();
            char[][] sopa = new char[rows.size()][rows.get(0).length];
            for (int i = 0; i < rows.size(); i++) {
                for (int j = 0; j < rows.size(); j++) {
                    sopa[i][j] = rows.get(i)[j];
                }
            }
            return sopa;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        char [][] sopa = readMatriz("sopa.txt");
        for (int i = 0; i < sopa.length; i++){
            String s = "";
            for (int j = 0; j < sopa[i].length; j++){
                s += sopa[i][j] + " ";
            }
            System.out.println(s);
        }
        rows = sopa.length;
        columns = sopa[0].length;
        // for (String p : words) {
        //     search(sopa, p);
        // }

        List<Thread> hilosL = new ArrayList<>();
        int hilos = 15;
        
        for(int i = 0; i < words.size(); ++i){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    String p = words.get(Integer.parseInt(Thread.currentThread().getName()));
                    try {
                        search(sopa, p);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } 
                }
            },""+i);
            t.start();
            hilosL.add(t);

            if(hilos == hilosL.size()){
                for(Thread threads : hilosL){
                    threads.join();
                }
                hilosL.clear();
            }
        }

        for(Thread threads : hilosL){
            threads.join();
        }
    }
}
