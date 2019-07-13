package com.drongox.yatzy;

import java.util.Map;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import static java.util.Map.Entry.comparingByKey;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public enum YatzyStrategy
{
  CHANCE(roll -> roll.stream().sum()),

  YATZY(roll -> roll.stream().distinct().count() == 1 ? 50 : 0),

  ONES(roll -> exactNumberStrategy(1, roll)),

  TWOS(roll -> exactNumberStrategy(2, roll)),

  THREES(roll -> exactNumberStrategy(3, roll)),

  FOURS(roll -> exactNumberStrategy(4, roll)),

  FIVES(roll -> exactNumberStrategy(5, roll)),

  SIXES(roll -> exactNumberStrategy(6, roll)),

  PAIR(roll -> someOfAKindStrategy(2, roll)),

  THREE_OF_A_KIND(roll -> someOfAKindStrategy(3, roll)),

  FOUR_OF_A_KIND(roll -> someOfAKindStrategy(4, roll)),

  SMALL_STRAIGHT(roll -> exactRollStrategy(new Roll(1, 2, 3, 4, 5), roll)),

  LARGE_STRAIGHT(roll -> exactRollStrategy(new Roll(2, 3, 4, 5, 6), roll)),

  FULL_HOUSE(YatzyStrategy::fullHouseStrategy),

  TWO_PAIRS(YatzyStrategy::twoPairsStrategy);


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


  private static Map<Integer, Long> countDice(Roll roll)
  {
    return roll.stream()
               .boxed()
               .collect(groupingBy(identity(), counting()));
  }


  private static Integer someOfAKindStrategy(int matchingCount, Roll roll)
  {
    return countDice(roll).entrySet()
                          .stream()
                          .filter(entry -> entry.getValue() >= matchingCount)
                          .max(comparingByKey())
                          .map(Map.Entry::getKey)
                          .map(die -> die * matchingCount)
                          .orElse(0);
  }


  private static Integer exactRollStrategy(Roll expectedRoll, Roll actualRoll)
  {
    if (actualRoll.equals(expectedRoll)) {
      return expectedRoll.stream().sum();
    } else {
      return 0;
    }
  }


  private static Integer twoPairsStrategy(Roll roll)
  {
    return severalPairsOrMore(roll, entry -> entry.getKey() * 2);
  }


  private static Integer fullHouseStrategy(Roll roll)
  {
    return severalPairsOrMore(roll, entry -> entry.getKey() * entry.getValue().intValue());
  }


  private static Integer severalPairsOrMore(Roll roll, ToIntFunction<Map.Entry<Integer, Long>> scoreCalculator)
  {
    var pairsOrMore = countDice(roll).entrySet()
                                     .stream()
                                     .filter(entry -> entry.getValue() >= 2)
                                     .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));

    if (pairsOrMore.size() != 2)
      return 0;
    else {
      return pairsOrMore.entrySet()
                        .stream()
                        .mapToInt(scoreCalculator)
                        .sum();
    }
  }
}
