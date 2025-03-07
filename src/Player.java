public class Player {
    public int health;
    public int position;
    public String inventory[];

    public Player(int health, int position, String[] inventory) {
        this.health = health;
        this.position = position;
        this.inventory = inventory;
    }

    public void takeDamage(int damage) {
        health -= damage;
        System.out.println("Player took " + damage + " damage!");
        if (health <= 0) {
            System.out.println("Player has died!");
        }
    }

    public void move(int destination){
        position = destination;
    }

    public void checkInventory(){
        System.out.println("Inventory:");
        for (String item : inventory) {
            if(item != null){
                System.out.println(item);
            }
        }
    }

    public void addItem(String item){
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null) {
                inventory[i] = item;
                break;
            }
        }
    }

    public void removeItem(String item){
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == item) {
                inventory[i] = null;
                break;
            }
        }
    }
}
