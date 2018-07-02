
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
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
        String dnacopy = dna;
        dna = dna.toUpperCase();
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
        return dnacopy.substring(firstIndex, minIndex+3);
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

    public StorageResource getAllGenes(String dna){
        StorageResource sr = new StorageResource();
        String gene = "";
        int startIndex = 0;
        int count = 0;
        while (true){
            gene = findGene(dna, startIndex);
            if (gene == ""){
                break;
            } else{
                //System.out.println(gene);
                sr.add(gene);
                count = count + 1;
                startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
    }
        System.out.println("number of genes:");
        System.out.println(count);
        return sr;
}
    public float cgRatio(String dna){
        int cs = 0;
        int gs = 0;
        for (int i = 0; i < dna.length();i++){
            if (dna.charAt(i) == 'C' || dna.charAt(i) == 'c'){
                cs += 1;
            }
            if (dna.charAt(i) == 'G' || dna.charAt(i) == 'g'){
                gs += 1;
            }
        }
        return (float)cs/gs;
    }
    
    public int countCTG(String dna){
        int count = 0;
        int startIndex = 0;
        while(true){
            startIndex = dna.indexOf("CTG",startIndex);
            if (startIndex == -1){
                break;
            }
            count = count + 1;
            startIndex = startIndex + 3;
        }
        return count;
    }
    
    public void processGenes(StorageResource sr){
        int count9 = 0;
        int countR = 0;
        String res = "";
        for (String gene: sr.data()){
            if (gene.length() > 60){
                //System.out.println(gene);
                count9 += 1;
            }
            if (cgRatio(gene) > 0.35){
                //System.out.println(gene);
                countR += 1;
            }
            if (gene.length() > res.length()){
                res = gene;
            }
            
        }
        System.out.print("longest gene: ");
        System.out.println(res.length());
        System.out.print("number of genes longer than 60:");
        System.out.println(count9);
        System.out.print("number o ration greater than 0.35: ");
        System.out.println(countR);
    }
    public void testProcessGenes(){
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        //System.out.print("File string: ");
        //System.out.print(dna);
        //dna = dna.toUpperCase();
        System.out.println("count CTG: ");
        System.out.println(countCTG(dna));
        StorageResource sr = getAllGenes(dna);
        System.out.println("number of genes");
        System.out.println(sr.size());
        processGenes(sr);
        /*
        // longer than 9 characters
        StorageResource sr = new StorageResource();
        String dna = "ATGxxxxxxTAGATGXXXXXXTAAXXXATGxxxxxxTAA";
        sr = getAllGenes(dna);
        processGenes(sr);
        // no longer than 9 characters
        dna = "ATGXXTAA";
        sr = getAllGenes(dna);
        processGenes(sr);
        // ratio > 0.35
        dna = "ATGCCCCCCCCCTGTAA";
        sr = getAllGenes(dna);
        processGenes(sr);
        
        // <0.35
        dna = "ATGXXXTAAATGCC";
        sr = getAllGenes(dna);
        processGenes(sr);
        */
    }
    void test(){
        String dna = "ATGXXXTAAXXATGTTTGAXXX";
        //printAllGenes(dna);
        StorageResource genes = getAllGenes(dna);
        for(String gene:genes.data()){
            System.out.println(gene);
        }
    }

}
