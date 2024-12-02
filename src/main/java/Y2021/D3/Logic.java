package Y2021.D3;

import CustomClasses.RyansFileClass;

import java.util.ArrayList;

public class Logic {
    ArrayList<String> fileArrayList;
    ArrayList<Integer> compiledBitArray;
    String gammaRate;
    String epsilonRate;

    public Logic(String fileName){
        this.fileArrayList = RyansFileClass.fileToStringArray(fileName);
        this.compiledBitArray = new ArrayList<>();

        int i = 0;
        while(i < fileArrayList.get(0).length()){
            compiledBitArray.add(0);
            i++;
        }
    }

    public void resetCompiledBitArray(){
        for(int i = 0; i < compiledBitArray.size(); i++){
            compiledBitArray.set(i,0);
        }
    }

    public void compileArray(ArrayList<String> array){
        for(String bitString : array){
            addBitString(bitString);
        }
    }

    public void calculateGammaAndEpsilonRates(){
        resetCompiledBitArray();
        compileArray(fileArrayList);
        calculateGammaRate();
        calculateEpsilonRate();
    }

    public ArrayList<String> filterArrayByCharIndex(ArrayList<String> array, int charIndex, char bitValue){
        ArrayList<String> filteredArray = new ArrayList<>();
        for(String bitString : array){
            if(bitString.charAt(charIndex) == bitValue){
                filteredArray.add(bitString);
            }
        }
        return filteredArray;
    }

    public int calculateOxygenRating(){
        ArrayList<String> possibleValues = fileArrayList;

        int charIndex = 0;
        while (possibleValues.size() != 1){
            resetCompiledBitArray();
            compileArray(possibleValues);
            char bitValue = compiledBitArray.get(charIndex) >= 0? '1' : '0';
            possibleValues = filterArrayByCharIndex(possibleValues, charIndex, bitValue);
            charIndex++;
        }

        return Integer.parseInt(possibleValues.get(0), 2);
    }

    public int calculateCO2Rating(){
        ArrayList<String> possibleValues = fileArrayList;

        int charIndex = 0;
        while (possibleValues.size() != 1){
            resetCompiledBitArray();
            compileArray(possibleValues);
            char bitValue = compiledBitArray.get(charIndex) < 0? '1' : '0';
            possibleValues = filterArrayByCharIndex(possibleValues, charIndex, bitValue);
            charIndex++;
        }

        return Integer.parseInt(possibleValues.get(0), 2);
    }

    private int calculateGammaRate(){
        resetCompiledBitArray();
        compileArray(fileArrayList);
        String gammaRate = "";
        for(int bitSum : compiledBitArray){
            if(bitSum > 0) gammaRate += "1";
            else if (bitSum < 0) gammaRate += "0";
            else if (bitSum == 0) throw new RuntimeException("Bit sum was zero. The world is falling apart!");
        }
        return Integer.parseInt(gammaRate, 2);
    }

    private int calculateEpsilonRate(){
        resetCompiledBitArray();
        compileArray(fileArrayList);
        String epsilonRate = "";
        for(int bitSum : compiledBitArray){
            if(bitSum > 0) epsilonRate += "0";
            else if (bitSum < 0) epsilonRate += "1";
            else if (bitSum == 0) throw new RuntimeException("Bit sum was zero. The world is falling apart!");
        }
        return Integer.parseInt(epsilonRate, 2);
    }

    public int calculateP1Solution(){
        return calculateGammaRate() * calculateEpsilonRate();
    }

    public int calculateP2Solution(){
        return calculateOxygenRating() * calculateCO2Rating();
    }

    private void addBitString(String bitString){
        for(int i = 0; i < bitString.length(); i++){
            if(bitString.charAt(i) == '1'){
                addOne(i);
            }
            else if (bitString.charAt(i) == '0'){
                addZero(i);
            }
            else{
                throw new RuntimeException("Bitstring char not recognized: " + bitString.charAt(i));
            }
        }
    }

    private void addOne(int columnIndex){
        compiledBitArray.set(columnIndex, compiledBitArray.get(columnIndex) + 1);
    }

    private void addZero(int columnIndex){
        compiledBitArray.set(columnIndex, compiledBitArray.get(columnIndex) - 1);
    }
}