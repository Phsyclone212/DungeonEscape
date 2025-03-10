//Main class
import java.util.Scanner;

public class DungeonEscape {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        Boolean gameOver = false;
        Boolean inCombat = false;

        Player player = new Player(100, 4, 2, new String[20]);
        player.addItem("Sword");
        player.addItem("Shield");
        player.addItem("Potion");
        // player.checkInventory();
        GameMap dungeon = new GameMap(5,5);
        dungeon.PrintMap();
        dungeon.exploreRoom(player.posX, player.posY);
        dungeon.PrintMap();
        //gameloop
        while(!gameOver){
            System.out.println("Enter a direction to move (1: left, 2: right, 3: up, 4: down): ");
            int direction = in.nextInt();
            player.move(dungeon, direction);
            if(dungeon.map[player.posX][player.posY].hasMonsters){
                System.out.println("You encountered a monster!");
                inCombat = true;
                while(inCombat){
                    System.out.println("Enter 1 to attack, 2 to use an item: ");
                    int action = in.nextInt();
                    if(action == 1){
                        player.attack();
                        inCombat = false;
                    } else if(action == 2){
                        player.useItem();
                        inCombat = false;
                    } else {
                        System.out.println("Invalid action!");
                    }
                }
            }

            // ask end of loop question
            System.out.println("Do you want to continue? (y/n): ");
            String cont = in.next();
            if(cont.equals("n")){
                System.out.println("Game Over!");
                gameOver = true;
            }
            // end of loop
        }

    }
}
