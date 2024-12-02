package Y2023.D1;

import CustomClasses.RyansFileClass;

import java.util.ArrayList;

public class Logic {
    ArrayList<String> rawInput;
    ArrayList<String> numbersAsStrings;

    public Logic(String filePath){
        this.rawInput = RyansFileClass.fileToStringArray(filePath);
        this.numbersAsStrings = new ArrayList<>();
        numbersAsStrings.add("one");
        numbersAsStrings.add("two");
        numbersAsStrings.add("three");
        numbersAsStrings.add("four");
        numbersAsStrings.add("five");
        numbersAsStrings.add("six");
        numbersAsStrings.add("seven");
        numbersAsStrings.add("eight");
        numbersAsStrings.add("nine");
    }

    private Character[] getFirstAndLastDigit(String line){
        Character[] firstAndLastDigit = new Character[2];
        firstAndLastDigit[0] = 'z';

        for(Character character : line.toCharArray()){
            if(Character.isDigit(character)){
                if (firstAndLastDigit[0] == 'z'){
                    firstAndLastDigit[0] = character;
                }
                firstAndLastDigit[1] = character;
            }
        }

        return firstAndLastDigit;
    }

    private Character[] getFirstAndLastDigitWithWords(String line){
        return new Character[]{
                getFirstDigitWithWords(line),
                getLastDigitWithWords(line)
        };
    }

    private Character getFirstDigitWithWords(String line){
        String charactersSeen = "";

        for(Character character : line.toCharArray()){
            if (Character.isDigit(character)){
                return character;
            }
            else {
                charactersSeen += character;
                for(String stringNum : numbersAsStrings){
                    if(charactersSeen.contains(stringNum)){
                        return stringNumberToChar(stringNum);
                    }
                }
            }
        }

        throw new RuntimeException("First digit could not be found in String: " + line);
    }

    private Character getLastDigitWithWords(String line){
        String charactersSeen = "";

        for(int i = line.length() - 1; i >= 0; i--){ //TODO: this is prob broken
            Character character = line.charAt(i);
            if (Character.isDigit(character)){
                return character;
            }
            else {
                charactersSeen = character + charactersSeen;
                for(String stringNum : numbersAsStrings){
                    if(charactersSeen.contains(stringNum)){
                        return stringNumberToChar(stringNum);
                    }
                }
            }
        }

        throw new RuntimeException("Last digit could not be found in String: " + line);
    }

    private Character stringNumberToChar(String stringNum){
        switch (stringNum){
            case "one":
                return '1';
            case "two":
                return '2';
            case "three":
                return '3';
            case "four":
                return '4';
            case "five":
                return '5';
            case "six":
                return '6';
            case "seven":
                return '7';
            case "eight":
                return '8';
            case "nine":
                return '9';
            default:
                throw new RuntimeException("StringNum not recognized.");
        }
    }

    private ArrayList<Integer> getFirstAndLastNumberArray(int programNumber){
        ArrayList<Integer> firstAndLastNumberArray = new ArrayList<>();

        for(String line : rawInput){
            Character[] lineFirstAndLastDigit;
            if(programNumber == 1){
                lineFirstAndLastDigit = getFirstAndLastDigit(line);
            } else {
                lineFirstAndLastDigit = getFirstAndLastDigitWithWords(line);
            }
            firstAndLastNumberArray.add(Integer.parseInt(lineFirstAndLastDigit[0] + "" + lineFirstAndLastDigit[1]));
        }

        return firstAndLastNumberArray;
    }

    private Integer sumArray(ArrayList<Integer> numArray){
        int sum = 0;
        for (int number : numArray){
            sum += number;
        }

        return sum;
    }

    public void runP1Program(){
        System.out.println(sumArray(getFirstAndLastNumberArray(1)));
    }

    public void runP2Program(){
        System.out.println(sumArray(getFirstAndLastNumberArray(2)));
    }
}
