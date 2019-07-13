package com.drongox.yatzy;

public class Yatzy
{
  public int score(YatzyStrategy yatzyStrategy, Roll roll)
  {
    return yatzyStrategy.scoreRoll(roll);
  }
}