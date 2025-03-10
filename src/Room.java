public class Room {
    String description;
    char tag = '?';
    Boolean hasMonsters;
    String items;

    public Room(String description, char tag, Boolean hasMonsters, String items){
        this.description = description;
        this.tag = tag;
        this.hasMonsters = hasMonsters;
        this.items = items;
    }

    public static Room generateRoom(){
        //select description, decide hasMonsters and items
        String[] possibleDescriptions = {"A Dark Room", "An open space", "Well-lit Room"};
        char tag = '?';
        boolean hasMonsters = Math.random() < 0.5;
        String[] items = {"Rusty Sword", "Wooden Shield", "Coins", null, null, null};

        return new Room (possibleDescriptions[(int)(Math.random()*possibleDescriptions.length)], tag,
        hasMonsters,
        items[(int)(Math.random()*items.length)]);
    }

    public void getDescription(){
        System.out.println("Room Description: "+description);
        if(hasMonsters){
            System.out.println("There are Monsters!");
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
            System.out.println("You found: "+items);
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
