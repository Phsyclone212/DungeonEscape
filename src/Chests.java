public class Chests {
    public String type;
    public int level;
    public int health;
    public int xp;
    public Items item;
    public Boolean isLocked;
    public Boolean isMimic;
    public String[] chestTypes = {"Wooden Chest", "Iron Chest", "Golden Chest"};

    public Chests() {
        this.type = chestTypes[(int)(Math.random()*chestTypes.length)];
        this.level = (int)(Math.random()*5) + 1; // Random level between 1 and 5
        this.health = this.level*5; // more health than monsters.
        this.xp = this.health*10; // XP based on level
        this.isLocked = Math.random() < 0.5; // 50% chance of being locked
        this.isMimic = Math.random() < 0.1; // 10% chance of being a mimic
    }

    public void generateLoot(Chests chest) {
        //generate loot based on chest type and level
        if(chest.isMimic) {
            System.out.println("Oh no!! It's a mimic!");
            return; // Exit if it's a mimic
        }
        Items[] woodTier = new Items[] {Items.woodShield, Items.coins, Items.potionHealth};
        Items[] ironTier = new Items[] {Items.ironSword, Items.coins, Items.potionHealth};
        Items[] goldTier = new Items[] {Items.sapphire, Items.coins, Items.potionHealth};
        switch (chest.type) {
            case "Wooden Chest":
                chest.item = woodTier[(int)Math.random()*woodTier.length];
                break;
            case "Iron Chest":
                chest.item = ironTier[(int)Math.random()*ironTier.length];
                break;
            case "Golden Chest":
                chest.item = goldTier[(int)Math.random()*goldTier.length];
                break;
            default:
                System.out.println("Invalid chest type!");
                break;
        }
    }

}
