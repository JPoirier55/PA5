//Jake Lord and Anna Beckman
// 9/21/14


public class Occurrence {
	public String docName;
	//Getters and Setter
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}

	
	
	
	public int termFrequency;
	//Getter and Setter
	public int getTermFrequency() {
		return termFrequency;
	}
	public void setTermFrequency(int termFrequency) {
		this.termFrequency = termFrequency;
	}

	
	
	public Occurrence (String name){
		termFrequency = 1;
		docName = name;
		
	}
	
	public void incFrequency (){
		termFrequency++;
	}

}
