package edu.jsu.mcis;

public class TicTacToeModel {
    
    private Mark[][] board; /* Game board */
    private boolean xTurn;  /* True if X is current player */
    private int width;      /* Size of game board */
    
    /* ENUM TYPE DEFINITIONS */
    
    /* Mark (represents X, O, or an empty square */
    
    public enum Mark {
        
        X("X"), 
        O("O"), 
        EMPTY("-");

        private String message;
        
        private Mark(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* Result (represents the final state of the game: X wins, O wins, a TIE,
       or NONE if the game is not yet over) */
    
    public enum Result {
        
        X("X"), 
        O("O"), 
        TIE("TIE"), 
        NONE("NONE");

        private String message;
        
        private Result(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel() {
        
        this(TicTacToe.DEFAULT_WIDTH);
        
    }
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel(int width) {
        
        /* Initialize width; X goes first */
        
        this.width = width;
        xTurn = true;
        
        /* Create board (width x width) as a 2D Mark array */
        
        board = new Mark[width][width];

        /* Initialize board by filling every square with empty marks */
        
        // INSERT YOUR CODE HERE
        for(int i = 0, i < width, i++)
        {
            for(int j = 0, j < width, i++)
            {
                board[i][j] = Mark.EMPTY;
            }
        }
    }
	
    public boolean makeMark(int row, int col) {
        
        /* Use "isValidSquare()" to check if the specified location is in range,
           and use "isSquareMarked()" to see if the square is empty!  If the
           specified location is valid, make a mark for the current player, then
           toggle "xTurn" from true to false (or vice-versa) to switch to the
           other player before returning TRUE.  Otherwise, return FALSE. */
        
        // INSERT YOUR CODE HERE
        if (isValidSquare(row, col) && !isSquareMarked(row, col)) {

            if (isXTurn()) {

                board[row][col] = Mark.X;

                xTurn = false;

            } else {

                board[row][col] = Mark.O;

                xTurn = true;

            }
            return true;
        
        }
         return false;
    }
  
    }
	
    private boolean isValidSquare(int row, int col) {
        
        /* Return TRUE if the specified location is within the bounds of the board */
        
        // INSERT YOUR CODE HERE
        if (row < size && row > -1 && col < size && col > -1) {

            return true;

        } else {

            return false;
        }
        
    }
	
    private boolean isSquareMarked(int row, int col) {
        
        /* Return TRUE if the square at specified location is marked */
        
        // INSERT YOUR CODE HERE
        if (!board[row][col].equals(Mark.EMPTY)) {

            return true;

        } else {

            return false;
        }  
        
    }
	
    public Mark getMark(int row, int col) {
        
        /* Return the mark from the square at the specified location */
        
        // INSERT YOUR CODE HERE
        return board[row][col];
            
    }
	
    public Result getResult() {
        
        /* Call "isMarkWin()" to see if X or O is the winner, if the game is a
           TIE, or if the game is not over.  Return the corresponding Result
           value */
        
        // INSERT YOUR CODE HERE
        if (isXTurn()) {

            if (isMarkWin(Mark.O)) {

                return Result.O;

            }

        } else if (!isXTurn()) {

            if (isMarkWin(Mark.X)) {

                return Result.X;

            }

        }
        
    }
	
    private boolean isMarkWin(Mark mark) {
        
        /* Check the squares of the board to see if the specified mark is the
           winner */
        
        // INSERT YOUR CODE HERE
        int currentRow = 0;
        int currentColumn = 0;
        int horizontalCounter = 0;
        int verticalCounter = 0;
        int diagonalCounterLTR = 0;
        int countedTopLeft = 0;
        int countedBottomRight = 0;
        int diagonalCounterRTL = 0;
        int countedTopRight = 0;
        int countedBottomLeft = 0;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {

                if (horizontalCounter != width) {
                    if (currentRow == i) {
                        if (board[i][j].equals(mark)) {
                            horizontalCounter++;
                        }
                    } else {
                        currentRow = i;
                        horizontalCounter = 0;
                        
                        if (board[i][j].equals(mark)) {

                            horizontalCounter++;
                        }
                    }
                }
            
                if (verticalCounter != width) {
                    if (currentColumn == i) {
                        if (board[j][i].equals(mark)) {
                            verticalCounter++;
                        }
                    } else {
                        currentColumn = i;
                        verticalCounter = 0;

                        if (board[j][i].equals(mark)) {
                            verticalCounter++;
                        }

                    }

                }
               
                if (diagonalCounterLTR != width) {

                    if (board[0][0].equals(mark) && countedTopLeft == 0) {
                        diagonalCounterLTR++;
                        countedTopLeft++;

                    }
                    if (board[width - 1][width - 1].equals(mark) && countedBottomRight == 0) {
                        diagonalCounterLTR++;
                        countedBottomRight++;
                    }
                    if (i < width - 1 && i > 0 && j < width - 1 && j > 0) {
                        if (board[i][j].equals(board[i + 1][j + 1]) && board[i][j].equals(mark)) {
                            diagonalCounterLTR++;

                        }

                    }

                }
                
                if (diagonalCounterRTL != width) {
                    if (board[0][width - 1].equals(mark) && countedTopRight == 0) {
                        diagonalCounterRTL++;
                        countedTopRight++;
                    }
                    if (board[width - 1][0].equals(mark) && countedBottomLeft == 0) {
                        diagonalCounterRTL++;
                        countedBottomLeft++;
                    }
                    if (i < width - 1 && i > 0 && j < width - 1 && j > 0) {

                        if (board[i][j].equals(board[i + 1][j - 1]) && board[i][j].equals(mark)) {
                            diagonalCounterRTL++;
                        }

                    }

                }

            }

        }

        if (horizontalCounter == width) {
            return true;
        } else if (verticalCounter == width) {
            return true;
        } else if (diagonalCounterLTR == width) {
            return true;
        } else if (diagonalCounterRTL == width) {
            return true;
        } else {
            return false;

        }
        
    }	
    private boolean isTie() {
        
        /* Check the squares of the board to see if the game is a tie */
        
        // INSERT YOUR CODE HERE

        if (numTurns == (width * width)) {
            if (!isMarkWin(Mark.X) && !isMarkWin(Mark.O)) {
                return true;
            }
        }
        return false; 
    }

    public boolean isGameover() {
        
        /* Return TRUE if the game is over */
        
        return (Result.NONE != getResult());
        
    }

    public boolean isXTurn() {
        
        /* Getter for xTurn */
        
        return xTurn;
        
    }

    public int getWidth() {
        
        /* Getter for width */
        
        return width;
        
    }
    
    @Override
    public String toString() {
        
        StringBuilder output = new StringBuilder("  ");
        
        /* Output the board contents as a string (see examples) */
        
        // INSERT YOUR CODE HERE
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {

                if (i == 0 && j == 0) {
                    for (int c = 0; c < width; c++) {

                        output.append(c);
                    }
                    output.append("\n");
                }

                if (j == 0) {
                    output.append(i + " ");
                }

                output.append(board[i][j]);
            }
            output.append("\n");
        }

        return output.toString();
        
    }