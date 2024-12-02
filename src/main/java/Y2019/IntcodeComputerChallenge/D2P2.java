package Y2019.IntcodeComputerChallenge;

import CustomClasses.RyansFileClass;

import java.util.ArrayList;

public class D2P2 {
    public static void main(String[] args){
        ArrayList<String> inputArray = RyansFileClass.fileToStringArray("src/main/java/Y2019/IntcodeComputerChallenge/day2PuzzleInput.txt");
        long[] result = IntcodeComputerHandler.determineNounAndVerbForInt(inputArray.get(0),19690720);
        System.out.println(100 * result[0] + result[1]);
    }
}
