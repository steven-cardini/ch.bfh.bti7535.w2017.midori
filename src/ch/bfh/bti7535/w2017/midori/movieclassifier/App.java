package ch.bfh.bti7535.w2017.midori.movieclassifier;

import java.io.File;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayesMultinomial;
import weka.classifiers.rules.ZeroR;
import weka.core.Instances;


/**
 * Imports ARFF file with instances and builds baseline as well as naive Bayes classifiers to evaluate the accuracy
 */
public class App {

  private final static File arffInputFile = new File("data" + File.separator + "movie-reviews.arff");
  private final static int numFolds = 10;

  public static void main(String[] args) throws Exception {

    Instances data = ArffImporter.importArff(arffInputFile);

    Classifier baselineClassifier = new ZeroR();
    Classifier naiveBayesClassifier = new NaiveBayesMultinomial();

    System.out.println("--- BASELINE ---");
    tenfoldCrossValidation(data, baselineClassifier);
    System.out.println("--- NAIVE BAYES ---");
    tenfoldCrossValidation(data, naiveBayesClassifier);

  }

  private static void tenfoldCrossValidation(Instances data, Classifier classifier) throws Exception {

    // randomize and stratify the data
    Random rand = new Random();
    Instances randData = new Instances(data);
    randData.randomize(rand);
    randData.stratify(numFolds);

    Evaluation eval = new Evaluation(randData);

    for (int n = 0; n < numFolds; n++) {

      Instances train = randData.trainCV(numFolds, n);
      Instances test = randData.testCV(numFolds, n);

      classifier.buildClassifier(train);
      eval.evaluateModel(classifier, test);
      System.out.println("Accuracy (" + n + "): " + eval.pctCorrect());
    }

  }

}
