package Y2022.D5;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Logic {

    public static String go(ArrayList<String> puzzleInput, int problemPartNumber){
        StackArray stackArray = new StackArray(puzzleInput);
        stackArray.view();
        processCommands(parseCommands(puzzleInput), stackArray, problemPartNumber);
        return getTopOfEachStack(stackArray);
    }

    public static ArrayList<int[]> parseCommands(ArrayList<String> puzzleInput){
        ArrayList<String> rawCommands = removeInitSection(puzzleInput);
        return condenseCommands(rawCommands);
    }

    public static ArrayList<int[]> condenseCommands(ArrayList<String> rawCommands){
        for(int i = 0; i < rawCommands.size(); i++){
            rawCommands.set(i,rawCommands.get(i).replace("move ","").replace(" from","").replace("to ",""));
        }

        ArrayList<int[]> condensedCommands = new ArrayList<>();
        for(String rawCommand : rawCommands){
            String[] stringCommands = rawCommand.split(" ");
            int[] intCommands = new int[3];
            intCommands[0] = Integer.parseInt(stringCommands[0]);
            intCommands[1] = Integer.parseInt(stringCommands[1]);
            intCommands[2] = Integer.parseInt(stringCommands[2]);
            condensedCommands.add(intCommands);
        }

        return condensedCommands;
    }

    public static ArrayList<String> removeInitSection(ArrayList<String> puzzleInput){
        ArrayList<String> commandSection = new ArrayList<>();

        int i;
        for(i = 0 ; i < puzzleInput.size(); i++){
            if(puzzleInput.get(i).contains(" 1")){
                i += 2;
                break;
            }
        }

        while(i < puzzleInput.size()){
            commandSection.add(puzzleInput.get(i));
            i++;
        }

        return commandSection;
    }

    public static void processCommands(ArrayList<int[]> condensedCommands, StackArray stackArray, int problemPartNumber){
        for(int[] command : condensedCommands){
//            stackArray.view();
            processMoveCommand(command, stackArray, problemPartNumber);
        }
        stackArray.view();
    }

    public static void processMoveCommand(int[] condensedCommand, StackArray stackArray, int problemPartNumber){
        if(problemPartNumber == 1){
            for(int i = 0; i < condensedCommand[0]; i++){
                stackArray.moveElement(condensedCommand[1],condensedCommand[2]);
            }
        }
        else if(problemPartNumber == 2){
            stackArray.moveMultipleElements(condensedCommand[1],condensedCommand[2],condensedCommand[0]);
        }
    }

    public static String getTopOfEachStack(StackArray stackArray){
        int size = stackArray.size();

        String topCrates = "";
        for(int i = 0; i < size; i++){
            topCrates += stackArray.peekStack(i + 1);
        }

        return topCrates;
    }
}
