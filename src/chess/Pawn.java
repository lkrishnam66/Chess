

/**
 * This class is for the Pawn and determines whether a move is valid has it's toString implementation 
 * @author Hritish Mehta and Likhit Krishnam
 */
public class Pawn extends Piece {
    /**
     * This is a constructor that initializes the Pawn object with the current row and column on and what player it belongs to(White or Black)
     * @param row - represents what row the piece is on
     * @param col - represents what column the piece is on
     * @param player - represents what player the piece belongs to. 0 = white and 1 = black
     */
    public Pawn(int row, int col, int player){
        super(row, col, player);
    }
    
    /** 
     * This method checks to see whether a move entered by the client can be made by the Pawn.
     * @param r-the ending row position for where the client wants to move piece
     * @param c-the ending column position for where the client wants to move piece
     * @param board-Board object that stores the current 2D Piece array
     * @return boolean-to see if entered move is possible for the current piece
     */
    @Override
    public boolean isValidMove(int r, int c, Board board){
        enPassant = false; 
      
        if (r<0 || r>7 || c<0 || c>7){
            return false;
        }

        if(r==this.getRow() && c == this.getCol()){
            return false; 
        }

        //default case: if user tries to move up by more than 2 regardless it is false
        if(Math.abs(r-this.getRow())>2){
    
            return false;
        }

        //if pawn has already moved and user tries to move it up by more than 2
        if(this.getHasMoved()==true && Math.abs(r-this.getRow())>=2){
            return false;
        }

        //noFriendly capture
        if(board.getPiece(r,c) != null && board.getPiece(r,c).getPlayer() == this.getPlayer()){
            return false;
        }

        

        int rowDistance = r - this.getRow(); //vertical movement effectively. Going UP means that index DECREASES
        int colDistance = Math.abs(c - this.getCol()); //horizontal movement

        //if white player, then move upwards, so the value r, should be less than .getRow(), so rowDistance should be NEGATIVE
        if (this.getPlayer() == 0) {
            if (rowDistance >= 0 || colDistance > 1) {
                return false;
            }
        } 
        //Similarly if black player, then rowDistance should be POSITIVE
        else {
            if (rowDistance <= 0 || colDistance > 1) {
               
                return false;
            }
        }

        boolean test = board.testMove(r,c,this);

        //checks if it's en passant
        if(board.getLastPiece()!=null && board.getLastPiece() instanceof Pawn && this.getRow()==board.getLastPiece().getRow() && Math.abs(this.getCol()-board.getLastPiece().getCol())==1 && Math.abs(board.getLastPiece().getRow()-board.getLastRow())==2 && c == board.getLastPiece().getCol()){
            enPassant = true;
        }

        if(enPassant){
            if(Math.abs(r - board.getLastPiece().getRow())==1 && (c==board.getLastPiece().getCol())){
                if(board.getPiece(r,c)==null){
                    return test; 
                }
                else if(board.getPiece(r, c).getPlayer()!=this.getPlayer()){
                    return test; 
                }
                else{
                    return false;
                }
            }
        }

        //default case for all movement: pawn moves UP one square or pawn moves one diagnoal
        if(Math.abs(this.getRow()-r)==1 && c==this.getCol() && board.getPiece(r, c)==null){
           return test; 
        }

        //capture
        if(colDistance>0){
            if(Math.abs(this.getRow()-r)==1 && Math.abs(c-this.getCol())==1 && board.getPiece(r,c) != null && board.getPiece(r, c).getPlayer()!=this.getPlayer()){
                return test; 
            }
            else{
                return false; 
            }
        }
       
        if(this.getHasMoved()==false){
            //if player is white
            if(this.getPlayer() == 0){
                if(board.getPiece(this.getRow()-1,c) != null ||  board.getPiece(this.getRow()-2,c) != null){
                    return false; 
                }
                else{
                    return test; 
                }
            }
            else{
                if(board.getPiece(this.getRow()+1,c) != null ||  board.getPiece(this.getRow()+2,c) != null){
                    return false; 
                }
                else{
                    return test; 
                }

            }

        }
     
        return false;
    }

    
    /** 
     * This method returns the string representation of the of the object. 
     * @return String
     */
    @Override
    public String toString(){
        String result = "";
        if(this.getPlayer()==0){
            result = "wP";

        }
        else{
            result = "bP";
        }
        return result;


    }

    
}
