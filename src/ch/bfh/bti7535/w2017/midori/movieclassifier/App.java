package ch.bfh.bti7535.w2017.midori.movieclassifier;

import java.io.File;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.stemmers.SnowballStemmer;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class App {

	private final static File arffInputFile = new File("data" + File.separator + "movie-reviews.arff");

	public static void main(String[] args) throws Exception {
		// https://weka.wikispaces.com/Programmatic+Use
		// InstanceLoader loader = new InstanceLoader();

		// ArffImporter imp = new ArffImporter();
		// imp.importArff("data\\movie-reviews.arff");

		System.out.println("naive bayes");
		naiveBayes();

		System.out.println("string to word vector");
		useStringToWordVector();

		// eval.crossValidateModel(cl, in, 10, new Random(1));
	}

	private static void naiveBayes() throws Exception {
		Random rand = new Random();

		Classifier cl = new NaiveBayes();
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

	private static void useStringToWordVector() throws Exception {
		Instances inst = ArffImporter.importArff(arffInputFile);

		Evaluation eval = new Evaluation(inst);

		inst.setClassIndex(inst.numAttributes() - 1);

		J48 tree = new J48();
		Classifier naiveBayes = new NaiveBayes();

		StringToWordVector filter = new StringToWordVector();
		filter.setInputFormat(inst);
		filter.setIDFTransform(true);
		SnowballStemmer stemmer = new SnowballStemmer();
		filter.setStemmer(stemmer);
		filter.setLowerCaseTokens(true);

		FilteredClassifier fc = new FilteredClassifier();
		fc.setFilter(filter);
		fc.setClassifier(tree);
		fc.buildClassifier(inst);

		for (int n = 0; n < 10; n++) {
			Instances train = inst.trainCV(10, n);
			Instances test = inst.testCV(10, n);

			fc.buildClassifier(train);
			eval.evaluateModel(fc, test);
			System.out.println(eval.pctCorrect());
		}

	}
}
