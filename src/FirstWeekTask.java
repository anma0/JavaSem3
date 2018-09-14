import java.util.Scanner;

public class FirstWeekTask {
    public static void main(String[] args) throws Exception {
        int x = readInt("Введите x");
        int y = readInt("Введите y");
        System.out.println("x + y = " + (x + y));
    }

    private static int readInt(String x) {
        while (true) {
            System.out.println(x);
            try {
                Scanner s = new Scanner(System.in);
                return Integer.parseInt(s.nextLine());
            } catch (Exception e) {
                System.out.println("Вы ввели не число! Попробуйте ещё раз.");
            }
        }
    }
    /*
    Дан список с именами файлов List<String>, необходимо их переименовать: добавить в начало номер списка.
    a.txt => 0a.txt
    b.txt => 1b.txt
    Подробная обработка всех исключений. Если файл не найден, если не удалось переименовать и т.д.
    */


}
