
/**
 * This class is for the Knight and determines whether a move is valid has it's toString implementation
 * @author Hritish Mehta and Likhit Krishnam
 */
public class Knight extends Piece {
    /**
     * This is a constructor to initialize a Knight object with its current row and column and whether it is black or white.
     * @param row - the current row of the Knight object on the board
     * @param col - the current column of the Knight object on the board
     * @param player - whether it is a black or white piece. 0 = white and 1 = black
     */
    public Knight(int row, int col, int player){
        super(row, col, player);
    }
    
    /** 
     * This method checks to see whether a move entered by the client can be made by the knight
     * @param r-the ending row position for where the client wants to move piece
     * @param c-the ending column position for where the client wants to move piece
     * @param board-Board object that stores the current 2D Piece array
     * @return boolean-to see if entered move is possible for the current piece
     */
    @Override
    public boolean isValidMove(int r, int c, Board board){
        // Check that the destination square is within the bounds of the board
        if (r < 0 || r > 7 || c < 0 || c > 7) {
            return false;
        }

        //noFriendly capture
        if(board.getPiece(r,c) != null && board.getPiece(r,c).getPlayer() == this.getPlayer()){
            return false;
        }

        // Calculate the distance moved in each direction
        int rowDistance = Math.abs(r - this.getRow());
        int colDistance = Math.abs(c - this.getCol());

        // Check that the move is L-shaped
        if (!((rowDistance == 1 && colDistance == 2) || (rowDistance == 2 && colDistance == 1))) {
            return false;
        }

        boolean test = board.testMove(r,c,this);
        return test; 
    }
    
    /**
     * This method returns a string representation of the object.
     * @return result - the string representation of the object
     */
    @Override
    public String toString(){
        String result = "";
        if(this.getPlayer()==0){
            result = "wN";

        }
        else{
            result = "bN";
        }
        return result;


    }

    
}
