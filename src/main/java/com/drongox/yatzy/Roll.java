package com.drongox.yatzy;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public final class Roll
{
  private final int[] roll;
  private final int hashValue;

  public Roll(int die1, int die2, int die3, int die4, int die5)
  {
    roll = new int[] {die1, die2, die3, die4, die5};
    if (stream().anyMatch(die -> die < 1 || die > 6))
      throw new IllegalArgumentException("We are using 6-side dices, so the values should be [1..6]");

    Arrays.sort(roll); //to simplify #equals implementation
    hashValue = Objects.hash(die1, die2, die3, die4, die5);
  }


  public IntStream stream()
  {
    return IntStream.of(roll);
  }


  @Override
  public int hashCode()
  {
    return hashValue;
  }


  @Override
  public boolean equals(Object o)
  {
    if (o == this) return true;
    if (!(o instanceof Roll)) return false;
    return Arrays.equals(this.roll, ((Roll) o).roll); //this works because arrays are sorted
  }
}
