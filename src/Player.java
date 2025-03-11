import java.util.Scanner;

public class Player {
    Scanner in = new Scanner(System.in);

    public int health;
    public int posX;
    public int posY;
    public Items inventory[];

    public Player(int health, int posX, int posY, Items[] inventory) {
        this.health = health;
        this.posX = posX;
        this.posY = posY;
        this.inventory = inventory;
    }

    public void takeDamage(int damage) {
        if(inventory[1] == Items.shield){
            damage -= Items.shield.strength;
            if(damage < 0) {
                damage = 0;
            }
        }
        health -= damage;
        System.out.println("Player took " + damage + " damage!");
        System.out.println("Player has " + health + " health left.");
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
        int label = 1;
        System.out.println("*******");
        for (Items item : inventory) {
            if(item != null){
                System.out.println(label+". "+item.name+" x"+item.amount);
                label++;
            }
        }
        System.out.println("*******");
    }

    public void addItem(Items item){
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == item && item.stackable == true) { //for things like coins/potions
                item.amount += 1;
                break;
            }

            if (inventory[i] == null) {
                inventory[i] = item;
                break;
            }
        }
    }

    public void getInfo(Items item){
        System.out.println("--Info--");
        System.out.println("Item: "+item.name+"\nType: "+item.type);

        switch (item.type) {
            case "Weapon":
                System.out.println("Attack bonus: "+item.strength);
                break;
            case "Armor":
                System.out.println("Defense bonus: "+item.strength);
                break;
            case "Consumable":
                System.out.println("Heals: +"+item.strength+" HP");
                break;
            default:
                break;
        }

        if(item.stackable){
            System.out.println("Amount: "+item.amount);
            System.out.println("Stackable");
        } else {
            System.out.println("Non-Stackable");
        }

        System.out.println("-----");
    }

    public void removeItem(Items item){
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == item) {
                inventory[i] = null;
                break;
            }
        }
    }

    public void attack(Monster monster){
        int damage = (int)Math.floor((Math.random()*4));
        if(inventory[0] == Items.sword){
            damage += Items.sword.strength;
        }
        System.out.println("Player attacked!");
        System.out.println("Player dealt "+damage+" damage!");
        monster.takeDamage(damage);
    }

    public void useItem(Player player){
        checkInventory();
        System.out.println("Enter the item you want to use: ");
        int choice = in.nextInt();
        int curhealth = player.health;
        Items item = inventory[choice-1];
        if(item.type == "Consumable"){
            player.health += item.strength;
            if(player.health > 20){
                player.health = 20;
                int healedBy = 20-curhealth;
                System.out.println("Player used "+item.name+" and healed "+healedBy+" HP!");
            } else {
                System.out.println("Player used "+item.name+" and healed "+item.strength+" HP!");
            }
            item.amount -= 1;
            if(item.amount <= 0){
                removeItem(item);
            }
        } else {
            System.out.println("Cannot use that item!");
        }
    }
}
