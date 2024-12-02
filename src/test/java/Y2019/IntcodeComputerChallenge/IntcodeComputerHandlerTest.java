package Y2019.IntcodeComputerChallenge;

import CustomClasses.RyansFileClass;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class IntcodeComputerHandlerTest {

    @Test
    void interpretIntcode() {
        ArrayList<Long> output = IntcodeComputerHandler.interpretIntcode("1,9,10,3,2,3,11,0,99,30,40,50");
        assertEquals(new ArrayList<>(Arrays.asList(1L,9L,10L,3L,2L,3L,11L,0L,99L,30L,40L,50L)), output);
    }

    @Test
    void processIntcode() {
        IntcodeComputer comp;
        ArrayList<String> rawIntcode;
        ArrayList<Long> intcodeProgram;

        comp = new IntcodeComputer(new ArrayList<>(Arrays.asList(1L,9L,10L,3L,2L,3L,11L,0L,99L,30L,40L,50L)));
        IntcodeComputerHandler.processUntilIO(comp);
        assertEquals(new ArrayList<>(Arrays.asList(3500L,9L,10L,70L,2L,3L,11L,0L,99L,30L,40L,50L)),comp.getIntcodeProgram());

        comp = new IntcodeComputer(new ArrayList<>(Arrays.asList(1L,0L,0L,0L,99L)));
        IntcodeComputerHandler.processUntilIO(comp);
        assertEquals(new ArrayList<>(Arrays.asList(2L,0L,0L,0L,99L)),comp.getIntcodeProgram());

        comp = new IntcodeComputer(new ArrayList<>(Arrays.asList(2L,3L,0L,3L,99L)));
        IntcodeComputerHandler.processUntilIO(comp);
        assertEquals(new ArrayList<>(Arrays.asList(2L,3L,0L,6L,99L)),comp.getIntcodeProgram());

        comp = new IntcodeComputer(new ArrayList<>(Arrays.asList(2L,4L,4L,5L,99L,0L)));
        IntcodeComputerHandler.processUntilIO(comp);
        assertEquals(new ArrayList<>(Arrays.asList(2L,4L,4L,5L,99L,9801L)),comp.getIntcodeProgram());

        comp = new IntcodeComputer(new ArrayList<>(Arrays.asList(1L,1L,1L,4L,99L,5L,6L,0L,99L)));
        IntcodeComputerHandler.processUntilIO(comp);
        assertEquals(new ArrayList<>(Arrays.asList(30L,1L,1L,4L,2L,5L,6L,0L,99L)),comp.getIntcodeProgram());

        comp = new IntcodeComputer(new ArrayList<>(Arrays.asList(1002L,4L,3L,4L,33L)));
        IntcodeComputerHandler.processUntilIO(comp);
        assertEquals(new ArrayList<>(Arrays.asList(1002L,4L,3L,4L,99L)),comp.getIntcodeProgram());

        comp = new IntcodeComputer(new ArrayList<>(Arrays.asList(3L,0L,4L,0L,99L)));
        IntcodeComputerHandler.processInputStep(comp,42);
        assertEquals(42,comp.processStep(null).outputValue);

        comp = new IntcodeComputer(new ArrayList<>(Arrays.asList(1101L,100L,-1L,4L,0L)));
        IntcodeComputerHandler.processUntilIO(comp);
        assertEquals(new ArrayList<>(Arrays.asList(1101L,100L,-1L,4L,99L)),comp.getIntcodeProgram());

        rawIntcode = RyansFileClass.fileToStringArray("src/main/java/Y2019/IntcodeComputerChallenge/day5PuzzleInput.txt");
        intcodeProgram = IntcodeComputerHandler.interpretIntcode(rawIntcode.get(0));
        comp = new IntcodeComputer(intcodeProgram);
        IntcodeComputerHandler.processInputStep(comp,1);
        IntcodeReport report = new IntcodeReport();
        while(!report.haltFlag && (report.outputValue == null || report.outputValue == 0)) { //if report.outputValue == 0 goes first program blows up because detects null
            report = IntcodeComputerHandler.processUntilIO(comp);
        }
        assertEquals(15259545, report.outputValue);

        assertEquals(0, IntcodeComputerHandler.processSimpleIO(new ArrayList<>(Arrays.asList(3L,9L,8L,9L,10L,9L,4L,9L,99L,-1L,8L)), 42));
        assertEquals(1, IntcodeComputerHandler.processSimpleIO(new ArrayList<>(Arrays.asList(3L,9L,8L,9L,10L,9L,4L,9L,99L,-1L,8L)), 8));

        assertEquals(0, IntcodeComputerHandler.processSimpleIO(new ArrayList<>(Arrays.asList(3L,9L,7L,9L,10L,9L,4L,9L,99L,-1L,8L)), 42));
        assertEquals(1, IntcodeComputerHandler.processSimpleIO(new ArrayList<>(Arrays.asList(3L,9L,7L,9L,10L,9L,4L,9L,99L,-1L,8L)), 1));

        assertEquals(0, IntcodeComputerHandler.processSimpleIO(new ArrayList<>(Arrays.asList(3L,3L,1108L,-1L,8L,3L,4L,3L,99L)), 42));
        assertEquals(1, IntcodeComputerHandler.processSimpleIO(new ArrayList<>(Arrays.asList(3L,3L,1108L,-1L,8L,3L,4L,3L,99L)), 8));

        assertEquals(0, IntcodeComputerHandler.processSimpleIO(new ArrayList<>(Arrays.asList(3L,3L,1107L,-1L,8L,3L,4L,3L,99L)), 42));
        assertEquals(1, IntcodeComputerHandler.processSimpleIO(new ArrayList<>(Arrays.asList(3L,3L,1107L,-1L,8L,3L,4L,3L,99L)), 1));

        assertEquals(0, IntcodeComputerHandler.processSimpleIO(new ArrayList<>(Arrays.asList(3L,12L,6L,12L,15L,1L,13L,14L,13L,4L,13L,99L,-1L,0L,1L,9L)), 0));
        assertEquals(1, IntcodeComputerHandler.processSimpleIO(new ArrayList<>(Arrays.asList(3L,12L,6L,12L,15L,1L,13L,14L,13L,4L,13L,99L,-1L,0L,1L,9L)), 42));

        assertEquals(0, IntcodeComputerHandler.processSimpleIO(new ArrayList<>(Arrays.asList(3L,3L,1105L,-1L,9L,1101L,0L,0L,12L,4L,12L,99L,1L)), 0));
        assertEquals(1, IntcodeComputerHandler.processSimpleIO(new ArrayList<>(Arrays.asList(3L,3L,1105L,-1L,9L,1101L,0L,0L,12L,4L,12L,99L,1L)), 42));

        assertEquals(999, IntcodeComputerHandler.processSimpleIO(new ArrayList<>(Arrays.asList(3L,21L,1008L,21L,8L,20L,1005L,20L,22L,107L,8L,21L,20L,1006L,20L,
                31L, 1106L,0L,36L,98L,0L,0L,1002L,21L,125L,20L,4L,20L,1105L,1L,46L,104L, 999L,1105L,1L,46L,1101L,1000L,1L,20L,4L,20L,1105L,1L,46L,98L,99L)), 0));
        assertEquals(1000, IntcodeComputerHandler.processSimpleIO(new ArrayList<>(Arrays.asList(3L,21L,1008L,21L,8L,20L,1005L,20L,22L,107L,8L,21L,20L,1006L,20L,
                31L, 1106L,0L,36L,98L,0L,0L,1002L,21L,125L,20L,4L,20L,1105L,1L,46L,104L, 999L,1105L,1L,46L,1101L,1000L,1L,20L,4L,20L,1105L,1L,46L,98L,99L)), 8));
        assertEquals(1001, IntcodeComputerHandler.processSimpleIO(new ArrayList<>(Arrays.asList(3L,21L,1008L,21L,8L,20L,1005L,20L,22L,107L,8L,21L,20L,1006L,20L,
                31L, 1106L,0L,36L,98L,0L,0L,1002L,21L,125L,20L,4L,20L,1105L,1L,46L,104L, 999L,1105L,1L,46L,1101L,1000L,1L,20L,4L,20L,1105L,1L,46L,98L,99L)), 42));


        rawIntcode = RyansFileClass.fileToStringArray("src/main/java/Y2019/IntcodeComputerChallenge/day5PuzzleInput.txt");
        intcodeProgram = IntcodeComputerHandler.interpretIntcode(rawIntcode.get(0));
        assertEquals(7616021, IntcodeComputerHandler.processSimpleIO(intcodeProgram, 5));
    }

//    @Test
//    void testProcessIntcodeWithString() {
//        assertAll(() -> assertEquals(new ArrayList<>(Arrays.asList(3500,9,10,70,2,3,11,0,99,30,40,50)),
//                (IntcodeComputerHandler.processIntcodeContinuously("1,9,10,3,2,3,11,0,99,30,40,50"))),
//                () -> assertEquals(new ArrayList<>(Arrays.asList(2,0,0,0,99)),
//                        (IntcodeComputerHandler.processIntcodeContinuously("1,0,0,0,99"))),
//                () -> assertEquals(new ArrayList<>(Arrays.asList(2,3,0,6,99)),
//                        (IntcodeComputerHandler.processIntcodeContinuously("2,3,0,3,99"))),
//                () -> assertEquals(new ArrayList<>(Arrays.asList(2,4,4,5,99,9801)),
//                        (IntcodeComputerHandler.processIntcodeContinuously("2,4,4,5,99,0"))),
//                () -> assertEquals(new ArrayList<>(Arrays.asList(30,1,1,4,2,5,6,0,99)),
//                        (IntcodeComputerHandler.processIntcodeContinuously("1,1,1,4,99,5,6,0,99"))));
//    }

    @Test
    void run1202ProgramAlarm() {
        ArrayList<String> inputArray = RyansFileClass.fileToStringArray("src/main/java/Y2019/IntcodeComputerChallenge/day2PuzzleInput.txt");
        assertEquals(9706670,IntcodeComputerHandler.run1202ProgramAlarm(inputArray.get(0)));
    }

    @Test
    void determineNounAndVerbForInt() {
        ArrayList<String> inputArray = RyansFileClass.fileToStringArray("src/main/java/Y2019/IntcodeComputerChallenge/day2PuzzleInput.txt");
        long[] result = IntcodeComputerHandler.determineNounAndVerbForInt(inputArray.get(0),19690720);
        assertEquals(2552,100 * result[0] + result[1]);
    }

    @Test
    void processSimpleIO() {
        assertEquals(0, IntcodeComputerHandler.processSimpleIO(new ArrayList<>(Arrays.asList(3L,9L,8L,9L,10L,9L,4L,9L,99L,-1L,8L)), 42));
        assertEquals(1, IntcodeComputerHandler.processSimpleIO(new ArrayList<>(Arrays.asList(3L,9L,8L,9L,10L,9L,4L,9L,99L,-1L,8L)), 8));

        assertEquals(0, IntcodeComputerHandler.processSimpleIO(new ArrayList<>(Arrays.asList(3L,9L,7L,9L,10L,9L,4L,9L,99L,-1L,8L)), 42));
        assertEquals(1, IntcodeComputerHandler.processSimpleIO(new ArrayList<>(Arrays.asList(3L,9L,7L,9L,10L,9L,4L,9L,99L,-1L,8L)), 1));

        assertEquals(0, IntcodeComputerHandler.processSimpleIO(new ArrayList<>(Arrays.asList(3L,3L,1108L,-1L,8L,3L,4L,3L,99L)), 42));
        assertEquals(1, IntcodeComputerHandler.processSimpleIO(new ArrayList<>(Arrays.asList(3L,3L,1108L,-1L,8L,3L,4L,3L,99L)), 8));

        assertEquals(0, IntcodeComputerHandler.processSimpleIO(new ArrayList<>(Arrays.asList(3L,3L,1107L,-1L,8L,3L,4L,3L,99L)), 42));
        assertEquals(1, IntcodeComputerHandler.processSimpleIO(new ArrayList<>(Arrays.asList(3L,3L,1107L,-1L,8L,3L,4L,3L,99L)), 1));
    }

    @Test
    void calculateSignalFromAmplifiers() {
        assertEquals(43210,
                IntcodeComputerHandler.calculateSignalFromAmplifiers(new ArrayList<>(Arrays.asList(3L,15L,3L,16L,1002L,16L,10L,16L,1L,16L,15L,15L,4L,15L,99L,0L,0L)),new int[] {4,3,2,1,0}));
        assertEquals(54321,
                IntcodeComputerHandler.calculateSignalFromAmplifiers(new ArrayList<>(Arrays.asList(3L,23L,3L,24L,1002L,24L,10L,24L,1002L,23L,-1L,23L,101L,5L,23L,23L,1L,24L,23L,23L,4L,23L,99L,0L,0L)),new int[] {0,1,2,3,4}));
        assertEquals(65210,
                IntcodeComputerHandler.calculateSignalFromAmplifiers(new ArrayList<>(Arrays.asList(3L,31L,3L,32L,1002L,32L,10L,32L,1001L,31L,-2L,31L,1007L,31L,0L,33L,1002L,33L,7L,33L,1L,33L,31L,31L,1L,32L,31L,31L,4L,31L,99L,0L,0L,0L)),new int[] {1,0,4,3,2}));

        ArrayList<String> rawIntcode = RyansFileClass.fileToStringArray("src/main/java/Y2019/IntcodeComputerChallenge/day7PuzzleInput.txt"); //D7P1
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
        assertEquals(99376, outputSignal);
    }

    @Test
    void calculateFeedbackSignalFromAmplifiers() {
        assertEquals(139629729,
                IntcodeComputerHandler.calculateFeedbackSignalFromAmplifiers(new ArrayList<>(Arrays.asList(3L,26L,1001L,26L,-4L,26L,3L,27L,1002L,27L,2L,27L,1L,27L,26L,27L,4L,27L,1001L,28L,-1L,28L,1005L,28L,6L,99L,0L,0L,5L)),new int[] {9,8,7,6,5}));
        assertEquals(18216,
                IntcodeComputerHandler.calculateFeedbackSignalFromAmplifiers(new ArrayList<>(Arrays.asList(3L,52L,1001L,52L,-5L,52L,3L,53L,1L,52L,56L,54L,1007L,54L,5L,55L,1005L,55L,26L,1001L,54L,-5L,54L,1105L,1L,12L,1L,53L,54L,53L,1008L,54L,0L,55L,1001L,55L,1L,55L,2L,53L,55L,53L,4L,53L,1001L,56L,-1L,56L,1005L,56L,6L,99L,0L,0L,0L,0L,10L)),new int[] {9,7,8,5,6}));

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

        assertEquals(8754464, outputSignal);
    }
}