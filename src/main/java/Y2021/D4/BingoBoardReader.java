package Y2021.D4;

import java.util.ArrayList;

public abstract class BingoBoardReader {
    public static ArrayList<Integer> readRow(BingoBoard bingoBoard, int rowIndex){
        ArrayList<ArrayList<Integer>> myBoard = bingoBoard.getBingoBoard();
        return myBoard.get(rowIndex);
    }

    public static ArrayList<Integer> readColumn(BingoBoard bingoBoard, int colIndex){
        ArrayList<ArrayList<Integer>> myBoard = bingoBoard.getBingoBoard();
        ArrayList<Integer> columnArray = new ArrayList<>();
        for(ArrayList<Integer> row : myBoard){
            columnArray.add(row.get(colIndex));
        }
        return columnArray;
    }

    public static ArrayList<Integer> readAllNumbers(BingoBoard bingoBoard){
        ArrayList<Integer> allNumbersArray = new ArrayList<>();
        for(ArrayList<Integer> row : bingoBoard.getBingoBoard()){
            for(int num : row){
                allNumbersArray.add(num);
            }
        }
        return allNumbersArray;
    }

    public static boolean hasWon(BingoBoard bingoBoard, ArrayList<Integer> bingoNumbers){
        return hasWinningRow(bingoBoard, bingoNumbers) || hasWinningColumn(bingoBoard, bingoNumbers);
    }

    private static boolean hasWinningRow(BingoBoard bingoBoard, ArrayList<Integer> bingoNumbers){
        for(int i = 0; i < bingoBoard.columnSize(); i++){
            if(bingoNumbers.containsAll(readRow(bingoBoard,i))) return true;
        }
        return false;
    }

    private static boolean hasWinningColumn(BingoBoard bingoBoard, ArrayList<Integer> bingoNumbers){
        for(int i = 0; i < bingoBoard.rowSize(); i++){
            if(bingoNumbers.containsAll(readColumn(bingoBoard,i))) return true;
        }
        return false;
    }

    public static boolean hasNumber(BingoBoard bingoBoard, int number){
        ArrayList<ArrayList<Integer>> myBoard = bingoBoard.getBingoBoard();
        for(ArrayList<Integer> row : myBoard){
            for(Integer rowNum : row){
                if(rowNum.equals(number)) return true;
            }
        }
        return false;
    }

    public static BingoCoord findNumber(BingoBoard bingoBoard, int number){
        ArrayList<ArrayList<Integer>> myBoard = bingoBoard.getBingoBoard();
        for(int rowIndex = 0; rowIndex < myBoard.size(); rowIndex++){
            for(int colIndex = 0; colIndex < myBoard.get(rowIndex).size(); colIndex++){
                if(myBoard.get(rowIndex).get(colIndex).equals(number)){
                    return new BingoCoord(rowIndex, colIndex);
                }
            }
        }
        return null;
    }
}
