
import java.util.*;
/**
 * This class represents the chess board itself and retains functionalities which involve controlling the board Example.) moving the pieces
 * @author Hritish Mehta and Likhit Krishnam
 */

public class Board {
    /**
     * represents an 2D array of pieces called board
     */
    private Piece[][] board;
    /**
     * represents the piece which was movedLast
     */
    private Piece lastMovedPiece; 
     /**
     * represents the starting row of the piece which was moved last
    */
    private int lastMovedFromRow; 
     /**
     * represents the starting column of the piece which was moved  last
    */
    private int lastMovedFromCol; 

    /**
     * This method is a constructor to create a new board object, an 8x8 array of pieces
     */
    public Board(){
        board = new Piece[8][8];
       // whiteToMove = true;
        init();
    }

     /**
     * This method initializes all the pieces on the board
     */
    private void init() {
        // Initialize pawns
        for (int col = 0; col < 8; col++) {
            //0-white and 1 - black  
            board[1][col] = new Pawn(1,col,1); //black starts from top and goes down 
            board[6][col] = new Pawn(6,col,0); //white starts from bottom and goes up 
        }

        // Initialize rooks
        board[0][0] = new Rook(0,0,1);
        board[0][7] = new Rook(0,7,1);
        board[7][0] = new Rook(7,0,0);
        board[7][7] = new Rook(7,7,0);

        // Initialize knights
        board[0][1] = new Knight(0,1,1);
        board[0][6] = new Knight(0,6,1);
        board[7][1] = new Knight(7,1,0);
        board[7][6] = new Knight(7,6,0);

        // Initialize bishops
        board[0][2] = new Bishop(0,2,1);
        board[0][5] = new Bishop(0,5,1);
        board[7][2] = new Bishop(7,2,0);
        board[7][5] = new Bishop(7,5,0);

        // Initialize queens
        board[0][3] = new Queen(0,3,1);
        board[7][3] = new Queen(7,3,0);

        // Initialize kings
        board[0][4] = new King(0,4,1);
        board[7][4] = new King(7,4,0);

        // Initialize empty squares
        for (int row = 2; row < 6; row++) {
            for (int col = 0; col < 8; col++) {
            board[row][col] = null;
            }
        }

    }
    
    /**
     * This method prints out the board as it is currently
     */
    public void printBoard(){
        String result = "\n";
        for(int i = 0; i < 8; i++){ 
            // result += 8-i +" ";
            for(int j = 0; j< 8; j++){
				String pieceStr;
                //sets up the board so that empty black squares have # and empty white squares have spaces
				if (board[i][j] == null){
                    if(i%2==0){
                        if(j%2==0){
                            pieceStr = "   ";
                        }
                        else{
                            pieceStr = "## ";
                        }
                    }
                    else{
                        if(j%2==0){
                            pieceStr = "## ";
                        }
                        else{
                            pieceStr = "   ";
                        }
                    }
                }

                else{
                    pieceStr = board[i][j].toString() + " ";
                }
                result += pieceStr;

                
            }
            
            result+= 8-i+"\n";
           
        }
	
        result +=    " a  b  c  d  e  f  g  h \n";
        System.out.println(result);
	}


    
    
 
    /** 
     * This method gets the Piece at a specified row and col
     * @param r - the specified row for which piece you want
     * @param c - the specified column for which piece you want
     * @return Piece - returns object of type piece at location board[r][c]
     */
    public Piece getPiece(int r, int c){
        //edge case
        if(r<0 || r>7 || c < 0 || c>7){
            return null;
        }
        return board[r][c];
    }

     /** 
     * This method removes a Piece from the board
     * @param p - input of object with type Piece, in order for it to be removed from the board
     */
    public void removePiece(Piece p){
        board[p.getRow()][p.getCol()] = null;
    }

