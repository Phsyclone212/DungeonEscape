public class Items {
    public String name;
    public int strength;
    public String type;
    public int amount;
    public boolean stackable;

    public Items(String name, int strength, String type, int amount, boolean stackable) {
        this.name = name;
        this.strength = strength;
        this.type = type;
        this.amount = amount;
        this.stackable = stackable;
    }

    public static Items sword = new Items("Sword", 5, "Weapon", 1, false);
    public static Items shield = new Items("Shield", 3, "Armor", 1, false);
    public static Items coins = new Items("Coins", 0, "Currency", (int)(Math.random()*10)+1, true);
    public static Items potionHealth = new Items("Health Potion", 10, "Consumable", 1, true);

    public void getInfo(Items item){
        System.out.println("Item: "+name+" Type: "+type);
        if(type.equals("Weapon")){
            System.out.println("Attack bonus: "+strength);
        }
        if(stackable){
            System.out.println("Stackable");
        } else {
            System.out.println("Non-Stackable");
        }
    }


    /*Not too sure if this is how I want it to be structured... Might be better to build smaller constructors for types? weapons/armor/etc. */
}
