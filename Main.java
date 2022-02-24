import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader lettore = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter scrittore = new BufferedWriter(new FileWriter("output.txt"));
        int tc = Integer.parseInt(lettore.readLine());

        generale:
        for(int i = 0; i < tc; i++) {
            String riga[] = lettore.readLine().split(" ");
            int righe = Integer.parseInt(riga[0]);
            int colonne = Integer.parseInt(riga[1]);

            Integer [][] tastiera = new Integer[righe][colonne];
            int assegnazione = 0;
            for(int j = 0; j < tastiera.length; j++) {
                for(int z = 0; z < tastiera[j].length; z++) {
                    tastiera[j][z] = assegnazione;
                    assegnazione++;
                }
            }

            int nTasti = Integer.parseInt(riga[2]);
            int posTastoGhosting = 0;

            for(int a = 3; a < 3 + nTasti; a++) {

                int tasto = Integer.parseInt(riga[a]);
                Integer[] pos = cercaNumero(tastiera, tasto);
                if(controlloGhosting(tastiera,tasto,pos[0], pos[1])){
                    scrittore.write("Case #" + (i+1) + ": " + posTastoGhosting + "\n");
                    continue generale;
                }
                posTastoGhosting++;
            }
            scrittore.write("Case #" + (i+1) + ": -1" + "\n");


        }
        scrittore.flush();
    }

    public static Integer[] cercaNumero(Integer [][] tastiera, int n) {
        for(int j = 0; j < tastiera.length; j++) {
            for(int z = 0; z < tastiera[j].length; z++) {
                if(tastiera[j][z] == n){
                    tastiera[j][z] = -1;
                    return new Integer[]{j,z};
                }
            }
        }
        return null;
    }

    public static boolean controlloGhosting(Integer [][] tastiera, int n, int rigaN, int colonnaN) {
        for(int j = 0; j < tastiera.length; j++) {

            if(j == rigaN) {
                continue;
            }

            for(int z = 0; z < tastiera[j].length; z++) {

                if(z == colonnaN) {
                    continue;
                }

                if(tastiera[j][z] == -1) {
                    return true;
                }
            }
        }
        return false;
    }
}


/*
            for(int j = 0; j < tastiera.length; j++) {
                for(int z = 0; z < tastiera[j].length; z++) {
                    System.out.print(tastiera[j][z] + "\t");
                }
                System.out.println("\n");
            }
*/
