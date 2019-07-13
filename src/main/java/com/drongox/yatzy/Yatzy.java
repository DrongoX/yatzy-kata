package com.drongox.yatzy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Map.Entry.comparingByKey;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public class Yatzy
{

  public static int chance(int d1, int d2, int d3, int d4, int d5)
  {
    return IntStream.of(d1, d2, d3, d4, d5).sum();
  }


  public static int yatzy(int... dice)
  {
    long distinctDicesCount = IntStream.of(dice)
                                       .distinct()
                                       .count();
    if (distinctDicesCount == 1)
      return 50;
    else
      return 0;
  }


  private static int exactNumberStrategy(int matchingNumber, int... dice)
  {
    return IntStream.of(dice)
                    .filter(die -> die == matchingNumber)
                    .sum();
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


  public Yatzy(int d1, int d2, int d3, int d4, int _5)
  {
    dice = new int[5];
    dice[0] = d1;
    dice[1] = d2;
    dice[2] = d3;
    dice[3] = d4;
    dice[4] = _5;
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


  private static Map<Integer, Long> countDice(int... dice)
  {
    return IntStream.of(dice)
                    .boxed()
                    .collect(groupingBy(identity(), counting()));
  }


  public static int score_pair(int d1, int d2, int d3, int d4, int d5)
  {
    Map<Integer, Long> counts = countDice(d1, d2, d3, d4, d5);
    return scoreSomeOfAKindStrategy(2, counts);
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


  public static int two_pair(int d1, int d2, int d3, int d4, int d5)
  {
    Map<Integer, Long> pairsOrMore = getPairsOrMore(d1, d2, d3, d4, d5);

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
    int[] tallies;
    tallies = new int[6];
    tallies[d1 - 1] += 1;
    tallies[d2 - 1] += 1;
    tallies[d3 - 1] += 1;
    tallies[d4 - 1] += 1;
    tallies[d5 - 1] += 1;
    if (tallies[0] == 1 &&
        tallies[1] == 1 &&
        tallies[2] == 1 &&
        tallies[3] == 1 &&
        tallies[4] == 1)
      return 15;
    return 0;
  }


  public static int largeStraight(int d1, int d2, int d3, int d4, int d5)
  {
    int[] tallies;
    tallies = new int[6];
    tallies[d1 - 1] += 1;
    tallies[d2 - 1] += 1;
    tallies[d3 - 1] += 1;
    tallies[d4 - 1] += 1;
    tallies[d5 - 1] += 1;
    if (tallies[1] == 1 &&
        tallies[2] == 1 &&
        tallies[3] == 1 &&
        tallies[4] == 1
        && tallies[5] == 1)
      return 20;
    return 0;
  }


  public static int fullHouse(int d1, int d2, int d3, int d4, int d5)
  {
    Map<Integer, Long> pairsOrMore = getPairsOrMore(d1, d2, d3, d4, d5);

    if (pairsOrMore.size() != 2)
      return 0;
    else
      return pairsOrMore.entrySet()
                        .stream()
                        .mapToInt(entry -> entry.getKey() * entry.getValue().intValue())
                        .sum();
  }


  private static Map<Integer, Long> getPairsOrMore(int... dice)
  {
    return countDice(dice).entrySet()
                          .stream()
                          .filter(entry -> entry.getValue() >= 2)
                          .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
  }
}