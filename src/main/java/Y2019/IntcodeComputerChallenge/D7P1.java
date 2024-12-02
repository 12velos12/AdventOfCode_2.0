package Y2019.IntcodeComputerChallenge;

import CustomClasses.RyansFileClass;

import java.util.ArrayList;

public class D7P1 {
    public static void main(String[] args){
        ArrayList<String> rawIntcode = RyansFileClass.fileToStringArray("src/main/java/Y2019/IntcodeComputerChallenge/day7PuzzleInput.txt");
        ArrayList<Long> intcodeProgram = IntcodeComputerHandler.interpretIntcode(rawIntcode.get(0));
        long outputSignal = Integer.MIN_VALUE;
        for(int phaseSettingA = 0; phaseSettingA < 5; phaseSettingA++){
            for(int phaseSettingB = 0; phaseSettingB < 5; phaseSettingB++){
                if(phaseSettingA == phaseSettingB) {continue;}
                for(int phaseSettingC = 0; phaseSettingC < 5; phaseSettingC++){
                    if(phaseSettingA == phaseSettingC || phaseSettingB == phaseSettingC) {continue;}
                    for(int phaseSettingD = 0; phaseSettingD < 5; phaseSettingD++){
                        if(phaseSettingA == phaseSettingD || phaseSettingB == phaseSettingD || phaseSettingC == phaseSettingD) {continue;}
                        for(int phaseSettingE = 0; phaseSettingE < 5; phaseSettingE++){
                            if(phaseSettingA == phaseSettingE || phaseSettingB == phaseSettingE || phaseSettingC == phaseSettingE || phaseSettingD == phaseSettingE) {continue;}
                            int[] phaseSettings = new int[] {phaseSettingA, phaseSettingB, phaseSettingC, phaseSettingD, phaseSettingE};
                            long tempOutputSignal = IntcodeComputerHandler.calculateSignalFromAmplifiers(intcodeProgram, phaseSettings);
                            if(tempOutputSignal > outputSignal){
                                outputSignal = tempOutputSignal;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(outputSignal);
    }
}
