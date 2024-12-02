package Y2021.D2;

import CustomClasses.RyansFileClass;

import java.util.ArrayList;

public class Logic {
    ArrayList<String> fileArray;
    Submarine submarine;

    public Logic(String fileName, String partNumber){
        this.fileArray = RyansFileClass.fileToStringArray(fileName);
        if(partNumber == "P1"){
            this.submarine = new P1Submarine();
        } else if (partNumber == "P2"){
            this.submarine = new P2Submarine();
        }
    }

    public void executeFileInstructions(){
        for(String instruction : fileArray){
            executeInstruction(instruction);
        }
    }

    public int calculatePuzzleOutput(){
        return submarine.getHorizontalPosition() * submarine.getDepth();
    }

    private void executeInstruction(String instruction){
        String instructionType = instruction.split(" ")[0];
        int instructionUnit = Integer.parseInt(instruction.split(" ")[1]);

        switch (instructionType){
            case "forward":
                moveSubForward(instructionUnit);
                break;
            case "up":
                moveSubUp(instructionUnit);
                break;
            case "down":
                moveSubDown(instructionUnit);
                break;
            default:
                System.out.println(String.format("Could not interpret Instruction Type: %s", instructionType));
        }
    }

    private void moveSubForward(int units){
        submarine.forward(units);
    }

    private void moveSubUp(int units){
        submarine.up(units);
    }

    private void moveSubDown(int units){
        submarine.down(units);
    }
}
