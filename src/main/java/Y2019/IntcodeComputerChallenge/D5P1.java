package Y2019.IntcodeComputerChallenge;

import CustomClasses.RyansFileClass;

import java.util.ArrayList;

public class D5P1 {
    public static void main(String[] args){
        ArrayList<String> rawIntcode = RyansFileClass.fileToStringArray("src/main/java/Y2019/IntcodeComputerChallenge/day5PuzzleInput.txt");
        ArrayList<Long> intcodeProgram = IntcodeComputerHandler.interpretIntcode(rawIntcode.get(0));
        IntcodeComputer comp = new IntcodeComputer(intcodeProgram);

//        System.out.println(comp.getIntcodeProgram());
        IntcodeComputerHandler.processInputStep(comp,1);
//        System.out.println(comp.getIntcodeProgram());
        IntcodeReport report = new IntcodeReport();
        while(!report.haltFlag){
            report = IntcodeComputerHandler.processUntilIO(comp);
//            System.out.println("Report: " + report);
            System.out.println("Report Output Value: " + report.outputValue);
//            System.out.println("Report Halt Flag: " + report.haltFlag);
        }
    }
}
