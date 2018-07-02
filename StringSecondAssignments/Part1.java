
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int n = dna.length();
        int curIndex = dna.indexOf(stopCodon, startIndex);
        while (curIndex != -1){
        int difference = curIndex - startIndex;
        if (difference%3 == 0){
            return curIndex;
        } else{
            curIndex = dna.indexOf(stopCodon, curIndex+1);
            //System.out.println(curIndex);
        }
    }
        return n;
    }
    
    void testFindStopCodon(){
        //            0123456789012345678901  
        String dna = "ATGxxxxxxxTAGxxxxTAGxx";
        int res = findStopCodon(dna, 0, "TAG");
        if (res != 10) System.out.println("error on 10");
        res = findStopCodon(dna, res, "TAG");
        if (res != 17) System.out.println("error on 17");
        System.out.println("test finished");
    }
    
    String findGene(String dna, int startIndex){
        int firstIndex = dna.indexOf("ATG", startIndex);
        if (firstIndex == -1){
            return "";
        }
        int firstTAA = findStopCodon(dna, firstIndex+3, "TAA");
        int firstTAG = findStopCodon(dna, firstIndex+3, "TAG");
        int firstTGA = findStopCodon(dna, firstIndex+3, "TGA");
        int n = dna.length();
        int minIndex = n;
        if (firstTAA != n){
            if (firstTAG != n && (firstTAG < firstTAA)){
                minIndex = firstTAG;
            } else{
                minIndex = firstTAA;
            }
        } else{
            if (firstTAG != n){
                minIndex = firstTAG;
            }
        }
        if (firstTGA < minIndex){
            minIndex = firstTGA;
        }
        if (minIndex == n){
            return "";
        }
        return dna.substring(firstIndex, minIndex+3);
    }
    
    void testFindGene(){
        
       // String dna = "xxxxxTAA";
       String dna = "AATGCTAACTAGCTGACTAAT";
        System.out.println(dna);
        String gene= findGene(dna,0);
        System.out.println(gene);
        /*
        dna = "xxATGXXXXXTAATAG";
        System.out.println(dna);
        gene= findGene(dna,0);
        System.out.println(gene);
        dna = "xxxATGXXXTGAXXTAGTAAXXXXXXX";
        System.out.println(dna);
        gene= findGene(dna,0);
        System.out.println(gene);
        dna = "ATGXXXXYXXTXX";
        System.out.println(dna);
        gene= findGene(dna,0);
        System.out.println(gene);
        */
       
    }
    
    void printAllGenes(String dna){
        String gene = "";
        int startIndex = 0;
        while (true){
            gene = findGene(dna, startIndex);
            if (gene == ""){
                break;
            } else{
                System.out.println(gene);
                startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
    }
}

    void test(){
        String dna = "ATGXXXTAAXXATGTTTGAXXX";
        printAllGenes(dna);
    }

}
