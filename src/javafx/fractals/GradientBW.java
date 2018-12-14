package javafx.fractals;

import javafx.scene.paint.Color;


public class GradientBW implements Palette {
    @Override
    public Color getColor(double ind) {
        return Color.gray(1 - Math.min(10 * ind, 1));
    }
}

