
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String a, String b){
        int la = a.length();
        int lb = b.length();
        int res = 0;
        if (la > lb){
            return res;
        }
        int curIndex = -1;
        while (true){
            curIndex = b.indexOf(a, curIndex);
            if (curIndex != -1){
                res += 1;
                curIndex = curIndex + la;
            } else{
                break;
            }
        }
        return res;
        
    }
    
    void testHowMany(){
        String a = "GAA";
        String b = "AAGAATTTGAAXXXGAA";
        System.out.println(howMany(a,b));
        
        a = "AA";
        b = "AAAAXAA";
        System.out.println(howMany(a,b));
    }

}
