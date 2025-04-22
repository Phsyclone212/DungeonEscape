//Main class
import java.util.Scanner;

public class DungeonEscape {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        Boolean gameOver = false;
        Boolean inCombat = false;
        Boolean invOpen = false;
        int mapSize = 5; //default size
        System.out.println("--**--**-***Welcome to Dungeon Escape!***-**--**--");
        System.out.println("You are dropped into a dungeon and must escape by finding the exit.");
        System.out.println("You will encounter monsters along the way, but don't fret!");
        System.err.println("You have a rusty sword and a cracked shield to defend yourself.");
        System.out.println("You also have a potion to heal yourself and coins to spend in your new life!");
        System.out.println("Please select a difficulty: \n1. Easy (5x5)\n2. Medium (10x10)\n3. Hard (15x15)");
        int difficulty = in.nextInt();
        switch (difficulty) {
            case 1:
                System.out.println("You have selected Easy mode.\nGood choice!");
                mapSize = 5;
                break;
            case 2:
                System.out.println("You have selected Medium mode.\nChallenging yourself?");
                mapSize = 10;
                break;
            case 3:
                System.out.println("You have selected Hard mode.\nGood luck.");
                mapSize = 15;
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Easy mode.");
                break;
        }

        int startX = (int)(Math.random()*mapSize); //should match the default or new difficulty size.
        int startY = (int)(Math.random()*mapSize);
        int playerHealth = 20;

        Player player = new Player(playerHealth, startX, startY, new Items[20]);
        player.addItem(Items.rustySword);
        player.addItem(Items.crackedShield);
        player.addItem(Items.potionHealth);
        player.addItem(Items.key); //will change back to coins after testing
        GameMap dungeon = new GameMap(mapSize,mapSize, player); //this is the dungeon that is now set to mapSize x mapSize
        dungeon.map[startX][startY].hasMonsters = false;
        dungeon.map[startX][startY].tag = '_';
        dungeon.map[startX][startY].items = null;
        System.out.println("You have been dropped into the dungeon. Try to escape!");
        dungeon.exploreRoom(player.posX, player.posY);
        dungeon.PrintMap();
        //gameloop
        while(!gameOver){
            System.out.println("-*-**MAIN MENU**-*-");
            System.out.println("1. Check Map\n2. Move\n3. Search\n4. Open Inventory\n5. Check Stats\n6. Quit\n-**-*-**-**-*-**-");
            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    // Check map
                    dungeon.PrintMap();
                    break;
                case 2:
                    // Move to a new room
                    System.out.println("Enter a direction to move (1: left, 2: right, 3: up, 4: down, 5: cancel): ");
                    int direction = in.nextInt();
                    player.move(dungeon, direction);
                    if(dungeon.map[player.posX][player.posY].hasMonsters){
                        Monster monster = new Monster((int)(Math.random()*10)+1);
                        System.out.println("You encountered a Lvl."+monster.level+" monster!");
                        monster.getHealth();
                        inCombat = true;
                        while(inCombat){
                            System.out.println("Enter 1 to attack, 2 to use an item: ");
                            int action = in.nextInt();
                            switch (action) {
                                case 1:
                                    player.attack(monster, player);
                                    if(monster.health <= 0){
                                        inCombat = false;
                                        player.gainXP(player, monster);
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
                                System.out.println("You died.\nGame Over!");
                                gameOver = true;
                                inCombat = false;
                            }
                        }
                    }
                    if(dungeon.map[player.posX][player.posY].isTheExit){
                        System.out.println("You found the exit! You win!");
                        gameOver = true;
                    }   
                    break;
                case 3:
                    //Search room
                    System.out.println("You begin searching the room...");
                    dungeon.searchRoom(dungeon.map[player.posX][player.posY], player);
                    break;
                case 4:
                    //Open inventory
                    player.checkInventory();
                    invOpen = true;
                    while(invOpen){
                        System.out.println("--Inventory Menu--\n1. List Inventory\n2. Get Item Info\n3. Use Item\n4. Close Inventory");
                        int invChoice = in.nextInt();
                        switch (invChoice) {
                            case 1:
                                player.checkInventory();
                                break;
                            case 2:
                                System.out.println("Enter the item number to get info: ");
                                int item = in.nextInt() - 1; // -1 to offset index to match label
                                player.getInfo(player.inventory[item]);
                                break;
                            case 3:
                                player.useItem(player);
                                break;
                            case 4:
                                System.out.println("Closing Inventory...");
                                invOpen = false;
                                break;
                            default:
                             System.out.println("Invalid choice!");
                                break;
                        }
                    }   break;
                case 5:
                    //Check stats
                    player.getStats(player);
                    break;
                case 6:
                    //Quit
                    System.out.println("Qutting game.\nThanks for playing!");
                    gameOver = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }
}
