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
   * 
   * @param arffinputfile
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

        /*
         * for (int i = 0; i <= data.numInstances() - 1; i++) { System.out.println(data.toString()); }
         */

      } catch (IOException e) {
        System.out.println("Fehler beim Verarbeiten der Datei!");
        e.printStackTrace();
      }

    } catch (FileNotFoundException e) {
      System.out.println("Datei konnte nicht gelesen werden!");
      e.printStackTrace();
    }

    return data;
  }
}
