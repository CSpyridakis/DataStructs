public class WordOfDictionary {
	private String word;
	private int pageExist;
	
    public WordOfDictionary(String word,int pageExist){
    	this.word=word;
        this.pageExist=pageExist;
    }

	public String getWord() {
		return word;
	}
	public int getPageExist() {
		return pageExist;
	}
	
	public void print(int i){
		System.out.println(i+") "+word+" "+pageExist);
	}
    
}
