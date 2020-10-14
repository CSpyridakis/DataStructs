import java.io.*;
import java.util.*;

public class Dictionary {

	WordOfDictionary[] wordArray;
	
	//Constructors
	public Dictionary(){};
	
	public Dictionary(WordOfDictionary[] wA){
		this.wordArray=new WordOfDictionary[wA.length];
		System.arraycopy(wA, 0, this.wordArray, 0, wA.length);
	}
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public boolean WriteIntoFile(){
		String Filename="Dictionary.txt";                                            //Name of File
		String buffer;                                                               //Buffer transfer data to File
        String buf;                                                                  //Temp buf to put info into buffer
		int nowPlace = 0;                                                            //Current group of words
        int nowPage=0;                                                               //Current Page
		int numOfWordsPerPage =6;                                                    //Number of words per page
		
		try{
            RandomAccessFile dictFile = new RandomAccessFile(Filename, "rw");        //Open FIle
             while(wordArray.length>nowPlace+numOfWordsPerPage){
                buffer=new String("");
                 //______________________________________________All Pages -1_________________________________________________________________________
                for(int i=0;i<numOfWordsPerPage;i++){                                                                                               //|
                    buf=new String("");                                                                                                             //|
                    buf=String.format("%20s",(wordArray[i+nowPlace].getWord()+"|"+String.valueOf(wordArray[i+nowPlace].getPageExist()))+"\n");      //|
                   // System.out.println(buf.length());                                                                                             //|
                    buffer+=buf;                                                      //Put string into buffer                                      //|
                }//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                buffer+="EndPage\n";                                                  //Fill needed place to reach 128 bytes

                 //------------------------Debugging---------------------------------
                 //System.out.println("----------Page "+ nowPage+"--------------");//|
                 //System.out.println(buffer);                                     //|
                 //System.out.println("Buffer Length: "+buffer.length());          //|
                 // -----------------------------------------------------------------

                //----Write into file---------
                dictFile.seek(nowPage*128); //|
                dictFile.writeBytes(buffer);//|
                //----------------------------
                nowPlace+=numOfWordsPerPage;                                           //Go to next group of Words
                nowPage++;                                                             //Change page
            }
            //______________________________________________Last Page of file_________________________________________________________________________
            buffer=new String("");                                                                                                                  //|
            for(int i=0;i<wordArray.length-nowPlace;i++){//																							//|                buf=new String("");
                buf=String.format("%20s",(wordArray[i+nowPlace].getWord()+"|"+String.valueOf(wordArray[i+nowPlace].getPageExist()))+"\n");          //|
                //System.out.println(buf.length());                                                                                                 //|
                buffer+=buf;                                                                                                                        //|
            }//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

            buffer=String.format("%128s",buffer);
            //------------------------Debugging---------------------------------
            //System.out.println("----------Page "+ nowPage+"--------------");//|
            //System.out.println(buffer);                                     //|
            //System.out.println("Buffer Length: "+buffer.length());          //|
            // ------------------------------------------------------------------

            //----Write into file---------
            dictFile.seek(nowPage*128); //|
            dictFile.writeBytes(buffer);//|
            //----------------------------

			dictFile.close();              //File closing
		}
		catch(FileNotFoundException e){
			e.printStackTrace();	
			return false;
		}
		catch(IOException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public int[] ReadFromFile(String givenWord){
		String Filename="Dictionary.txt";
		String buffer ;
		String wordS;
		int nowPlace = 0 ;
		int numOfWordsPerPage =6;
		int [] reArr={-1,-1};

		try{
			RandomAccessFile dictFile = new RandomAccessFile(Filename, "r");

			String[] pageValues;
            pageValues=new String [6];

			int size= (int) (dictFile.length()/128);
            int start=0;
            int end=size-1;
            int middle;
            int page;

            int foundOn=-1;
			int diskaccess=0;

            do {
                middle=(start+end)/2;
                page=middle;

                for (int i = 0; i < 6; i++) {
                    dictFile.seek(i * 20 + page * 128);
                    pageValues[i] = dictFile.readLine();
                   // System.out.print(pageValues[i]);
                }
               // System.out.println("");
                diskaccess++;
                int i = checkIfThere(pageValues, givenWord);
               //System.out.println(i);

                if (i >= 0) {
                    foundOn=i;
                    System.out.println("First time in page: " + i);
                    reArr[0]=i;
                    reArr[1]=diskaccess;
                    break;
                }
                if(end-start==1){
                    System.out.println("This word does not exist");
                    break;
                }

               if (i == -1) {
                    //System.out.println("Right");
                    start = middle;
                }
                else if (i == -2) {
                   //System.out.println("Left");
                    end = middle;
                }
            }while(true);

			dictFile.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();	
			return reArr;
		}
		catch(IOException e){
			e.printStackTrace();
			return reArr;
		}
		return reArr;
	}
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public int checkIfThere(String [] arrayOfElements,String word){
        boolean flag=false;
        int returnValue=-1;                                 //If it is to the right

        for(int i=0;i<6&&!flag;i++){                        //
            String tmp=arrayOfElements[i];                  //
            tmp=tmp.replace(" ","");                        //
            tmp=tmp.replace("|"," ");                       //
            String[] parts=tmp.split(" ");                  //Delete uneccessery
            //System.out.println(parts[0]+parts[1]);

            int aInt = Integer.parseInt(parts[1]);

            if(parts[0].compareToIgnoreCase(word)>0){
                returnValue=-2;                             //If it is to the Left
            }
            if(word.equals(parts[0])){                      //If it founds
                return aInt;
            }
        }
        return returnValue;
    }

}


