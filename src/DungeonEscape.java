//Main class
public class DungeonEscape {
    public static void main(String[] args) throws Exception {
        Boolean gameOver = false;

        Player player = new Player(100, 0, new String[20]);
        player.addItem("Sword");
        player.addItem("Shield");
        player.addItem("Potion");
        player.checkInventory();
        Room currentRoom = Room.generateRoom();
        currentRoom.getDescription();
    }
}
