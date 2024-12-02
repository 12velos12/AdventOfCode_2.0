package Y2019.IntcodeComputerChallenge;

import CustomClasses.RyansFileClass;

import java.util.ArrayList;

public class D2P1 {
    public static void main(String[] args){
        ArrayList<String> inputArray = RyansFileClass.fileToStringArray("src/main/java/Y2019/IntcodeComputerChallenge/day2PuzzleInput.txt");
        System.out.println(IntcodeComputerHandler.run1202ProgramAlarm(inputArray.get(0)));
    }
}
