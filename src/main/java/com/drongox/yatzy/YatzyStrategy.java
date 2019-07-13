package com.drongox.yatzy;

import java.util.function.Function;

public enum YatzyStrategy
{
  CHANCE(roll -> roll.stream().sum()),

  YATZY(roll -> roll.stream().distinct().count() == 1 ? 50 : 0),

  ONES(roll -> exactNumberStrategy(1, roll));


  private final Function<Roll, Integer> rollCalculator;


  YatzyStrategy(Function<Roll, Integer> rollCalculator)
  {
    this.rollCalculator = rollCalculator;
  }


  public int scoreRoll(Roll roll)
  {
    return rollCalculator.apply(roll);
  }


  private static Integer exactNumberStrategy(int matchingNumber, Roll roll)
  {
    return roll.stream()
               .filter(die -> die == matchingNumber)
               .sum();
  }
}
