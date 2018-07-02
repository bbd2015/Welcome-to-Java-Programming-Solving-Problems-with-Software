import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of babyName here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class babyName {
    public void totalBirths(){
        FileResource fr = new FileResource();
        int numBoys = 0;
        int numGirls = 0;
        int totalNames = 0;
        for(CSVRecord record: fr.getCSVParser(false)){
            String gender = record.get(1);
            int num = Integer.parseInt(record.get(2));
            if (gender.equals("M")){
                numBoys += num;
            } else{
                numGirls += num;
            }
            totalNames += num;
        }
        System.out.println("number of boys = " + numBoys);
        System.out.println("number of girls = " + numGirls);
        System.out.println("number of total = " + totalNames);
    }
    
    public int getRank(int year, String name, String gender, CSVParser parser){
        int res = -1;
        //FileResource fr = new FileResource();
        int count = 1;
        //for(CSVRecord record: fr.getCSVParser(false)){
        for(CSVRecord record: parser){
            String curName = record.get(0);
            String curGender = record.get(1);
            if (curGender.equals(gender)){
                if(curName.equals(name)){
                    return count;
                }
                count += 1;
            }
        }
        return res;
        
    }
    
    public void testRank(){
        String name = "Frank";
        String gender = "M";
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int rank = getRank(1971, name, gender, parser);
        System.out.println( name + " in 1971 " + " with rank " + rank);
        name = "Emily";
        gender = "F";
        fr = new FileResource();
        parser = fr.getCSVParser();
        rank = getRank(1960, name, gender,parser);
        System.out.println( name + " in 1960 " + " with rank " + rank);
    }
    
    public String getName(int year, int rank, String gender){
        String res = "NO NAME";
        FileResource fr = new FileResource();
        int count = 1;
        for(CSVRecord record: fr.getCSVParser(false)){
            String curGender = record.get(1);
            if (curGender.equals(gender)){
                if(rank == count){
                    return record.get(0);
                }
                count += 1;
            }
        }
        return res;
    }
    
    void testgetName(){
        int year = 1980;
        int rank = 350;
        String gender = "F";
        String name = getName(year, rank, gender);
        System.out.println("with rank " + rank + " in " + year + " with gender " + gender +  " name is " + name);
        year = 1982;
        rank = 450;
        gender = "M";
        name = getName(year, rank, gender);
        System.out.println("with rank " + rank + " in " + year + " name is" + name);
        
    }
    
    public void whatIsNameInYear(String name, int year,int newYear, String gender){
        FileResource fr_year = new FileResource();
        int oldRank = 1;
        String g = "She";
        if(!gender.equals(g)){
            g = "He";
        }
        for(CSVRecord record: fr_year.getCSVParser(false)){
            if (record.get(1).equals(gender)){
                if (record.get(0).equals(name)){
                    break;
                }
                oldRank += 1;
            }
        }
        System.out.println("old rank is " + oldRank);
        FileResource fr_newyear = new FileResource();
        String newName = "";
        int newRank = 1;
        for(CSVRecord record:fr_newyear.getCSVParser(false)){
            if(record.get(1).equals(gender)){
                if(newRank==oldRank){
                    newName = record.get(0);
                    System.out.println(name + " born in " + year + " would be " + newName + " if " + g + "was born in " + newYear);
                }
                newRank += 1;
            }
        }
        
    }
    
    public int yearOfHighestRank(String name, String gender){
        int res = -1;
        int rank = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f:dr.selectedFiles()){
            String filename = f.getName();
            int year = Integer.parseInt(filename.substring(3,7));
            FileResource fr = new FileResource(f);
            int curRank = getRank(year, name, gender,fr.getCSVParser(false));
            if (curRank == -1){
                break;
            } else{
                if (rank == 0 || curRank < rank){
                    rank = curRank;
                    res = year;
                }
            }
        }
        return res;
    }
    
    public void testHigestRank(){
        //System.out.println("highest rank is " + yearOfHighestRank("Genevieve", "F"));
        System.out.println("highest rank Mich is " + yearOfHighestRank("Mich", "M"));
    }
    
    public double getAverageRank(String name, String gender){
        int rank = 0;
        double cnt = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f:dr.selectedFiles()){
            String filename = f.getName();
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            int year = Integer.parseInt(filename.substring(3,7));
            int curRank = getRank(year, name, gender,parser);
            if (curRank != -1){
                rank += curRank;
                cnt += 1;
            }
        }
        if (cnt == 0){
            return -1;
        }
        return rank/cnt;
    }
    
    public void testAvg(){
        System.out.println("average with name susan " + getAverageRank("Susan","F"));
        System.out.println("average with name Robert " + getAverageRank("Robert","M"));
    }


    public int getTotalBirthsRankedHigher(int year,String name, String gender){
        FileResource fr = new FileResource();
        int res = 0;
        for(CSVRecord record:fr.getCSVParser(false)){
            String curName = record.get(0);
            String curGender = record.get(1);
            if(curName.equals(name) && curGender.equals(gender)){
                return res;
            }
            res += Integer.parseInt(record.get(2));
        }
        return res;
    }
    
    public void testHigher(){
        String name = "Emily";
        String gender = "F";
        System.out.println("number higher than " + name + "=" + getTotalBirthsRankedHigher(1990, name, gender));
        name = "Drew";
        gender = "M";
        System.out.println("number higher than " + name + "=" + getTotalBirthsRankedHigher(1990, name, gender));
    }
    
}

