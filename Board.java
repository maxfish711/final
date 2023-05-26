
public class Board  {


    private int rows;
    private int cols;
    
    /** The grid of pieces */
    public Player[][] grid;
    
    

    public Board(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        grid = new Player[rows][cols];
        // set each cell of the board to null (empty).
        reset();

    }
    
    public void reset() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = null;
            }
        }
    }
    
    public int getRows() {
        return rows;
    }
    
    public int getCols() {
        return cols;
    }
    
    
    public boolean boardFull(){ 

        int counter = 0;

        for(int c = 0; c < cols; c++){
                if(grid[rows - 1][c] != null){
                    counter++;
                }
                if(counter == 8){
                    return true;
                }
            }
        
        return false;
    }

       
    
    /**
    * Returns the Player whose piece occupies the given location, 
    * @param row int
    * @param col int
    */

    public Player getCell(int row, int col ) throws IndexOutOfBoundsException{
        if( (row < 0) || (col < 0) || (row >= rows) || (col >= cols) ) {
            throw new IndexOutOfBoundsException();
        } else {
            return grid[row][col];
        }
    }
    
    

    // Returns true if move is possible given board state.  
    public boolean possibleMove(Move move) {
        
        int c = move.getColumn();

        if(grid[rows-1][c] != null)
            return false;
        else {
            return true;
        }
    }
    
    // Adds a piece to the board for a given Move
    public void addPiece(Move move) {
        
        if(possibleMove(move) == true){
            for(int r = 0; r < rows; r++){
                 if(grid[r][move.getColumn()] == null){
                        grid[r][move.getColumn()] = move.getPlayer();
                            break;
                 }      
            }
        }
    }
        
    
    public Player winner(Move lastMove) {
        if (columnWin(lastMove) || rowWin(lastMove) || diagonalWin(lastMove)){
            return lastMove.getPlayer();
        } else {
            return null;
        }
    }

    public boolean columnWin(Move lastMove) {

       boolean win = false;
       int counter = 0;

       for(int c = 0; c < cols; c++){
            for(int r = 0; r < rows - 1; r++){
                if(grid[r][c] == grid[r+1][c] && grid[r][c] != null){
                    counter++;
                    if(counter >= 3){
                        win = true;
                    }
                }

                else{
                    counter = 0;
                }
            }

       }

       return win;
    
    }

    
    public boolean rowWin(Move lastMove){

        boolean win = false;
        int counter = 0;
      
        for(int r = 0; r < rows; r++){
             for(int c = 0; c < cols - 1; c++){
                 if(grid[r][c] == grid[r][c+1] && grid[r][c] != null){
                        counter++;
                     if(counter >= 3){
                         win = true;
                     }
                 }
 
                 else{
                     counter = 0;
                 }
             }
 
        }
 
            return win;
    }

    

    public boolean diagonalWin(Move lastMove) {
        
        boolean win = false;

        for(int r = 0; r < rows - 3;r++){
            for(int c = 0; c < cols - 3;c++){
                if(grid[r][c] == grid[r+1][c+1] && grid[r+1][c+1] == grid[r+2][c+2] && grid[r+2][c+2] == grid[r+3][c+3] && grid[r][c] != null){
                    win = true;
                }
            }
        }

        for(int r = rows - 1; r >= 3;r--){
            for(int c = 0; c < cols - 3;c++){
                if(grid[r][c] == grid[r-1][c+1] && grid[r-1][c+1] == grid[r-2][c+2] && grid[r-2][c+2] == grid[r-3][c+3] && grid[r][c] != null){
                    win = true;
                }
            }
        }

       return win;
    }
    

}




