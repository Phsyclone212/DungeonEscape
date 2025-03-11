//Main class
import java.util.Scanner;

public class DungeonEscape {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        Boolean gameOver = false;
        Boolean inCombat = false;
        Boolean invOpen = false;
        int startX = (int)(Math.random()*5); // ONLY set to 5 because the dungeon is currently also set to 5
        int startY = (int)(Math.random()*5); // same as above
        int playerHealth = 20;

        Player player = new Player(playerHealth, startX, startY, new Items[20]);
        player.addItem(Items.sword);
        player.addItem(Items.shield);
        player.addItem(Items.potionHealth);
        player.addItem(Items.coins);
        GameMap dungeon = new GameMap(5,5); //this is the dungeon that is also set to 5x5
        dungeon.map[startX][startY].hasMonsters = false;
        dungeon.map[startX][startY].tag = '_';
        dungeon.map[startX][startY].items = null;
        System.out.println("You are in a dungeon. Try to escape!");
        dungeon.exploreRoom(player.posX, player.posY);
        dungeon.PrintMap();
        //gameloop
        while(!gameOver){

            System.out.println("1. Move\n2. Search\n3. Open Inventory\n4. Check Stats\n 5. Quit");
            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    // Move to a new room
                    System.out.println("Enter a direction to move (1: left, 2: right, 3: up, 4: down): ");
                    int direction = in.nextInt();
                    player.move(dungeon, direction);
                    if(dungeon.map[player.posX][player.posY].hasMonsters){
                        System.out.println("You encountered a monster!");
                        Monster monster = new Monster((int)(Math.random()*10)+1);
                        monster.getHealth();
                        inCombat = true;
                        while(inCombat){
                            System.out.println("Enter 1 to attack, 2 to use an item: ");
                            int action = in.nextInt();
                            switch (action) {
                                case 1:
                                    player.attack(monster);
                                    if(monster.health <= 0){
                                        inCombat = false;
                                        dungeon.map[player.posX][player.posY].hasMonsters = false;
                                        dungeon.map[player.posX][player.posY].tag = '_';
                                        dungeon.PrintMap();
                                    } else {
                                        monster.attack(player); //monsters always attack back
                                    }   break;
                                case 2:
                                    player.useItem(player); //FIXED
                                    monster.attack(player); //monsters always attack back
                                    break;
                                default:
                                    System.out.println("Invalid action!");
                                    break;
                            }
                            if(player.health <= 0){ //did you die?
                                System.out.println("Game Over!");
                                gameOver = true;
                                inCombat = false;
                            }
                        }
                    }   break;
                case 2:
                    //Search room
                    System.out.println("You begin searching the room...");
                    dungeon.searchRoom(dungeon.map[player.posX][player.posY], player);
                    break;
                case 3:
                    //Open inventory
                    player.checkInventory();
                    invOpen = true;
                    while(invOpen){
                        System.out.println("--Inventory Menu--\n1. List Inventory\n2. Get Item Info\n3. Close Inventory");
                        int invChoice = in.nextInt();
                        if(invChoice == 1){
                            player.checkInventory();
                        } else if(invChoice == 2){
                            System.out.println("Enter the item number to get info: ");
                            int item = in.nextInt() - 1; // -1 to offset index to match label
                            player.getInfo(player.inventory[item]);
                        } else if(invChoice == 3){
                            System.out.println("Closing Inventory...");
                            invOpen = false;
                        } else {
                            System.out.println("Invalid choice!");
                        }
                    }   break;
                case 4:
                    //Check stats
                    System.out.println("--Player Stats--");
                    System.out.println("HP: "+player.health);
                    System.out.println("Slot 1: "+player.inventory[0].name);
                    System.out.println("Slot 2: "+player.inventory[1].name);
                    break;
                case 5:
                    //Quit
                    gameOver = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }
}
