public class Player {
    public int health;
    public int position;
    public String inventory[];

    public void takeDamage(int damage) {
        health -= damage;
    }

    public void move(int destination){
        position = destination;
    }

    public void checkInventory(){
        for (String item : inventory) {
            System.out.println(item);
        }
    }

    public void addItem(String item){
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null) {
                inventory[i] = item;
                break;
            }
        }
    }
}
