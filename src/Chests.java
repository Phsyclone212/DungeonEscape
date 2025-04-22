public class Chests {
    public String type;
    public int level;
    public int health;
    public int xp;
    public Items[] items;
    public Boolean isLocked;
    public String[] chestTypes = {"Wooden Chest", "Iron Chest", "Golden Chest"};

    public Chests(int level, int health, int xp, Items[] items, Boolean isLocked) {
        this.type = chestTypes[(int)(Math.random()*chestTypes.length)];
        this.level = level;
        this.health = health;
        this.xp = xp;
        this.items = items;
        this.isLocked = isLocked;
    }

    //add openChest, unlockChest, and lootChest methods. Add relevent calls to Player, GameMap, and main classes.
    public void generateLoot(Chests chest, Player player) {
        //generate loot based on chest type and level
        switch (chest.type) {
            case "Wooden Chest":
                chest.items = new Items[] {Items.woodShield, Items.coins, Items.potionHealth};
                break;
            case "Iron Chest":
                chest.items = new Items[] {Items.ironSword, Items.coins, Items.potionHealth};
                break;
            case "Golden Chest":
                chest.items = new Items[] {Items.sapphire, Items.coins, Items.potionHealth};
                break;
            default:
                System.out.println("Invalid chest type!");
                break;
        }
    }
}
