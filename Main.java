import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader reader=new BufferedReader(new FileReader("Input.txt"));
             BufferedWriter writer=new BufferedWriter(new FileWriter("Output.txt"))) {
            String assigment;
            String[] assigment2;
            int R;
            int C;
            int N;
            List<Integer> keys=new ArrayList<>();

            int temp=0;

            while(reader.ready()) {
                keys.clear();
                assigment= reader.readLine();
                assigment2=assigment.split(" ");
                R=Integer.parseInt(assigment2[0]);
                try {
                    C=Integer.parseInt(assigment2[1]);
                    N=Integer.parseInt(assigment2[2]);
                } catch (IndexOutOfBoundsException | NegativeArraySizeException e) {
                    e.printStackTrace();
                    temp++;
                    continue;
                }
                Arrays.stream(assigment2).skip(3).mapToInt(Integer::parseInt).forEach(keys::add);

                boolean[][] booleans=mapKeyboard(R, C, keys);
/*
                for(int i = 0; i<R; i++)
                {
                    for(int j = 0; j<C; j++)
                    {
                        System.out.print(booleans[i][j]);
                    }
                    System.out.println();
                }
*/
                int result=scan(booleans, R, C);
                System.out.println(result);
                int index=keys.indexOf(result);
                writer.write("Case #"+temp+": "+index+"\n");
                temp++;

            }
            writer.flush();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int scan(boolean [][] keyboard, int R, int C) { //returns -1 if no pattern is find
        
    }

    public static boolean[][] mapKeyboard(int R, int C, List<Integer> keys) {
        boolean[][] g=new boolean[R][C];
        int lastNumber=0;
        for(int i=0; i<R; i++) {
            for(int k=0; k<C; k++) {
                if(keys.contains(lastNumber)) {
                    g[i][k]=true;
                }
                else {
                    g[i][k]=false;
                }
                lastNumber++;
            }
        }
        return g;
    }

    public static int convertToKey(int i, int k, int R, int C) {
        int lastNumber=0;
        for(int f=0; f<R; f++) {
            for(int g=0; g<C; g++) {
                if(f==i && g==k) {
                    return lastNumber;
                }
                lastNumber++;
            }
        }
        return -1;
    }
}

