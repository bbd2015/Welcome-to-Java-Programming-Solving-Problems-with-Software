
/**
 * Write a description of Parse here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class Parse {
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "Nauru"));
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser,"cocoa"));
        parser = fr.getCSVParser();
        bigExporters(parser,"$1,000,000,000,000");
        
        
    }
    
    public String countryInfo(CSVParser parser, String country){
        System.out.println("country info method: ");
        //System.out.println(country);
        for(CSVRecord record: parser){
            String count = record.get("Country");
            if (count.contains(country)){
                //System.out.print("contains");
                String export = record.get("Exports");
                //System.out.println(export);
                String val = record.get("Value (dollars)");
                System.out.println(val);
                return country + ":" + export + ":" + val;
            } 
        }
        return "NOT FOUND";
    }
    
    public void  listExportersTwoProducts(CSVParser parser, String exportItem1,
        String exportItem2){
            System.out.println("list Exporters two products: ");
            for (CSVRecord record: parser){
                String export = record.get("Exports");
                if (export.contains(exportItem1) && export.contains(exportItem2)){
                    System.out.println(record.get("Country"));
                }
            }
    }
    public int numberOfExporters(CSVParser parser, String exportItem){
        int res = 0;
        System.out.println("number of exporters: ");
        for(CSVRecord record: parser){
            String export = record.get("Exports");
            if (export.contains(exportItem)){
                res = res + 1;
            }
        }
        return res;
    }
    
    public void bigExporters(CSVParser parser, String amount)
    {   String val = "";
        int count = 0;
        System.out.println("big exporters: ");
        for(CSVRecord record: parser){
            val = record.get("Value (dollars)");
            if (val.length() >= amount.length()){
                count += 1;
                if (count == 3){
                    System.out.println(record.get("Country") + " " + val);
                }
            }
        }
        
    }
}
