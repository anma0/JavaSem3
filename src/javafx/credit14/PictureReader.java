package javafx.credit14;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PictureReader extends Application {
    private Label question;
    private ImageView picture;
    private List<Question> listOfQuestions = new ArrayList<>();
    private int answer = 0;
    private int current = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Просмотр изображения");
        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));
        loadText();
        loadImage();
        initInteraction();

        primaryStage.show();

    }

    private Parent initInterface() {
        picture = new ImageView();
        question = new Label();
        question.setFont(new Font("Serif", 24));
        VBox box = new VBox(question, picture);
        picture.fitWidthProperty().bind(box.widthProperty());
        picture.fitHeightProperty().bind(box.heightProperty());
        picture.setPreserveRatio(true);
        return box;
    }

    private void loadText() {
        try (
                InputStream text = PictureReader
                        .class
                        .getResourceAsStream("QandA.txt")
        ) {
            Scanner scan = new Scanner(text);

            while (scan.hasNextLine()) {
                Question q = new Question(scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextLine());
                listOfQuestions.add(q);
            }

        } catch (IOException e) {
            question.setText("Error in loadText!");
        }

    }

    private void loadImage() {
        try (
                InputStream image = PictureReader
                        .class
                        .getResourceAsStream("order.jpg")

        ) {
            Image img = new Image(image);
            picture.setImage(img);

        } catch (IOException e) {
            System.out.println("Error in loadImage!");
        }
    }

    private void initInteraction() {
        question.setText(listOfQuestions.get(0).getText());
        picture.addEventHandler(
                MouseEvent.MOUSE_CLICKED,
                a -> {
                    Question q = listOfQuestions.get(current);
                    double xMouse = a.getX();
                    double yMouse = a.getY();
                    if (q.getX() < xMouse && q.getY() < yMouse && (q.getX() + q.getW()) > xMouse && (q.getY() + q.getH()) > yMouse)
                        answer++;
                    if (current < listOfQuestions.size() - 1) {
                        current++;
                    } else {
                        showAlertWithHeaderText();
                        current = 0;
                        answer = 0;
                    }
                    question.setText(listOfQuestions.get(current).getText());
                }
        );

    }

    private void showAlertWithHeaderText() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Результат: " + answer + " из " + listOfQuestions.size());
        if (answer > 2)
            alert.setContentText("Задание выполнено, молодец!");
        else
            alert.setContentText("Ты пытался...");

        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}