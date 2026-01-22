package Classes;

import javafx.scene.Node;
import javafx.scene.control.Label;

import java.util.Random;

public class ShapeChallenge extends Challenge {
    private String[] symbols;
    private final Random rnd = new Random();

    public ShapeChallenge() {
        super();
    }

    /**
     * symbolsList مثال: "★,☆,@,#,$,%,&"
     */
    public ShapeChallenge(int id, String type, String symbolsList) {
        super(id, type, "Symbol Challenge");
        if (symbolsList == null || symbolsList.trim().isEmpty()) {
            this.symbols = new String[] {"★","☆","@","#","$","%","&"};
        } else {
            this.symbols = symbolsList.split(",");
            for (int i=0;i<symbols.length;i++) symbols[i] = symbols[i].trim();
        }
    }

    public String[] getSymbols() { return symbols; }
    public void setSymbols(String[] symbols) { this.symbols = symbols; }

    @Override
    public Node generateNode(double x, double y, Runnable onHit) {
        if (symbols == null || symbols.length == 0) symbols = new String[] {"★","☆","@","#","$","%","&"};
        String symbol = symbols[rnd.nextInt(symbols.length)];
        Label lbl = new Label(symbol);
        lbl.setStyle("-fx-font-size: 36px; -fx-font-weight: bold;");
        lbl.setLayoutX(x);
        lbl.setLayoutY(y);
        lbl.setOnMouseClicked(e -> onHit.run());
        return lbl;
    }

    @Override
    public String toString() {
        return "ShapeChallenge{" +
                "symbols=" + (symbols == null ? "" : String.join(",", symbols)) +
                "} " + super.toString();
    }
}
