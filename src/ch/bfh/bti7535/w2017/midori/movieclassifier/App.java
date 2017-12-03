package ch.bfh.bti7535.w2017.midori.movieclassifier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        // https://weka.wikispaces.com/Programmatic+Use
        //InstanceLoader loader = new InstanceLoader();
    	
    	ArffImporter imp = new ArffImporter();
    	//imp.importArff("data\\movie-reviews.arff");
    	
    	Stratifier.Fold(10, "data\\movie-reviews.arff");
    }
}
