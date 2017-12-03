package ch.bfh.bti7535.w2017.midori.movieclassifier.converter;

import ch.bfh.bti7535.w2017.midori.movieclassifier.converter.ArffGenerator.Label;

public class InstanceFeatures {

  private Label label = null;
  //private int positive, negative, strong, weak, active, passive, pleasur = 0;
  private int positive, negative, strong, weak,  pleasur = 0;
  
  public static String[] titles() {
    String[] str = {
        //"positive", "negative", "strong", "weak", "active", "passive", "pleasur", "class"
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
  
  /*public int getActive() {
	  return active;
  }
  
  public int getPassive() {
	  return passive;
  }*/
  
  public int getPleasur() {
	  return pleasur;
  }
  
  public String[] toStringArray() {
    String[] str = {
        //String.valueOf(positive), String.valueOf(negative), String.valueOf(strong), String.valueOf(weak),String.valueOf(active), String.valueOf(passive), String.valueOf(pleasur), label.name()
        String.valueOf(positive), String.valueOf(negative), String.valueOf(strong), String.valueOf(weak), String.valueOf(pleasur), label.name()
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
    /*if(wc.isActive())
      addActive();
    if(wc.isPassive())
    	addPassive();*/
    if(wc.isPleasur())
    	addPleasur();
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
  
 /* private void addActive() {
	  active++;
  }
  
  private void addPassive() {
	  passive++;
  }*/
  private void addPleasur() {
	  pleasur++;
  }
}