     /** 
     * This method inserts a Piece into the board at specified row,col
     * @param r - row position for a Piece to be inserted in 
     * @param c - column position for a Piece to be inserted in 
     * @param p - object of type Piece to be inserted at position board[r][c]
     */
    public void insertPiece(int r, int c, Piece p){
        board[r][c] = p;
    }

    /** 
     * This method moves a Piece to a specified row,col on the board
     * @param endRow - ending row position for a Piece to be moved to
     * @param endCol - ending column position for a Piece to be moved to 
     * @param board - object of type Board that stores current state of chess board and spots to move
     * @param p - object of type Piece to be moved to a specified ending row / column
     * @return boolean - returns true if the move was succesful, and false otherwise
     */
    public boolean move(int endRow, int endCol, Board board, Piece p){
        //checking for castling
        if(p instanceof King && !p.getHasMoved()){
            if(p.getPlayer() == 0){
                //king side castle
                if((endRow == 7 && endCol == 6) && board.getPiece(7,7) != null && !board.getPiece(7,7).getHasMoved()){
                    if(board.getPiece(7,5)==null && board.getPiece(7,6)==null){
                        Piece kingTemp = p; 
                        Piece rookTemp = board.getPiece(7,7);
                        removePiece(p);
                        removePiece(board.getPiece(7,7));
                        insertPiece(7,6,kingTemp);
                        insertPiece(7,5,rookTemp);
                        kingTemp.setRow(7);
                        kingTemp.setCol(6);
                        kingTemp.setHasMoved(true);
                        rookTemp.setHasMoved(true);
                        rookTemp.setRow(7);
                        rookTemp.setCol(5);
                    }
                }
                //queen side castle
                if((endRow == 7 && endCol == 2) && board.getPiece(7,0) != null && !board.getPiece(7,0).getHasMoved()){
                    if(board.getPiece(7,1)==null && board.getPiece(7,2) == null && board.getPiece(7,3)== null){
                        Piece kingTemp = p; 
                        Piece rookTemp = board.getPiece(7,0);
                        removePiece(p);
                        removePiece(board.getPiece(7,0));
                        insertPiece(7,2,kingTemp);
                        insertPiece(7,3,rookTemp);
                        kingTemp.setRow(7);
                        kingTemp.setCol(2);
                        kingTemp.setHasMoved(true);
                        rookTemp.setHasMoved(true);
                        rookTemp.setRow(7);
                        rookTemp.setCol(3);
                    }
                }
            }
            else{
                if((endRow == 0 && endCol == 6) && board.getPiece(0,7) != null && !board.getPiece(0,7).getHasMoved()){
                    if(board.getPiece(0,5)==null && board.getPiece(0,6)==null){
                        Piece kingTemp = p; 
                        Piece rookTemp = board.getPiece(0,7);
                        removePiece(p);
                        removePiece(board.getPiece(0,7));
                        insertPiece(0,6,kingTemp);
                        insertPiece(0,5,rookTemp);
                        kingTemp.setRow(0);
                        kingTemp.setCol(6);
                        kingTemp.setHasMoved(true);
                        rookTemp.setHasMoved(true);
                        rookTemp.setRow(0);
                        rookTemp.setCol(5);
                    }
                }
                //queen side castle
                if((endRow == 0 && endCol == 2) && board.getPiece(0,0) != null && !board.getPiece(0,0).getHasMoved()){
                    if(board.getPiece(0,1)==null && board.getPiece(0,2) == null && board.getPiece(0,3)== null){
                        Piece kingTemp = p; 
                        Piece rookTemp = board.getPiece(0,0);
                        removePiece(p);
                        removePiece(board.getPiece(0,0));
                        insertPiece(0,2,kingTemp);
                        insertPiece(0,3,rookTemp);
                        kingTemp.setRow(0);
                        kingTemp.setCol(2);
                        kingTemp.setHasMoved(true);
                        rookTemp.setHasMoved(true);
                        rookTemp.setRow(0);
                        rookTemp.setCol(3);
                    }
                }
            }
        }
        if(p.isValidMove(endRow, endCol, board)){
            //captures if enPassant
            if(p instanceof Pawn && p.enPassant == true && getLastPiece() != null){
                board.removePiece(board.getLastPiece());
            }

            //getting the last moved piece's move and piece itself
            lastMovedFromRow = p.getRow(); 
            lastMovedFromCol = p.getCol();
            lastMovedPiece = p; 
            
            removePiece(p);

        
            if(board.getPiece(endRow, endCol)!=null){
                board.removePiece(board.getPiece(endRow, endCol));
            }

            p.setRow(endRow);
            p.setCol(endCol);
            insertPiece(endRow, endCol, p);
            p.setHasMoved(true);
            return true;
        }
        return false;
    }

