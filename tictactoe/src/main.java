import java.util.Scanner;

public class main {
    public static boolean playerX = false;
    public static boolean win = false;
    public static boolean tie = false;
    public static final int ROWS = 3, COLS = 3;
    public static String[][] board = new String[ROWS][COLS];
    public static boolean check = false;

    public static int rowActual;
    public static int colActual;

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        do{
            boardInit();
            System.out.println("**Console TicTacToe**");
            System.out.println("1) PvP");
            System.out.println("2) PvE");
            System.out.println("3) Exit");
            switch (scn.nextInt()){
                case 1:
                    gamePCvsPC();
                    break;
                case 2:
                    System.out.println("Zatím není dokončeno!");
                    break;
                case 3:
                    System.exit(0);
            }
        }while (true);
    }

    public static void gamePCvsPC(){
        boolean continueTicTacToe = true;

        do {
            boardPrint();
            System.out.println();
            gameInput();
            win = checkWin(playerX, rowActual, colActual);
            tie = checkTie();

            if (win == true || tie == true) {
                continueTicTacToe = false;
            }

        } while (continueTicTacToe == true);

        boardPrint();

        if (win == true) {
            String znak;
            if (playerX == true){
                znak = "O";
            }else{
                znak = "X";
            }
            System.out.println("Vyhrál hráč: " + znak);
        }else if (tie == true) {
            System.out.println("Remíza.");
        }
    }

    public static void boardInit() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = "_";
            }
        }
    }


    public static void gameInput() {
        Scanner scn = new Scanner(System.in);
        String znak;
        if (playerX == true){
            znak = "X";
        }else{
            znak = "O";
        }

        System.out.println("Hraje hráč: " + znak);
        System.out.println("Zadejte hodnotu sloupce: ");
        int valueCOL = scn.nextInt();
        System.out.println("Zadejte hodnotu řádku: ");
        int valueROW = scn.nextInt();

        checkValues(valueROW, valueCOL);

        if (check == false) {
            if (playerX == true) {
                move(--valueROW, --valueCOL, "X");
            } else if(playerX == false){
                move(--valueROW, --valueCOL, "O");
            }
        }
        check = false;
    }

    public static void checkValues(int valueROW, int valueCOL){
        if (valueROW > ROWS || valueROW < 1){
            gameInput();
            check = true;
        } else if (valueCOL > COLS || valueCOL < 1){
            gameInput();
            check = true;
        }
    }

    public static void move(int row, int column, String value) {
        if (board[row][column] == "_") {
            board[row][column] = value;
            rowActual = row;
            colActual = column;
            if (playerX == true) {
                playerX = false;
            }else{
                playerX = true;
            }
        }else{
            System.out.println("Pole je již obsazeno!");
            gameInput();
        }
    }

    public static void boardPrint() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {

                System.out.print(board[i][j]);

                if(j != COLS - 1){
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i != ROWS - 1) {
                System.out.println("-----");
            }


        }
    }

    public static boolean checkWin(boolean playerX, int row, int col){
        String znak;
        if (playerX == true){
            znak = "O";
        }else{
            znak = "X";
        }

        return (board[row][0] == znak && board[row][1] == znak && board[row][2] == znak ||
                board[0][col] == znak && board[1][col] == znak && board[2][col] == znak ||
                row == col && board[0][0] == znak && board[1][1] == znak && board[2][2] == znak ||
                row + col == 2 && board[0][2] == znak && board[1][1] == znak && board[2][0] == znak);
    }

    public static boolean checkTie(){
        int counter = 0;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j] == "X" || board[i][j] == "O") {
                    counter++;
                }
            }
        }
        if (counter == 9) {
            return true;
        }else{
            return false;
        }
    }

}
