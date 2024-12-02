package Y2021.D4;

import java.util.ArrayList;

public class BingoBoard {
    private ArrayList<ArrayList<Integer>> bingoBoard;

    public ArrayList<ArrayList<Integer>> getBingoBoard() {
        return bingoBoard;
    }
    private void setBingoBoard(ArrayList<ArrayList<Integer>> bingoBoard) {
        this.bingoBoard = bingoBoard;
    }

    public int rowSize(){
        return bingoBoard.get(0).size();
    }

    public int columnSize(){
        return bingoBoard.size();
    }

    public BingoBoard(ArrayList<String> bingoBoardStrings){
        ArrayList<ArrayList<Integer>> bingoBoard = new ArrayList<>();
        ArrayList<Integer> bingoBoardRowArray;
        for(String bingoBoardLine : bingoBoardStrings){
            bingoBoardRowArray = new ArrayList<>();
            String[] bingoBoardLineArray = bingoBoardLine.split(" ");
            for(String number : bingoBoardLineArray){
                if(number.isBlank()) continue;
                bingoBoardRowArray.add(Integer.parseInt(number));
            }
            bingoBoard.add(bingoBoardRowArray);
        }
        this.bingoBoard = bingoBoard;
    }

    public void display(){
        for(ArrayList<Integer> line : bingoBoard){
            System.out.println(line.toString());
        }
    }
}
