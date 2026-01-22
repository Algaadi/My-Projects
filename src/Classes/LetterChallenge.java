package Classes;

import javafx.scene.Node;
import javafx.scene.control.Label;

import java.util.Random;

public class LetterChallenge extends Challenge {
    private String letters; // مثال: "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private final Random rnd = new Random();

    public LetterChallenge() {
        super();
    }

    public LetterChallenge(int id, String type, String letters) {
        super(id, type, "Letter Challenge");
        this.letters = letters;
    }

    public String getLetters() { return letters; }
    public void setLetters(String letters) { this.letters = letters; }

    @Override
    public Node generateNode(double x, double y, Runnable onHit) {
        if (letters == null || letters.isEmpty()) letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char ch = letters.charAt(rnd.nextInt(letters.length()));
        Label lbl = new Label(String.valueOf(ch));
        lbl.setStyle("-fx-font-size: 32px; -fx-font-weight: bold;");
        lbl.setLayoutX(x);
        lbl.setLayoutY(y);
        lbl.setOnMouseClicked(e -> onHit.run());
        return lbl;
    }

    @Override
    public String toString() {
        return "LetterChallenge{" +
                "letters='" + letters + '\'' +
                "} " + super.toString();
    }
}
