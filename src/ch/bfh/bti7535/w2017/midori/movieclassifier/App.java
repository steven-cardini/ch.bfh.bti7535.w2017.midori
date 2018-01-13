package ch.bfh.bti7535.w2017.midori.movieclassifier;

import java.io.File;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayesMultinomial;
import weka.core.Instances;


public class App {

	private final static File arffInputFile = new File("data" + File.separator + "movie-reviews.arff");

	public static void main(String[] args) throws Exception {
		// https://weka.wikispaces.com/Programmatic+Use
		// InstanceLoader loader = new InstanceLoader();

		// ArffImporter imp = new ArffImporter();
		// imp.importArff("data\\movie-reviews.arff");

		System.out.println("Naive Bayes");
		naiveBayes();

		// eval.crossValidateModel(cl, in, 10, new Random(1));
	}

	private static void naiveBayes() throws Exception {
		Random rand = new Random();

		NaiveBayesMultinomial cl = new NaiveBayesMultinomial();
		Instances inst = ArffImporter.importArff(arffInputFile);

		inst.randomize(rand);
		inst.stratify(10);
	
	
		Evaluation eval = new Evaluation(inst);

		for (int n = 0; n < 10; n++) {
			Instances train = inst.trainCV(10, n);
			Instances test = inst.testCV(10, n);

			cl.buildClassifier(train);
			eval.evaluateModel(cl, test);
			System.out.println(eval.pctCorrect());
		}

	}
}
