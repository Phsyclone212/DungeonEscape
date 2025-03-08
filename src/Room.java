public class Room {
    String description;
    Boolean hasMonsters;
    String items;

    public Room(String description, Boolean hasMonsters, String items){
        this.description = description;
        this.hasMonsters = hasMonsters;
        this.items = items;
    }

    public static Room generateRoom(){
        //select description, decide hasMonsters and items
        String[] possibleDescriptions = {"Dark Room", "Open space", "Well-lit Room"};
        boolean hasMonsters = Math.random() < 0.5;
        String[] possibleItems = {"Rusty Sword", "Wooden Shield", "Coins"};

        return new Room (possibleDescriptions[(int)(Math.random()*possibleDescriptions.length)],
        hasMonsters,
        possibleItems[(int)(Math.random()*possibleItems.length)]);
    }

    public void getDescription(){
        System.out.println("Description: "+description);
        if(hasMonsters){
            System.out.println("There are Monsters!");
        } else {
            System.out.println("No monsters in this room.");
        }
    }
}
