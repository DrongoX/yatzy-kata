package com.drongox.yatzy;

import org.junit.*;

import static com.drongox.yatzy.YatzyStrategy.*;
import static org.junit.Assert.*;

public class YatzyTest
{
  private final Yatzy yatzy = new Yatzy();


  @Test
  public void should_return_15_when_strategy_chance_and_roll_2_3_4_5_1()
  {
    //given
    Roll roll = new Roll(2, 3, 4, 5, 1);
    int expected = 15;
    //when
    int actual = yatzy.score(CHANCE, roll);
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
    int actual = yatzy.score(YATZY, roll);
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
    int actual = yatzy.score(YATZY, roll);
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
    int actual = yatzy.score(ONES, roll);
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
    int actual = yatzy.score(TWOS, roll);
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
    int actual = yatzy.score(THREES, roll);
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
    int actual = yatzy.score(FOURS, roll);
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
    int actual = yatzy.score(FIVES, roll);
    //then
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_18_when_strategy_sixes_and_roll_6_5_6_6_5()
  {
    int expected = 18;
    Yatzy yatzy = new Yatzy(6, 5, 6, 6, 5);

    int actual = yatzy.sixes();

    assertEquals(expected, actual);
  }


  @Test
  public void should_return_12_when_strategy_pair_and_roll_5_3_6_6_5()
  {
    int expected = 12;
    int actual = Yatzy.score_pair(5, 3, 6, 6, 5);
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_0_when_strategy_pair_and_roll_1_2_3_6_5()
  {
    int expected = 0;
    int actual = Yatzy.score_pair(1, 2, 3, 6, 5);
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_16_when_strategy_two_pairs_and_roll_3_3_5_5_5()
  {
    int expected = 16;
    int actual = Yatzy.two_pair(3, 3, 5, 5, 5);
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_0_when_strategy_two_pairs_and_roll_3_3_4_5_6()
  {
    int expected = 0;
    int actual = Yatzy.two_pair(3, 3, 4, 5, 6);
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_9_when_strategy_three_of_a_kind_and_roll_3_3_3_3_5()
  {
    int expected = 9;
    int actual = Yatzy.three_of_a_kind(3, 3, 3, 3, 5);
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_0_when_strategy_three_of_a_kind_and_roll_3_3_4_4_5()
  {
    int expected = 0;
    int actual = Yatzy.three_of_a_kind(3, 3, 4, 4, 5);
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_12_when_strategy_four_of_a_kind_and_roll_3_3_3_3_3()
  {
    int expected = 12;
    int actual = Yatzy.four_of_a_kind(3, 3, 3, 3, 3);
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_0_when_strategy_four_of_a_kind_and_roll_3_3_3_5_5()
  {
    int expected = 0;
    int actual = Yatzy.four_of_a_kind(3, 3, 3, 5, 5);
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_15_when_strategy_small_straight_and_roll_1_2_3_4_5()
  {
    int expected = 15;
    int actual = Yatzy.smallStraight(1, 2, 3, 4, 5);
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_0_when_strategy_small_straight_and_roll_5_2_3_4_5()
  {
    int expected = 0;
    int actual = Yatzy.smallStraight(5, 2, 3, 4, 5);
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_20_when_strategy_large_straight_and_roll_6_2_3_4_5()
  {
    int expected = 20;
    int actual = Yatzy.largeStraight(6, 2, 3, 4, 5);
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_0_when_strategy_large_straight_and_roll_1_2_3_4_5()
  {
    int expected = 0;
    int actual = Yatzy.largeStraight(1, 2, 3, 4, 5);
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_18_when_strategy_full_house_and_roll_6_2_2_2_6()
  {
    int expected = 18;
    int actual = Yatzy.fullHouse(6, 2, 2, 2, 6);
    assertEquals(expected, actual);
  }


  @Test
  public void should_return_0_when_strategy_full_house_and_roll_6_2_2_2_2()
  {
    int expected = 0;
    int actual = Yatzy.fullHouse(6, 2, 2, 2, 2);
    assertEquals(expected, actual);
  }
}