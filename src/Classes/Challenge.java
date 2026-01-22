package Classes;

import javafx.scene.Node;

public abstract class Challenge {
    private int id;
    private String type;
    private String description;

    public Challenge() {}

    public Challenge(int id, String type, String description) {
        this.id = id;
        this.type = type;
        this.description = description;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    /**
     * كل كائن Challenge يولّد Node خاص به عند الإظهار.
     * onHit: Runnable يُنفَّذ عند نقر العنصر (player hit).
     */
    public abstract Node generateNode(double x, double y, Runnable onHit);

    @Override
    public String toString() {
        return "Challenge{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
