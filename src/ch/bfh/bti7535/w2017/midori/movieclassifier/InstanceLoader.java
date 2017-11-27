package ch.bfh.bti7535.w2017.midori.movieclassifier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class InstanceLoader {

  public enum Label {
    POSITIVE,
    NEGATIVE
  }

  private final static String lexiconFile = "data" + File.separator + "general_inquirer_lexicon" + File.separator + "inquirerbasic.csv";

  private final static String instancesFolderPrefix = "data" + File.separator + "txt_sentoken" + File.separator;
  private final static File negativeInstancesFolder = new File(instancesFolderPrefix + "neg");
  private final static File positiveInstancesFolder = new File(instancesFolderPrefix + "pos");

  private final static List<String> relevantCols = Arrays.asList("Entry", "Positiv", "Negativ", "Hostile", "Strong", "Power", "Weak", "Active", "Passive");

  private static Map<String, WordConnotation> dictionary = new HashMap<>();

  public InstanceLoader() throws FileNotFoundException {
    loadLexicon();
    File[] files = positiveInstancesFolder.listFiles();
    loadInstanceFile(files[0], Label.POSITIVE);
  }

  private void loadInstanceFile(File file, Label label) throws FileNotFoundException {

    System.out.println(file.getAbsolutePath());

    Scanner in = new Scanner(file);
    in.useDelimiter(".");

    while (in.hasNextLine()) {
      String instance = in.nextLine();
      int[] features = {
          0, // pos
          0, // neg
          0, // strong
          0  // weak
      };
      System.out.println("-- " + instance);
      String[] words = instance.split("[^A-Za-z]");
      for (String word : words) {
        word = word
            .replaceAll("[^A-Za-z]", "")
            .toLowerCase();
        if (word.length() < 3)
          continue;
        if (!dictionary.containsKey(word))
          continue;
        WordConnotation wc = dictionary.get(word);
        if (wc.isPositive())
          features[0]++;
        if (wc.isNegative())
          features[1]++;
        if (wc.isStrong())
          features[2]++;
        if (wc.isWeak())
          features[3]++;
        //System.out.println("---- " + wc.getWord() + " - pos: " + wc.isPositive() + " - neg: " + wc.isNegative() + " - str: " + wc.isStrong() + " - weak: " + wc.isWeak());
      }

      System.out.println("--- pos: " + features[0] + " - neg: " + features[1] + " - str: " + features[2] + " - weak: " + features[3]);

    }

  }

  private void loadLexicon() throws FileNotFoundException {

    CSVParser parser =
        new CSVParserBuilder()
        .withSeparator(';')
        .build();

    CSVReader reader =
        new CSVReaderBuilder(new FileReader(lexiconFile))
        .withCSVParser(parser)
        .build();

    Iterator<String[]> it = reader.iterator();    
    String[] csvHeader = it.next();

    Map<String, Integer> csvColMap = getColIndices(csvHeader);    

    while (it.hasNext()) {
      String[] wordInfo = it.next();
      String word = wordInfo[csvColMap.get("Entry")];
      word = word
          .replaceAll("[^A-Za-z]", "")
          .toLowerCase();
      if (dictionary.containsKey(word))
        continue;
      //System.out.println("Test: word is " + word);
      //System.out.println("Positiv: " + wordInfo[csvColMap.get("Positiv")]);
      boolean positive = wordInfo[csvColMap.get("Positiv")].equals("Positiv");
      boolean negative = wordInfo[csvColMap.get("Negativ")].equals("Negativ");
      boolean strong = wordInfo[csvColMap.get("Strong")].equals("Strong");
      boolean weak = wordInfo[csvColMap.get("Weak")].equals("Weak");
      WordConnotation wc = new WordConnotation(word, positive, negative, strong, weak);
      dictionary.put(word, wc);
      //System.out.println("is positive: " + wc.isPositive());
    }


  }

  private Map<String, Integer> getColIndices (String[] csvHeader) {
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
