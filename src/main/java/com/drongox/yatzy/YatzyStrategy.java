package com.drongox.yatzy;

import java.util.function.Function;

public enum YatzyStrategy
{
  CHANCE(roll -> roll.stream().sum()),

  YATZY(roll -> roll.stream().distinct().count() == 1 ? 50 : 0),

  ONES(roll -> exactNumberStrategy(1, roll)),

  TWOS(roll -> exactNumberStrategy(2, roll)),

  THREES(roll -> exactNumberStrategy(3, roll)),

  FOURS(roll -> exactNumberStrategy(4, roll)),

  FIVES(roll -> exactNumberStrategy(5, roll)),

  SIXES(roll -> exactNumberStrategy(6, roll));


  private final Function<Roll, Integer> scoreCalculator;


  YatzyStrategy(Function<Roll, Integer> scoreCalculator)
  {
    this.scoreCalculator = scoreCalculator;
  }


  public int scoreRoll(Roll roll)
  {
    return scoreCalculator.apply(roll);
  }


  private static Integer exactNumberStrategy(int matchingNumber, Roll roll)
  {
    return roll.stream()
               .filter(die -> die == matchingNumber)
               .sum();
  }
}
