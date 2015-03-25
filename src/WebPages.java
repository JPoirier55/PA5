import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Scanner;

//Jake Lord and Jake Poirier
// 9/21/14
public class WebPages {

	public int totalNumDocs = -1, middle;
	public HashTable table;
	public Graph graph;

	public ArrayList<Term>termIndex; 
	public ArrayList<Term>repeats = new ArrayList<Term>();
	public ArrayList<String>files, deleteWords, queryWords;
	public static ArrayList<String>queries;
	public static double[] docSpecific;
	public double[] common;
	public double queryWeights;
	public double[] docs;
	public String max = "";



	private int indexOfRepeats = 0;
	//These fix problems within our loops that were too difficult to reprogram
	private int fix = 0;
	private int otherFix = 0;
	private int fileIndex = 0;
	private int CHECKIN = 0;
	String [] fileFoundArray;
	int PRUNEnumber=0;
	public String outputName;

	public WebPages(){
		this.termIndex = new ArrayList<Term>();
		this.queries = new ArrayList<String>();
		queryWords = new ArrayList<String>();

		this.graph = new Graph(0);
		

	}

	public String[]whichPages(String word) {
		int counter=-1;
		for(int i=0; i<termIndex.size(); i++) {
			if(word.toLowerCase().equals(termIndex.get(i).getName())) {
				counter=i;
			}	
		}
		if(counter==-1) {
			return null;
		}
		fileFoundArray=new String[termIndex.get(counter).list.size()];
		for (int i=0; i<fileFoundArray.length; i++) {
			fileFoundArray[i]=termIndex.get(counter).list.get(i).docName;
		}		
		return fileFoundArray;
	}
	public void fileReader (String filename){
		CHECKIN = 1;
		files = new ArrayList<String>();
		deleteWords = new ArrayList<String>();
		File file = new File(filename);

		try{
			Scanner s = new Scanner(file);
			outputName = s.next();
			//Find arraysize
			HashTable.size = s.nextInt();
			table = new HashTable(HashTable.size);
			//This adds filenames into arraylist and breaks once we hit EOFs
			while (true){
				String holder = s.next();
				//	System.out.println(holder);
				totalNumDocs++;
				if(holder.equals("*EOFs*")) break;
				files.add(holder);
				graph.vertices.add(new Vertex(holder,0));
				
			}

			Collections.sort(files);
			//skip EOFs
			s.nextLine();
			//allocate prunenumber
			
			//Store words that are needed for whichPages method
			while (s.hasNextLine()){
				String line = s.nextLine();
				//System.out.println(line);
				if(line.equals("*STOPs*")){
					//					line = s.nextLine();
					break;
				}
				if (line.length()>0)
					deleteWords.add(line);

			}
			while(s.hasNextLine()){
				String line = s.nextLine();
				queries.add(line);
			}

			for (int i = 0; i<files.size(); i++){
				addPage(files.get(i));
				fileIndex++;
				indexOfRepeats = repeats.size();
			}


		}catch (IOException e){
			System.out.println("Encountered the following error"+e);
		}
	}

