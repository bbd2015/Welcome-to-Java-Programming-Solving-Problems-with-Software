
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
//import java.io.*;
//import edu.duke.URLResource;
import java.io.File;
import java.util.*;
 

public class Part4 {
    public void search(){
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String word : ur.lines()){
            String wordLow = word.toLowerCase();
            if (wordLow.contains("youtube.com") && (wordLow.contains("https://") || wordLow.contains("http://"))){
                int start = wordLow.indexOf("\"");
                int stop = wordLow.lastIndexOf("\"");
                String result = word.substring(start+1, stop);
                System.out.println(result);
        }  
    }
}

}
