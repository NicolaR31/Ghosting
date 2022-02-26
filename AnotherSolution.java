import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("Output.txt"))) {
            int R;
            int C;
            int N;
            String[] row;
            List<Integer> keys = new ArrayList<>();

            int numTests=Integer.parseInt(reader.readLine());
            int ghost;
            int finalIndex;

            int cont=1;
            while (reader.ready()) {
                row=reader.readLine().split(" ");
                R=Integer.parseInt(row[0]);
                C=Integer.parseInt(row[1]);
                N=Integer.parseInt(row[2]);
                Arrays.stream(row).skip(3).mapToInt(Integer::parseInt).forEach((b)->keys.add(b));

                ghost=scan(mapKeyboard(R,C, keys), R, C);
                System.out.println(ghost);
                finalIndex=keys.indexOf(ghost);
                writer.write("Case #"+cont+": "+finalIndex+"\n");
                cont++;
                keys.clear();

            }
            writer.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean[][] mapKeyboard(int R, int C, List<Integer> keys) {
        int temp=0;
        boolean[][] keyboard=new boolean[R][C];
        for(int i=0; i<R; i++) {
            for(int k=0; k<C; k++) {
                if(keys.contains(temp)) {
                    keyboard[i][k]=true;
                }
                else {
                    keyboard[i][k]=false;
                }
                temp++;
            }
        }
        return keyboard;
    }

    public static int scan(boolean[][] keyboard, int R, int C) {
        for(int col=0; col<C; col++) {
            for(int row=R-1; row>=0; row--) {
                if(keyboard[row][col]) {
                    for(int leftCol=col+1; leftCol<C; leftCol++) {
                        if(keyboard[row][leftCol]) {
                            for(int topRow=0; topRow<R; topRow++) {
                                if(topRow==row) {
                                    continue;
                                }
                                if(keyboard[topRow][leftCol]) {
                                    if(!keyboard[topRow][col]) {
                                        return cordinateToNumber(R,C, topRow, leftCol);
                                    }
                                }
                            }
                        }
                    }
                    for(int rightCol=0; rightCol<col; rightCol++) {
                        if(keyboard[row][rightCol]) {
                            for(int topRow=0; topRow<R; topRow++) {
                                if(topRow==row) {
                                    continue;
                                }
                                if(keyboard[topRow][rightCol]) {
                                    if(!keyboard[topRow][col]) {
                                        return cordinateToNumber(R, C, topRow, rightCol);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static int cordinateToNumber(int R, int C, int row, int col) {
        int temp=0;
        for (int i=0; i<R; i++) {
            for(int k=0; k<C; k++) {
                if(i==row && k==col) {
                    return temp;
                }
                temp++;
            }
        }
        return -1;
    }

}
