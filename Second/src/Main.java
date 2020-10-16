
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Table hashingTable = new Table();
        Random rand = new Random();
        int [] states={20,100,1000,10000};

        //___________________________For creating each hash table with values________________________________
        for(int j=0;j<4;j++){
            System.out.println("\nFor :"+states[j]+ " Values");

            //Putting into list
            for (int i = 0; i < states[j]; i++) {
               hashingTable.addInTable(rand.nextInt(Integer.MAX_VALUE - 1));
            }
            System.out.println("Average ADD with " + states[j] + " values: " + hashingTable.getAddSum()/states[j]);

            //Searching into list
            for (int i = 0; i < states[j]; i++) {
                hashingTable.searchInTable(rand.nextInt(Integer.MAX_VALUE - 1));
            }
            System.out.println("Average SEARCH with " + states[j] + " values: " + ((hashingTable.getSearchsum()/states[j])+1));

            //Deleting from list
            for (int i = 0; i < states[j]; i++) {
                hashingTable.deleteInTable(rand.nextInt(Integer.MAX_VALUE - 1));
            }
            System.out.println("Average DELETE with " + states[j] + " values: " + ((hashingTable.getSearchsum()/states[j])+1));
           //__________________________________________________________________________________________________
        }
    }
}