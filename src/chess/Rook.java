
/**
 * This class is for the Rook and determines whether a move is valid has it's toString implementation
 * @author Hritish Mehta and Likhit Krishnam
 */
public class Rook extends Piece{
    /**
     * This is a constructor which initializes the current row, column, and player attributes for the Rook object 
     * @param row - the current row the Rook is on
     * @param col - the current column the Rook is on
     * @param player - whether the Rook is a white or black player. 
     */
    public Rook(int row, int col, int player){
        super(row, col, player);
    }

    
    /** 
     * This method checks to see whether a move entered by the client can be made by the Rook
     * @param r - the ending row position for where the client wants to move piece
     * @param c - the ending column position for where the client wants to move piece
     * @param board - Board object that stores the current 2D Piece array
     * @return boolean - to see if entered move is possible for the current piece
     */
    @Override
    public boolean isValidMove(int r, int c, Board board) {
        // Check that the destination square is within the bounds of the board
        if (r < 0 || r > 7 || c < 0 || c > 7) {
            return false;
        }

        // Check that the rook is moving either horizontally OR vertically
        int rowDistance = Math.abs(r - this.getRow());
        int colDistance = Math.abs(c - this.getCol());
        if (rowDistance > 0 && colDistance > 0) {
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
        for (int i = 1; i < Math.max(rowDistance, colDistance); i++) {
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
            result = "wR";

        }
        else{
            result = "bR";
        }
        return result;


    }
    
}