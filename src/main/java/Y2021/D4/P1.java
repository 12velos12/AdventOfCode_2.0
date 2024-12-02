package Y2021.D4;

public class P1 {
    public static void main(String[] args) {
        Logic logic = new Logic("src/main/java/Y2021/D4/pInput.txt");
        int winningBoardIndex = logic.findWinningBingoBoard();
        System.out.println(logic.calculateBoardScore(winningBoardIndex));
    }
}
