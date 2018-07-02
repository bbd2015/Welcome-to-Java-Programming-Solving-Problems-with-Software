
/**
 * Write a description of FindGene here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna){
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){
            return result;
        }
        int endIndex = dna.indexOf("TAA", startIndex + 3);
        if (endIndex == -1){
            return result;
        }
        result = dna.substring(startIndex, endIndex + 3);
        return result;
    }
    
    public void testSimpleGene(){
        String dna = "";
        String gene = "";
        dna = "AATGTTAAGTAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "AATTAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "AATGTAGG";
        System.out.println("DNA stand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
    }
    

}
