package Classes;

public class LocalPlayer extends Player {
    private String screen_section;

    public LocalPlayer() {
        super();
    }

    public LocalPlayer(int player_id, String name, String screen_section) {
        super(player_id, name);
        this.screen_section = screen_section;
    }

    public String getScreen_section() {
        return screen_section;
    }

    public void setScreen_section(String screen_section) {
        this.screen_section = screen_section;
    }

    @Override
    public String toString() {
        return "local_player{" +
                "player_id=" + getPlayer_id() +
                ", name='" + getName() + '\'' +
                ", screen_section='" + screen_section + '\'' +
                '}';
    }
}
