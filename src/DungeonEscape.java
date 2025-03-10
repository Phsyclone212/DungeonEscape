//Main class
import java.util.Scanner;

public class DungeonEscape {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        Boolean gameOver = false;
        Boolean inCombat = false;
        int startX = (int)(Math.random()*5); // ONLY set to 5 because the dungeon is currently also set to 5
        int startY = (int)(Math.random()*5); // same as above

        Player player = new Player(100, startX, startY, new String[20]);
        player.addItem("Sword");
        player.addItem("Shield");
        player.addItem("Potion");
        player.checkInventory();
        GameMap dungeon = new GameMap(5,5); //this is the dungeon that is also set to 5x5
        dungeon.map[startX][startY].hasMonsters = false;
        dungeon.map[startX][startY].tag = '_';
        dungeon.map[startX][startY].items = null;
        System.out.println("You are in a dungeon. Try to escape!");
        dungeon.exploreRoom(player.posX, player.posY);
        dungeon.PrintMap();
        //gameloop
        while(!gameOver){

            System.out.println("1. Move\n2. Search\n3. Check Inventory\n4. Quit");
            int choice = in.nextInt();
            if(choice == 1){
                System.out.println("Enter a direction to move (1: left, 2: right, 3: up, 4: down): ");
                int direction = in.nextInt();
                player.move(dungeon, direction);
                if(dungeon.map[player.posX][player.posY].hasMonsters){
                    System.out.println("You encountered a monster!");
                    Monster monster = new Monster((int)(Math.random()*5)+1);
                    monster.getHealth();
                    inCombat = true;
                    while(inCombat){
                        System.out.println("Enter 1 to attack, 2 to use an item: ");
                        int action = in.nextInt();
                        if(action == 1){
                            player.attack(monster);
                            if(monster.health <= 0){
                                inCombat = false;
                                dungeon.map[player.posX][player.posY].hasMonsters = false;
                                dungeon.map[player.posX][player.posY].tag = '_';
                            } else {
                                monster.attack(player); //monsters always attack back
                            }
                        } else if(action == 2){
                            player.useItem();
                            inCombat = false;
                        } else {
                            System.out.println("Invalid action!");
                        }
                    }
                }
            } else if(choice == 2){
                dungeon.searchRoom(dungeon.map[player.posX][player.posY], player);
            } else if(choice == 3){
                player.checkInventory();
            } else if(choice == 4){
                gameOver = true;
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }
}
