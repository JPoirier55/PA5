import java.text.DecimalFormat;


public class PA5 {

	public static void main (String args[]){

		WebPages X = new WebPages();
		X.addPage(args[0]);
		//System.out.println(X.Tree.root.getItem().getName());

		//		X.printBSTTerms();

//		System.out.println("WORDS");
		for(int i = 0; i < X.deleteWords.size(); i++){
			X.pruneStopWords(X.deleteWords.get(i));
		}

//		for(int i = 0; i < X.table.size(); i++){
//			if(X.table.termArray[i] != null ){
//				if(X.table.termArray[i].getName().compareTo("RESERVED")!=0){
//					System.out.println(X.table.termArray[i].getName());
//				}
//			}
	//	}

//		System.out.println();
		//for (int i = 0; i<X.deleteWords.size();i++){

		//X.whichPagesBST(X.deleteWords.get(i), true);
		//}
		X.docSpecific= new double[X.files.size()];

		X.setupSpecifics();


		for(int i = 0; i < X.queries.size(); i++){
			X.bestPages(X.queries.get(i));
			
			X.simValReturn();


		}
		
//		for(int j = 0; j < X.graph.vertices.size(); j++){
//			if(X.graph.vertices.get(j).name.compareTo("")!=0){
//				System.out.println(X.graph.vertices.get(j).name + " "+ X.graph.vertices.get(j).degree);
//			}
//		}
//		for(int i = 0; i< X.graph.edges.size();i++){
//			System.out.println(X.graph.edges.get(i).from+" -> "+X.graph.edges.get(i).to);
//		}
		X.graph.writeDotFile(X.outputName+".dot");

	}

}
