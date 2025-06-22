import java.util.Scanner;

public class Room {
    Scanner in = new Scanner(System.in);
    String description;
    char tag = '?';
    Boolean hasMonsters;
    Items items;
    Boolean isTheExit = false;
    Boolean hasChest = false;
    Chests chest;

    public Room(String description, char tag, Boolean hasMonsters, Items items, Boolean isTheExit, Boolean hasChest) {
        this.description = description;
        this.tag = tag;
        this.hasMonsters = hasMonsters;
        this.items = items;
        this.isTheExit = isTheExit;
        this.hasChest = hasChest;
    }

    public static Room generateRoom(){
        //select description, decide hasMonsters and items
        String[] possibleDescriptions = {"A Dark Room", "An open space", "Well-lit Room"};
        char tag = '?';
        boolean hasMonsters = Math.random() < 0.5;
        Items[] items = {Items.ironSword, Items.woodShield, Items.coins, Items.potionHealth, Items.crackedShield, Items.rustySword, Items.sapphire, Items.key, null, null, null};
        Boolean isTheExit = false; //this gets revisited after the whole map generates, an exit IS chosen.
        Boolean hasChest = Math.random() < 0.1; //10% chance of a chest in the room- decent.

        return new Room (possibleDescriptions[(int)(Math.random()*possibleDescriptions.length)], tag,
        hasMonsters, 
        items[(int)(Math.random()*items.length)],
        isTheExit, hasChest);
    }
    
    public void getDescription(){
        System.out.println("Room Description: "+description);
        if(hasMonsters){
            System.out.println("There's a monster in this room!");
        } else {
            System.out.println("No monsters in this room.");
        }
    }

    public void exploreRoom(){
        if(hasMonsters){
            this.tag = '!';
        } else {
            this.tag = '_';
        }
        this.getDescription();
    }

    public void searchRoom(Player player){
        if(items != null){
            System.out.println("You found: "+items.amount+" "+items.name);
            player.addItem(items);
            items = null;
        } else {
            System.out.println("No items found.");
        }
        if(this.hasChest){ //Chests were re-rolling into different types on repeated searches. has been changed, not tested.
            if(this.chest == null){
                this.chest = new Chests(0, 0, 0, true);
            }
            chest.generateLoot(this.chest);

            System.out.println("You found a "+chest.type+"! It is locked. You'll need a key to open it.");

            if(player.playerHasKey()){
                System.out.println("You have a key!");
                System.out.print("Do you want to unlock the chest? (y/n): ");
                char choice = in.next().charAt(0);
                if (choice == 'y' || choice == 'Y') {
                    player.removeItem(Items.key);
                    System.out.println("You unlock the chest...");
                    //this needs testing
                    if (chest.items != null) {
                        System.out.println("You found: " + chest.items.name + " in the chest.");
                        player.addItem(chest.items);
                    }
                    this.hasChest = false;
                } else {
                    System.out.println("You chose not to unlock the chest.");
                    this.tag = '#'; //mark for unopened chest
                }
            } else {
                System.out.println("You don't have a key to open the chest.");
            }
        } else {
            System.out.println("No chests found.");
        }
    }

    public char getTag(){
        return tag;
    }
}
