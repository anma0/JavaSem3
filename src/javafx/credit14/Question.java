package javafx.credit14;

public class Question {
    private int x;
    private int y;
    private int w;
    private int h;
    private String text;

    public Question(int x, int y, int w, int h, String qText){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.text = qText;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public String getText() {
        return text;
    }
}
