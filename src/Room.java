public class Room {
    String description = "";
    Boolean hasMonsters = false;
    String[] items;

    public Room(String description, Boolean hasMonsters, String[] items){
        this.description = description;
        this.hasMonsters = hasMonsters;
        this.items = items;
    }

    public static Room generateRoom(){
        //select description, decide hasMonsters and items
        String[] possibleDescriptions = {"Dark Room", "Open space", "Well-lit Room"};
        boolean hasMonsters = Math.random() < 0.5;
        String[] possibleItems = {"Rusty Sword", "Wooden Shield", "Coins"};

        return null;
    }

    public void getDescription(){
        System.out.println(description);
    }
}
