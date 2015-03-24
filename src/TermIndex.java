//File Name: 
//Jake Poirier
//Email: Jake.Poirier55@gmail.com
//Date: 

public interface TermIndex {

     public void add(String filename, String newWord);

     public int size();

     public void delete(String word);

     public Term get(String word, Boolean printP);

}

