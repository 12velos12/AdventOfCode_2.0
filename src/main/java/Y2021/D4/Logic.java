package Y2021.D4;

import CustomClasses.RyansFileClass;

import java.util.ArrayList;

public class Logic {
    public ArrayList<Integer> bingoNumbers;
    public ArrayList<BingoBoard> bingoBoards;

    public Logic(String filePath){
        ArrayList<String> rawInput = RyansFileClass.fileToStringArray(filePath);
        configInput(rawInput);
    }

    private void configInput(ArrayList<String> rawInput){
        initBingoNumbers(rawInput.get(0));
        initBingoBoards(rawInput);
    }

    private void initBingoNumbers(String nums){
        ArrayList<Integer> bingoNumbers = new ArrayList<>();
        String[] numArray = nums.split(",");
        for(String num : numArray){
            bingoNumbers.add(Integer.parseInt(num));
        }
        this.bingoNumbers = bingoNumbers;
    }

    private void initBingoBoards(ArrayList<String> rawInput){
        ArrayList<BingoBoard> bingoBoards = new ArrayList<>();
        ArrayList<String> bingoBoardStrings = new ArrayList<>();
        for(int i = 2; i < rawInput.size(); i++){
            if(rawInput.get(i).isBlank()){
                BingoBoard bingoBoard = new BingoBoard(bingoBoardStrings);
                bingoBoards.add(bingoBoard);
                bingoBoardStrings = new ArrayList<>();
            }
            else{
                bingoBoardStrings.add(rawInput.get(i));
            }
        }
        BingoBoard bingoBoard = new BingoBoard(bingoBoardStrings);
        bingoBoards.add(bingoBoard);

        this.bingoBoards = bingoBoards;
    }

    public int findWinningBingoBoard(){
        ArrayList<Integer> calledBingoNumbers = new ArrayList<>();
        for(int calledNumber : bingoNumbers){
            calledBingoNumbers.add(calledNumber);
            for(int i = 0; i < bingoBoards.size(); i++){
                if(BingoBoardReader.hasWon(bingoBoards.get(i), calledBingoNumbers)){
                    return i;
                }
            }
        }

        throw new RuntimeException("Could not determine a winning bingo board.");
    }

    public int findLastWinningBingoBoard(){
        ArrayList<Integer> calledBingoNumbers = new ArrayList<>();
        ArrayList<Integer> remainingBingoBoards = new ArrayList<>();
        for(int i = 0; i < bingoBoards.size(); i++){
            remainingBingoBoards.add(i);
        }
        int calledNumberIndex = -1;
        while(remainingBingoBoards.size() > 1){
            calledNumberIndex++;
            calledBingoNumbers.add(bingoNumbers.get(calledNumberIndex));
            for(int i = 0; i < remainingBingoBoards.size(); i++){
                if(BingoBoardReader.hasWon(bingoBoards.get(remainingBingoBoards.get(i)), calledBingoNumbers)){
                    remainingBingoBoards.remove(i);
                }
            }
        }


        return remainingBingoBoards.get(0);
    }

    public int calculateBoardScore(int boardIndex){
        ArrayList<Integer> calledBingoNumbers = new ArrayList<>();
        for(int calledNumber : bingoNumbers){
            calledBingoNumbers.add(calledNumber);
            if(BingoBoardReader.hasWon(bingoBoards.get(boardIndex), calledBingoNumbers)){
                ArrayList<Integer> boardNumbers = BingoBoardReader.readAllNumbers(bingoBoards.get(boardIndex));
                int score = 0;
                boardNumbers.removeAll(calledBingoNumbers);
                for(int num : boardNumbers){
                    score += num;
                }
                return score * calledNumber;
            }
        }

        throw new RuntimeException("Board is not a winning board.");
    }

    public void displayAllBoards(){
        for(BingoBoard bingoBoard : bingoBoards){
            bingoBoard.display();
            System.out.println();
        }
    }

    public void testReader(){
        BingoBoard bingoBoard = bingoBoards.get(0);
        System.out.println("Row: " + BingoBoardReader.readRow(bingoBoard,0).toString());
        System.out.println("Col: " + BingoBoardReader.readColumn(bingoBoard,0).toString());
        System.out.println("Has 25: " + BingoBoardReader.hasNumber(bingoBoard, 25));
        System.out.println("Has 1: " + BingoBoardReader.hasNumber(bingoBoard, 1));
        System.out.println("Find 1: " + BingoBoardReader.findNumber(bingoBoard, 1).toString());
    }

}
