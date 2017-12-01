package ch.bfh.bti7535.w2017.midori.movieclassifier;

import java.io.IOException;

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
    	
    	imp.imortArff("C:\\Users\\buche\\Documents\\Git\\ch.bfh.bti7535.w2017.midori\\data\\test.arff");
        
    }
}
