package Y2019.IntcodeComputerChallenge;

import java.util.ArrayList;
import java.util.Arrays;

public class IntcodeComputer {
    private ArrayList<Long> intcodeProgram;
    private int programCounter;
    private long relativeBase;
    private boolean haltFlag;

    Long input;
    private boolean needsInput;
    private Long outputValue;

    public IntcodeComputer(ArrayList<Long> intcodeProgram){
        this.intcodeProgram = copyIntcodeProgram(intcodeProgram);
        programCounter = 0;
        relativeBase = 0;
        haltFlag = false;

        Long input = null;
        needsInput = false;
        outputValue = null;
    }

    public ArrayList<Long> getIntcodeProgram(){
        return intcodeProgram;
    }

    private long readFromIntcodeProgram(int index){
        while(index + 1 > intcodeProgram.size()){
            intcodeProgram.add(0L);
        }
        return intcodeProgram.get(index);
    }

    private void writeToIntcodeProgram(int index, long value) {
        while(index + 1 > intcodeProgram.size()){
            intcodeProgram.add(0L);
        }
        intcodeProgram.set(index, value);
    }

    private static ArrayList<Long> copyIntcodeProgram(ArrayList<Long> intcodeProgram){
        ArrayList<Long> newIntcodeProgram = new ArrayList<>();

        for(long number : intcodeProgram){
            newIntcodeProgram.add(number);
        }

        return newIntcodeProgram;
    }

    public IntcodeReport processStep(Long input){
        this.input = input;
        needsInput = false;
        outputValue = null;

//        System.out.println("Program Counter: " + programCounter);
//        System.out.println("Relative Base: " + relativeBase);
//        System.out.println(intcodeProgram);
        if(programCounter > intcodeProgram.size()){
            throw new RuntimeException("Program counter: " + programCounter + " exceeds intcode program's size: " + intcodeProgram.size());
        }

        performOpCode();

        return new IntcodeReport(needsInput, outputValue, haltFlag);
    }

    public static int getOpCode(int value){
        String stringValue = String.valueOf(value);
        int substringIndex = stringValue.length() > 2 ? stringValue.length() - 2 : 0;
        String stringOpCode = stringValue.substring(substringIndex);
        return Integer.parseInt(stringOpCode);
    }

    public static String getParameterModes(int value){
        String stringValue = String.valueOf(value);
        if(stringValue.length() > 2){
            String parameter = stringValue.substring(0,stringValue.length()-2);
            while(parameter.length() < 3){
                parameter = "0" + parameter;
            }
            return parameter;
        }
        else{
            return "000";
        }
    }

    private long getOperand(char parameterMode, long parameter){
        if(parameterMode == '0'){ //position mode (address mode)
            return readFromIntcodeProgram((int) parameter); //ArrayList capacity is only size int
        } else if(parameterMode == '1'){ //immediate mode
            return parameter;
        } else if(parameterMode == '2'){ //relative mode
            return readFromIntcodeProgram((int) (parameter + relativeBase));
        } else {
            throw new RuntimeException("Parameter Mode not recognized: " + parameterMode);
        }
    }

    private int getDestination(char parameterMode, long parameter){
        if(parameterMode == '0' || parameterMode == '1'){ //position mode (address mode)
            return (int) parameter;
        } else if(parameterMode == '2'){ //relative mode
            return (int) (parameter + relativeBase);
        } else {
            throw new RuntimeException("Parameter Mode not recognized: " + parameterMode);
        }
    }

    public void performOpCode(){
        switch(getOpCode((int) readFromIntcodeProgram(programCounter))){
            case 1:
                processOpCodeAdd();
                break;
            case 2:
                processOpCodeMultiply();
                break;
            case 3:
                processOpCodeInput();
                break;
            case 4:
                processOpCodeOutput();
                break;
            case 5:
                processOpCodeJumpIfTrue();
                break;
            case 6:
                processOpCodeJumpIfFalse();
                break;
            case 7:
                processOpCodeLessThan();
                break;
            case 8:
                processOpCodeEquals();
                break;
            case 9:
                processOpCodeAdjustRelativeBase();
                break;
            case 99:
                processOpCodeHalt();
                break;
            default:
                throw new RuntimeException("Unsupported opcode encountered: " + readFromIntcodeProgram(programCounter) + " Terminating program.");
        }
    }

    private void processOpCodeAdd(){ //OpCode 1
        String parameterModes = getParameterModes((int) readFromIntcodeProgram(programCounter));
        long operandA = getOperand(parameterModes.charAt(2),readFromIntcodeProgram(programCounter + 1));
        long operandB = getOperand(parameterModes.charAt(1),readFromIntcodeProgram(programCounter + 2));
        int destination = getDestination(parameterModes.charAt(0),readFromIntcodeProgram(programCounter + 3));
//        System.out.println("Add: " + operandA + " and " + operandB + " to " + destination);

        writeToIntcodeProgram(destination, operandA + operandB);
        programCounter += 4;
    }

