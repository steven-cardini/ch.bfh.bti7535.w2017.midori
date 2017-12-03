package ch.bfh.bti7535.w2017.midori.movieclassifier.converter;

public class WordConnotation {
  
  private String word;
  //private boolean positive, negative, strong, weak, active, passive, pleasur;
  private boolean positive, negative, strong, weak, pleasur;

//  public WordConnotation(String word, boolean positive, boolean negative, boolean strong, boolean weak, boolean active, boolean passive, boolean pleasur) {
  public WordConnotation(String word, boolean positive, boolean negative, boolean strong, boolean weak, boolean pleasur) {
    this.word = word;
    this.positive = positive;
    this.negative = negative;
    this.strong = strong;
    this.weak = weak;
    //this.active = active;
    //this.passive = passive;
    this.pleasur = pleasur;
    
  }

  public String getWord() {
    return word;
  }

  public boolean isPositive() {
    return positive;
  }
  
  public boolean isNegative() {
    return negative;
  }

  public boolean isStrong() {
    return strong;
  }

  public boolean isWeak() {
    return weak;
  }
/*
  public boolean isActive() {
	  return active;
  }
  
  public boolean isPassive() {
	  return passive;
  }*/
  
  public boolean isPleasur() {
	  return pleasur;
  }
}
