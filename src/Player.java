import java.util.Scanner;

public class Player {
    Scanner in = new Scanner(System.in);

    public int level = 1;
    public int xp = 0;
    public int xpToNextLevel = Math.round((float)(5*Math.pow(level, 2) + 5*level));
    public int health;
    public int posX;
    public int posY;
    public Items inventory[];
    public Boolean hasKey;

    public Player(int health, int posX, int posY, Items[] inventory) {
        this.health = health;
        this.posX = posX;
        this.posY = posY;
        this.inventory = inventory;
        this.level = 1;
        this.xp = 0;
        this.xpToNextLevel = Math.round((float)(5*Math.pow(this.level, 2) + 5*this.level));
    }


    public boolean playerHasKey() {
        //check if player has key in inventory
        for (Items item : inventory) {
            if (item != null && item.type.equals("Key")) {
                return true;
            }
        }
        return false;
    }

    public void takeDamage(int damage) {
        Items slot2 = inventory[1]; //slot 2 is for shields/Armor
        if(slot2.type == "Armor"){
            damage -= slot2.strength;
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
            case 5:
                System.out.println("Cancelled move.");
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
        int label = 1;
        System.out.println("***Inventory***");
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

    public void attack(Monster monster, Player player){
        Items slot1 = player.inventory[0]; //slot 1 is for weapons
        int damage = (int)Math.floor((Math.random()*player.level)+1); //new damage formula 3/17/25, player level scaling
        if(slot1.type == "Weapon"){
            damage += slot1.strength; //sword bonus currently static, constantly dealing max damage
            System.out.println("You swing your "+slot1.name+"!");
        } else {
        System.out.println("Player attacked!");
        }
        System.out.println("Player dealt "+damage+" damage!");
        monster.takeDamage(damage);
    }

    public void useItem(Player player){
        checkInventory();
        System.out.println("Select the item you want to use: ");
        int choice = in.nextInt();
        int curhealth = player.health; //prevents over-healing on 172
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

    public void gainXP(Player player, Monster monster){
        player.xp += monster.xp;
        System.out.println("Player gained "+monster.xp+" XP!");
        checkLevelUp(player);
    }

    public void checkLevelUp(Player player){
        if(player.xp >= player.xpToNextLevel){
            player.level += 1;
            int remXP = player.xp - player.xpToNextLevel; //catches extra xp (NO XP WASTE BOIS)
            player.xp = 0;
            player.xpToNextLevel = Math.round((float)(5*Math.pow(player.level, 2) + 5*player.level));
            System.out.println("Player leveled up to level "+player.level+"!");
            player.xp += remXP; //adds any extra xp back to level
            checkLevelUp(player); //recursion to check if player can level up again
        }
    }

    public void getStats(Player player){
        System.out.println("--Player Stats--");
        System.out.println("LVL: "+player.level+" HP: "+player.health);
        System.out.println("XP: "+player.xp+"/"+player.xpToNextLevel);
        System.out.println("Slot 1: "+player.inventory[0].name);
        System.out.println("Slot 2: "+player.inventory[1].name);
        System.out.println("Position: "+player.posX+", "+player.posY);
        System.out.println("-----");
    }
}
