package Classes;

public class BluetoothPlayer extends Player {
    private String device_address;

    public BluetoothPlayer() {
        super();
    }

    public BluetoothPlayer(int player_id, String name, String device_address) {
        super(player_id, name);
        this.device_address = device_address;
    }

    public String getDevice_address() {
        return device_address;
    }

    public void setDevice_address(String device_address) {
        this.device_address = device_address;
    }

    @Override
    public String toString() {
        return "Bluetooth_player{" +
                "player_id=" + getPlayer_id() +
                ", name='" + getName() + '\'' +
                ", device_address='" + device_address + '\'' +
                '}';
    }
}
