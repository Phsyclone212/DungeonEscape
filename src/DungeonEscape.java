//Main class
public class DungeonEscape {
    public static void main(String[] args) throws Exception {
        Boolean gameOver = false;

        Player player = new Player(100, 0, new String[20]);
        player.addItem("Sword");
        player.addItem("Shield");
        player.addItem("Potion");
        player.checkInventory();
        GameMap dungeon = new GameMap(5,5);
        dungeon.PrintMap();

    }
}
