package com.drongox.yatzy;

import org.junit.*;

import static com.drongox.yatzy.YatzyStrategy.*;
import static org.junit.Assert.*;

public class YatzyStrategyTest
{
  @Test
  public void should_return_15_when_strategy_chance_and_roll_2_3_4_5_1()
  {
    //given
    Roll roll = new Roll(2, 3, 4, 5, 1);
    int expected = 15;
    //when
    int actual = CHANCE.scoreRoll(roll);
    //then
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_50_when_strategy_yatzy_and_roll_4_4_4_4_4()
  {
    //given
    Roll roll = new Roll(4, 4, 4, 4, 4);
    int expected = 50;
    //when
    int actual = YATZY.scoreRoll(roll);
    //then
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_0_when_strategy_yatzy_and_roll_4_4_4_4_1()
  {
    //given
    Roll roll = new Roll(4, 4, 4, 4, 1);
    int expected = 0;
    //when
    int actual = YATZY.scoreRoll(roll);
    //then
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_2_when_strategy_ones_and_roll_1_2_1_4_5()
  {
    //given
    Roll roll = new Roll(1, 2, 1, 4, 5);
    int expected = 2;
    //when
    int actual = ONES.scoreRoll(roll);
    //then
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_4_when_strategy_twos_and_roll_1_2_3_2_6()
  {
    //given
    Roll roll = new Roll(1, 2, 3, 2, 6);
    int expected = 4;
    //when
    int actual = TWOS.scoreRoll(roll);
    //then
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_6_when_strategy_threes_and_roll_1_2_3_2_3()
  {
    //given
    Roll roll = new Roll(1, 2, 3, 2, 3);
    int expected = 6;
    //when
    int actual = THREES.scoreRoll(roll);
    //then
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_8_when_strategy_fours_and_roll_4_4_5_5_5()
  {
    //given
    Roll roll = new Roll(4, 4, 5, 5, 5);
    int expected = 8;
    //when
    int actual = FOURS.scoreRoll(roll);
    //then
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_15_when_strategy_fives_and_roll_4_4_5_5_5()
  {
    //given
    Roll roll = new Roll(4, 4, 5, 5, 5);
    int expected = 15;
    //when
    int actual = FIVES.scoreRoll(roll);
    //then
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_18_when_strategy_sixes_and_roll_6_5_6_6_5()
  {
    //given
    Roll roll = new Roll(6, 5, 6, 6, 5);
    int expected = 18;
    //when
    int actual = SIXES.scoreRoll(roll);
    //then
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_12_when_strategy_pair_and_roll_5_3_6_6_5()
  {
    //given
    Roll roll = new Roll(5, 3, 6, 6, 5);
    int expected = 12;
    //when
    int actual = PAIR.scoreRoll(roll);
    //then
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_0_when_strategy_pair_and_roll_1_2_3_6_5()
  {
    //given
    Roll roll = new Roll(1, 2, 3, 6, 5);
    int expected = 0;
    //when
    int actual = PAIR.scoreRoll(roll);
    //then
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_16_when_strategy_two_pairs_and_roll_3_3_5_5_5()
  {
    //given
    Roll roll = new Roll(3, 3, 5, 5, 5);
    int expected = 16;
    //when
    int actual = TWO_PAIRS.scoreRoll(roll);
    //then
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_0_when_strategy_two_pairs_and_roll_3_3_4_5_6()
  {
    //given
    Roll roll = new Roll(3, 3, 4, 5, 6);
    int expected = 0;
    //when
    int actual = TWO_PAIRS.scoreRoll(roll);
    //then
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_9_when_strategy_three_of_a_kind_and_roll_3_3_3_3_5()
  {
    //given
    Roll roll = new Roll(3, 3, 3, 3, 5);
    int expected = 9;
    //when
    int actual = THREE_OF_A_KIND.scoreRoll(roll);
    //then
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_0_when_strategy_three_of_a_kind_and_roll_3_3_4_4_5()
  {
    //given
    Roll roll = new Roll(3, 3, 4, 4, 5);
    int expected = 0;
    //when
    int actual = THREE_OF_A_KIND.scoreRoll(roll);
    //then
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_12_when_strategy_four_of_a_kind_and_roll_3_3_3_3_3()
  {
    //given
    Roll roll = new Roll(3, 3, 3, 3, 3);
    int expected = 12;
    //when
    int actual = FOUR_OF_A_KIND.scoreRoll(roll);
    //then
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_0_when_strategy_four_of_a_kind_and_roll_3_3_3_5_5()
  {
    //given
    Roll roll = new Roll(3, 3, 3, 5, 5);
    int expected = 0;
    //when
    int actual = FOUR_OF_A_KIND.scoreRoll(roll);
    //then
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_15_when_strategy_small_straight_and_roll_3_2_1_4_5()
  {
    //given
    Roll roll = new Roll(3, 2, 1, 4, 5);
    int expected = 15;
    //when
    int actual = SMALL_STRAIGHT.scoreRoll(roll);
    //then
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_0_when_strategy_small_straight_and_roll_5_2_3_4_5()
  {
    //given
    Roll roll = new Roll(5, 2, 3, 4, 5);
    int expected = 0;
    //when
    int actual = SMALL_STRAIGHT.scoreRoll(roll);
    //then
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_20_when_strategy_large_straight_and_roll_6_2_3_4_5()
  {
    //given
    Roll roll = new Roll(6, 2, 3, 4, 5);
    int expected = 20;
    //when
    int actual = LARGE_STRAIGHT.scoreRoll(roll);
    //then
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_0_when_strategy_large_straight_and_roll_1_2_3_4_5()
  {
    //given
    Roll roll = new Roll(1, 2, 3, 4, 5);
    int expected = 0;
    //when
    int actual = LARGE_STRAIGHT.scoreRoll(roll);
    //then
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_18_when_strategy_full_house_and_roll_6_2_2_2_6()
  {
    //given
    Roll roll = new Roll(6, 2, 2, 2, 6);
    int expected = 18;
    //when
    int actual = FULL_HOUSE.scoreRoll(roll);
    //then
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_0_when_strategy_full_house_and_roll_6_2_2_2_2()
  {
    //given
    Roll roll = new Roll(6, 2, 2, 2, 2);
    int expected = 0;
    //when
    int actual = FULL_HOUSE.scoreRoll(roll);
    //then
    assertEquals(expected, actual);
  }
}