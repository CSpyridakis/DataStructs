import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		int choose;									//For user choice of menu
		WordOfDictionary [] wordsIntoDict=null;		//For Dictionary Array
		List<String> placeOfWord=null;				//For Index List
		boolean flagForsecondchoose=false;			//For checking stem 1 has already be done
		boolean flagFileExist=false;				//For checking if index and dictionary exist
		Dictionary dictionary=new Dictionary();		//For checking if Dictionary exist
		Index index=new Index();					//For checking if Index exist
		
		flagFileExist=true;
		do{
			//________________________________Reading From User What wants to do_______________________________
			Scanner in= new Scanner(System.in);							 		//For reading info from console|
			System.out.println("\nWhat you want to do: ");				        //					           |
			System.out.println("1)Open Files and Create Dictionary & Index");	//                             |
			System.out.println("2)Copy Dictionary and Index into Disk"); 		//                             |
			System.out.println("3)Find a specific word from a file");    		//                             |
			System.out.println("(Press Everything Else for Exit)");    			//                             |
			choose=in.nextInt();									 			//                             |
			//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			
			//________________________________Open Files and Create Dictionary & Index_______________________________________
			if(choose==1&&!flagForsecondchoose){//                             								                 |
				//________________________________Reading From User filePaths__________________________________  			 |	
				System.out.print("How many files you want to give in: ");	 //Ask for files number			   |             |
				int numOfFiles=in.nextInt();								 //Number of files user will give  |             |
				String[] pathFiles=new String [numOfFiles];					 //Array in which paths will stored|             |
																			 //								   |             |
				//-------------------------------Reading actual paths------------------------------------      |             |
				for(int i=0;i<numOfFiles;i++){								 //						  	|      |             |
					System.out.println("Give the full path of the file: ");	 //User gives paths			|      |             |
					String path=in.next();									 //Read path				|      |             |
					//System.out.println(path);								 //							|	   |             |
					pathFiles[i]=path;										 //Put path into a String[]	|      |             |
				}//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~              |
				//_____________Open Files and return an Array for Dictionary and a List for Index______________              |
				FilesOpen fO=new FilesOpen(pathFiles,numOfFiles);			 	//Create File Opener		   |             |
																			 	//							   |             |
				if(fO.FilesToStructs()){									 	//Makes files into String	   |             |
					wordsIntoDict= fO.getWordDictionary();					    //Return Array for Dictionary  |             |
					placeOfWord=fO.getListofWordsPossition();					//Return List  for Index	   |             |
					System.out.println("All Files have successfully opened");	//						       |             |
				}																//							   |		     |
				//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~             |
				flagForsecondchoose=true;										//											 |
			}//For case someone choose again first for second time				//                                           |
			else if(choose==1){System.out.println("You Already had done this Step!\n");}//									 |										                                                             
			//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			

			//________________________________Putting Index and Dictionary into Disk________________________________________________________________________
			else if(choose==2&&flagForsecondchoose){		//																			                    |
				//TODO put index and dictionary into disk																				                    |
				//System.out.println(wordsIntoDict.length);											//For debugging purpose print num of word dictionary Has|				
				dictionary=new Dictionary(wordsIntoDict);											//Create Dictionary							   		    |
				index=new Index(placeOfWord);														//Create index								            |
				if(dictionary.WriteIntoFile()){System.out.println("Dictionary written into Disk");}	//Write dictionary into file							|
				if(index.WriteIntoFile()){System.out.println("Index written into Disk");}			//Write index into disk									|
				flagForsecondchoose=false;															//									   				  	|
			}//For that case, someone choose 2 and had not choose already 1																   					|
			else if(choose==2){System.out.println("You Already had write data to disk\nor you have not even done first Step");}// 							|
			//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			
			//____________________________________________Finding from Disk Given word___________________________________________________
			else if(choose==3&&flagFileExist){											//												 |
				System.out.println("Please give word you want: ");						//Ask user to give a word						 |
				String givenWord=in.next();												//												 |
				int[] retu=dictionary.ReadFromFile(givenWord);
				int daindex=0;															//Take from user which word wants				 |
				if(retu[0]>=0){
					daindex=index.ReadFromFile(retu[0]);
					System.out.println("\nThere have been : "+(daindex+retu[1])+" disk accesses");
				}
				//if(!dr.ReadFromFile(givenWord)){										//		                                         |
					//System.out.println("The word you gave does not exist in any file");//Check if exist word and print if	             |
				//}																		//                                               |
			}//For case someone choose first 3 and Files do not exist																	 |
			else if(choose==3){System.out.println("Index and Dictionary don't exist please do first steps");} 				//			 |
			//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			else if(choose>0&&choose<4){in.close();}
		}while(choose>0&&choose<4);
	
	}

}

