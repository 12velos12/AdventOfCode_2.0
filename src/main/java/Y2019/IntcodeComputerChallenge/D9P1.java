package Y2019.IntcodeComputerChallenge;

import CustomClasses.RyansFileClass;

import java.util.ArrayList;

public class D9P1 {
    public static void main(String[] args){
        ArrayList<String> rawIntcode = RyansFileClass.fileToStringArray("src/main/java/Y2019/IntcodeComputerChallenge/day9PuzzleInput.txt");
        ArrayList<Long> intcodeProgram = IntcodeComputerHandler.interpretIntcode(rawIntcode.get(0));
        IntcodeComputer comp = new IntcodeComputer(intcodeProgram);

        IntcodeComputerHandler.processUntilIO(comp);
        System.out.println(IntcodeComputerHandler.processSimpleIO(comp,1));
    }
}
