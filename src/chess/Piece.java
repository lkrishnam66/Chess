
/**
 * This abstract class defines the Piece blueprint for all the other Piece objects such as King, Queen, etc. It includes basic setter and getter methods to receive the row, column, player, whether the piece has moved, and if it's an enPassant which is only set in the Pawn class
 * @author Hritish Mehta and Likhit Krishnam
 */
public abstract class Piece {
    /**
     * represents the row attribute
     */
    private int row;
    /**
     * represents the column attribute
     */
    private int col;
    /**
     * represents the player of the piece whether it is white or black 
     */
    private int player; //player 0= White, player 1 = Black
    /**
     * represents whether the piece has Moved or not 
     */
    private boolean hasMoved;
    /**
     * represents whether a pawn has don enPassant or not
     */
    public boolean enPassant; 

/**
 * This is a constructor which initializes the object's row, col, and player attributes
 * @param row - represents current row of piece
 * @param col - represents current col of piece
 * @param player - represents whether piece is black or white. 0 = white and 1 = black
 */
    public Piece(int row, int col, int player){
        this.row = row;
        this.col = col;
        this.player = player;
        this.hasMoved=false;
    }
    
    
    /** 
     * This is a getter method for the row
     * @return int - current row of piece
     */
    public int getRow(){
        return row;
    }

    /**
     * This is a getter method for whether the piece has moved or not
     * @return boolean - whether the piece has moved or not
     */
    public boolean getHasMoved(){
        return hasMoved;
    }


    /**
     * This is a getter method for the col
     * @return int - current column of the piece 
     */
    public int getCol(){
        return col;

    }

    /**
     * This is a getter method for what player the piece is. 
     * @return int - 0 for white and 1 for black 
     */
    public int getPlayer(){
        return player;
    }
    /**
     * This is a setter method for the row of the piece
     * @param r - the new row of the piece
     */
    public void setRow(int r){
        row = r; 
    }
    /**
     * This is a setter method for the column of the piece
     * @param c - The new column of the piece
     */
    public void setCol(int c){
        col = c;
    }
    /**
     * This is a setter method for the player of the piece
     * @param p - 0 for white and 1 for black 
     */
    public void setPlayer(int p){
        player = p; 
    }
    /**
     * This is a setter method for whether the player has moved or not
     * @param val- a boolean value for whether the piece has moved or not 
     */
    public void setHasMoved(boolean val){
        hasMoved = val; 
    }


    /**
     * This is an abstract method implemented differently based on each piece which shows whether the move the client is trying to make is valid or not based on the piece's game logic. 
     * @param r - the endRow of the piece
     * @param c - the endColumn of the piece
     * @param board - the Board object representing the ChessBoard 
     * @return boolean - whether the move is valid or not for the given piece. 
     */
    public abstract boolean isValidMove(int r, int c, Board board);
/**
 * This is a abstract method which returns the string representation of the given object. 
 * @return String - the string representation of the object.
 */
    public abstract String toString();


    
}
