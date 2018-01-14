package ch.bfh.bti7535.w2017.midori.movieclassifier.converter;

import ch.bfh.bti7535.w2017.midori.movieclassifier.converter.ArffGenerator.Label;

/**
 * This class holds all features for one instance (review)
 */
public class InstanceFeatures {

  /**
   * Enumeration that handles word impact / weight on the InstanceFeature object
   */
  public enum Weight {
    STRONG(5), REGULAR(3), WEAK(1);

    private final double weight;

    Weight(double weight) {
      this.weight = weight;
    }

    double getValue() {
      return weight;
    }
  }

  private Label label;
  private double positive, negative, pleasur, arousal, pain, virtue, hostile = 0;

  /**
   * @return the names of the features
   */
  public static String[] titles() {
    return new String[]{
        "positive", "negative", "pleasur", "arousal", "pain", "virtue", "hostile", "class"
    };
  }

  public InstanceFeatures(Label label) {
    this.label = label;
  }

  /**
   * @return the feature values as String array
   */
  public String[] toStringArray() {
    return new String[]{
        String.valueOf(positive), String.valueOf(negative), String.valueOf(pleasur), String.valueOf(arousal), String.valueOf(pain), String.valueOf(virtue), String.valueOf(hostile), label.name()
    };
  }

  /**
   * Method to add a new word. Connotation features are directly extracted by the method.
   *
   * @param wc      the WordConnotation object of the word to be added
   * @param negated tells if the word is to be handled as a negation
   */
  public void addWord(WordConnotation wc, boolean negated) {
    // determine the weight of the connotation
    Weight weight = Weight.REGULAR;
    if (wc.isStrong())
      weight = Weight.STRONG;
    else if (wc.isWeak())
      weight = Weight.WEAK;

    // add connotations that apply - consider negations
    if (wc.isPositive()) {
      if (negated)
        addNegative(weight);
      else
        addPositive(weight);
    }
    if (wc.isNegative()) {
      if (negated)
        addPositive(weight);
      else
        addNegative(weight);
    }
    if (wc.isArousal())
      addArousal();
    if (wc.isHostile())
      addHostile();
    if (wc.isPain())
      addPain();
    if (wc.isPleasur())
      addPleasur();
    if (wc.isVirtue())
      addVirtue();
  }

  private void addPositive(Weight weight) {
    positive = positive + weight.getValue();
  }

  private void addNegative(Weight weight) {
    negative = negative + weight.getValue();
  }

  private void addArousal() {
    arousal++;
  }

  private void addHostile() {
    hostile++;
  }

  private void addPain() {
    pain++;
  }

  private void addPleasur() {
    pleasur++;
  }

  private void addVirtue() {
    virtue++;
  }
}
