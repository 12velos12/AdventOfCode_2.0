package Y2019.IntcodeComputerChallenge;

import CustomClasses.RyansFileClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class IntcodeComputerTest {

    @Test
    void getIntcodeProgram() {
        ArrayList<Long> inputProgram = new ArrayList<>(Arrays.asList(1L,9L,10L,3L,2L,3L,11L,0L,99L,30L,40L,50L));
        IntcodeComputer comp = new IntcodeComputer(inputProgram);
        assertEquals(inputProgram, comp.getIntcodeProgram());
    }

    @Test
    void getOpCode() {
        assertAll(() -> assertEquals(1, IntcodeComputer.getOpCode(10101)),
                () -> assertEquals(2, IntcodeComputer.getOpCode(102)),
                () -> assertEquals(3, IntcodeComputer.getOpCode(3)),
                () -> assertEquals(4, IntcodeComputer.getOpCode(1104)),
                () -> assertEquals(99, IntcodeComputer.getOpCode(99)));
    }

    @Test
    void getParameters() {
        assertAll(() -> assertEquals("101", IntcodeComputer.getParameterModes(10101)),
                () -> assertEquals("001", IntcodeComputer.getParameterModes(102)),
                () -> assertEquals("000", IntcodeComputer.getParameterModes(3)),
                () -> assertEquals("011", IntcodeComputer.getParameterModes(1104)),
                () -> assertEquals("000", IntcodeComputer.getParameterModes(99)));
    }

    @Test
    @DisplayName("performOpCode: OpCode 1 Test")
    void performOpCode1() {
        IntcodeComputer comp = new IntcodeComputer(new ArrayList<>(Arrays.asList(1L,9L,10L,3L,2L,3L,11L,0L,99L,30L,40L,50L)));
        comp.performOpCode();
        assertEquals(new ArrayList<>(Arrays.asList(1L,9L,10L,70L,2L,3L,11L,0L,99L,30L,40L,50L)), comp.getIntcodeProgram());
    }
    @Test
    @DisplayName("performOpCode: OpCode 2 Test")
    void performOpCode2() {
        IntcodeComputer comp = new IntcodeComputer(new ArrayList<>(Arrays.asList(2L,3L,0L,3L,99L)));
        comp.performOpCode();
        assertEquals(new ArrayList<>(Arrays.asList(2L,3L,0L,6L,99L)), comp.getIntcodeProgram());
    }
    @Test
    @DisplayName("performOpCode: OpCode 3 Test")
    void performOpCode3() {
        IntcodeComputer comp = new IntcodeComputer(new ArrayList<>(Arrays.asList(3L,2L,99L)));
        comp.processStep(131L);
        assertEquals(new ArrayList<>(Arrays.asList(3L,2L,131L)), comp.getIntcodeProgram());
    }
    @Test
    @DisplayName("performOpCode: OpCode 4 Test")
    void performOpCode4() {
        IntcodeComputer comp = new IntcodeComputer(new ArrayList<>(Arrays.asList(4L,0L,99L)));
        IntcodeReport report = comp.processStep(null);
        assertEquals(4, report.outputValue);
    }
    @Test
    @DisplayName("performOpCode: OpCode 5 Test")
    void performOpCode5() {
        IntcodeComputer comp = new IntcodeComputer(new ArrayList<>(Arrays.asList(1005L,0L,5L,104L,0L,104L,1L)));
        IntcodeReport report = IntcodeComputerHandler.processUntilIO(comp);
        assertEquals(1, report.outputValue);
    }
    @Test
    @DisplayName("performOpCode: OpCode 6 Test")
    void performOpCode6() {
        IntcodeComputer comp = new IntcodeComputer(new ArrayList<>(Arrays.asList(1106L,0L,5L,104L,0L,104L,1L)));
        IntcodeReport report = IntcodeComputerHandler.processUntilIO(comp);
        assertEquals(1, report.outputValue);
    }
    @Test
    @DisplayName("performOpCode: OpCode 7 Test")
    void performOpCode7() {
        IntcodeComputer comp = new IntcodeComputer(new ArrayList<>(Arrays.asList(10107L,12L,0L,5L,104L,42L)));
        IntcodeReport report = IntcodeComputerHandler.processUntilIO(comp);
        assertEquals(1, report.outputValue);
    }
    @Test
    @DisplayName("performOpCode: OpCode 8 Test")
    void performOpCode8() {
        IntcodeComputer comp = new IntcodeComputer(new ArrayList<>(Arrays.asList(10108L,10108L,0L,5L,104L,42L)));
        IntcodeReport report = IntcodeComputerHandler.processUntilIO(comp);
        assertEquals(1, report.outputValue);
    }
    @Test
    @DisplayName("performOpCode: OpCode 9 Test")
    void performOpCode9() {
        IntcodeComputer comp = new IntcodeComputer(new ArrayList<>(Arrays.asList(109L,3L,21201L,1L,21L,4L,104L,0L)));
        IntcodeReport report = IntcodeComputerHandler.processUntilIO(comp);
        assertEquals(42, report.outputValue);
    }
    @Test
    @DisplayName("performOpCode: OpCode 99 Test")
    void performOpCode99() {
        IntcodeComputer comp = new IntcodeComputer(new ArrayList<>(Arrays.asList(99L,1L,1L,4L,99L,5L,6L,0L,99L)));
        comp.performOpCode();
        assertEquals(new ArrayList<>(Arrays.asList(99L,1L,1L,4L,99L,5L,6L,0L,99L)), comp.getIntcodeProgram());
    }

    @Test
    void testExamplesD9P1() {
        IntcodeComputer comp;
        IntcodeReport report;

        comp = new IntcodeComputer(new ArrayList<>(Arrays.asList(109L,1L,204L,-1L,1001L,100L,1L,100L,1008L,100L,16L,101L,1006L,101L,0L,99L)));
        assertEquals(109, IntcodeComputerHandler.processUntilIO(comp).outputValue);
        assertEquals(1, IntcodeComputerHandler.processUntilIO(comp).outputValue);
        assertEquals(204, IntcodeComputerHandler.processUntilIO(comp).outputValue);
        assertEquals(-1, IntcodeComputerHandler.processUntilIO(comp).outputValue);
        assertEquals(1001, IntcodeComputerHandler.processUntilIO(comp).outputValue);
        assertEquals(100, IntcodeComputerHandler.processUntilIO(comp).outputValue);
        assertEquals(1, IntcodeComputerHandler.processUntilIO(comp).outputValue);
        assertEquals(100, IntcodeComputerHandler.processUntilIO(comp).outputValue);
        assertEquals(1008, IntcodeComputerHandler.processUntilIO(comp).outputValue);
        assertEquals(100, IntcodeComputerHandler.processUntilIO(comp).outputValue);
        assertEquals(16, IntcodeComputerHandler.processUntilIO(comp).outputValue);
        assertEquals(101, IntcodeComputerHandler.processUntilIO(comp).outputValue);
        assertEquals(1006, IntcodeComputerHandler.processUntilIO(comp).outputValue);
        assertEquals(101, IntcodeComputerHandler.processUntilIO(comp).outputValue);
        assertEquals(0, IntcodeComputerHandler.processUntilIO(comp).outputValue);
        assertEquals(99, IntcodeComputerHandler.processUntilIO(comp).outputValue);

        comp = new IntcodeComputer(new ArrayList<>(Arrays.asList(1102L,34915192L,34915192L,7L,4L,7L,99L,0L)));
        report = IntcodeComputerHandler.processUntilIO(comp);
        assertEquals(1219070632396864L, report.outputValue);

        comp = new IntcodeComputer(new ArrayList<>(Arrays.asList(104L,1125899906842624L,99L)));
        report = IntcodeComputerHandler.processUntilIO(comp);
        assertEquals(1125899906842624L, report.outputValue);
    }

    @Test
    void D9P1Problem(){
        ArrayList<String> rawIntcode = RyansFileClass.fileToStringArray("src/main/java/Y2019/IntcodeComputerChallenge/day9PuzzleInput.txt");
        ArrayList<Long> intcodeProgram = IntcodeComputerHandler.interpretIntcode(rawIntcode.get(0));
        IntcodeComputer comp = new IntcodeComputer(intcodeProgram);

        IntcodeComputerHandler.processUntilIO(comp);
        assertEquals(2775723069L,IntcodeComputerHandler.processSimpleIO(comp,1));
    }

    @Test
    void D9P2Problem(){
        ArrayList<String> rawIntcode = RyansFileClass.fileToStringArray("src/main/java/Y2019/IntcodeComputerChallenge/day9PuzzleInput.txt");
        ArrayList<Long> intcodeProgram = IntcodeComputerHandler.interpretIntcode(rawIntcode.get(0));
        IntcodeComputer comp = new IntcodeComputer(intcodeProgram);

        IntcodeComputerHandler.processUntilIO(comp);
        assertEquals(49115,IntcodeComputerHandler.processSimpleIO(comp,2));
    }
}