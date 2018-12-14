package javafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;

public class Gallery extends Application {
    private SplitPane root = new SplitPane();

    private ListView<File> images = new ListView<>();
    private ObservableList<File> listOfImages = FXCollections.observableArrayList();
    private DirectoryChooser directoryChooser = new DirectoryChooser();

    private TextField pathToDir = new TextField();
    private Button chooseButton = new Button("Выбрать");
    private ImageView fullImage = new ImageView();
    private Pane paneWithImage = new Pane(fullImage);


    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Просмотр изображений");
        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));

        //5. Сделайте так, что каталог можно выбирать. Используйте класс Directory Chooser. Это должен быть один DirectoryChooser на всю программу, созадйте его один раз при запуске программы, тогда он будет запоминать, какой каталог открывал в прошлый раз.
        configuringDirectoryChooser(directoryChooser);
        chooseButton.setOnAction(event -> {
            File dir = directoryChooser.showDialog(primaryStage);
            if (dir != null) {
                images.getItems().clear();
                pathToDir.setText(dir.getAbsolutePath());
                initInteraction(dir);
            } else {
                pathToDir.setText(null);
                System.out.println("ERROR");
            }
        });
        primaryStage.show();
    }

    private Parent initInterface() {
        HBox choosePath = new HBox(pathToDir, chooseButton);
        HBox.setHgrow(pathToDir, Priority.ALWAYS);

        VBox rightArea = new VBox(choosePath, paneWithImage);
        VBox.setVgrow(paneWithImage, Priority.ALWAYS);

        images.setPlaceholder(new Label("Нет изображений"));

        fullImage.fitWidthProperty().bind(paneWithImage.widthProperty());
        fullImage.fitHeightProperty().bind(paneWithImage.heightProperty());
        fullImage.setPreserveRatio(true);

        root.getItems().addAll(images, rightArea);
        return root;
    }

    private void initInteraction(File imagesDir) {
        //Для начала выберите какой-то фиксированный каталог с изображениями, напишите код, который находит все файлы в этом каталоге и добавляет их в список ObservableList. Считайте пока, что все файлы в этом каталоге являются изображениями. В следующих пунктах нужно будет обрабатывать ошибки, и тогда потребуется исключить из списка все файлы, которые не удалось загрузить как изображения.
        //File imagesDir = new File("./eXample");
        if (imagesDir.isDirectory()) {
            File[] imageFiles = imagesDir.listFiles();
            if (imageFiles != null)
                listOfImages.addAll(Arrays.asList(imageFiles));
        }
        images.setItems(listOfImages);


        //Сделайте так, что при выборе изображения в списке, оно бы отображалось в ImageView. Для того, чтобы иметь доступ к выбранным элементам списка, нужно обратиться к myListView.getSelectionModel(). В этой Модели есть информация о выбранном элементе (файле), его индексе (номеру в списке), при этом выбранный элемент и его индекс — это свойства, поэтому вы можете следить за их изменениями и, при необходимотсти, связывать их с другими свойствами.
        images.getSelectionModel().selectedItemProperty().addListener(
                prop -> {
                    File selectedImage = images.getSelectionModel().getSelectedItem();
                    String imageURL = selectedImage.toURI().toString();
                    Image img = new Image(imageURL);
                    fullImage.setImage(img);
                }
        );

        //Сделайте так, что ListView при отображении элемента показывает картинку и имя файла. Используйте CellFactory и метод setGraphics(new ImageView(...)) у вашего анонимного класса, наследника ListCell. А при загрузке картинки с диска, укажите, что вы хотите загрузить уменьшенную версию изображения, для этого можно при загрузке указать в качестве дополнительных аргументов размеры уменьшенной копии, например, 64 на 64.
        images.setCellFactory(
                (lv) -> new ListCell<File>() {
                    @Override
                    protected void updateItem(File item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty)
                            setText("");
                        else {
                            String imageURL = item.toURI().toString();
                            Image img = new Image(imageURL, 64, 64, true, true);
                            setGraphic(new ImageView(img));

                            String name = item.getName();
                            setText(name);
                        }
                    }
                }
        );

    }

    private void configuringDirectoryChooser(DirectoryChooser directoryChooser) {
        // Set title for DirectoryChooser
        directoryChooser.setTitle("Select Some Directories");
        // Set Initial Directory
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }

    public static void main(String[] args) {
        launch(args);
    }

}