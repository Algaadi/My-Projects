package Classes;

public class Symbol {
    private int symbol_id;
    private String symbol_type;
    private String symbol_value;
    private int points;

    public Symbol() {
    }

    public Symbol(int symbol_id, String symbol_type, String symbol_value, int points) {
        this.symbol_id = symbol_id;
        this.symbol_type = symbol_type;
        this.symbol_value = symbol_value;
        this.points = points;
    }

    public int getSymbol_id() {
        return symbol_id;
    }

    public void setSymbol_id(int symbol_id) {
        this.symbol_id = symbol_id;
    }

    public String getSymbol_type() {
        return symbol_type;
    }

    public void setSymbol_type(String symbol_type) {
        this.symbol_type = symbol_type;
    }

    public String getSymbol_value() {
        return symbol_value;
    }

    public void setSymbol_value(String symbol_value) {
        this.symbol_value = symbol_value;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "symbol_id=" + symbol_id +
                ", symbol_type='" + symbol_type + '\'' +
                ", symbol_value='" + symbol_value + '\'' +
                ", points=" + points +
                '}';
    }
}
