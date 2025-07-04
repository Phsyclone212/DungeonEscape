import java.util.Scanner;

public class Items {
    public Scanner in = new Scanner(System.in);

    public String name;
    public int strength;
    public String type;
    public int amount;
    public boolean stackable;
    public int value;

    public Items(String name, int strength, String type, int amount, boolean stackable, int value) {
        this.name = name;
        this.strength = strength;
        this.type = type;
        this.amount = amount;
        this.stackable = stackable;
        this.value = value;
    }

    //WEAPONS AND ARMOR
    public static Items rustySword = new Items("Rusty Sword", 2, "Weapon", 1, false, 1);
    public static Items crackedShield = new Items("Cracked Shield", 1, "Armor", 1, false, 1);
    public static Items ironSword = new Items("Iron Sword", 5, "Weapon", 1, false, 5);
    public static Items woodShield = new Items("Wooden Shield", 3, "Armor", 1, false, 3);
    public static Items ironLongsword = new Items("Iron Longsword", 7, "Weapon", 1, false, 10);

    //CURRENCY AND TREASURE
    public static Items coins = new Items("Coins", 0, "Currency", (int)(Math.random()*10)+1, true, 1); //value can't be 1 for a stack tho...
    public static Items sapphire = new Items("Sapphire", 0, "Currency", 1, false, 5); //singular ver for fun?
    public static Items key = new Items("Key", 0, "Currency", 1, false, 25); //for unlocking chests

    //CONSUMABLES
    public static Items potionHealth = new Items("Health Potion", 10, "Consumable", 1, true, 10);

    public String getName(Items item) {
        return item.name;
    }
}