	public void addPage(String filename){
		if(CHECKIN==0){
			fileReader(filename);
		}else{
			File file = new File(filename);

			try{
				Scanner scan = new Scanner(file);
				String y = "";
				while (scan.hasNextLine()){
				
					y = y + scan.nextLine() + " ";
					
				}
//				System.out.println("File pased in: " + filename);

				getLinks(y, y.length(),filename);
				
					y = y.toLowerCase().replaceAll("[^\\w\\s\\<\\>\\']"," ").replaceAll("\\<.*?\\>", " ");
					
					y = y.replaceAll("[\\<\\>]", " ");
					Scanner z = new Scanner (y);

					while(z.hasNext()){
						String properTerm = z.next();
						//System.out.println(properTerm);

						Term aTerm = new Term(properTerm);
						repeats.add(aTerm);
//						System.out.println(z.toString());
					}
				
				
				scan.close();

			}catch (IOException e){
				System.out.println("Encountered the following error"+e);

			}
			try{
				//allocate();
				allocateHashTable();
			}catch(NoSuchElementException e){
			}
		}	
	



	}
	public void getLinks(String a, int length, String filename){
		
		int vertexCheck = 0;
		int count = 0;
		String word = "";
		//System.out.println(a);
		if(a.contains("http://")){

			int num = a.indexOf("http://");
			char i = 34;
			while(a.charAt(num + 7 + count) != i){

				word = word + a.charAt(num +7 + count);

				count++;
			}
			word = word.replaceAll("[^\\w\\s\\.\\']","");

			//System.out.println("word = " + word);
			
			for(int j = 0; j < graph.vertices.size(); j++){
//				graph.vertices.get(j).name.toLowerCase();

				if(graph.vertices.get(j).name.compareTo(word) ==0){
					//System.out.println(word);
					graph.vertices.get(j).degree++;
					//System.out.println(graph.vertices.get(j).name);
					vertexCheck = 1;

				}
				
			}
			//System.out.println(filename + "  " + word + "  " + vertexCheck);
			
			if(vertexCheck == 0) {
				
				graph.vertices.add(new Vertex (word,1));
				//System.out.println(filename+">>"+word);
			}
			
			
			

			if(word.compareTo("") != 0){

				graph.edges.add(new Edge(filename,word));
				//System.out.println(word);
			}
			
			String nextString = a.substring(num+7+count,a.length());
			getLinks(nextString,nextString.length(),filename);


		}



	}
	
	
//	public String binarySearch(ArrayList <Term> A , String key, int imin, int imax)
//	{
//		// continue searching while [imin,imax] is not empty
//
//		while (imax >= imin)
//		{
//
//			// calculate the midpoint for roughly equal partition
//			int imid = (imin+imax)/2;
//
//
//			if(A.get(imid).getName().equals(key)){
//				// key found at index imid
//
//				if(otherFix==1){ A.get(imid).incFrequency(files.get(fileIndex));
//				}
//				otherFix = 1;
//
//				return "Found"; }
//			// determine which subarray to search
//			else if (A.get(imid).getName().compareTo(key)<0) 
//				// change min index to search upper subarray
//				imin = imid + 1;
//			else         
//				// change max index to search lower subarray
//				imax = imid - 1;
//		}
//		// key was not found
//
//		return "Not Found";
//	}
//
//	public void allocate (){
//		try{
//			if (fix == 0){
//				termIndex.add(repeats.get(0));
//				termIndex.get(0).incFrequency(files.get(fileIndex));
//				fix = 1;
//			}
//			int loopBreak = 0;
//
//
//
//			for(int i = indexOfRepeats; i < repeats.size(); i++){
//				loopBreak = 0;
//				String b = binarySearch(termIndex,repeats.get(i).getName(),0,termIndex.size()-1);
//
//				if(b.equals("Not Found")){
//
//
//					for (int j = 0; j<termIndex.size(); j++){
//
//						if(repeats.get(i).getName().compareTo(termIndex.get(j).getName())<0 && loopBreak == 0  ){
//
//							termIndex.add(j,repeats.get(i));
//							termIndex.get(j).incFrequency(files.get(fileIndex));
//
//							break;
//
//						}else if(repeats.get(i).getName().compareTo(termIndex.get(termIndex.size()-1).getName())>0 && loopBreak == 0){
//							termIndex.add(repeats.get(i));
//							termIndex.get(termIndex.size()-1).incFrequency(files.get(fileIndex));
//							break;
//						}
//					}
//				}
//
//				//indexOfRepeats = repeats.size();
//				//fileIndex=fileIndex+1;
//			}
//		}catch(IndexOutOfBoundsException e){
//			System.out.println("Error: Nothing legal to add to arraylist");
//		}
//	}
//
//	public void allocateBST(){


		//		if (fix == 0){
		//			
		//			Tree.add(files.get(fileIndex),repeats.get(0).getName());
		//			Tree.root.getItem().incFrequency(files.get(fileIndex));
		//			fix = 1;
		//			}
		//System.out.println(indexOfRepeats+" "+fileIndex+" "+files.get(fileIndex)+" "+repeats.get(0).getName());


//		for(int i = indexOfRepeats; i < repeats.size(); i++){
//
//			table.add(files.get(fileIndex), repeats.get(i).getName());
//
//		}
//
//	}

	public void allocateHashTable(){

		for(int i = indexOfRepeats; i < repeats.size(); i++){

			table.add(files.get(fileIndex), repeats.get(i).getName());

		}
		
//		if (fix == 0){
		//			
		//			Tree.add(files.get(fileIndex),repeats.get(0).getName());
		//			Tree.root.getItem().incFrequency(files.get(fileIndex));
		//			fix = 1;
		//			}
		//System.out.println(indexOfRepeats+" "+fileIndex+" "+files.get(fileIndex)+" "+repeats.get(0).getName());

	}

	//	public void pruneStopWords(int n){
	//		
	//		termIndex = mergeSort(termIndex);
	//
	//		
	//		System.out.println("Copies: "+mergeCounter1);
	//		for (int i = 0; i < n; i++){
	//			termIndex.remove(0);
	//		}

	//	//	termIndex = mergeSortWords(termIndex);
	//		System.out.println("Copies: "+mergeCounter1);
	//		System.out.println();
	//		
	//	}

