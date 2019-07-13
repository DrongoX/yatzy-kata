package com.drongox.yatzy;

import java.util.stream.IntStream;

public final class Roll
{
  private final int[] roll;

  public Roll(int die1, int die2, int die3, int die4, int die5)
  {
    roll = new int[] {die1, die2, die3, die4, die5};
    if (IntStream.of(roll).anyMatch(die -> die < 1 || die > 6))
      throw new IllegalArgumentException("We are using 6-side dices, so the values should be [1..6]");
  }


  public IntStream stream()
  {
    return IntStream.of(roll);
  }
}
