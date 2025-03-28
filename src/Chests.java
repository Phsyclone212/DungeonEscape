public class Chests {
    public String type;
    public int level;
    public int health;
    public int xp;
    public Items[] items;
    public Boolean isLocked;

    public Chests(String type, int level, int health, int xp, Items[] items, Boolean isLocked) {
        this.type = type;
        this.level = level;
        this.health = health;
        this.xp = xp;
        this.items = items;
        this.isLocked = isLocked;
    }

    //add openChest, unlockChest, and lootChest methods. Add relevent calls to Player, GameMap, and main classes.
}
