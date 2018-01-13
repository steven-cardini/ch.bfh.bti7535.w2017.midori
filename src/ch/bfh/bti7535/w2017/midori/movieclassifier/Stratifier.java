package ch.bfh.bti7535.w2017.midori.movieclassifier;

import java.io.File;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;

public class Stratifier {
	
	/**
	 * Methode für x-Faltungen
	 * 
	 * @param xTimes Anzahl Faltungen
	 * @param arffFilePath ARFF-File
	 */
  public static void Fold(int xTimes, File arffFilePath) {
    Classifier c1 = new NaiveBayes();
    Evaluation eval = null;
    
    try {
      eval = new Evaluation(ArffImporter.importArff(arffFilePath));
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    try {
      eval.crossValidateModel(c1, ArffImporter.importArff(arffFilePath), xTimes, new Random(1));
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println("Estimated Accuracy: " + Double.toString(eval.pctCorrect()));
  }
}
