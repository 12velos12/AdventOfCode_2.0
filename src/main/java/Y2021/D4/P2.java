package Y2021.D4;

public class P2 {
    public static void main(String[] args) {
        Logic logic = new Logic("src/main/java/Y2021/D4/pInput.txt");
        int lastWinningBoardIndex = logic.findLastWinningBingoBoard();
        System.out.println(logic.calculateBoardScore(lastWinningBoardIndex));
    }
}
