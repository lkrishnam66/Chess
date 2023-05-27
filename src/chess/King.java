
/**
 * This class is for the Knight and determines whether a move is valid has it's toString implementation 
 * @author Hritish Mehta and Likhit Krishnam
 */
public class King extends Piece{
    /**
     * This is a constructor to initialize a King object with its current row and column and whether it is black or white.
     * @param row - the current row of the King object on the board
     * @param col - the current column of the King object on the board
     * @param player - whether it is a black or white piece. 0 = white and 1 = black
     */
    public King(int row, int col, int player){
        super(row, col, player);
    }
    
    /** 
     * This method checks to see whether a move entered by the client can be made by the King
     * @param r-the ending row position for where the client wants to move piece
     * @param c-the ending column position for where the client wants to move piece
     * @param board-Board object that stores the current 2D Piece array
     * @return boolean-to see if entered move is possible for the current piece
     */
    @Override
    public boolean isValidMove(int r, int c, Board board){
        if(r<0 || r>8 || c<0 || c>8){
            return false; 
        }

        //doesn't allow friendly capture 
        if(board.getPiece(r,c) != null && board.getPiece(r,c).getPlayer() == this.getPlayer()){
            return false; 
        }

        int rowDistance = Math.abs(r-this.getRow());
        int colDistance = Math.abs(c-this.getCol());

        if(rowDistance > 1 || colDistance > 1){
            return false; 
        }
        
         // Check that the king is not moving into check
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
            result = "wK";

        }
        else{
            result = "bK";
        }
        return result;
    }
    
}
