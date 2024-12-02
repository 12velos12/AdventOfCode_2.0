package Y2019.IntcodeComputerChallenge;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntcodeComputerHandler {
    private IntcodeComputerHandler(){}

    public static ArrayList<Long> interpretIntcode(String intcodeLine){
        String[] rawIntcodeArray = intcodeLine.split(",");
        ArrayList<Long> interpretedIntcode = new ArrayList<>();

        try{
            for(String rawIntcode : rawIntcodeArray){
                interpretedIntcode.add(Long.parseLong(rawIntcode));
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }

        return interpretedIntcode;
    }

    public static IntcodeReport processInputStep(IntcodeComputer comp, long input){
        IntcodeReport report = comp.processStep(input);
        return report;
    }

    public static IntcodeReport processUntilIO(IntcodeComputer comp){
        IntcodeReport report;

        do{
            report = comp.processStep(null);
//            System.out.println(comp.getIntcodeProgram());
        } while (!report.needsInput && !report.haltFlag && report.outputValue == null);

        return report;
    }

    public static long processSimpleIO(IntcodeComputer comp, long input){ //One input at beginning produces single output at end
        IntcodeComputerHandler.processUntilIO(comp);
        IntcodeComputerHandler.processInputStep(comp,input);
        IntcodeReport report = IntcodeComputerHandler.processUntilIO(comp);
        return report.outputValue;
    }

    public static long processSimpleIO(ArrayList<Long> intcodeProgram, long input){ //One input at beginning produces single output at end
        IntcodeComputer comp = new IntcodeComputer(intcodeProgram);
        return processSimpleIO(comp, input);
    }

    public static long run1202ProgramAlarm(String intcodeLine){
        ArrayList<Long> intcode = interpretIntcode(intcodeLine);
        intcode.set(1,(long) 12);
        intcode.set(2,(long) 2);
        IntcodeComputer comp = new IntcodeComputer(intcode);
        processUntilIO(comp);
        return comp.getIntcodeProgram().get(0);
    }

    public static long[] determineNounAndVerbForInt(String intcodeLine, long targetValue){
        ArrayList<Long> intcode = interpretIntcode(intcodeLine);
        for(long noun = 0; noun < 100; noun++){
            for(long verb = 0; verb < 100; verb++){
                intcode.set(1, noun);
                intcode.set(2, verb);
                IntcodeComputer comp = new IntcodeComputer(intcode);
                processUntilIO(comp);
                if(comp.getIntcodeProgram().get(0).equals(targetValue)){
                    return new long[]{noun, verb};
                }
            }
        }

        System.out.println("No noun and verb for target int found.");
        return null;
    }

    public static long calculateSignalFromAmplifiers(ArrayList<Long> intcodeProgram, int[] phaseSequence){
        long outputSignal = 0;

        for(int phaseSetting : phaseSequence){
            IntcodeComputer amplifier = new IntcodeComputer(intcodeProgram);
            processUntilIO(amplifier);
            processInputStep(amplifier, phaseSetting);
            processUntilIO(amplifier);
            outputSignal = processSimpleIO(amplifier, outputSignal);
        }

        return outputSignal;
    }

    public static long calculateFeedbackSignalFromAmplifiers(ArrayList<Long> intcodeProgram, int[] phaseSequence){
        ArrayList<IntcodeComputer> compArray = new ArrayList<>();
        for(int i = 0; i < 5; i++){ //Creates and initializes computers with phaseSequence
            IntcodeComputer comp = new IntcodeComputer(intcodeProgram);
            processInputStep(comp,phaseSequence[i]);
            compArray.add(comp);
        }
        long outputSignal = 0;
        IntcodeReport report = new IntcodeReport();

        while(!report.haltFlag){
            for(IntcodeComputer comp : compArray){
                report = processUntilIO(comp);
                if(report.haltFlag){ break; }
                processInputStep(comp, outputSignal);
                report = processUntilIO(comp);
                if(report.haltFlag){
                    break;
                } else {
                    outputSignal = report.outputValue;
                }
            }
        }

        return outputSignal;
    }
}
