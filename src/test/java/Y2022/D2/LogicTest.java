package Y2022.D2;

import CustomClasses.RyansFileClass;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LogicTest {

    @Test
    void calculateStrategyScore() {
        ArrayList<String> strategyGuide = RyansFileClass.fileToStringArray(new File("src/main/java/Y2022/D2/e1Input.txt"));
        assertEquals(15, Logic.calculateStrategyScore(strategyGuide, 1));
        System.out.println("P1 Example Passed");
        assertEquals(12, Logic.calculateStrategyScore(strategyGuide, 2));
        System.out.println("P2 Example Passed");
        strategyGuide = RyansFileClass.fileToStringArray(new File("src/main/java/Y2022/D2/pInput.txt"));
        assertEquals(9759, Logic.calculateStrategyScore(strategyGuide, 1));
        System.out.println("P1 Puzzle Passed");
        assertEquals(12429, Logic.calculateStrategyScore(strategyGuide, 2));
        System.out.println("P2 Puzzle Passed");
    }

    @Test
    void calculateRoundScore() {
        assertEquals(8, Logic.calculateRoundScore("A Y",1));
        assertEquals(1, Logic.calculateRoundScore("B X",1));
        assertEquals(6, Logic.calculateRoundScore("C Z",1));
    }

    @Test
    void getShapeScore() {
        assertEquals(1, Logic.getShapeScore(Logic.Hand.ROCK));
        assertEquals(2, Logic.getShapeScore(Logic.Hand.PAPER));
        assertEquals(3, Logic.getShapeScore(Logic.Hand.SCISSORS));
    }

    @Test
    void getRoundOutcomeScore() {
        assertEquals(6, Logic.getRoundOutcomeScore(Logic.Hand.ROCK, Logic.Hand.PAPER));
        assertEquals(0, Logic.getRoundOutcomeScore(Logic.Hand.PAPER, Logic.Hand.ROCK));
        assertEquals(3, Logic.getRoundOutcomeScore(Logic.Hand.SCISSORS, Logic.Hand.SCISSORS));
    }

    @Test
    void determineRoundOutcome() {
        assertEquals(Logic.Outcome.WIN, Logic.determineRoundOutcome(Logic.Hand.ROCK, Logic.Hand.PAPER));
        assertEquals(Logic.Outcome.LOSS, Logic.determineRoundOutcome(Logic.Hand.PAPER, Logic.Hand.ROCK));
        assertEquals(Logic.Outcome.DRAW, Logic.determineRoundOutcome(Logic.Hand.SCISSORS, Logic.Hand.SCISSORS));
    }

    @Test
    void parseOpponentMove() {
        assertEquals(Logic.Hand.ROCK, Logic.parseOpponentMove("A Y"));
        assertEquals(Logic.Hand.PAPER, Logic.parseOpponentMove("B X"));
        assertEquals(Logic.Hand.SCISSORS, Logic.parseOpponentMove("C Z"));
    }

    @Test
    void parseYourHandP1() {
        assertEquals(Logic.Hand.PAPER, Logic.parseYourHandP1("A Y"));
        assertEquals(Logic.Hand.ROCK, Logic.parseYourHandP1("B X"));
        assertEquals(Logic.Hand.SCISSORS, Logic.parseYourHandP1("C Z"));
    }

    @Test
    void getOpponentHand() {
        assertEquals(Logic.Hand.ROCK, Logic.getOpponentHand("A"));
        assertEquals(Logic.Hand.PAPER, Logic.getOpponentHand("B"));
        assertEquals(Logic.Hand.SCISSORS, Logic.getOpponentHand("C"));
    }

    @Test
    void getYourHandP1() {
        assertEquals(Logic.Hand.ROCK, Logic.getYourHandP1("X"));
        assertEquals(Logic.Hand.PAPER, Logic.getYourHandP1("Y"));
        assertEquals(Logic.Hand.SCISSORS, Logic.getYourHandP1("Z"));
    }

    @Test
    void parseYourHandP2() {
        assertEquals(Logic.Hand.ROCK, Logic.parseYourHandP2("A Y"));
        assertEquals(Logic.Hand.ROCK, Logic.parseYourHandP2("B X"));
        assertEquals(Logic.Hand.ROCK, Logic.parseYourHandP2("C Z"));
    }

    @Test
    void parseDesiredOutcomeP2() {
        assertEquals(Logic.Outcome.DRAW, Logic.parseDesiredOutcomeP2("A Y"));
        assertEquals(Logic.Outcome.LOSS, Logic.parseDesiredOutcomeP2("B X"));
        assertEquals(Logic.Outcome.WIN, Logic.parseDesiredOutcomeP2("C Z"));
    }

    @Test
    void getDesiredOutcomeP2() {
        assertEquals(Logic.Outcome.DRAW, Logic.getDesiredOutcomeP2("Y"));
        assertEquals(Logic.Outcome.LOSS, Logic.getDesiredOutcomeP2("X"));
        assertEquals(Logic.Outcome.WIN, Logic.getDesiredOutcomeP2("Z"));
    }
}