public class Chests {
    public String type;
    public int level;
    public int health;
    public int xp;
    public Items items;
    public Boolean isLocked;
    public String[] chestTypes = {"Wooden Chest", "Iron Chest", "Golden Chest"};

    public Chests(int level, int health, int xp, Boolean isLocked) {
        this.type = chestTypes[(int)(Math.random()*chestTypes.length)];
        this.level = level;
        this.health = health;
        this.xp = xp;
        // this.items = items;
        this.isLocked = isLocked;
    }

    //add openChest, unlockChest, and lootChest methods. Add relevent calls to Player, GameMap, and main classes.
    public void generateLoot(Chests chest, Player player) {
        //generate loot based on chest type and level
        Items[] woodTier = new Items[] {Items.woodShield, Items.coins, Items.potionHealth};
        Items[] ironTier = new Items[] {Items.ironSword, Items.coins, Items.potionHealth};
        Items[] goldTier = new Items[] {Items.sapphire, Items.coins, Items.potionHealth};
        switch (chest.type) {
            case "Wooden Chest":
                chest.items = woodTier[(int)Math.random()*woodTier.length];
                break;
            case "Iron Chest":
                chest.items = ironTier[(int)Math.random()*ironTier.length];
                break;
            case "Golden Chest":
                chest.items = goldTier[(int)Math.random()*goldTier.length];
                break;
            default:
                System.out.println("Invalid chest type!");
                break;
        }
    }
}
