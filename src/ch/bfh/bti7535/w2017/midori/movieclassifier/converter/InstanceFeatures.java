<<<<<<< HEAD
package ch.bfh.bti7535.w2017.midori.movieclassifier.converter;

import ch.bfh.bti7535.w2017.midori.movieclassifier.converter.ArffGenerator.Label;

public class InstanceFeatures {

  private Label label = null;
  private int positive, negative, strong, weak = 0;
  
  public static String[] titles() {
    String[] str = {
    		"positive", "negative", "strong", "weak", "pleasur", "class"
    };
    return str;
  }
  
  public InstanceFeatures(Label label) {
    this.label = label;
  }
  
  public int getPositive() {
    return positive;
  }

  public int getNegative() {
    return negative;
  }

  public int getStrong() {
    return strong;
  }

  public int getWeak() {
    return weak;
  }
  
  public String[] toStringArray() {
    String[] str = {
        String.valueOf(positive), String.valueOf(negative), String.valueOf(strong), String.valueOf(weak), label.name()
    };    
    return str;
  }
  
  public void addWord(WordConnotation wc) {
    if(wc.isPositive())
      addPositive();
    if(wc.isNegative())
      addNegative();
    if(wc.isStrong())
      addStrong();
    if(wc.isWeak())
      addWeak();
  }
  
  private void addPositive() {
    positive++;
  }
  
  private void addNegative() {
    negative++;
  }
  
  private void addStrong() {
    strong++;
  }
  
  private void addWeak() {
    weak++;
  }
}
=======
package ch.bfh.bti7535.w2017.midori.movieclassifier.converter;

import ch.bfh.bti7535.w2017.midori.movieclassifier.converter.ArffGenerator.Label;

public class InstanceFeatures {

  public enum Weight {
    STRONG (5),
    REGULAR (3),
    WEAK (1);
    
    private final double weight;
    Weight(double weight) {
      this.weight = weight;
    }
    double getValue() {
      return weight;
    }
  }
  
  private Label label = null;
  //private int positive, negative, strong, weak, active, passive, pleasur = 0;
  private double positive, negative, pleasur = 0;
  
  public static String[] titles() {
    String[] str = {
        //"positive", "negative", "strong", "weak", "active", "passive", "pleasur", "class"
    		"positive", "negative", "pleasur", "class"
    };
    return str;
  }
  
  public InstanceFeatures(Label label) {
    this.label = label;
  }
  
  public double getPositive() {
    return positive;
  }

  public double getNegative() {
    return negative;
  }

  
  /*public int getActive() {
	  return active;
  }
  
  public int getPassive() {
	  return passive;
  }*/
  
  public double getPleasur() {
	  return pleasur;
  }
  
  public String[] toStringArray() {
    String[] str = {
        //String.valueOf(positive), String.valueOf(negative), String.valueOf(strong), String.valueOf(weak),String.valueOf(active), String.valueOf(passive), String.valueOf(pleasur), label.name()
        String.valueOf(positive), String.valueOf(negative), String.valueOf(pleasur), label.name()
    };    
    return str;
  }
  
  public void addWord(WordConnotation wc, boolean negated) {
    Weight weight = Weight.REGULAR;
    if(wc.isStrong())
      weight = Weight.STRONG;
    else if (wc.isWeak())
      weight = Weight.WEAK;
    
    if(wc.isPositive()) {
      if (negated)
        addNegative(weight);
      else
        addPositive(weight);
    }
    if(wc.isNegative()) {
      if(negated)
        addPositive(weight);
      else
        addNegative(weight);
    }      
    /*if(wc.isActive())
      addActive();
    if(wc.isPassive())
    	addPassive();*/
    if(wc.isPleasur())
    	addPleasur();
  }
  
  private void addPositive(Weight weight) {
    positive = positive + weight.getValue();
  }
  
  private void addNegative(Weight weight) {
    negative = negative + weight.getValue();
  }
  
  /*private void addStrong() {
    strong++;
  }
  
  private void addWeak() {
    weak++;
  }
  
  private void addActive() {
	  active++;
  }
  
  private void addPassive() {
	  passive++;
  }*/
  private void addPleasur() {
	  pleasur++;
  }
  
}
>>>>>>> branch 'master' of https://github.com/stoeffu/ch.bfh.bti7535.w2017.midori
