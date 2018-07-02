import edu.duke.*;
import java.io.*;
/**
 * Write a description of gray here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class gray {
    public ImageResource makeGray(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel pixel:outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(),pixel.getY());
            int avg = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) / 3;
            pixel.setRed(avg);
            pixel.setGreen(avg);
            pixel.setBlue(avg);
        }
        return outImage;
    }
    public ImageResource makeInverted(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel pixel: outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int red = 255 - inPixel.getRed();
            int green = 255 - inPixel.getGreen();
            int blue = 255 - inPixel.getBlue();
            pixel.setRed(red);
            pixel.setGreen(green);
            pixel.setBlue(blue);
        }
        return outImage;
    }
    
    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = makeGray(inImage);
            ImageResource invert = makeInverted(inImage);
            String fname = inImage.getFileName();
            String newName = "gray-" + fname;
            String newName2 = "inverted-" + fname;
            invert.setFileName(newName2);
            invert.draw();
            invert.save();
            gray.setFileName(newName);
            gray.draw();
            gray.save();
        }
    }

    

}
