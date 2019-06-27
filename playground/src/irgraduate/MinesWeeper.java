import java.util.Scanner;

/**
 * Created by Ryan on 2017/4/10.
 */
public class MinesWeeper {
    private int row;
    private int column;
    private String[][] strings;
    private Grid[][] board;


    /**
     * Constructor of the game
     * @param row - row of the board
     * @param column - column of the board
     * @param s - the actual situation of the board stored in a two-dimension array
     * @throws RuntimeException 0 < n,m < 100
     */
    public MinesWeeper(int row, int column, String[][] s) throws RuntimeException {
        if (row < 0 || row > 100 || column > 100 || column < 0)
            throw new RuntimeException();
        this.row = row;
        this.column = column;
        strings = s;
        board = new Grid[row][column];
        init();
        setNumber();
        show();
    }


    public void init() {
        for (int i = 0; i < row ; i++) {
            for (int j = 0; j < column ; j++) {
                board[i][j] = new Grid(strings[i][j]);
            }
        }
    }

    public void setNumber() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (board[i][j].getAdjacentMine() == -1) {

                    for (int r = -1; r < 2; r++)
                        for (int c = -1; c < 2; c++) {
                            int rBorder = r + i;
                            int cBorder = c + j;

                            if (rBorder >= 0 && rBorder < row
                                    && cBorder >= 0 && cBorder < column &&
                                    board[rBorder][cBorder].getAdjacentMine() != -1)
                                board[rBorder][cBorder].increment();
                        }
                }
            }
        }
    }

    public void show() {
        for (int i = 0; i < row ; i++) {
            for (int j = 0; j < column; j++) {
                if (j ==  column - 1)
                    System.out.println(board[i][j]);
                else
                    System.out.print(board[i][j]);
            }
        }
    }

    class Grid {
        private String mineLabel;
        private int adjacentMine = 0; // -1 is mine, other numbers are the adjacent mines around this grid

        public Grid(String mineLabel) {
            this.mineLabel = mineLabel;
            setMine();
        }

        public void setMine() {
            if (mineLabel.equals("*"))
                adjacentMine = -1;
        }

        public void increment() {
            adjacentMine++;
        }

        public int getAdjacentMine() {
            return adjacentMine;
        }

        @Override
        public String toString() {
            if (adjacentMine == -1)
                return "*";
            return "" + adjacentMine;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int column = scanner.nextInt();
        scanner.nextLine();
        String[][] strings = new String[row][column];
        for (int i = 0; i < row ; i++) {
            strings[i] = scanner.nextLine().split("");
        }
        new MinesWeeper(row,column,strings);
    }
}
