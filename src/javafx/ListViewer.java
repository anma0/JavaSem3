package javafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ListViewer extends Application {

    private ListView<Integer> listView;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Примеры коллекций и ListView");
        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        initData();

    }

    private void initData() {
        //ListView отображает данные, которые хранятся в списке.
        //Это может быть список любых объектов: String, File, Image, Student
        //В ListView описывается, как отображать отдельные объекты. Например, отобразить String - это просто написать текст. Отобразить Integer - превратить в String и написать. Отобразить Image - нарисовать изображение, можно дополнительно написать формат, размер изображения. Данные для ListView хранятся в структуре данных, похожей на List<>, но обычный лист был неудобен. Он не сообщает о  том, что в нём что-то изменилось. В JavaFX используем ObservableList, он генерирует событие на любое изменение своего содержимого. ListView подпишется на это событие, и после этого любое изменение содержимого списка будет сразу видно на экране.

        ObservableList<Integer> nums = FXCollections.observableArrayList(10, 20, 30);
        listView.setItems(nums);
        //дописываем число уже после того, как назначили список в ListView
        nums.add(42);

        nums = new ObservableListBase<Integer>() {
            @Override
            public Integer get(int index) {
                return index + 1;
            }

            @Override
            public int size() {
                return 1000;
            }
        };

        listView.setItems(nums);

    }

    private Parent initInterface() {
        listView = new ListView<>();
        //теперь настроим отображение, т.е. научим ListView показывать элементы как-нибудь нестандартно. Сейчас он вызывает toString для всех объектов и пишет полученный текст на экран
        listView.setCellFactory(
                //фабрике даётся ListView (у нас lv == listView)
                //а вернуть надо ячейку,т.е. такой элемент интерфейса, который отображает один элемент списка. ListCell устроен как Button и Label. У всех трёх элементов интерфейса есть св-во text и свойство image - изображение, которое показывается обычно слева от текста
                (lv)->new ListCell<Integer>() {
                    @Override
                    protected void updateItem(Integer item, boolean empty) {
                        //переопределяем метод, котрый настраивает отображение ячейки. В начале вызываем этот же метод базового класса

                        super.updateItem(item, empty);
                        //важно обеспечить правильное отображение ячейки, если она пустая, т.е. если на эту ячейку не хватило элементов
                        if (empty)
                            setText("");
                        else {
                            //что нужно отобразить хранится  впеременной item.
                            String i = Integer.toString(item);
                            String oddity = item % 2 == 0 ? "чет" : "нечет";
                            setText(i + " " + oddity);
                        }
                    }

                }
        );//установим "фабрику ячеек"

        return listView;
    }
}
