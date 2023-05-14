import java.util.Random;

public class Main {
    private static Board board = new Board();
    private static Random random = new Random();

    public static void main(String[] args) {
        String winner;
        do {
            board.printBoard();
            processPlayerMove();
            winner = board.getWinner();
            if (!Board.EMPTY_CELL.equals(winner) || board.isFull()) {
                break;
            }
            processComputerMove();
            winner = board.getWinner();
        } while (!board.isFull() && Board.EMPTY_CELL.equals(winner));

        board.printBoard();
        if (Board.EMPTY_CELL.equals(winner)) {
            System.out.println("It's a tie!");
        } else if (Board.PLAYER.equals(winner)) {
           System.out.println("You won!");
        } else {
            System.out.println("You lost!");
        }
    }

    public static void processPlayerMove() {
        int x = -1;
        int y = -1;
        boolean invalidInput = true;
        do {
            try {
                System.out.printf("Enter row: ");
                x = Integer.parseInt(System.console().readLine());
                System.out.printf("Enter col: ");
                y = Integer.parseInt(System.console().readLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
                continue;
            }

            if (x < 0 || x > 2 || y < 0 || y > 2) {
                System.out.println("Invalid input");
                continue;
            } else if (!board.isCellEmpty(x, y)) {
                System.out.println("Cell is not empty");
                continue;
            }

            invalidInput = false;
        } while (invalidInput);
        board.setBoard(x, y, Board.PLAYER);
    }

    public static void processComputerMove() {
        int x = -1;
        int y = -1;
        boolean invalidInput = true;
        do {
            x = random.nextInt(3);
            y = random.nextInt(3);

            if (board.isCellEmpty(x, y)) {
                invalidInput = false;
            }
        } while (invalidInput);
        board.setBoard(x, y, Board.COMPUTER);
    }
}
  