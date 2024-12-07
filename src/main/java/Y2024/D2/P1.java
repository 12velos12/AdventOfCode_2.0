package Y2024.D2;

import Y2024.D2.Logic;

public class P1 {
    public static void main(String[] args) {
        Logic logic = new Logic("src/main/java/Y2024/D2/pInput.txt");
        System.out.println(logic.runP1Program());
    }
}
