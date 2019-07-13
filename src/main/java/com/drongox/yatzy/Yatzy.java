package com.drongox.yatzy;

import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.Map.Entry.comparingByKey;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public class Yatzy
{
  public int score(YatzyStrategy yatzyStrategy, Roll roll)
  {
    return yatzyStrategy.scoreRoll(roll);
  }


  public static int two_pair(int d1, int d2, int d3, int d4, int d5)
  {
    var pairsOrMore = getPairsOrMore(d1, d2, d3, d4, d5);

    if (pairsOrMore.size() != 2)
      return 0;
    else
      return pairsOrMore.keySet()
                        .stream()
                        .mapToInt(die -> die * 2)
                        .sum();
  }


  public static int smallStraight(int d1, int d2, int d3, int d4, int d5)
  {
    var smallStraight = Set.of(1, 2, 3, 4, 5);
    return exactRollStrategy(smallStraight, d1, d2, d3, d4, d5);
  }


  public static int largeStraight(int d1, int d2, int d3, int d4, int d5)
  {
    var largeStraight = Set.of(2, 3, 4, 5, 6);
    return exactRollStrategy(largeStraight, d1, d2, d3, d4, d5);
  }


  public static int fullHouse(int d1, int d2, int d3, int d4, int d5)
  {
    var pairsOrMore = getPairsOrMore(d1, d2, d3, d4, d5);

    if (pairsOrMore.size() != 2)
      return 0;
    else
      return pairsOrMore.entrySet()
                        .stream()
                        .mapToInt(entry -> entry.getKey() * entry.getValue().intValue())
                        .sum();
  }


  private static Map<Integer, Long> countDice(int... dice)
  {
    return IntStream.of(dice)
                    .boxed()
                    .collect(groupingBy(identity(), counting()));
  }


  private static int exactRollStrategy(Set<Integer> expectedRoll, int... dice)
  {
    boolean isMatching = IntStream.of(dice)
                                  .boxed()
                                  .collect(toSet())
                                  .equals(expectedRoll);
    if (isMatching) {
      return expectedRoll.stream().mapToInt(Integer::intValue).sum();
    } else {
      return 0;
    }
  }


  private static Map<Integer, Long> getPairsOrMore(int... dice)
  {
    return countDice(dice).entrySet()
                          .stream()
                          .filter(entry -> entry.getValue() >= 2)
                          .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
  }
}