import java.util.Scanner;

public class Player {
    public int health;
    public int posX;
    public int posY;
    public String inventory[];

    public Player(int health, int posX, int posY, String[] inventory) {
        this.health = health;
        this.posX = posX;
        this.posY = posY;
        this.inventory = inventory;
    }

    public void takeDamage(int damage) {
        health -= damage;
        System.out.println("Player took " + damage + " damage!");
        if (health <= 0) {
            System.out.println("Player has died!");
        }
    }

    public void move(GameMap dungeon, int posX, int posY){ // FIX THIS -- needs to take in current pos, return direction to move
        Scanner in = new Scanner(System.in);
        String position = posX+", "+posY; //mutable string to display position
        System.out.println("Select the direction you want to move: ");
        int direction = in.nextInt();
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
