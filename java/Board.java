public class Board {
    public static final String EMPTY_CELL = " ";
    public static final String PLAYER = "X";
    public static final String COMPUTER = "O";
    private static final String CELL_IS_NOT_EMPTY_ERR = "Cell is not empty";
    private String[][] state = new String[3][3];
    private int emptyCells = 9;

    public Board() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                state[i][j] = EMPTY_CELL;
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
        state[x][y] = value;
    }

    public String getCellValue(int x, int y) {
        return state[x][y];
    }

    public boolean isFull() {
        return emptyCells == 0;
    }

    public String getWinner() {
        for (int i = 0; i < 3; i++) {
            if (state[i][0].equals(state[i][1]) && state[i][1].equals(state[i][2])) {
                return state[i][0];
            }
        }
        for (int i = 0; i < 3; i++) {
            if (state[0][i].equals(state[1][i]) && state[1][i].equals(state[2][i])) {
                return state[0][i];
            }
        }
        if (state[0][0].equals(state[1][1]) && state[1][1].equals(state[2][2])) {
            return state[0][0];
        }
        if (state[0][2].equals(state[1][1]) && state[1][1].equals(state[2][0])) {
            return state[0][2];
        }
        return EMPTY_CELL;
    }

    public void printBoard() {
        System.out.println();
        System.out.println("    0   1   2");
        System.out.println("0 | " + state[0][0] + " | " + state[0][1] + " | " + state[0][2] + " |");
        System.out.println("  |---|---|---|");
        System.out.println("1 | " + state[1][0] + " | " + state[1][1] + " | " + state[1][2] + " |");
        System.out.println("  |---|---|---|");
        System.out.println("2 | " + state[2][0] + " | " + state[2][1] + " | " + state[2][2] + " |");
        System.out.println();
    }
}
