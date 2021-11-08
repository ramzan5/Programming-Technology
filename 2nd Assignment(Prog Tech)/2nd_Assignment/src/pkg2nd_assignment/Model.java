
package pkg2nd_assignment;

/**
 *
 * @author muhammad
 */
public class Model {

    private int rows;
    private int columns;
    
    private int lastcolumn;
    private int lastrow;
    
    private Player actualPlayer;

    private Player[][] table;

    public Model(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.lastrow = 0;
        this.lastcolumn = 0;
        actualPlayer = Player.X;

        table = new Player[rows][columns];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                table[i][j] = Player.NOBODY;
            }
        }
    }

    public Player step(int row, int column) {
        if (table[row][column] != Player.NOBODY) {
            return table[row][column];
        }
        int i;
        for(i =rows-1;i>=0;--i){
            if(table[i][column] == Player.NOBODY){
                table[i][column] = actualPlayer;
                break;
            }
        }

        if (actualPlayer == Player.X) {
            actualPlayer = Player.O;
        } else {
            actualPlayer = Player.X;
        }
        lastrow = i;
        lastcolumn = column;
        return table[i][column];
    }
    //getter for last row
    public int getLastrow() {
        return lastrow;
    }
    //getter for last coloumn
    public int getLastcolumn() {
        return lastcolumn;
    }

    public Player findWinner() {
        //we will check for 4 across the rows
        for(int row = 0; row<rows; row++){
            for (int col = 0;col < columns - 3;col++){
                if (table[row][col] != Player.NOBODY && table[row][col] == table[row][col+1] && 
                        table[row][col+1] == table[row][col+2] &&
                        table[row][col+2] == table[row][col+3] &&
                        table[row][col+3] == table[row][col]){
                        return table[row][col];
                }
            }			
        }
        //we will check for 4 across the coloumns
        for(int row = 0; row < rows - 3; row++){
            for(int col = 0; col < columns; col++){
                if (table[row][col] != Player.NOBODY && table[row][col] == table[row+1][col] && 
                        table[row+1][col] == table[row+2][col] &&
                        table[row+2][col] == table[row+3][col] &&
                        table[row+3][col] == table[row][col]){
                        return table[row][col];
                }
            }
        }
        //we will check across the  diagonals of the board
        for(int row = 3; row < rows; row++){
            for(int col = 0; col < columns - 3; col++){
                if (table[row][col] != Player.NOBODY && table[row][col] == table[row-1][col+1] && 
                        table[row-1][col+1] == table[row-2][col+2] &&
                        table[row-2][col+2] == table[row-3][col+3] &&
                        table[row-3][col+3] == table[row][col]){
                        return table[row][col];
                }
            }
        }
        //check downward diagonal
        for(int row = 0; row < rows - 3; row++){
            for(int col = 0; col < columns - 3; col++){
                if (table[row][col] != Player.NOBODY && table[row][col] == table[row+1][col+1] && 
                        table[row+1][col+1] == table[row+2][col+2] &&
                        table[row+2][col+2] == table[row+3][col+3] &&
                        table[row+3][col+3] == table[row][col]){
                        return table[row][col];
                }
            }
        }
        return Player.NOBODY;
    }
    //this function will check for the draw game
    public Boolean draw() {
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < columns; col++){
                if(table[row][col] == Player.NOBODY){
                    return false;
                }
            }
        }
        return true;
    }

    ///functon for returning the actua player
    public Player getActualPlayer() {
        return actualPlayer;
    }

}