    private void processOpCodeMultiply(){ //OpCode 2
        String parameterModes = getParameterModes((int) readFromIntcodeProgram(programCounter));
        long operandA = getOperand(parameterModes.charAt(2),readFromIntcodeProgram(programCounter + 1));
        long operandB = getOperand(parameterModes.charAt(1),readFromIntcodeProgram(programCounter + 2));
        int destination = getDestination(parameterModes.charAt(0),readFromIntcodeProgram(programCounter + 3));
//        System.out.println("Multiply: " + operandA + " and " + operandB + " to " + destination);

        writeToIntcodeProgram(destination, operandA * operandB);
        programCounter += 4;
    }

    private void processOpCodeInput(){ //OpCode 3
        String parameterModes = getParameterModes((int) readFromIntcodeProgram(programCounter));
        int destination = getDestination(parameterModes.charAt(2),readFromIntcodeProgram(programCounter + 1));
//        System.out.println("Input " + input + " to " + destination);

        if(input != null){
            writeToIntcodeProgram(destination, input);
            programCounter += 2;
        }
        else{
            needsInput = true;
        }
    }

    private void processOpCodeOutput(){ //OpCode 4
        String parameterModes = getParameterModes((int) readFromIntcodeProgram(programCounter));
        long output = getOperand(parameterModes.charAt(2),readFromIntcodeProgram(programCounter + 1));
//        System.out.println("Output: " + output);

        outputValue = output;
        programCounter += 2;
    }

    private void processOpCodeJumpIfTrue(){ //OpCode 5
        String parameterModes = getParameterModes((int) readFromIntcodeProgram(programCounter));
        long operandA = getOperand(parameterModes.charAt(2),readFromIntcodeProgram(programCounter + 1));
        long operandB = getOperand(parameterModes.charAt(1),readFromIntcodeProgram(programCounter + 2));
//        System.out.println("JumpIfTrue: " + operandA != 0 + " to location: " + operandB);

        programCounter = (operandA != 0) ? (int) operandB : programCounter+3; //(int) because programCounter cannot go above int
    }

    private void processOpCodeJumpIfFalse(){ //OpCode 6
        String parameterModes = getParameterModes((int) readFromIntcodeProgram(programCounter));
        long operandA = getOperand(parameterModes.charAt(2),readFromIntcodeProgram(programCounter + 1));
        long operandB = getOperand(parameterModes.charAt(1),readFromIntcodeProgram(programCounter + 2));
//        System.out.println("JumpIfFalse: " + operandA != 0 + " to location: " + operandB);

        programCounter = (operandA == 0) ? (int) operandB : programCounter+3; //(int) because programCounter cannot go above int
    }

    private void processOpCodeLessThan(){ //OpCode 7
        String parameterModes = getParameterModes((int) readFromIntcodeProgram(programCounter));
        long operandA = getOperand(parameterModes.charAt(2),readFromIntcodeProgram(programCounter + 1));
        long operandB = getOperand(parameterModes.charAt(1),readFromIntcodeProgram(programCounter + 2));
        int destination = getDestination(parameterModes.charAt(0),readFromIntcodeProgram(programCounter + 3));
//        System.out.println("WriteIfLessThan: " + (operandA < operandB) + " to location: " + destination);

        writeToIntcodeProgram(destination, (operandA < operandB) ? (long) 1 : 0);
        programCounter += 4;
    }

    private void processOpCodeEquals(){ //OpCode 8
        String parameterModes = getParameterModes((int) readFromIntcodeProgram(programCounter));
        long operandA = getOperand(parameterModes.charAt(2),readFromIntcodeProgram(programCounter + 1));
        long operandB = getOperand(parameterModes.charAt(1),readFromIntcodeProgram(programCounter + 2));
        int destination = getDestination(parameterModes.charAt(0),readFromIntcodeProgram(programCounter + 3));
//        System.out.println("WriteIfEqual: " + (operandA == operandB) + " to location: " + destination);

        writeToIntcodeProgram(destination, (operandA == operandB) ? (long) 1 : 0);
        programCounter += 4;
    }

    private void processOpCodeAdjustRelativeBase(){ //OpCode 9
        String parameterModes = getParameterModes((int) readFromIntcodeProgram(programCounter));
        long operandA = getOperand(parameterModes.charAt(2),readFromIntcodeProgram(programCounter + 1));
//        System.out.println("Relative base increased by: " + operandA);

        relativeBase += operandA;
        programCounter += 2;
    }

    private void processOpCodeHalt(){
//        System.out.println("OpCode 99: Halt Flag Enabled");
        haltFlag = true;
    }


}
