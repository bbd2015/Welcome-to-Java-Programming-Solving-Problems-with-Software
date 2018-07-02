
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class WordLengths {
    public int indexOf(String word){
        int res = word.length();
        if (! Character.isLetter(word.charAt(res-1))){
            res -= 1;
        }
        if (! Character.isLetter(word.charAt(0))){
            res -= 1;
        }
        return res;
        
        }
      
    public void countWordLengths(FileResource resource, int[] counts){
        int n = counts.length;
        for (String word: resource.words()){
            int idx = indexOf(word)-1;
            if (idx > n-1){
                idx = n-1;
            }
            counts[idx] += 1;
        }
        
    }
    
    public void testCountWordLengths(){
        FileResource resource = new FileResource("smallHamlet.txt");
        int [] counts = new int[31];
        countWordLengths(resource, counts);
        System.out.println(indexOfMax(counts));
    }
    public int indexOfMax(int[] values){
        int n = values.length;
        int res = values[0];
        for (int i = 1; i < n; i++){
            if (values[i] > res){
                res = values[i];
            }
        }
        return res;
    }

}
