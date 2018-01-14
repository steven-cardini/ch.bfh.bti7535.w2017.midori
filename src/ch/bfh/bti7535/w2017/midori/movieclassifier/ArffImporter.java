package ch.bfh.bti7535.w2017.midori.movieclassifier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;

public class ArffImporter {

  /***
   * Class to load ARFF Files
   * @param arffinputfile The input file in ARFF format
   */
  public static Instances importArff(File arffinputfile) {

    Instances data = null;

    try {
      BufferedReader reader = new BufferedReader(new FileReader(arffinputfile));

      try {
        ArffReader arff = new ArffReader(reader, 1000);

        data = arff.getStructure();
        data.setClassIndex(data.numAttributes() - 1);

        Instance inst;
        while ((inst = arff.readInstance(data)) != null) {
          data.add(inst);
        }
      } catch (IOException e) {
        System.out.println("Error while processing the file!");
        e.printStackTrace();
      }

    } catch (FileNotFoundException e) {
      System.out.println("The file could not be read!");
      e.printStackTrace();
    }

    return data;
  }
}
