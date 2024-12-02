package Y2021.D2;

public class P2 {
    public static void main(String[] args) {
        Logic logic = new Logic("src/main/java/Y2021/D2/pInput.txt", "P2");
        logic.executeFileInstructions();
        System.out.println(logic.calculatePuzzleOutput());
    }
}
