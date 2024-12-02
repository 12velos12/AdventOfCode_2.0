package Y2022.D2;

import java.util.ArrayList;

public class Logic {
    enum Hand {
        ROCK,
        PAPER,
        SCISSORS
    }

    enum Outcome {
        WIN,
        DRAW,
        LOSS
    }

    public static int calculateStrategyScore(ArrayList<String> strategyGuide, int partNum){
        int scoreSum = 0;
        for(String round : strategyGuide){
            scoreSum += calculateRoundScore(round, partNum);
        }

        return scoreSum;
    }

    public static int calculateRoundScore(String roundMoves, int partNum){
        if(partNum == 1){
            return getShapeScore(parseYourHandP1(roundMoves)) + getRoundOutcomeScore(parseOpponentMove(roundMoves), parseYourHandP1(roundMoves));
        }
        else if(partNum == 2){
            return getShapeScore(parseYourHandP2(roundMoves)) + getRoundOutcomeScore(parseOpponentMove(roundMoves), parseYourHandP2(roundMoves));
        }
        throw new RuntimeException("Error - calculateRoundScore(String roundMoves, int partNum) - partNum not recognized: " + partNum);
    }

    public static int getShapeScore(Hand yourHand){
        if(yourHand == Hand.ROCK)
            return 1;
        else if(yourHand == Hand.PAPER)
            return 2;
        else if(yourHand == Hand.SCISSORS)
            return 3;
        throw new RuntimeException("Error - getShapeScore(Hand yourHand) - Hand not recognized: " + yourHand);
    }

    public static int getRoundOutcomeScore(Hand opponentHand, Hand yourHand){
        switch (determineRoundOutcome(opponentHand, yourHand)){
            case WIN:
                return 6;
            case DRAW:
                return 3;
            case LOSS:
                return 0;
            default:
                throw new RuntimeException("Error - getRoundOutcomeScore(Hand opponentHand, Hand yourHand) - Outcome not recognized: " + determineRoundOutcome(opponentHand, yourHand));
        }
    }

    public static Outcome determineRoundOutcome(Hand opponentHand, Hand yourHand){

        if(opponentHand == yourHand)
            return Outcome.DRAW;
        else if(opponentHand == Hand.ROCK && yourHand == Hand.PAPER)
            return Outcome.WIN;
        else if(opponentHand == Hand.ROCK && yourHand == Hand.SCISSORS)
            return Outcome.LOSS;
        else if(opponentHand == Hand.PAPER && yourHand == Hand.ROCK)
            return Outcome.LOSS;
        else if(opponentHand == Hand.PAPER && yourHand == Hand.SCISSORS)
            return Outcome.WIN;
        else if(opponentHand == Hand.SCISSORS && yourHand == Hand.ROCK)
            return Outcome.WIN;
        else if(opponentHand == Hand.SCISSORS && yourHand == Hand.PAPER)
            return Outcome.LOSS;

        throw new RuntimeException("Error - determineRoundOutcome(Hand opponentHand, Hand yourHand) - Combo not accounted for: opponentHand: " + opponentHand + " yourHand: " + yourHand);
    }

    public static Hand parseOpponentMove(String roundMoves){
        String[] moves = roundMoves.split(" ");
        return getOpponentHand(moves[0]);
    }

    public static Hand parseYourHandP1(String roundMoves){
        String[] moves = roundMoves.split(" ");
        return getYourHandP1(moves[1]);
    }

    public static Hand getOpponentHand(String symbol){
        switch(symbol){
            case "A":
                return Hand.ROCK;
            case "B":
                return Hand.PAPER;
            case "C":
                return Hand.SCISSORS;
            default:
                throw new RuntimeException("Error - getOpponentHand(String symbol) - Symbol not recognized: " + symbol);
        }
    }

    public static Hand getYourHandP1(String symbol){
        switch(symbol){
            case "X":
                return Hand.ROCK;
            case "Y":
                return Hand.PAPER;
            case "Z":
                return Hand.SCISSORS;
            default:
                throw new RuntimeException("Error - getYourHandP1(String symbol) - Symbol not recognized: " + symbol);
        }
    }

    public static Hand parseYourHandP2(String roundMoves){
        Hand opponentHand = parseOpponentMove(roundMoves);
        Outcome desiredOutcome = parseDesiredOutcomeP2(roundMoves);

        if(determineRoundOutcome(opponentHand,Hand.ROCK) == desiredOutcome)
            return Hand.ROCK;
        else if(determineRoundOutcome(opponentHand,Hand.PAPER) == desiredOutcome)
            return Hand.PAPER;
        else if(determineRoundOutcome(opponentHand,Hand.SCISSORS) == desiredOutcome)
            return Hand.SCISSORS;
        throw new RuntimeException("Error - determineYourHandP2(String roundMoves) - Hand could not be determined - opponentHand: " + opponentHand + " desiredOutcome: " + desiredOutcome);
    }

    public static Outcome parseDesiredOutcomeP2(String roundMoves){
        String[] moves = roundMoves.split(" ");
        return getDesiredOutcomeP2(moves[1]);
    }

    public static Outcome getDesiredOutcomeP2(String symbol){
        switch(symbol){
            case "X":
                return Outcome.LOSS;
            case "Y":
                return Outcome.DRAW;
            case "Z":
                return Outcome.WIN;
            default:
                throw new RuntimeException("Error - getDesiredOutcomeP2(String symbol) - Symbol not recognized: " + symbol);
        }
    }
}
