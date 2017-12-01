package ch.bfh.bti7535.w2017.midori.movieclassifier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        // https://weka.wikispaces.com/Programmatic+Use
        //InstanceLoader loader = new InstanceLoader();
    	
    	ArffImporter imp = new ArffImporter();
    	Files.list(Paths.get(".")).forEach(System.out::println);
    	imp.imortArff("data\\movie-reviews.arff");
        
    }
}
