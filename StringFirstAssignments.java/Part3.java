
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
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
    
    public boolean twoOccurrences(String a, String b){
        int count = 0;
        int curIndex = b.indexOf(a);
        int len = a.length();
        while (curIndex != -1){
            count += 1;
            curIndex = b.indexOf(a, curIndex + len);
        }
        if (count >= 2){
            return true;
        }
        return false; 
    }
    
    public String lastPart(String a, String b){
        int lena = a.length();
        int lenb = b.length();
        String result = "";
        int startCodon = b.indexOf(a);
        if (startCodon == -1){
            return b;
        }
        result = b.substring(startCodon + lena, lenb);
        return result;
    }
    public void testing(){
        String a = "";
        String b = "";
        boolean result;
        String lastPart = "";
        a = "abc";
        b = "aabcccabc";
        // return True
        System.out.println("string a is " + a);
        System.out.println("string b is " + b);
        result = twoOccurrences(a, b);
        System.out.println("result is " + result);
        lastPart = lastPart(a, b);
        System.out.println("last part is " + lastPart);
        
        a = "taatg";
        b = "atgtaat";
        System.out.println("string a is " + a);
        System.out.println("string b is " + b);
        result = twoOccurrences(a, b);
        System.out.println("result is " + result);
        lastPart = lastPart(a,b);
        System.out.println("last part is " + lastPart);
        
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
