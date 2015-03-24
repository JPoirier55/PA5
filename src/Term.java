import java.util.LinkedList;

//Jake Lord and Anna Beckman
// 9/21/14


public class Term {
	 public int number=0;
	private String name;
	
	//getters and setters for the name
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//the count of the number of documents a particular word is in; this word is a name
	private int docFrequency;
	
	public int getDocFrequency() {
		return docFrequency;
	}

	public void setDocFrequency(int docFrequency) {
		this.docFrequency = docFrequency;
	}
	
	//the total number of times that a name appears across all documents
	private int totalFrequency=0;
	
	//Linked List of occurrence objects
	LinkedList<Occurrence> list = new LinkedList<Occurrence>();
	
	//Constructor
	public Term (String name){
		this.name = name;
		setDocFrequency(1);
	}
	
	//Incrementor for Total Frequency
	public void incFrequency (String document){
		//setTotalFrequency(getTotalFrequency() + 1);
		int listCheck=0;
		totalFrequency++;
		if(list.isEmpty()){
			list.add(new Occurrence(document));
	}else{
		for (Occurrence x : list){
			if(x.getDocName().equals(document)){
				x.incFrequency();
				listCheck=1;
			}
		}	
				if(listCheck!=1){
				setDocFrequency(getDocFrequency() + 1);
				list.add(new Occurrence(document));
				listCheck=0;
		
				}
			}
		}
	


	

	public int getTotalFrequency() {
		return totalFrequency;
	}

	public void setTotalFrequency(int totalFrequency) {
		this.totalFrequency = totalFrequency;
	}

}
