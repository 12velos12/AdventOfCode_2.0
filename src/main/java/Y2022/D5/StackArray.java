package Y2022.D5;

import java.util.ArrayList;
import java.util.Stack;

public class StackArray {
    private ArrayList<Stack<Character>> stackArray;

    public StackArray(ArrayList<String> puzzleInput){
        //Keeps only top part of puzzle input (Stuff that initializes Stacks)
        ArrayList<String> stackInitArray = new ArrayList<>();
        for(String row : puzzleInput){
            stackInitArray.add(row);
            if(row.contains(" 1")){
                break;
            }
        }

        //Creates ArrayList with stacks and gets amount of stacks for input
        int stackArraySize = stackInitArray.get(stackInitArray.size()-1).replace(" ","").length();
        stackInitArray.remove(stackInitArray.size() - 1);
        ArrayList<Stack<Character>> stackArray = new ArrayList<>();
        for (int i = 0; i < stackArraySize; i++){
            Stack stack = new Stack();
            stackArray.add(stack);
        }

        //Populates the stacks with starting values
        for(int i = stackInitArray.size() - 1; i >= 0; i--){
            char[] stackCrates = stripRawStackString(stackInitArray.get(i)).toCharArray();
            for(int j = 0; j < stackCrates.length; j++){
                if(stackCrates[j] == ' ') continue;
                stackArray.get(j).add(stackCrates[j]);
            }
        }

        this.stackArray = stackArray;
    }

    public int size(){
        return this.stackArray.size();
    }

    public char peekStack(int stackNumber){
        if(this.stackArray.get(stackNumber - 1).empty()){
            return ' ';
        }

        return this.stackArray.get(stackNumber - 1).peek();
    }

    public void moveMultipleElements(int originStackNumber, int destinationStackNumber, int amount){
        Stack<Character> intermediateStack = new Stack<>();

        for(int i = 0; i < amount; i++){
            intermediateStack.push(popStack(originStackNumber));
        }
        while(!intermediateStack.empty()){
            pushStack(destinationStackNumber,intermediateStack.pop());
        }
    }

    public void moveElement(int originStackNumber, int destinationStackNumber){
        char element = popStack(originStackNumber);
        pushStack(destinationStackNumber, element);
    }

    private char popStack(int stackNumber){
        return this.stackArray.get(stackNumber - 1).pop();
    }

    private void pushStack(int stackNumber, char element){
        this.stackArray.get(stackNumber - 1).push(element);
    }

    private String stripRawStackString(String rawStackString){
        if((rawStackString.length() - 3) % 4 != 0 ) {
            throw new RuntimeException("Error - stripRawStackString(String rawStackString, int stackArraySize) - length is weird: rawStackString: " + rawStackString);
        }

        String compressedStackString = "";
        compressedStackString += rawStackString.charAt(1);
        rawStackString = rawStackString.substring(3);

        while(rawStackString.length() >= 4){
            compressedStackString += rawStackString.charAt(2);
            rawStackString = rawStackString.substring(4);
        }

        return compressedStackString;
    }

    public void view(){
        System.out.println("-----------STACK VIEW-----------");
        for(int i = 0; i < this.stackArray.size(); i++){
            String stackView = "";
            stackView += i+1 + "  ";
            ArrayList<Character> stackContents = new ArrayList<Character>();
            while(!this.stackArray.get(i).empty()){
                stackContents.add(this.stackArray.get(i).pop());
            }

            String stackString = "";
            for(int j = stackContents.size() - 1; j >= 0; j--){
                stackString += stackContents.get(j);
                this.stackArray.get(i).push(stackContents.get(j));
            }
            stackView += stackString;
            System.out.println(stackView);
        }
        System.out.println("BOTTOM----------------------TOP");
    }
}
