package ch.bfh.bti7535.w2017.midori.movieclassifier.converter;

public class WordConnotation {

  private String word;
  private boolean positive, negative, strong, weak, pleasur, arousal, pain, virtue, hostile;

  /**
   * 
   * @param word
   * @param positive
   * @param negative
   * @param strong
   * @param weak
   * @param pleasur
   * @param arousal
   * @param pain
   * @param virtue
   * @param hostile
   */
  public WordConnotation(String word, boolean positive, boolean negative, boolean strong, boolean weak, boolean pleasur, boolean arousal, boolean pain, boolean virtue, boolean hostile) {
    this.word = word;
    this.positive = positive;
    this.negative = negative;
    this.strong = strong;
    this.weak = weak;
    this.pleasur = pleasur;
    this.arousal = arousal;
    this.pain = pain;
    this.virtue = virtue;
    this.hostile = hostile;
  }

  public boolean isPleasur() {
    return pleasur;
  }

  public boolean isArousal() {
    return arousal;
  }

  public boolean isPain() {
    return pain;
  }

  public boolean isVirtue() {
    return virtue;
  }

  public boolean isHostile() {
    return hostile;
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
