package ch.bfh.bti7535.w2017.midori.movieclassifier;

public class WordConnotation {
  
  private String word;
  private boolean positive, negative, hostile, strong, power, weak, active, passive;

  public WordConnotation(String word, boolean positive, boolean negative, boolean hostile, boolean strong, boolean power, boolean weak, boolean active, boolean passive) {
    this.word = word;
    this.positive = positive;
    this.negative = negative;
    this.hostile = hostile;
    this.strong = strong;
    this.power = power;
    this.weak = weak;
    this.active = active;
    this.passive = passive;
  }

}