	public void pruneStopWords(String word){

		table.delete( word);

	}

//	public void printTerms(){
//		System.out.println("WORDS");
//		for (int i = 0; i<termIndex.size(); i++)
//			System.out.println(termIndex.get(i).getName()+"  "+termIndex.get(i).getTotalFrequency());



//	}
	//	public void printBSTTerms(){
	//		InorderIterator i1 = new InorderIterator(table.myTree);
	//		i1.setInorder();
	//		System.out.println("WORDS");
	//		while(i1.hasNext())
	//			System.out.println(i1.next().getName());
	//		
	//	}
	//This code was taken from softbase.ipfw.edu
	//START


//	public void whichPagesBST(String word, boolean depthPrint){
//		DecimalFormat d = new DecimalFormat("0.00");
//		Term foundTerm = table.get(word, depthPrint);
//		if(foundTerm==null){
//			System.out.println(word+" not found");
//		}else{
//			System.out.print(word+" in pages: ");
//			for(int i = 0; i<foundTerm.list.size();i++)
//				if(i != foundTerm.list.size()-1){
//					double tf = TFIDF(foundTerm.list.get(i).getDocName(),foundTerm);
//					System.out.print(foundTerm.list.get(i).getDocName()+ ": "+d.format(tf)+ ", " );
//				}else{
//					double tf = TFIDF(foundTerm.list.get(i).getDocName(),foundTerm);
//					System.out.print(foundTerm.list.get(i).getDocName()+ ": "+d.format(tf) );
//
//				}
//			System.out.println();
//
//		}
//
//	}

	public double TFIDF(String document, Term t) {
		double TFIDF=0;
		float docFreq=0;
		for (int i=0; i<t.list.size(); i++) {
			if(t.list.get(i).getDocName().equals(document)) {
				docFreq=(float)t.list.get(i).getTermFrequency();
			}
		}
		float totalDoc=(float)t.getDocFrequency();
		TFIDF=docFreq*Math.log((float)totalNumDocs/totalDoc);
		return TFIDF;
	}

	public double QIDF(Term t) {
		double QIDF=0;

		float totalDoc=(float)t.getDocFrequency();
		QIDF=.5*(1+Math.log((float)totalNumDocs/totalDoc));
		return QIDF;
	}

	public void setupSpecifics(){

		
		double tf;
	

		for(int i = 0; i < table.size(); i++){
			if(table.termArray[i] != null ){

				if(table.termArray[i].getName().compareTo("RESERVED")!=0){


					Term foundTerm = table.termArray[i];
					//System.out.println("Getting table terms  "+ foundTerm.getName());
					for (int j = 0; j < foundTerm.list.size(); j++) {

						tf = TFIDF(foundTerm.list.get(j).getDocName(), foundTerm);

						for (int k = 0; k < files.size(); k++) {
							if (files.get(k).compareTo(foundTerm.list.get(j).docName) == 0) {

								docSpecific[k] = docSpecific[k] + tf * tf;
								
							}
						}
					}
				} 
			}
		}
	}
	
//	public void binarySearch(String key, int low, int high) {
//		while (low <= high) {
//			middle = low + ((high - low) / 2);
//			if (key.compareTo(queryWords.get(middle)) > 0) {
//				low = middle + 1;
//				middle++;
//				binarySearch(key, low, high);
//			} else if (key.compareTo(queryWords.get(middle)) < 0) {
//				high = middle - 1;
//				binarySearch(key, low, high);
//			} else
//				return;
//		}
//	}
	public int binarySearch(ArrayList<String> words, String processed) {
        int start = 0;
        int mid = 0;
        int end = words.size();

        while (end > start) {
            mid = (start + end) / 2;
            if (processed.compareTo(words.get(mid)) > 0) {
            	start = mid + 1;
            }
            else if (processed.compareTo(words.get(mid)) < 0) {
            	end = mid;
            }
        }
        return end;
    }


	public void bestPages(String query){

		common = new double[files.size()];
	
		queryWords = new ArrayList<String>();
		queryWeights = 0;
		double tf;
		double qf;

		

		Scanner scanQuery = new Scanner(query);
		while (scanQuery.hasNext()) {
			String temp = scanQuery.next();
			temp = temp.toLowerCase();
			//System.out.println("Query word = " + temp);
			
			queryWords.add(binarySearch(queryWords, temp), temp);
		}
		
		//System.out.println(queryWords.toString());
		
//		for (int i = 0; i < queryWords.size(); i++) {
//			if(queryWords.get(i).compareTo(max)> 0){
//				System.out.println("Comparing " + queryWords.get(i) + " and " + max);
//				max = queryWords.get(i);
//				System.out.println("max = " +max);
//				String temp = queryWords.get(0);
//				queryWords.set(i, temp);
//				queryWords.set(0, max);
//				
//				
//			}else{
//				System.out.println("Comparing " + queryWords.get(i) + " and " + max);
//			}
//			System.out.println(queryWords.toString());
//			
//		}


		for (int i = 0; i < queryWords.size(); i++) {

			if (table.get(queryWords.get(i), false) != null) {

				Term foundTerm = table.get(queryWords.get(i), false);

				qf = QIDF(foundTerm);
				queryWeights = queryWeights + qf * qf;

				for (int j = 0; j < foundTerm.list.size(); j++) {

					//System.out.println(foundTerm.list.get(j).docName);
					tf = TFIDF(foundTerm.list.get(j).getDocName(), foundTerm);

					for (int k = 0; k < files.size(); k++) {
						if (files.get(k).compareTo(foundTerm.list.get(j).docName) == 0) {

							common[k] = common[k] + tf * qf;
				
						}
					}
				}
			} 
		}
	}

