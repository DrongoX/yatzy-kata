package com.drongox.yatzy;

import org.junit.Test;

public class RollTest
{
  @Test(expected = IllegalArgumentException.class)
  public void should_throw_IAE_when_dice_values_less_than_1()
  {
    new Roll(1, 1, 0, 2, 2);
  }


  @Test(expected = IllegalArgumentException.class)
  public void should_throw_IAE_when_dice_values_more_than_6()
  {
    new Roll(1, 9, 4, 2, 2);
  }
}