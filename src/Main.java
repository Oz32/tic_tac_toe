import java.util.Scanner;

public class Main {
    static int turn = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] game = new char[9];
        for (int i = 0; i < game.length; i++) {
            game[i] = ' ';
        }
        fillMap(game);
        while (true) {
            checkAndPlace(game);
            if (result(game).equals("Draw") || result(game).equals("X wins") || result(game).equals("O wins")) {
                System.out.println(result(game));
                break;
            }
        }
    }

    public static void fillMap(char[] game) {
        System.out.println("---------");
        System.out.printf("| %c %c %c |\n", game[0], game[1], game[2]);
        System.out.printf("| %c %c %c |\n", game[3], game[4], game[5]);
        System.out.printf("| %c %c %c |\n", game[6], game[7], game[8]);
        System.out.println("---------");
    }

    public static void checkAndPlace(char[] game) {
        while (true) {
            int coordinata = 0;
            Scanner scanner = new Scanner(System.in);
            String userCoordinates = scanner.nextLine();
            char rowInputChar = userCoordinates.charAt(0);
            char placeInputChar = userCoordinates.charAt(2);
            boolean flag = false;
            if (!Character.isDigit(rowInputChar) || !Character.isDigit(placeInputChar)) {
                System.out.println("You should enter numbers!");
                continue;
            }
            int rowInput = Integer.parseInt(String.valueOf(rowInputChar));
            int placeInput = Integer.parseInt(String.valueOf(placeInputChar));
            if (rowInput < 0 || rowInput > 3 || placeInput < 0 || placeInput > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            if (rowInput == 1) {
                coordinata = rowInput + placeInput - 2;
            } else if (rowInput == 2) {
                coordinata = rowInput + placeInput;
            } else if (rowInput == 3) {
                coordinata = rowInput + placeInput + 2;
            }
            for (int i = 0; i < game.length; i++) {
                if (game[coordinata] == 'X' || game[coordinata] == 'O') {
                    System.out.println("This cell is occupied! Choose another one!");
                    break;
                }
                if (turn % 2 == 0) {
                    game[coordinata] = 'X';
                    flag = true;
                    fillMap(game);
                    turn++;
                    break;
                } else {
                    game[coordinata] = 'O';
                    flag = true;
                    fillMap(game);
                    turn++;
                    break;
                }
            }
            if (flag) {
                return;
            }
        }
    }

    public static String result(char[] game) {
        String result = "";
        int countX = 0;
        int countO = 0;
        int countSpace = 0;
        boolean countXwin = false;
        boolean countOwin = false;

        for (int i = 0; i < game.length; i++) {
            if (game[i] == 'X') {
                countX++;
            } else if (game[i] == 'O') {
                countO++;
            } else if (game[i] == ' ' || game[i] == '_') {
                countSpace++;
            }
        }

        if (game[0] == 'X' && game[1] == 'X' && game[2] == 'X' ||
                game[3] == 'X' && game[4] == 'X' && game[5] == 'X' ||
                game[6] == 'X' && game[7] == 'X' && game[8] == 'X' ||
                game[0] == 'X' && game[3] == 'X' && game[6] == 'X' ||
                game[1] == 'X' && game[4] == 'X' && game[7] == 'X' ||
                game[2] == 'X' && game[5] == 'X' && game[8] == 'X' ||
                game[0] == 'X' && game[4] == 'X' && game[8] == 'X' ||
                game[2] == 'X' && game[4] == 'X' && game[6] == 'X') {
            countXwin = true;
        }

        if (game[0] == 'O' && game[1] == 'O' && game[2] == 'O' ||
                game[3] == 'O' && game[4] == 'O' && game[5] == 'O' ||
                game[6] == 'O' && game[7] == 'O' && game[8] == 'O' ||
                game[0] == 'O' && game[3] == 'O' && game[6] == 'O' ||
                game[1] == 'O' && game[4] == 'O' && game[7] == 'O' ||
                game[2] == 'O' && game[5] == 'O' && game[8] == 'O' ||
                game[0] == 'O' && game[4] == 'O' && game[8] == 'O' ||
                game[2] == 'O' && game[4] == 'O' && game[6] == 'O') {
            countOwin = true;
        }

        if (!countOwin && !countXwin && countSpace == 0) {
            result = "Draw";
        } else if (countOwin) {
            result = "O wins";
        } else if (countXwin) {
            result = "X wins";
        }
        return result;
    }
}