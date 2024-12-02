package Y2021.D5;

import CustomClasses.RyansFileClass;

import java.util.ArrayList;
import java.util.HashSet;

public class Logic {
    ArrayList<Line> ventLines = new ArrayList<>();
    HashSet ventCoords = new HashSet();
    HashSet overlappingVentCoords = new HashSet();

    public Logic(String filePath){
        ArrayList<Line> ventLines = new ArrayList<>();
        ArrayList<String> rawInput = RyansFileClass.fileToStringArray(filePath);
        for(String stringLine : rawInput){
            ventLines.add(getLine(stringLine));
        }

        this.ventLines = ventLines;
    }

    public Line getLine(String stringLine){
        String[] stringCoords = stringLine.split(" -> ");
        Coordinate coordOne = new Coordinate(Integer.parseInt(stringCoords[0].split(",")[0]),Integer.parseInt(stringCoords[0].split(",")[1]));
        Coordinate coordTwo = new Coordinate(Integer.parseInt(stringCoords[1].split(",")[0]),Integer.parseInt(stringCoords[1].split(",")[1]));

        return new Line(coordOne,coordTwo);
    }

    public void runP1Program(){
        recordOrthogonalVentCoords();
        System.out.println(overlappingVentCoords.size());
    }

    public void runP2Program(){
        recordAllVentCoords();
        System.out.println(overlappingVentCoords.size());
    }

    public void recordOrthogonalVentCoords(){
        for(Line line : ventLines){
            for(Coordinate coord : getOrthogonalLineCoords(line)){
                if(!ventCoords.contains(coord)){
                    ventCoords.add(coord);
                } else {
                    overlappingVentCoords.add(coord);
                }
            }
        }
    }

    public void recordAllVentCoords(){
        for(Line line : ventLines){
            for(Coordinate coord : getVentLineCoords(line)){
                if(!ventCoords.contains(coord)){
                    ventCoords.add(coord);
                } else {
                    overlappingVentCoords.add(coord);
                }
            }
        }
    }

    public ArrayList<Coordinate> getVentLineCoords(Line line){
        if(isOrthagonalLine(line)){
            return getOrthogonalLineCoords(line);
        } else {
            return getDiagonalLineCoords(line);
        }
    }

    public boolean isOrthagonalLine(Line line){
        Coordinate lineStart = line.getLineStart();
        Coordinate lineEnd = line.getLineEnd();
        return lineStart.getxCoord() == lineEnd.getxCoord() || lineStart.getyCoord() == lineEnd.getyCoord();
    }

    public ArrayList<Coordinate> getOrthogonalLineCoords(Line line){
        Coordinate lineStart = line.getLineStart();
        Coordinate lineEnd = line.getLineEnd();
        if(lineStart.getxCoord() != lineEnd.getxCoord() && lineStart.getyCoord() != lineEnd.getyCoord()) {
            return new ArrayList<>();
        }
        ArrayList<Coordinate> orthogonalLineCoords = new ArrayList<>();

        if(lineStart.getxCoord() == lineEnd.getxCoord()){
            ArrayList<Integer> closedIntegerInterval = getClosedIntegerInterval(lineStart.getyCoord(), lineEnd.getyCoord());
            for(int yCoord : closedIntegerInterval){
                orthogonalLineCoords.add(new Coordinate(lineStart.getxCoord(),yCoord));
            }
        } else {
            ArrayList<Integer> closedIntegerInterval = getClosedIntegerInterval(lineStart.getxCoord(), lineEnd.getxCoord());
            for(int xCoord : closedIntegerInterval){
                orthogonalLineCoords.add(new Coordinate(xCoord,lineStart.getyCoord()));
            }
        }

        return orthogonalLineCoords;
    }

    public ArrayList<Coordinate> getDiagonalLineCoords(Line line){
        Coordinate lineStart = line.getLineStart();
        Coordinate lineEnd = line.getLineEnd();
        ArrayList<Integer> xCoords = getClosedIntegerInterval(lineStart.getxCoord(),lineEnd.getxCoord());
        ArrayList<Integer> yCoords = getClosedIntegerInterval(lineStart.getyCoord(),lineEnd.getyCoord());
        ArrayList<Coordinate> diagonalLineCoords = new ArrayList<>();

        for(int i = 0; i < xCoords.size(); i++){
            diagonalLineCoords.add(new Coordinate(xCoords.get(i),yCoords.get(i)));
        }

        return diagonalLineCoords;
    }

    public ArrayList<Integer> getClosedIntegerInterval(int startingNum, int endingNum){
        ArrayList<Integer> closedIntegerInterval = new ArrayList<>();
        closedIntegerInterval.add(startingNum);
        if(startingNum < endingNum){
            while(startingNum < endingNum){
                startingNum++;
                closedIntegerInterval.add(startingNum);
            }
            return closedIntegerInterval;
        } else {
            while(startingNum > endingNum){
                startingNum--;
                closedIntegerInterval.add(startingNum);
            }
            return closedIntegerInterval;
        }
    }
}
