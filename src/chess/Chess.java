
/**
 * This is the driver class of the Chess project and starts the actual game
 * @author Hritish Mehta and Likhit Krishnam
 */
public class Chess {
    
    /** 
     * This is the main method of the driver class which instanitates a Game object to start the chess process. 
     * @param args - string array based on command input from terminal 
     */
    public static void main(String[] args){
        Board board = new Board(); 
        Game game = new Game(board);
        
        game.start();
    }
}
