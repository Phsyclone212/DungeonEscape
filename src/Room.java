

public class Room {
    String description;
    char tag = '?';
    Boolean hasMonsters;
    Items items;
    Boolean isTheExit = false;

    public Room(String description, char tag, Boolean hasMonsters, Items items, Boolean isTheExit) {
        this.description = description;
        this.tag = tag;
        this.hasMonsters = hasMonsters;
        this.items = items;
        this.isTheExit = isTheExit;
    }

    public static Room generateRoom(){
        //select description, decide hasMonsters and items
        String[] possibleDescriptions = {"A Dark Room", "An open space", "Well-lit Room"};
        char tag = '?';
        boolean hasMonsters = Math.random() < 0.5;
        Items[] items = {Items.sword, Items.shield, Items.coins, Items.potionHealth, null, null, null, null};

        return new Room (possibleDescriptions[(int)(Math.random()*possibleDescriptions.length)], tag,
        hasMonsters,
        items[(int)(Math.random()*items.length)],
        false);
    }

    public void generateExit(GameMap map, Player player){
        int exitX = (int)(Math.random()*map.map.length);
        int exitY = (int)(Math.random()*map.map[0].length);
        if(exitX != player.posX && exitY != player.posY){
            map.map[exitX][exitY].isTheExit = true;
        }
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
    }

    public char getTag(){
        return tag;
    }
}
