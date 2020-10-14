import java.io.*;
import java.util.*;
public class FilesOpen {
	
	//--------------------------------For Files Needed--------------------------------------
	private String[] filesPaths;       						//Paths for each file			|
	private int numFiles;									//Number Of files				|
	private String[] textscontain;							//For each text what it contains|
	//--------------------------------------------------------------------------------------
	
	//--------------------------------For Returning Needed--------------------------------------
	private String[] sortedWords;							//Array with Dictionary Words		|
	private WordOfDictionary[] wordDictionary;				//End Array with Dictionary Words	|
	List<String> Index = new ArrayList<>();				//End List	with places of each word	|		
	//-------------------------------------------------------------------------------------------
	
	//---------------------------------------Additional Needed------------------------------------------
	private String[] placesWordAppear;						//Where each word is into each text			|
	private String[] textWordAppear=						//String for each text         TODO add more|
		{"a.txt","b.txt","c.txt","d.txt","e.txt","f.txt","g.txt","h.txt","i.txt"};					  //|
	//---------------------------------------------------------------------------------------------------
	
	//Constructor
	public FilesOpen(String[] filesPaths,int numFiles){		//Take files paths and number of given files
		this.numFiles=numFiles;
		this.filesPaths=new String[numFiles];
		this.textscontain= new String[numFiles];

		for(int i=0;i<numFiles;i++){						//Putting each filePath into Array
			this.filesPaths[i]=filesPaths[i];
		}
	}
	
	public boolean FilesToStructs(){					
		List<String> word = new ArrayList<>();
		
		for(int i=0;i<numFiles;i++){
			try {
				//____________________Open Files clear String which get and put it into list________________________
		        Scanner fileIn=new Scanner(new File(filesPaths[i]));								//				|
		        String tmp;																			//				|
		        textscontain[i]=new String("");														//				|
				while(fileIn.hasNext()){															//				|
					tmp=fileIn.next();								//Put it into tmp variable						|
					textscontain[i]+=DeleteUseless(tmp," ")+" ";	//Container for each text						|
					tmp=DeleteUseless(tmp,"");						//Clear from useless characters					|	
					if(tmp.length()>=1&&tmp.length()<13){			//												|
					word.add(tmp);}									//Put new word into List						|
		        }																					//				|
				fileIn.close();																		//				|
				//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		    }
		    catch(FileNotFoundException ex) {		//Exception
		        System.out.println("Can not open file \"" + filesPaths[i] + "\"");    
		        return false;
		    }
		}
		 //___________________________________Converting word List into Sorted and Unique Word Array_________________________________________
		 String[] temp=word.toArray(new String[0]);								//For converting word List into Sorted and Unique Word Array |
		 Set<String> dupliremove = new HashSet<>(Arrays.asList(temp));			//For Remove Duplicates										 |
		 temp=dupliremove.toArray(new String[0]);								//Duplicates Removed										 |
		 List<String> sortedNow = new ArrayList<>(Arrays.asList(temp));			//For Sorting												 |
	     Collections.sort(sortedNow, String.CASE_INSENSITIVE_ORDER);			//Sort List													 |
	     sortedNow.removeAll(Arrays.asList("", null));							//Delete nothing											 |
	     sortedNow.removeAll(Arrays.asList(" ", null));							//Delete Space												 |
	     sortedWords = new String[sortedNow.size()];							//															 |
	     sortedWords = sortedNow.toArray(new String[0]);						//Put Sorted Word List into Array							 |
	     wordDictionary=new WordOfDictionary[sortedWords.length];				//Array size create											 |
	     //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	     
	     //__________________________________Creating Array___________________________________________
	     placesWordAppear=new String[sortedWords.length];										    //|
	     for(int i=0;i<sortedWords.length;i++){					  		 					   	    //|
        	 wordDictionary[i]=new WordOfDictionary(sortedWords[i],i);	//Creat array of words      //|
        	 placesWordAppear[i]=new String("");						//For each place word appear//|
         }//------------------------------------------------------------------------------------------
	     
	     //________________________________Find where on each text each word is appeared___________________________________________
         for(int i=0;i<textscontain.length;i++){																				//|				  		 						 
        	 String [] thisText=textscontain[i].split(" ");								//Split each text into array			  |	
        	 long nowSize=0;															//initialize for each array start length  |
        	 for(int j=0;j<thisText.length;j++){										//For each word of each text			  |
        		 boolean flag=false;													//Flag for If word found stop loop		  |	
        		 for(int diLegth=0;diLegth<sortedWords.length&&!flag;diLegth++){		//For each word of dictionary			  |
        			 if(thisText[j].equals(sortedWords[diLegth])){						//If word found on Dictionary			  |
        				 placesWordAppear[diLegth]+=textWordAppear[i]+","+nowSize+" ";	//Put info for text and place             |
        				 flag=true;														//If we find word stop loop				  |
        			 }																	//										  |
        		 }																		//										  |
        		 nowSize+=thisText[j].length()+1;										//pointer where we are on the text        |
        	 }																			//                                        |
         }//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         
         //__________________________________Creating List____________________________________________
		 for(int diLegth=0;diLegth<sortedWords.length;diLegth++){									//|
			 if(!placesWordAppear[diLegth].equals("\n")){											//|
				 Index.add(placesWordAppear[diLegth]);												//|
			 }																						//|
		 }//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		

		 //for(int i=0;i<sortedWords.length;i++){
			// System.out.println(i+")"+sortedWords[i]+Index.get(i)+"\n");
		 //}
		return true;
	}
	
	
	public String DeleteUseless(String befor,String c){	
		String after;
		if(c==""){
			after=befor.replace("--", c);
			//after=after.replace("-", " ");
		}
		else{
			after=befor.replace("--","  ");
			//after=after.replace("-", " ");
		}
		after=after.replace("\n",c);//Useless characters
		after=after.replace(".", c);
		after=after.replace(",", c);
		after=after.replace("(", c);
		after=after.replace(")", c);
		after=after.replace("{", c);
		after=after.replace("[", c);
		after=after.replace("]", c);
		after=after.replace("!", c);
		after=after.replace("'", c);
		after=after.replace("}", c);
		after=after.replace(":", c);
		after=after.replace(";", c);
		after=after.replace("\'", c);
		after=after.replace("\"", c);
		after=after.replace("?",  c);
		//after=after.replace(" ",  c);
		after=after.toLowerCase();
		return after;
	}

	
	public WordOfDictionary[] getWordDictionary() {
		return wordDictionary;
	}
	
	
	public List<String> getListofWordsPossition() {
		return Index;
	}



	
}
