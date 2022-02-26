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

            int [][] tastiera = new int[righe][colonne];
            int assegnazione = 0;
            for(int j = 0; j < tastiera.length; j++) {
                for(int z = 0; z < tastiera[j].length; z++) {
                    tastiera[j][z] = assegnazione;
                    assegnazione++;
                }
            }

            int nTasti = Integer.parseInt(riga[2]);
            int posTastoGhosting = 0;
            boolean isPrimo = true;

            for(int a = 3; a < 3 + nTasti; a++) {

                int tasto = Integer.parseInt(riga[a]);
                int[] pos = cercaNumero(tastiera, tasto);

                if(controlloGhosting(tastiera, pos[0], pos[1], isPrimo)){
                    scrittore.write("Case #" + (i+1) + ": " + posTastoGhosting + "\n");
                    continue generale;
                }
                posTastoGhosting++;
                isPrimo = false;
            }
            scrittore.write("Case #" + (i+1) + ": -1" + "\n");


        }
        scrittore.flush();
    }

    public static int[] cercaNumero(int [][] tastiera, int n) {
        for(int j = 0; j < tastiera.length; j++) {
            for(int z = 0; z < tastiera[j].length; z++) {
                if(tastiera[j][z] == n){
                    tastiera[j][z] = -1;
                    return new int[]{j,z};
                }
            }
        }
        return null;
    }

    public static boolean controlloGhosting(int [][] tastiera, int rigaN, int colonnaN, boolean isPrimo) {

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

        /*
        if(isPrimo) {
            return false;
        }
        boolean stato = true;

        for(int i = 0; i < tastiera[rigaN].length; i++) {
            if(tastiera[rigaN][i] == -1 && (i != colonnaN)) {
                stato = false;
            }
        }

        System.out.println(stato);

        for(int i = 0; i< tastiera.length; i++) {
            if(tastiera[i][colonnaN] == -1 && (i != rigaN)) {
                stato = false;
            }
        }

        return stato;
        */
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
