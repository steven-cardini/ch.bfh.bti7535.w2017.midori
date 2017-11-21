package ch.bfh.bti7535.w2017.midori.movieclassifier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class InstanceLoader {
  
  private final static String lexiconFile = "data" + File.separator + "general_inquirer_lexicon" + File.separator + "inquirerbasic.csv";
  
  private final static String instancesFolderPrefix = "data" + File.separator + "txt_sentoken" + File.separator;
  private final static String negativeInstancesFolder = instancesFolderPrefix + "neg" + File.separator;
  private final static String positiveInstancesFolder = instancesFolderPrefix + "pos" + File.separator;
  
  private final static List<String> relevantCols = Arrays.asList("Entry", "Positiv", "Negativ", "Hostile", "Strong", "Power", "Weak", "Active", "Passive");
  
  private static Map<String, WordConnotation> dictionary = new HashMap<>();
  
  public InstanceLoader() throws FileNotFoundException {
    loadLexicon();
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
      System.out.println("Test: word is " + word);
      boolean positive = wordInfo[csvColMap.get("Positiv")].equals("Positiv");
      boolean negative = wordInfo[csvColMap.get("Negativ")].equals("Negativ");
      boolean hostile = wordInfo[csvColMap.get("Hostile")].equals("Hostile");
      boolean strong = wordInfo[csvColMap.get("Strong")].equals("Strong");
      boolean power = wordInfo[csvColMap.get("Power")].equals("Power");
      boolean weak = wordInfo[csvColMap.get("Weak")].equals("Weak");
      boolean active = wordInfo[csvColMap.get("Active")].equals("Active");
      boolean passive = wordInfo[csvColMap.get("Passive")].equals("Passive");
      WordConnotation wc = new WordConnotation(word, positive, negative, hostile, strong, power, weak, active, passive);
      dictionary.put(word, wc);
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
