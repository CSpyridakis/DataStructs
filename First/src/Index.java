import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class Index {
	List<String> placeOfWord = new ArrayList<>();
	
	public Index(){};
	
	public Index(List<String> pW){
		for(int i=0;i<pW.size();i++){
			placeOfWord.add(pW.get(i));
		}
	}

	public boolean WriteIntoFile(){
		String Filename="Index.txt";
        int FreePage = placeOfWord.size() ;
        String buffer;
        String buf;
		int nowPlace = FreePage ;
        int nowPage=0;
		int numOfWordsPerPage =10;
        List<String> wordleft = new ArrayList<>();

        String placesOfOneword;

		try{
			RandomAccessFile dictFile = new RandomAccessFile(Filename, "rw");

            for(int nodeOfIndex=0;nodeOfIndex<placeOfWord.size();nodeOfIndex++){            //For each node of the index
                buffer="";                                                                  //Initialize buffer
                placesOfOneword=placeOfWord.get(nodeOfIndex);                               //Take one String with Places
                String [] placesIntoArray=placesOfOneword.split(" ");                       //Split into array
                int numberOfPlacesAppear=placesIntoArray.length;                            //number of apperances
                boolean flagcont=true;

                for(int eachPlace=0;eachPlace<numberOfPlacesAppear&&flagcont;eachPlace++){ //For each time a word appears

                    if(eachPlace<10) {
                        buf="";                                                             //Initialize tmp
                        buf = String.format("%11s", (placesIntoArray[eachPlace] + "|"));    //Put into one item
                        buffer += buf;                                                      //Buffer <= tmp

                        if (eachPlace == numberOfPlacesAppear - 1) {                        //If it is less than 10
                            buffer += String.valueOf(-1);                                   //Has not new Page
                            buffer +="\n";                                                  //New Line
                            buffer = String.format("%128s", buffer);                        //Set buffer has 128 bytes
                        }
                        else if (numberOfPlacesAppear > 10 && eachPlace == 9) {             //If it has more pages
                            buffer += String.valueOf(FreePage);                             //Put Page is needed
                            FreePage+=(((numberOfPlacesAppear-10)/10)+1);                   //Put Next Page
                            buffer +="\n";                                                  //New Line
                            buffer = String.format("%128s", buffer);                        //Set buffer has 128 bytes
                            wordleft.add(placeOfWord.get(nodeOfIndex));
                        }
                    }
                    else{
                        flagcont=false;
                    }
                }
                //System.out.println(buffer.length()+" "+buffer);

                //----Write into file---------
                dictFile.seek(nowPage*128); //|
                dictFile.writeBytes(buffer);//|
                //----------------------------
                nowPage++;                                                             //Change page
            }


            for(int leftpages=0;leftpages<wordleft.size();leftpages++){
                String pleft=wordleft.get(leftpages);
                String[] leftArray=pleft.split(" ");
                //_________________________________Creating an Array with left values____________________________
                for(int i=0;i<10;i++){                          //                                              |
                    leftArray[i]=null;                          //                                              |
                }                                               //                                              |
                                                                //                                              |
                int numLeft=0;                                  //                                              |
                for(int i=0;i<leftArray.length;i++){            //                                              |
                    if(leftArray[i]!=null){                     //                                              |
                        //System.out.print(leftArray[i]);       //                                              |
                        numLeft++;                              //                                              |
                    }                                           //                                              |
                }                                               //                                              |
                String[] lefted=new String[numLeft];            //                                              |
                int j=0;                                        //                                              |
                for(int i=0;i<leftArray.length;i++){            //                                              |
                    if(leftArray[i]!=null){                     //                                              |
                        lefted[j]=leftArray[i];                 // NeedArray                                    |
                        j++;                                    //                                              |
                    }                                           //                                              |
                }                                               //                                              |
                //System.out.println("\n"+numLeft);             //                                              |
                                                                //                                              |
                for(int page=0;page<lefted.length;page++){      //                                              |
                    //System.out.print(lefted[page]);           //                                              |
                }//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

                //_________________________________Put Rest into other Arrays____________________________
                int na=0;
                for(int page=0;page<(lefted.length/10+1);page++){
                    buffer="";
                    boolean f=true;

                    for(int nowe=0;nowe<lefted.length-10*page&&f;nowe++){
                        buf = "";
                        if (nowe<10) {
                            buf = String.format("%11s",(lefted[nowe+na] + "|"));
                            buffer += buf;
                        }
                        else{
                            f=false;
                        }
                    }

                    if(page==(lefted.length/10)){
                        buffer += String.valueOf(-1);                                   //Has not new Page
                        buffer +="\n";                                                  //New Line
                        buffer = String.format("%128s", buffer);                        //Set buffer has 128 bytes
                    }
                    else{
                        buffer += String.valueOf(nowPlace);                             //Put Page is needed
                        buffer +="\n";                                                  //New Line
                        buffer = String.format("%128s", buffer);                        //Set buffer has 128 bytes
                    }
                   // System.out.println("Length: "+buffer.length()+" "+buffer);

                    //----Write into file---------
                    dictFile.seek(nowPlace*128); //|
                    dictFile.writeBytes(buffer);//|
                    //----------------------------
                    nowPlace++;
                    na++;
                }

            }//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			dictFile.close();
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
	
	public int ReadFromFile(int pageFound){                     //Read from file specific pages
		String Filename="Index.txt";
        String pageValues;
		int nowPlace = 0 ;
		int FreePage = 0 ;
		int numOfWordsPerPage =10;
        int numOfLoads=0;
        int diskaccess=0;
        try{
			RandomAccessFile dictFile = new RandomAccessFile(Filename, "r");

            int page=pageFound;
            //
            int last;

            do {
                pageValues="";
                last=-1;
                int i=0;

                dictFile.seek(page * 128);                      //Disk page
                pageValues = dictFile.readLine();               //Read it
                //System.out.print(pageValues);

                String tmp=pageValues;                          //
                tmp=tmp.replace(" ","");                        //
                tmp=tmp.replace("|"," ");                       //
                String[] parts=tmp.split(" ");                  //Costumize it

                for(int j=0;j<parts.length;j++){
                    if(j<parts.length-1) {
                        System.out.print(parts[j] + " ");      //Print it
                    }
                }
                diskaccess++;                                   //disk
                page = Integer.parseInt(parts[parts.length-1]); //Transfer into int

                last=page;                                      //if == -1
                page++;
                //System.out.println("\nnext page: "+page);
            }while(last!=-1);

            dictFile.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();	
			return -1;
		}
		catch(IOException e){
			e.printStackTrace();
			return -1;
		}
		return diskaccess;
	}

}
