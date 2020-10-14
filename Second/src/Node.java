public class Node {

    //____________Children_______________
    private int leftkey=0;             //|
    private int rightkey=1;            //|
    private Node leftside;             //|
    private Node rightside;            //|
    //-----------------------------------
    private int key;
    private int depth;
    private boolean needNode;
    private boolean valueExist;

    public Node(int key,int depth){
        this.leftside=null;
        this.rightside=null;
        this.key=key;
        this.depth=depth;
        valueExist=false;
    }

    //________________________________Add Value_____________________________________
    public int addValue(String[] valueInBinary,int bitsLeft) {
        if (bitsLeft == 1) {
            if (valueInBinary[valueInBinary.length - depth-1].equals("0")) {//if zero number
                Node left = new Node(0, ++depth );
                left.valueExist = true;
                left.needNode = true;
                leftside = left;
            } else if (rightside == null) {
                Node right = new Node(1, ++depth );
                right.valueExist = true;
                right.needNode = true;
                rightside = right;
            } else if (rightside.valueExist == true) {
                System.out.println("Value exist already");
            }
            return depth;
        } else {
            if (valueInBinary[valueInBinary.length - depth-1].equals("0")) {
                if (leftside == null) {//If empty
                    Node left = new Node(0, depth + 1);//creat node and put it
                    left.needNode = true;
                    leftside = left;
                }
                return leftside.addValue(valueInBinary, --bitsLeft);//continue

            } else {
                if (rightside == null) {//If empty
                    Node right = new Node(1, depth + 1);//creat node and put it
                    right.needNode = true;
                    rightside = right;
                }
                return rightside.addValue(valueInBinary, --bitsLeft);//continue
            }

        }
    }//_____________________________________________________________________________


    //________________________________Search Value________________________________
    public int searchValue(String[] valueInBinary,int bitsLeft){
        if (bitsLeft == 1) {//If only 1 left
            if (valueInBinary[valueInBinary.length - depth-1].equals("0")) {//if zero number
                if (leftside == null) {
                   // System.out.println("Value :"+ valueInBinary +" does not exist");
                    return depth;
                }
               // System.out.println("Value :"+ valueInBinary +" exist");
                return depth;
            } else if (rightside == null) {
              //  System.out.println("Value :"+ valueInBinary +" does not exist");
                return depth;
            } else if (rightside.valueExist == true) {
               // System.out.println("Value :"+ valueInBinary +" exist");
                return depth;
            }
            return depth;
        } else {
            if (valueInBinary[valueInBinary.length - depth-1].equals("0")) {
                if (leftside == null) {//If empty
                  //  System.out.println("Value :"+ valueInBinary +" does not exist");
                    return depth;
                }
                return leftside.searchValue(valueInBinary, --bitsLeft);//continue

            } else {
                if (rightside == null) {//If empty
                  //  System.out.println("Value :"+ valueInBinary +" does not exist");
                    return depth;
                }
                try{
                    return rightside.searchValue(valueInBinary, --bitsLeft);//continue
                }
                catch (Exception e){
                    System.out.println("Exception: " + e);
                    return rightside.searchValue(valueInBinary, bitsLeft);//continue
                }
            }

        }
    }//_____________________________________________________________________________


    //__________________________________Delete Value_______________________________
    public int deleteValue(String[] valueInBinary,int bitsLeft){
        if (bitsLeft == 1) {//If only 1 left
            if (valueInBinary[valueInBinary.length - depth-1].equals("0")) {//if zero number
                if (leftside == null) {
                   // System.out.println("Value :"+ valueInBinary +" does not exist");
                    return depth;
                }
                valueExist=false;
                //System.out.println("Value :"+ valueInBinary +" exist");
                return depth;
            } else if (rightside == null) {
              //  System.out.println("Value :"+ valueInBinary +" does not exist");
                return depth;
            } else if (rightside.valueExist == true) {
               // System.out.println("Value :"+ valueInBinary +" exist");
                valueExist=false;
                return depth;
            }
            return depth;
        } else {
            if (valueInBinary[valueInBinary.length - depth-1].equals("0")) {
                if (leftside == null) {//If empty
                  //  System.out.println("Value :"+ valueInBinary +" does not exist");
                    return depth;
                }
                return leftside.searchValue(valueInBinary, --bitsLeft);

            } else {
                if (rightside == null) {//If empty
                  //  System.out.println("Value :"+ valueInBinary +" does not exist");
                    return depth;
                }
                return rightside.searchValue(valueInBinary, --bitsLeft);//continue
            }

        }
    }//_____________________________________________________________________________

}
