
/**
 * Write a description of weather here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class weather {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord res = null;
        for (CSVRecord record: parser){
            if (res == null && record.get("TemperatureF") != "-9999"){
                res = record;
            } else{
                Double curTemp = Double.parseDouble(record.get("TemperatureF"));
                Double smallTemp = Double.parseDouble(res.get("TemperatureF"));
                if (curTemp != -9999 && curTemp < smallTemp){
                    res = record;
                }
            }
        }
        return res;
        
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = coldestHourInFile(parser);
        System.out.println("Coldest Temperature in this file is " + record.get("TemperatureF")
            + ", Coldest time is " + record.get("DateUTC"));
    }
    
    
    public String fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldest = null;
        String filename = "";
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord cur = coldestHourInFile(fr.getCSVParser());
            if (coldest == null){
                coldest = cur;
            } else{
                Double curTemp = Double.parseDouble(cur.get("TemperatureF"));
                Double coldestTemp = Double.parseDouble(coldest.get("TemperatureF"));
                if (curTemp < coldestTemp){
                    coldest = cur;
                    filename = f.getName();
                }
            }
        }
        return filename;  
        }
    
    public void testFileWithColdestTemperature() {
        String fname = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + fname);
        FileResource fr = new FileResource("nc_weather/2013/"+fname);
        CSVRecord record = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was " + record.get("TemperatureF"));
        
        
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowest = null;
        for(CSVRecord cur: parser){
            if (lowest == null && !cur.get("Humidity").equals("N/A")){
                lowest = cur;
                //System.out.println(lowest.get("Humidity"));
            } else{
                if (cur.get("Humidity").equals("N/A")){
                    break;
                }
                else {
                    Double lowestH = Double.parseDouble(lowest.get("Humidity"));
                    Double curH = Double.parseDouble(cur.get("Humidity"));
                    if (curH < lowestH){
                        lowest = cur;
                    }
                }
            }
    }
        return lowest;
    }
    
    public void testColdestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at "
             + csv.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord record = null;
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord cur = lowestHumidityInFile(parser);
            if(record == null ){
                record = cur;
            } else{
                Double lowestH = Double.parseDouble(record.get("Humidity"));
                Double curH = Double.parseDouble(cur.get("Humidity"));
                if (curH < lowestH){
                    record = cur;
                }
            }
        }
        return record;
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at "
             + csv.get("DateUTC"));
    }
    
    public Double averageTemperatureInFile(CSVParser parser){
        Double res = 0.0;
        int count = 0;
        for(CSVRecord record:parser){
            if(record.get("TemperatureF").equals("-9999")){
                break;
            } else{
                Double temp = Double.parseDouble(record.get("TemperatureF"));
                res = res + temp;
                count = count + 1;
            }
        }
        return res/count;
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Avereage temp in this file is " + averageTemperatureInFile(parser));
    }
    
    public Double averageTemperatureWithHighHumidityInFile(CSVParser parser,int val){
        Double temp = 0.0;
        int cnt = 0;
        for(CSVRecord record:parser){
            //String curTemp = ;
            //String curHum = ;
            if (record.get("Humidity").equals("N/A") || record.get("TemperatureF").equals("-9999")){
                break;
            } else{
                if (Double.parseDouble(record.get("Humidity")) >= val){
                    temp = temp + Double.parseDouble(record.get("TemperatureF"));
                    cnt = cnt + 1;
            }
            }
    }
    return temp/cnt;
}
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Average temp when high temperature is " + averageTemperatureWithHighHumidityInFile(parser, 80));
    }
    
}


    
    

