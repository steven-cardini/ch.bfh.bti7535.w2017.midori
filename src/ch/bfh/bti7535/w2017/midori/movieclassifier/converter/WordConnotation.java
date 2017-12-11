package ch.bfh.bti7535.w2017.midori.movieclassifier.converter;

public class WordConnotation {
  
  private String word;
  private boolean positive, negative, strong, weak;

  public WordConnotation(String word, boolean positive, boolean negative, boolean strong, boolean weak) {
    this.word = word;
    this.positive = positive;
    this.negative = negative;
    this.strong = strong;
    this.weak = weak;
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
}
