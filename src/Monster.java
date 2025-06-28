import java.util.Scanner;

public class Monster {
    Scanner in = new Scanner(System.in);
    
    public int level;
    public int health;
    public int xp;
    //want to introduce types of monsters. diversify the combat and scenarios.

    public Monster(int level) {
        this.level = level;
        this.health = level*3;
        this.xp = this.health*3; // 3xp per health/damage dealt
    }

    public void attack(Player player){
        int damage = (int)Math.floor((Math.random()*level));
        player.takeDamage(damage);
    }

    public void takeDamage(int damage){
        health -= damage;
        // System.out.println("Monster took "+damage+" damage!");
        System.out.println("Monster has "+health+" health left.");
        if(health <= 0){
            System.out.println("Monster has died!");
        }
    }

    public void getHealth(){
        System.out.println("Monster health: "+health);
    }

}
