package Y2019.IntcodeComputerChallenge;

import CustomClasses.RyansFileClass;

import java.util.ArrayList;

public class D7P2 {
    public static void main(String[] args){
        ArrayList<String> rawIntcode = RyansFileClass.fileToStringArray("src/main/java/Y2019/IntcodeComputerChallenge/day7PuzzleInput.txt");
        ArrayList<Long> intcodeProgram = IntcodeComputerHandler.interpretIntcode(rawIntcode.get(0));
        long outputSignal = Integer.MIN_VALUE;
        for(int phaseSettingA = 5; phaseSettingA < 10; phaseSettingA++){
            for(int phaseSettingB = 5; phaseSettingB < 10; phaseSettingB++){
                if(phaseSettingA == phaseSettingB) {continue;}
                for(int phaseSettingC = 5; phaseSettingC < 10; phaseSettingC++){
                    if(phaseSettingA == phaseSettingC || phaseSettingB == phaseSettingC) {continue;}
                    for(int phaseSettingD = 5; phaseSettingD < 10; phaseSettingD++){
                        if(phaseSettingA == phaseSettingD || phaseSettingB == phaseSettingD || phaseSettingC == phaseSettingD) {continue;}
                        for(int phaseSettingE = 5; phaseSettingE < 10; phaseSettingE++){
                            if(phaseSettingA == phaseSettingE || phaseSettingB == phaseSettingE || phaseSettingC == phaseSettingE || phaseSettingD == phaseSettingE) {continue;}
                            int[] phaseSettings = new int[] {phaseSettingA, phaseSettingB, phaseSettingC, phaseSettingD, phaseSettingE};
                            long tempOutputSignal = IntcodeComputerHandler.calculateFeedbackSignalFromAmplifiers(intcodeProgram, phaseSettings);
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
