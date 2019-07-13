package com.drongox.yatzy;

import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.Map.Entry.comparingByKey;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public class Yatzy
{
  public Yatzy() {}


  public int score(YatzyStrategy yatzyStrategy, Roll roll)
  {
    return yatzyStrategy.scoreRoll(roll);
  }


  public static int ones(int d1, int d2, int d3, int d4, int d5)
  {
    return exactNumberStrategy(1, d1, d2, d3, d4, d5);
  }


  public static int twos(int d1, int d2, int d3, int d4, int d5)
  {
    return exactNumberStrategy(2, d1, d2, d3, d4, d5);
  }


  public static int threes(int d1, int d2, int d3, int d4, int d5)
  {
    return exactNumberStrategy(3, d1, d2, d3, d4, d5);
  }


  protected int[] dice;


  public Yatzy(int d1, int d2, int d3, int d4, int d5)
  {
    dice = new int[]{d1, d2, d3, d4, d5};
  }


  public int fours()
  {
    return exactNumberStrategy(4, dice);
  }


  public int fives()
  {
    return exactNumberStrategy(5, dice);
  }


  public int sixes()
  {
    return exactNumberStrategy(6, dice);
  }


  public static int score_pair(int d1, int d2, int d3, int d4, int d5)
  {
    var counts = countDice(d1, d2, d3, d4, d5);
    return scoreSomeOfAKindStrategy(2, counts);
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


  public static int four_of_a_kind(int d1, int d2, int d3, int d4, int d5)
  {
    return scoreSomeOfAKindStrategy(4, countDice(d1, d2, d3, d4, d5));
  }


  public static int three_of_a_kind(int d1, int d2, int d3, int d4, int d5)
  {
    return scoreSomeOfAKindStrategy(3, countDice(d1, d2, d3, d4, d5));
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


  private static int exactNumberStrategy(int matchingNumber, int... dice)
  {
    return IntStream.of(dice)
                    .filter(die -> die == matchingNumber)
                    .sum();
  }


  private static Map<Integer, Long> countDice(int... dice)
  {
    return IntStream.of(dice)
                    .boxed()
                    .collect(groupingBy(identity(), counting()));
  }


  private static int scoreSomeOfAKindStrategy(int matchingCount, Map<Integer, Long> counts)
  {
    return counts.entrySet()
                 .stream()
                 .filter(entry -> entry.getValue() >= matchingCount)
                 .max(comparingByKey())
                 .map(Map.Entry::getKey)
                 .map(die -> die * matchingCount)
                 .orElse(0);
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