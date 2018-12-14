import java.util.Scanner;

public class FirstWeekTask {
    public static void main(String[] args){
        int x = readInt("Введите x");
        int y = readInt("Введите y");
        System.out.println("x + y = " + (x + y) + "\n");
    }

    private static int readInt(String x) {
        while (true) {
            System.out.println(x);
            try {
                Scanner s = new Scanner(System.in);
                return Integer.parseInt(s.nextLine());
            } catch (Exception e){
                System.out.println("Вы ввели не число! Попробуйте ещё раз.");
            }
        }
    }
}
