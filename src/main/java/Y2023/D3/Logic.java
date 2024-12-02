package Y2023.D3;

import CustomClasses.RyansFileClass;

import java.util.ArrayList;

public class Logic {
    ArrayList<String> engineSchematic;

    Logic(String filePath){
        this.engineSchematic = RyansFileClass.fileToStringArray(filePath);
    }

    private Boolean isSymbol(char character){
        return !Character.isDigit(character) && character != '.';
    }

    private Boolean isOutsideMap(Coordinate coordinate){
        return coordinate.getxCoord() < 0
                || coordinate.getyCoord() < 0
                || coordinate.getxCoord() >= engineSchematic.get(0).length()
                || coordinate.getyCoord() >= engineSchematic.size();
    }

    private ArrayList<Coordinate> getAllSymbolCoords(){
        ArrayList<Coordinate> symbolCoords = new ArrayList<>();
        for(int y = 0; y < engineSchematic.size(); y++){
            String line = engineSchematic.get(y);
            for(int x = 0; x < line.length(); x++){
                if (isSymbol(line.charAt(x))){
                    symbolCoords.add(new Coordinate(x,y));
                }
            }
        }
        return symbolCoords;
    }

    private ArrayList<Coordinate> getAdjacentCoords(Coordinate inputCoord){
        ArrayList<Coordinate> adjacentCoords = new ArrayList();
        adjacentCoords.add(new Coordinate(inputCoord.getxCoord() - 1, inputCoord.getyCoord()));
        adjacentCoords.add(new Coordinate(inputCoord.getxCoord() + 1, inputCoord.getyCoord()));
        adjacentCoords.add(new Coordinate(inputCoord.getxCoord(), inputCoord.getyCoord() - 1));
        adjacentCoords.add(new Coordinate(inputCoord.getxCoord(), inputCoord.getyCoord() + 1));
        adjacentCoords.add(new Coordinate(inputCoord.getxCoord() - 1, inputCoord.getyCoord() - 1));
        adjacentCoords.add(new Coordinate(inputCoord.getxCoord() - 1, inputCoord.getyCoord() + 1));
        adjacentCoords.add(new Coordinate(inputCoord.getxCoord() + 1, inputCoord.getyCoord() - 1));
        adjacentCoords.add(new Coordinate(inputCoord.getxCoord() + 1, inputCoord.getyCoord() + 1));
        ArrayList curatedAdjacentCoords = new ArrayList();
        for(Coordinate adjacentCoord : adjacentCoords){
            if (!isOutsideMap(adjacentCoord)){
                curatedAdjacentCoords.add(adjacentCoord);
            }
        }
        return curatedAdjacentCoords;
    }

    //TODO: Finish this method
    private ArrayList<PartNumber> getAllPartNumbers(){
        ArrayList<Coordinate> symbolCoords = getAllSymbolCoords();
        return null;
    }

    private int calculateSumOfPartsInEngineSchematic(){
        ArrayList<PartNumber> partNumbers= getAllPartNumbers();

        int partNumbersSum = 0;
        for (PartNumber partNumber : partNumbers){
            partNumbersSum += partNumber.getNumber();
        }

        return partNumbersSum;
    }

    public void runP1Program(){
        System.out.println(calculateSumOfPartsInEngineSchematic());
    }
}
