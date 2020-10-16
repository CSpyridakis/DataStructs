public class Table {

    final static int N = 100;
    final static int root = 0;
    private int addsum;
    private int searchsum;
    private int deletesum;
    int num;
    private Node[] hashingTable;

    //______________________Hash Table_______________________
    public Table() {                                          //|
        hashingTable = new Node[N];                           //|
        for (int i = 0; i < N; i++) {                         //|
            hashingTable[i] = new Node(root,root);            //|
        }                                                     //|
        searchsum=0;
        deletesum=0;
        addsum=0;                                             //|
    }//_________________________________________________________

    //__________________________________________addInTable___________________________________
    public void addInTable(int num) {                                                      //|
        addsum+=hashingTable[Function(num)].addValue(toBinary(num),toBinary(num).length);  //|
    }//______________________________________________________________________________________

    //_______________________________________searchInTable_________________________________________
    public void searchInTable(int num) {                                                         //|
        searchsum+=hashingTable[Function(num)].searchValue(toBinary(num),toBinary(num).length);  //|
    }//_____________________________________________________________________________________________

    //_______________________________________deleteInTable_________________________________________
    public void deleteInTable(int num) {                                                         //|
        deletesum+=hashingTable[Function(num)].deleteValue(toBinary(num),toBinary(num).length);  //|
    }//_____________________________________________________________________________________________

    //_______Hashing Function_________
    public int Function(int key) {  //|
        int tableplace = key % N;   //|
        return tableplace;          //|
    }//-------------------------------

    //______________To binary______________________
    public String[] toBinary(int num) {          //|
        String binary = Integer.toString(num,2); //|
        String [] binaryArray=binary.split("");  //|

    //   for(int i=0;i<binaryArray.length;i++){System.out.print(binaryArray[i]);}
    //    System.out.println();
        return binaryArray;                      //|
    }//____________________________________________

    //____________Get_____________________
    public Node[] gethashingTable() {   //|
        return hashingTable;            //|
    }                                   //|
    public static int getN() {          //|
        return N;                       //|
    }                                   //|
    public int getAddSum() {            //|
        return addsum;                  //|
    }                                   //|
    public int getSearchsum() {         //|
        return searchsum;               //|
    }                                   //|
    public int getDeletesum() {         //|
        return deletesum;               //|
    }//------------------------------------

    //______________________SET____________________________
    public void sethashingTable(Node[] hashingTable) {   //|
        this.hashingTable = hashingTable;                //|
    }//-----------------------------------------------------
}