     /** 
     * This method takes in a piece and determines if its movement to a specific row/col is a valid castle
     * @param endRow - ending row position for a Piece to be moved to
     * @param endCol - ending column position for a Piece to be moved to
     * @param p - object of type Piece to check to see if a castle is possible with that piece
     * @param board - object of type Board that stores current state of chess board and spots to move
     * @return boolean - returns true if castling is possible based on specified piece, endRow, endCol, otherwise returns false
     */
    public boolean canCastle(int endRow, int endCol, Piece p, Board board){
        if(p instanceof King && !p.getHasMoved()){
            if(p.getPlayer() == 0){
                //king side castle
                if((endRow == 7 && endCol == 6) && board.getPiece(7,7) != null && !board.getPiece(7,7).getHasMoved()){
                    if(board.getPiece(7,5)==null && board.getPiece(7,6)==null){
                        return true; 
                    }
                }
                //queen side castle
                if((endRow == 7 && endCol == 2) && board.getPiece(7,0) != null && !board.getPiece(7,0).getHasMoved()){
                    if(board.getPiece(7,1)==null && board.getPiece(7,2) == null && board.getPiece(7,3)== null){
                       return true; 
                    }
                }
            }
            else{
                if((endRow == 0 && endCol == 6) && board.getPiece(0,7) != null && !board.getPiece(0,7).getHasMoved()){
                    if(board.getPiece(0,5)==null && board.getPiece(0,6)==null){
                        return true; 
                    }
                }
                //queen side castle
                if((endRow == 0 && endCol == 2) && board.getPiece(0,0) != null && !board.getPiece(0,0).getHasMoved()){
                    if(board.getPiece(0,1)==null && board.getPiece(0,2) == null && board.getPiece(0,3)== null){
                        return true; 
                    }
                }
            }
        }
        return false; 

    }
    
     /** 
     * This method takes in a piece and determines if its movement to a specific row/col is a valid pawn promotion
     * @param p - object of type Piece to check to see if pawn promotion is possible with that piece
     * @param endRow - ending row position for a Piece to be moved to
     * @param endCol - ending column position for a Piece to be moved to
     * @return boolean - returns true if pawn promotion is possible based on specified piece, endRow, endCol, otherwise returns false
     */
    public boolean pawnPromotion(Piece p, int endRow, int endCol){
        if(p instanceof Pawn){
            if(p.getPlayer() == 0){
                if(endRow == 0){
                    if(p.isValidMove(endRow,endCol, this)){
                        return true; 
                    }
                }
            }
            else{
                if(endRow == 7){
                    if(p.isValidMove(endRow, endCol, this)){
                        return true; 
                    }
                }
            }
        }
        return false; 
    }

    /** 
     * This method returns the row of the piece which was moved last
     * @return int - returns the Row of the lastMoved Piece in the board
     */
    public int getLastRow(){
        return lastMovedFromRow;
    }

     /** 
     * This method returns the coloumn of the piece which was moved last
     * @return int - returns the column of the lastMoved Piece in the board
     */
    public int getLastCol(){
        return lastMovedFromCol; 
    }

