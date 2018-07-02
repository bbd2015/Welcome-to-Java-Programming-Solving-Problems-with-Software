
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, int startCodon, int stopCodon){
        String result = "";
        /*int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){
            return result;
        }
        int endIndex = dna.indexOf("TAA", startIndex + 3);
        if (endIndex == -1){
            return result;
        }*/
        if (startCodon == -1 || stopCodon == -1) {
            return result;
        }
        result = dna.substring(startCodon, stopCodon + 3);
        return result;
    }
    
    public void testSimpleGene(){
        String dna = "";
        String gene = "";
        int startCodon = -1;
        int stopCodon = -1;
        //dna = "AATGTTAAGTAA";
        dna = "aatgttaagtaa";
        System.out.println("DNA strand is " + dna);
        startCodon = dna.indexOf("atg");
        stopCodon = dna.indexOf("taa",startCodon + 3);
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("Gene is " + gene);
        
        dna = "AATTAA";
        System.out.println("DNA strand is " + dna);
        startCodon = dna.indexOf("ATG");
        stopCodon = dna.indexOf("TAA",startCodon + 3);
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("Gene is " + gene);
        
        dna = "AATGTAGG";
        System.out.println("DNA stand is " + dna);
        //gene = findSimpleGene(dna);
        startCodon = dna.indexOf("ATG");
        stopCodon = dna.indexOf("TAA",startCodon + 3);
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("Gene is " + gene);
        
    }

}
