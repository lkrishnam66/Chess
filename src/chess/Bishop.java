
/**
 * This class is for the Bishop and determines whether a move is valid has it's toString implementation
 * @author Hritish Mehta and Likhit Krishnam
 */
public class Bishop extends Piece {
    /**
     * This is a constructor which initializes the current row, column, and player attributes for the Bishop object
     * @param row-the current row the Bishop is on
     * @param col-the current column the Bishop is on
     * @param player-whether the Bishop is a white or black player. 
     */
    public Bishop(int row, int col, int player){
        super(row, col, player);
    }
    
    /** 
     * This method checks to see whether a move entered by the client can be made by the bishop
     * @param r-the ending row position for where the client wants to move piece
     * @param c-the ending column position for where the client wants to move piece
     * @param board-Board object that stores the current 2D Piece array
     * @return boolean-to see if entered move is possible for the current piece
     */
    @Override
    public boolean isValidMove(int r, int c, Board board) {
        // Check that the destination square is within the bounds of the board
        if (r<0 || r>7 || c<0 || c>7) {
            return false;
        }
        
        // Check that the bishop is moving diagonally
        int rowDistance = Math.abs(r - this.getRow());
        int colDistance = Math.abs(c - this.getCol());
        if (rowDistance != colDistance) {
            return false;
        }
        
        //noFriendly capture
        if(board.getPiece(r,c) != null && board.getPiece(r,c).getPlayer() == this.getPlayer()){
            return false;
        }
        
        // Check that there are no pieces in the way
        int startRow = this.getRow();
        int startCol = this.getCol();
        int rowDirection = Integer.signum(r - startRow);
        int colDirection = Integer.signum(c - startCol);
        for (int i = 1; i < rowDistance; i++) {
            int row = startRow + i * rowDirection;
            int col = startCol + i * colDirection;
            if (board.getPiece(row, col) != null) {
                return false;
            }
        }
        
        // Check that the destination square is either empty or occupied by an opponent's piece
        Piece destPiece = board.getPiece(r, c);
        if (destPiece == null || destPiece.getPlayer() != this.getPlayer()) {
            boolean test = board.testMove(r,c,this);
            return test; 
        }
        
        return false;
    }

    /**
     * This method returns a string representation of the object.
     * @return result - the string representation of the object
     */
    @Override
    public String toString(){
        String result = "";
        if(this.getPlayer()==0){
            result = "wB";

        }
        else{
            result = "bB";
        }
        return result;


    }

    
}