     /** 
     * This method returns the piece which was moved last
     * @return Piece - returns an object of type Piece which was last moved 
     */
    public Piece getLastPiece(){
        return lastMovedPiece; 
    }

    /** 
     * This method returns an array list of all the possible moves of a given Piece p
     * @param p - takes an object of type Piece for which to determine all it's possible moved
     * @return ArrayList - returns an arraylist of strings containing all the possible moves of a given Piece
     */
    public ArrayList<String> getPossibleMoves(Piece p){

        ArrayList<String> moves = new ArrayList<>(); 
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                if(p.isValidMove(i,j,this)){
                    moves.add(i+""+j); 
                }
            }
        }

        return moves; 
    }

     /** 
     * This methods returns a string of the current location of the king of the specified player, either white or black
     * @param player - takes an input of an integer player representing the color, white or black, to find the location of the given player's king
     * @return String - returns a string of the row+col of the given player's king
     */
    public String findKing(int player){
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                if(board[i][j] instanceof King){
                    if(board[i][j].getPlayer() == player){
                        return i + "" + j;
                    }
                }
            }
        }
        return "";
    }

    /** 
     * this method returns true if a specific player,white or black, is in check or false otherwise
     * @param player - takes an input of an integer player representing the color, white or black, to determine if a given player is in Check
     * @return boolean - returns true if the player is in check, otherwise false
     */
    public boolean isCheck(int player){
        String location = findKing(player);
        int KingX = Integer.parseInt(location.substring(0,1));
        int KingY = Integer.parseInt(location.substring(1));    

        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                if(board[i][j]!=null && board[i][j].getPlayer() != player){
                    if(board[i][j].isValidMove(KingX, KingY, this)){
                        return true; 
                    }
                }
            }
        }
        return false; 
    }

    /** 
     * this method returns true if a specific player,white or black, is in checkmate or false otherwise
     * @param player - takes an input of an integer player representing the color, white or black, to determine if a given player is in Check mate
     * @return boolean - returns true if the player is in checkmate, otherwise false
     */
    public boolean isCheckMate(int player){
        //1. Find King's Possible moves
        //2. See if any of the opposing team's piece's are threatening those moves or not 
        //3. Check if any of the king's team's pieces can block the path of the current threatening piece
        //4. If 1 and 2 are true but 3 is false than it is checkmate 
        String location = findKing(player);
        int KingX = Integer.parseInt(location.substring(0,1));
        int KingY = Integer.parseInt(location.substring(1));
        Piece king = getPiece(KingX, KingY);

        if(isCheck(player)){
            ArrayList<String> moves = getPossibleMoves(king);
            boolean[] threatening = new boolean[moves.size()]; 
            for(int i = 0; i<moves.size(); i++){
                for(int a = 0; a<8; a++){
                    for(int b = 0; b<8; b++){
                        if(this.getPiece(a,b) != null && this.getPiece(a,b).getPlayer() !=player){
                            ArrayList<String> threateningMoves = getPossibleMoves(this.getPiece(a,b));
                            for(int x = 0; x<moves.size(); x++){
                                for(int y = 0; y<threateningMoves.size(); y++){
                                    if(moves.get(x).equals(threateningMoves.get(y))){
                                        threatening[x] = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        
            boolean kingCanMove = false;
            for(boolean b: threatening){
                if(b==false){
                    kingCanMove = true;
                }
            }

            if(!kingCanMove){
                boolean finalCheckMate = true; 
                Piece threat = getCheckingPiece(player);

                int rowDistance = Math.abs(KingX - threat.getRow());
                int colDistance = Math.abs(KingY - threat.getCol());
                if(threat instanceof Rook || threat instanceof Queen){
                    int startRow = threat.getRow();
                    int startCol = threat.getCol();
                    int rowDirection = Integer.signum(KingX - startRow);
                    int colDirection = Integer.signum(KingY - startCol);
                    for (int i = 1; i < Math.max(rowDistance, colDistance); i++) {
                        int row = startRow + i * rowDirection;
                        int col = startCol + i * colDirection;
                        if(playerCanMove(row,col,player)){
                            finalCheckMate = false; 
                        }
                    }
                }

                if(threat instanceof Bishop){
                    int startRow = threat.getRow();
                    int startCol = threat.getCol();
                    int rowDirection = Integer.signum(KingX - startRow);
                    int colDirection = Integer.signum(KingY - startCol);
                    for (int i = 1; i < rowDistance; i++) {
                        int row = startRow + i * rowDirection;
                        int col = startCol + i * colDirection;
                        if (playerCanMove(row,col,player)) {
                            finalCheckMate = false; 
                        }
                    }
                }

                //check if it can capture the piece or not
                if(playerCanMove(threat.getRow(), threat.getCol(),player)){
                    finalCheckMate = false; 
                }
                return finalCheckMate; 
               
            }

        }
        return false; 
    } 

     /** 
     * this method returns the piece that is currently threatening the given player, white or black.
     * @param player - takes an input of an integer player representing the color, white or black, to get the piece that is threatening the player's king
     * @return Piece - returns an object of type Piece that is currently threatening the king
     */
    public Piece getCheckingPiece(int player){
        String position = findKing(player);
        int kingX = Integer.parseInt(position.substring(0,1));
        int kingY = Integer.parseInt(position.substring(1));

        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                if(board[i][j]!=null){
                    Piece p = board[i][j];
                    if(p.getPlayer() !=  player && p.isValidMove(kingX, kingY, this)){
                        return p;
                    }
                }
            }
        }
        return null; 
         
    }

     /**
     * this method returns true if a given player, white or black, has any pieces that can move to a specified r,c. Otherwise, returns false 
     * @param r - row input to see if a player, white or black, has any pieces that can move to the given row, r
     * @param c - column input to see if a player, white or black, has any pieces that can move to the given col, c
     * @param player - integer of either 0 or 1, where 0 represents white and 1 represents black
     * @return boolean - returns true if any pieces of the player can move to a specific location r,c
     */
    public boolean playerCanMove(int r, int c, int player){
        
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                if(board[i][j] != null && board[i][j].getPlayer() == player){
                    if(board[i][j].isValidMove(r,c,this)){
                        return true; 
                    }
                }
            }
        }

        return false; 
    }

     /** 
     * This method returns true if a given piece can legally move to a specified location and returns false if moving results in check
     * @param r - row for a specfic piece to be moved to
     * @param c - column for a specfic piece to be moved to
     * @param p - object of type Piece to test if p can be moved to the corresponding r,c without reulting in check
     * @return boolean - returns true if Piece p can legally move to r,c. Returns false if moving results in a check
     */
    public boolean testMove(int r, int c, Piece p){
          //making sure you can't move piece unless move stops the current check 
          int startRow = p.getRow();
          int startCol = p.getCol();
          boolean isCheck = false; 
          Piece temp = null; 
  
          //checks if endRow and endCol are occupied by enemy piece if so saves it's instance and reupdates it when isCheck is true; 
          if(getPiece(r,c)!=null && this.getPiece(r,c).getPlayer() != p.getPlayer()){
              temp = getPiece(r,c);
          }
  
          insertPiece(startRow, startCol, null);
          insertPiece(r,c,p);
          p.setRow(r);
          p.setCol(c);
          isCheck = isCheck(p.getPlayer());
          
          if(isCheck){
              p.setRow(startRow);
              p.setCol(startCol);
              insertPiece(r,c,null);
              insertPiece(startRow,startCol,p);
              insertPiece(r,c,temp);
              return false; 
          }
          
          //reupdate board back to normal 
          p.setRow(startRow);
          p.setCol(startCol);
          insertPiece(r,c,null);
          insertPiece(startRow,startCol,p);
          insertPiece(r,c,temp);
          return true; 
    }
}