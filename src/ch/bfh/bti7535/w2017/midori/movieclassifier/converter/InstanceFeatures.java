package ch.bfh.bti7535.w2017.midori.movieclassifier.converter;

import ch.bfh.bti7535.w2017.midori.movieclassifier.converter.ArffGenerator.Label;

public class InstanceFeatures {

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

  private Label label = null;
  private double positive, negative, pleasur, arousal, pain, virtue, hostile = 0;

  public static String[] titles() {
    String[] str = { "positive", "negative", "pleasur", "arousal", "pain", "virtue", "hostile", "class" };
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

  public String[] toStringArray() {
    String[] str = { String.valueOf(positive), String.valueOf(negative), String.valueOf(pleasur), String.valueOf(arousal), String.valueOf(pain),
        String.valueOf(virtue), String.valueOf(hostile), label.name() };
    return str;
  }

  public void addWord(WordConnotation wc, boolean negated) {
    Weight weight = Weight.REGULAR;
    if (wc.isStrong())
      weight = Weight.STRONG;
    else if (wc.isWeak())
      weight = Weight.WEAK;

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