	public void simValReturn(){
		double[] simVal = new double[files.size()];
		for (int d = 0; d < simVal.length; d++) {
			simVal[d] = common[d]
					/ (Math.sqrt(docSpecific[d]) * Math.sqrt(queryWeights));
			//System.out.println("Sqroot  "+Math.sqrt(docSpecific[d]));
			//		System.out.println("   | common              |  docspecific          | queryweights");
			//		System.out.print(d+"  | "+common[d] +        " |       "+ docSpecific[d] +" | " +queryWeights);
			//		System.out.println();
		}
		int arrayPlace = 0;
		double max = 0;
		// double zeroVal = simVal[0];
		for (int j = 0; j < simVal.length; j++) {
			if (simVal[j] > max) {

				arrayPlace =j;
				max = simVal[j]*graph.inDegree(files.get(j));
				
			}
			//System.out.println(simVal[j]);
			
		}
		DecimalFormat fmt = new DecimalFormat("###0.00");
		System.out.print("[");
		for(int i = 0; i < queryWords.size();i++){
			System.out.print(queryWords.get(i)+" ");
			
		}if(max == 0){
			System.out.println("] "+"not found");
		}else{
			System.out.println("] in " + files.get(arrayPlace)
					+ ": " + fmt.format(max));
		}
		
		
	}
	

//	int mergeCounter1=0;
//	int mergeCounter2=0;
//
//	public ArrayList<Term> mergeSort(ArrayList<Term> a) {
//		mergeCounter1=0;
//		if (a.size() <= 1) {
//			return a;
//		}
//		ArrayList<Term> firstHalf = new ArrayList<>();
//		ArrayList<Term> secondHalf = new ArrayList<>();
//		for (int i = 0; i < a.size() / 2; i++) {
//			firstHalf.add(a.get(i));
//		}
//		for (int i = a.size() / 2; i < a.size(); i++) {
//			secondHalf.add(a.get(i));
//		}
//		return merge(mergeSort(firstHalf), mergeSort(secondHalf));
//	}
//
//	public ArrayList<Term> merge(ArrayList<Term> l1, ArrayList<Term> l2) {
//
//		if (l1.size() == 0) {
//			return l2;
//		}
//		if (l2.size() == 0) {
//			return l1;
//		}
//		ArrayList<Term> result = new ArrayList<>();
//		Term nextElement;
//		if (l1.get(0).getTotalFrequency() <= l2.get(0).getTotalFrequency()) {
//
//			nextElement = l2.get(0);
//			l2.remove(0);
//		} else {
//			nextElement = l1.get(0);
//			l1.remove(0);
//		}
//		result.add(nextElement);
//		result.addAll(merge(l1,l2));
//		mergeCounter1++;
//		return result;
//	}
//
//	public ArrayList<Term> mergeSortWords(ArrayList<Term> a) {
//		mergeCounter1=0;
//		if (a.size() <= 1) {
//			return a;
//		}
//		ArrayList<Term> firstHalf = new ArrayList<>();
//		ArrayList<Term> secondHalf = new ArrayList<>();
//		for (int i = 0; i < a.size() / 2; i++) {
//			firstHalf.add(a.get(i));
//		}
//		for (int i = a.size() / 2; i < a.size(); i++) {
//			secondHalf.add(a.get(i));
//		}
//		return mergeWords(mergeSortWords(firstHalf), mergeSortWords(secondHalf));
//	}
//
//	public ArrayList<Term> mergeWords(ArrayList<Term> l1, ArrayList<Term> l2) {
//
//		if (l1.size() == 0) {
//			return l2;
//		}
//		if (l2.size() == 0) {
//			return l1;
//		}
//		ArrayList<Term> result = new ArrayList<>();
//		Term nextElement;
//		if (l1.get(0).getName().compareTo(l2.get(0).getName()) > 0) {
//			mergeCounter1++;
//			nextElement = l2.get(0);
//			l2.remove(0);
//
//		} else {
//			nextElement = l1.get(0);
//			l1.remove(0);
//
//		}
//		result.add(nextElement);
//		result.addAll(mergeWords(l1,l2));
//
//		return result;
//
//	}
//	//END OF CODE TAKEN FROM softbase.ipfw.edu



}




