package Classes;

import javafx.scene.Node;
import javafx.scene.control.Label;

import java.util.Random;

public class WordChallenge extends Challenge {
    private String[] words;
    private final Random rnd = new Random();

    public WordChallenge() {
        super();
    }

    /**
     * wordsList: كلمات مفصولة بفاصلة، مثال: "Java,Code,Play"
     */
    public WordChallenge(int id, String type, String wordsList) {
        super(id, type, "Word Challenge");
        if (wordsList == null || wordsList.trim().isEmpty()) {
            this.words = new String[] {"Java", "Code", "Play"};
        } else {
            this.words = wordsList.split(",");
            for (int i = 0; i < this.words.length; i++) this.words[i] = this.words[i].trim();
        }
    }

    public String[] getWords() { return words; }
    public void setWords(String[] words) { this.words = words; }

    @Override
    public Node generateNode(double x, double y, Runnable onHit) {
        if (words == null || words.length == 0) words = new String[] {"Java","Code","Play"};
        String word = words[rnd.nextInt(words.length)];
        Label lbl = new Label(word);
        lbl.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");
        lbl.setLayoutX(x);
        lbl.setLayoutY(y);
        lbl.setOnMouseClicked(e -> onHit.run());
        return lbl;
    }

    @Override
    public String toString() {
        return "WordChallenge{" +
                "words=" + (words == null ? "" : String.join(",", words)) +
                "} " + super.toString();
    }
}
