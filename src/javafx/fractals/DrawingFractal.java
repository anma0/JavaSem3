package javafx.fractals;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DrawingFractal extends Application {
    private int WIDTH = 400;
    private int HEIGHT = 400;

    private double x0 = -2;
    private double y0 = 2;
    private  double dx = 0.01;

    private ImageView imgView = new ImageView();
    private WritableImage wImage = new WritableImage(WIDTH, HEIGHT);
    private PixelWriter pWriter = wImage.getPixelWriter();

    private Fractal fractal = new Circle();
    private Palette palette = new BlackWhitePalette();


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Фракталы");

        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));

        initInteraction();
        primaryStage.show();
    }

    private Parent initInterface() {
        imgView.setImage(wImage);
        return new HBox(imgView);
    };

    private void  initInteraction() {
        /*как рисовать фрактал
    перебрать все пиксели
    for x' от 0 до ширина - 1
        for y' от 0 до длина - 1
            x = x0 + x' * dx
            y = y0 + y' * dx
            colorInd = fractal.getColor (x, y);
            color = palette.getColor(colorInd);
            pw.write(x', y', color)

     */
        for (int xl = 0; xl < WIDTH - 1; xl++){
            for (int yl = 0; yl < HEIGHT - 1; yl++) {
                double x = x0 + xl * dx;
                double y = y0 - yl * dx;
                double colorInd = fractal.getColor(x, y);
                Color color = palette.getColor(colorInd);
                pWriter.setColor(xl, yl, color);
            }
        }
    };
}
