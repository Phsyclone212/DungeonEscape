import java.util.Scanner;

public class Player {
    Scanner in = new Scanner(System.in);

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

    public void move(GameMap dungeon, int direction){ 
        String position = posX+", "+posY; //mutable string to display position

        switch (direction) {
            case 1:
                if(posY-1 >= 0){
                    posY -= 1; // move left
                    position = posX+", "+posY;
                } else {
                    System.out.println("Cannot move in that direction!");
                }
                break;
            case 2:
                if(posY+1 < dungeon.map[0].length){
                    posY += 1; // move right
                    position = posX+", "+posY;
                } else {
                    System.out.println("Cannot move in that direction!");
                }
                break;
            case 3:
                if(posX-1 >= 0){
                    posX -= 1; // move up
                    position = posX+", "+posY;
                } else {
                    System.out.println("Cannot move in that direction!");
                }
                break;
            case 4:
                if(posX+1 < dungeon.map.length){
                    posX += 1; // move down
                    position = posX+", "+posY;
                } else {
                    System.out.println("Cannot move in that direction!");
                }
                break;
            default:
                System.out.println("Invalid direction!");
                break;
        }
        System.out.println("Player moved to position: "+position);
        dungeon.exploreRoom(posX, posY);
        dungeon.PrintMap();
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
