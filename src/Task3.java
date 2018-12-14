import java.io.File;
import java.nio.file.Files;

public class Task3 {
    public static void main(String[] args) {
        File myFolder = new File("../task 3");
        rename(myFolder);
    }

    private static void rename(File f){
        for(File item : f.listFiles())
            if(item.isFile()) {
                //Files.move();
                System.out.println(item.getName());
            }
    }
    /*
    Дан список имен файлов. Предполагается, что эти файлы лежат в одном заранее выбранном вами каталоге. Нужно переименовать каждый файл так, чтобы в начало к нему был приписан номер файла в списке. Например, если был список a.txt, b.txt, то нунжо переименовать файлы в 1 a.txt и 2 b.txt. Для каждого файла выведите, удалось ли его переименовать, а если нет, то что именно пошло не так. Проследите, чтобы не выводилось лишней информации об ошибках наподобие содержимого стека. Используйте метод Files.move().
    */
}
