package ch.bfh.bti7535.w2017.midori.movieclassifier;

import java.io.File;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayesMultinomial;
import weka.core.Instances;


public class App {

  private final static File arffInputFile = new File("data" + File.separator + "movie-reviews.arff");
  private final static int numFolds = 10;

  public static void main(String[] args) throws Exception {

    naiveBayes();

  }

  private static void naiveBayes() throws Exception {
    Random rand = new Random();

    NaiveBayesMultinomial cl = new NaiveBayesMultinomial();
    Instances inst = ArffImporter.importArff(arffInputFile);

    inst.randomize(rand);
    inst.stratify(numFolds);

    Evaluation eval = new Evaluation(inst);

    for (int n = 0; n < numFolds; n++) {
      Instances train = inst.trainCV(numFolds, n);
      Instances test = inst.testCV(numFolds, n);

      cl.buildClassifier(train);
      eval.evaluateModel(cl, test);
      System.out.println(eval.pctCorrect());
    }

  }
}
