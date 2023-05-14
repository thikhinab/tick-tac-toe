public class Board {
    public static final String EMPTY_CELL = " ";
    public static final String PLAYER = "X";
    public static final String COMPUTER = "O";
    private static final String CELL_IS_NOT_EMPTY_ERR = "Cell is not empty";
    private String[][] board = new String[3][3];
    private int emptyCells = 9;

    public Board() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY_CELL;
            }
        }
    }

    public boolean isCellEmpty(int x, int y) {
        return EMPTY_CELL.equals(getCellValue(x, y));
    }

    public void setBoard(int x, int y, String value) {
        if (!EMPTY_CELL.equals(getCellValue(x, y))) {
            throw new IllegalArgumentException(CELL_IS_NOT_EMPTY_ERR);
        }
        emptyCells--;
        board[x][y] = value;
    }

    public String getCellValue(int x, int y) {
        return board[x][y];
    }

    public boolean isFull() {
        return emptyCells == 0;
    }

    public String getWinner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) {
                return board[i][0];
            }
        }
        for (int i = 0; i < 3; i++) {
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])) {
                return board[0][i];
            }
        }
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            return board[0][0];
        }
        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
            return board[0][2];
        }
        return EMPTY_CELL;
    }

    public void printBoard() {
        System.out.println();
        System.out.println("    0   1   2");
        System.out.println("0 | " + board[0][0] + " | " + board[0][1] + " | " + board[0][2] + " |");
        System.out.println("  |---|---|---|");
        System.out.println("1 | " + board[1][0] + " | " + board[1][1] + " | " + board[1][2] + " |");
        System.out.println("  |---|---|---|");
        System.out.println("2 | " + board[2][0] + " | " + board[2][1] + " | " + board[2][2] + " |");
        System.out.println();
    }
}
