public class GameMap {
    //will hold the rooms
    Room[][] map;

    public GameMap(int rows, int cols, Player player){
        map = new Room[rows][cols];

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                map[i][j] = Room.generateRoom();
            }
        }

        int exitX = (int)(Math.random()*map.length);
        int exitY = (int)(Math.random()*map[0].length);
        System.out.println("DEBUG MSG: Generating Exit at: "+exitX+", "+exitY);
        if(exitX != player.posX && exitY != player.posY){
            map[exitX][exitY].isTheExit = true;
        }
    }

    public void exploreRoom(int x, int y){
        if(x >= 0 && x <= map.length && y >= 0 && y <= map[0].length){
            map[x][y].exploreRoom();
        }
    }

    public void searchRoom(Room room, Player player){
        room.searchRoom(player);
    }

    public void generateExitRoom(){
    }

    public void PrintMap(){
        System.out.println("Current Map:");
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++){
                System.out.print("["+map[i][j].getTag()+"] ");
            }
            System.out.println();
        }
    }
}
