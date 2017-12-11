package ch.bfh.bti7535.w2017.midori.movieclassifier.converter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import weka.core.Instances;
import weka.core.converters.CSVLoader;

public class ArffGenerator {

  public enum Label {
    POSITIVE,
    NEGATIVE
  }

  private final static File lexiconInputFile = new File ("data" + File.separator + "general_inquirer_lexicon" + File.separator + "inquirerbasic.csv");
  private static Map<String, WordConnotation> connotationLexicon = new HashMap<>();

  private final static String trainingDataFolderPrefix = "data" + File.separator + "txt_sentoken" + File.separator;
  private final static File negativeInstancesFolder = new File(trainingDataFolderPrefix + "neg");
  private final static File positiveInstancesFolder = new File(trainingDataFolderPrefix + "pos");
  
  private final static File csvOutputFile = new File("data" + File.separator + "movie-reviews.csv");
  private final static File arffOutputFile = new File("data" + File.separator + "movie-reviews.arff");

  private final static List<String> relevantCols = Arrays.asList("Entry", "Positiv", "Negativ", "Hostile", "Strong", "Power", "Weak");
  private final static Pattern wordDelimiterPattern = Pattern.compile("[^A-Za-z]");  
  
  
  public static void main(String args[]) throws IOException {
    
    loadLexicon();
    CSVWriter csvWriter = new CSVWriter(new FileWriter(csvOutputFile));
    // write attribute titles to CSV file
    csvWriter.writeNext(InstanceFeatures.titles());
    
    // iterate through all positively labeled files and add its features to the CSV file
    File[] files = positiveInstancesFolder.listFiles();
    for (File file : files) {
      InstanceFeatures features = loadCommentFile(file, Label.POSITIVE);
      csvWriter.writeNext(features.toStringArray());
    }
    
    // iterate through all negatively labeled files and add its features to the CSV file
    files = negativeInstancesFolder.listFiles();
    for (File file : files) {
      InstanceFeatures features = loadCommentFile(file, Label.NEGATIVE);
      csvWriter.writeNext(features.toStringArray());
    }
    
    csvWriter.close();
    
    // convert CSV file to ARFF file
    CSVLoader csvLoader = new CSVLoader();
    csvLoader.setSource(csvOutputFile);
    Instances data = csvLoader.getDataSet();
    data.setClassIndex(data.numAttributes()-1);
        
    BufferedWriter writer = new BufferedWriter(new FileWriter(arffOutputFile));
    writer.write(data.toString());
    writer.flush();
    writer.close();
    
  }
  
  private static InstanceFeatures loadCommentFile(File file, Label label) throws FileNotFoundException {
    
    InstanceFeatures features = new InstanceFeatures(label);
    Scanner in = new Scanner(file);
    in.useDelimiter(wordDelimiterPattern);
    while(in.hasNext()) {
      String word = in.next()
          .toLowerCase();
      if(word.length() < 3 || !connotationLexicon.containsKey(word))
        continue;
      WordConnotation wc = connotationLexicon.get(word);
      features.addWord(wc);
      
      //System.out.println(word);
      //System.out.println("-- pos: " + wc.isPositive() + " | neg: " + wc.isNegative() + " | strong: " + wc.isStrong() + " | weak: " + wc.isWeak());
      //System.out.println();
    }
    
    in.close();
    
    return features;
    
  }
  

  private static void loadLexicon() throws FileNotFoundException {

    CSVParser parser =
        new CSVParserBuilder()
        .withSeparator(';')
        .build();

    CSVReader reader =
        new CSVReaderBuilder(new FileReader(lexiconInputFile))
        .withCSVParser(parser)
        .build();

    Iterator<String[]> it = reader.iterator();    
    String[] csvHeader = it.next();

    Map<String, Integer> csvColMap = getColIndices(csvHeader);    

    while (it.hasNext()) {
      String[] wordInfo = it.next();
      String word = wordInfo[csvColMap.get("Entry")];
      word = word
          .replaceAll(wordDelimiterPattern.pattern(), "")
          .toLowerCase();
      if (connotationLexicon.containsKey(word))
        continue;
      //System.out.println("Test: word is " + word);
      //System.out.println("Positiv: " + wordInfo[csvColMap.get("Positiv")]);
      boolean positive = wordInfo[csvColMap.get("Positiv")].equals("Positiv");
      boolean negative = wordInfo[csvColMap.get("Negativ")].equals("Negativ");
      boolean strong = wordInfo[csvColMap.get("Strong")].equals("Strong");
      boolean weak = wordInfo[csvColMap.get("Weak")].equals("Weak");;
      WordConnotation wc = new WordConnotation(word, positive, negative, strong, weak);
      connotationLexicon.put(word, wc);
      //System.out.println("is positive: " + wc.isPositive());
    }


  }

  private static Map<String, Integer> getColIndices (String[] csvHeader) {
    // Map to map column indices in the csv and the relevant attributes
    Map<String, Integer> csvColMap = new HashMap<>();

    for (int i = 0; i < csvHeader.length; i++) {
      if (relevantCols.contains(csvHeader[i])) {
        csvColMap.put(csvHeader[i], i);
      }
    }

    return csvColMap;    
  }
}