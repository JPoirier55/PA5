import java.util.Arrays;

//File Name: 
//Jake Poirier
//Email: Jake.Poirier55@gmail.com
//Date: 

public class HashTable implements TermIndex {

	public static int size, wordNum;
	public static double trigger;
	Term[] termArray, tempArray;	


	public HashTable(int size){
		this.size = size;
		termArray = new Term[size];
		trigger = 0;
		wordNum = 0;
		//		System.out.println("Size " + size);
	}

	@Override
	public void add(String filename, String newWord) {


		

		int key = Math.abs(newWord.hashCode());
		int hk = key % size;

		int count = 0;

		trigger = (double)wordNum / (double)size;
		//System.out.println("Trigger : "+ trigger + " Word num = :" +wordNum + "  size = " + size);
		

			while(count < size){
				//System.out.println("Filename: "+filename+"  newWord: "+newWord );
				if(termArray[hk] == null || termArray[hk].getName().equals("RESERVED")){

					termArray[hk] = new Term(newWord);
					wordNum++;

					termArray[hk].incFrequency(filename);
				
					break;

				}else if(termArray[hk].getName().compareTo(newWord) ==0){
					
					
					termArray[hk].incFrequency(filename);
					break;
				}else{

					count++;
					hk = (key + count*count) %size;
				}
			}	
			if(trigger >= 0.80){
				//System.out.println(newWord);
				//System.out.println("Rebuilding...");
				rebuild();
			}
		}
	

	public void rebuild(){
		
		//System.out.println("Word num = " + wordNum);
		this.size = (2*this.size)+1;
		
		tempArray = new Term[this.size];

		

		for(int i = 0; i < termArray.length; i++){
			if(termArray[i]!=null){
				//System.out.println("Copying term: "+termArray[i].getName());
				if(termArray[i].getName().compareTo("RESERVED")!=0){
					//System.out.println(termArray[i].getName());
					rebuildAdd(termArray[i].getName(),i);
				}
			}

		}

		termArray = tempArray;

	}
	
	public void rebuildAdd(String addWord, int position){
		
		int key = Math.abs(addWord.hashCode());
		int hk = key % size;

		int count = 0;


			while(count < size){
				//System.out.println("Filename: "+filename+"  newWord: "+newWord );
				if(tempArray[hk] == null){

					tempArray[hk] = termArray[position];
			
					break;

		
				}else{

					count++;
					hk = (key + count*count) %size;
				}
			}	
		}
		


	@Override
	public int size() {

			return this.size;

	}

	@Override
	public void delete(String word) {

		int key = Math.abs(word.hashCode());
		int hk = key % size;

		int count = 0;


		while(count < size){
			if(termArray[hk].getName().compareTo(word) == 0 ){
				termArray[hk] = new Term("RESERVED");
				wordNum--;
				break;

			}else if(termArray[hk] == null){
				break;
			}else{
				count++;
				hk = (key + count*count) %size;
			}
		}	




	}

	@Override
	public Term get(String word, Boolean printP) {

		int key = Math.abs(word.hashCode());
		int hk = key % size;

		int count = 0;

		while(count < size){
			if(termArray[hk] == null){
				return null;
		
			}else{
//				System.out.println("Hash "+ termArray[hk].getName());
				if(termArray[hk].getName().compareTo(word) == 0 ){
					//System.out.println("Found "+ termArray[hk]);
					return termArray[hk];

				}else{
					count++;
					hk = (key + count*count) %size;
				}
			}
		}
		return null;


	}
	public static void main (String args[]){
		HashTable t1 = new HashTable(20);

		//    	System.out.println(Math.abs("oatmeal".hashCode()));
		t1.add("Document", "apple");
		t1.add("Document", "air");
		t1.add("Document", "cat");
		t1.add("Document", "bear");
		t1.add("Document", "dog");
		t1.add("Document", "tree");
		t1.add("Document", "snake");
		t1.add("Document", "noobs");
		t1.add("Document", "xan");
		t1.add("Document", "oatmeal");
		for(int i = 0; i < t1.size; i++){
			if(t1.termArray[i] != null){
				System.out.println(t1.termArray[i].getName());

			}else{
				System.out.println("____");
			}

		}
		System.out.println();
		System.out.println();
		System.out.println();
		//    	System.out.println(t1.get("oatmeal", false).getName());
		//
		//    	System.out.println(t1.get("noobs", false).getName());

		t1.delete("dog");

		for(int i = 0; i < t1.size; i++){
			if(t1.termArray[i] != null){
				System.out.println(t1.termArray[i].getName());

			}else{
				System.out.println("____");
			}

		}
		t1.add("Document","dog");
		t1.add("Document","dog");


		System.out.println();
		System.out.println();
		System.out.println();
		for(int i = 0; i < t1.size; i++){
			if(t1.termArray[i] != null){
				System.out.println(t1.termArray[i].getName());

			}else{
				System.out.println("____");
			}

		}

	}

}

