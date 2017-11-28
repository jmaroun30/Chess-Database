import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PgnReader {

    public static String tagValue(String tagName, String game) {
        if (game.contains(tagName)) {
            int ind = game.indexOf(tagName);
            ind = ind + tagName.length() + 2;
            int last = game.indexOf(']', ind) - 1;
            String tag = game.substring(ind, last);
            return tag;
        } else {
            return "NOT GIVEN";
        }
    }

    public static String finalPosition(String game) {
        String[][] board = {{"R", "N", "B", "Q", "K", "B", "N", "R"},
            {"P", "P", "P", "P", "P", "P", "P", "P"},
            {"1", "1", "1", "1", "1", "1", "1", "1"},
            {"1", "1", "1", "1", "1", "1", "1", "1"},
            {"1", "1", "1", "1", "1", "1", "1", "1"},
            {"1", "1", "1", "1", "1", "1", "1", "1"},
            {"p", "p", "p", "p", "p", "p", "p", "p"},
            {"r", "n", "b", "q", "k", "b", "n", "r"}
        };

        String[] lines = game.split("\n");
        String subLines = "";

        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("1.") && !lines[i].contains("11.")) {
                subLines = lines[i];
                for (int j = i + 1; j < lines.length; j++) {
                    subLines = subLines + " " + lines[j];
                }
            }
        }

        String[] moves = subLines.split(" ");

        for (int k = 0; k < moves.length; k++) {
            String color = "";
            if (moves[k].contains("+") || moves[k].contains("#")) {
                moves[k] = moves[k].substring(0 , moves[k].length() - 1);
            }
            moves[k] = moves[k].replace("?", "");
            moves[k] = moves[k].replace("!", "");

            if (moves[k].contains(".")) {
                int lol = 4;
            } else {
                String type = identifier(moves[k]);
                if (k % 3 == 1) {
                    color = "White";
                } else {
                    color = "Black";
                }
                int file = (int) (moves[k].charAt(moves[k].length() - 2)) - 97;
                int rank = (int) (moves[k].charAt(moves[k].length() - 1)) - 49;
                if (moves[k].contains("=")) {
                    file = (int) (moves[k].charAt(moves[k].length() - 4)) - 97;
                    rank = (int) (moves[k].charAt(moves[k].length() - 3)) - 49;
                }
                if (type.equals("Pawn")) {
                    pawnMover(board, color, file, rank, moves[k]);
                } else if (type.equals("Rook")) {
                    rookMover(board, color, file, rank);
                } else if (type.equals("Bishop")) {
                    bishopMover(board, color, file, rank);
                } else if (type.equals("Knight")) {
                    knightMover(board, color, file, rank);
                } else if (type.equals("King")) {
                    kingMover(board, color, file, rank);
                } else if (type.equals("Queen")) {
                    queenMover(board, color, file, rank);
                } else if (type.equals("Castle")) {
                    castleMover(board, color, moves[k]);
                }
            }
        }
        return converter(board);
    }

    public static String identifier(String move) {
        if (move.substring(0 , 1).equals("R")) {
            return "Rook";
        } else if (move.substring(0 , 1).equals("B")) {
            return "Bishop";
        } else if (move.substring(0 , 1).equals("N")) {
            return "Knight";
        } else if (move.substring(0 , 1).equals("K")) {
            return "King";
        } else if (move.substring(0 , 1).equals("Q")) {
            return "Queen";
        } else if (move.substring(0 , 1).equals("O")) {
            return "Castle";
        } else  if ((int) (move.charAt(0)) >= 96 && (int)
            (move.charAt(0)) <= 104) {
            return "Pawn";
        } else {
            return "";
        }
    }

    public static void pawnMover(String[][] board, String color, int file,
        int rank, String move) {
        if (color.equals("Black")) {
            if (rank == 4 && board[rank + 2][file].equals("p")) {
                board[rank + 2][file] = "1";
                board[rank][file] = "p";
            } else if (move.contains("x") && move.contains("=")) {
                if ((move.charAt(0)) == ('x')) {
                    if (file != 0 && board[rank + 1][file - 1].equals("p")) {
                        board[rank + 1][file - 1] = "1";
                    } else {
                        board[rank + 1][file + 1] = "1";
                    }
                    board[rank][file] = move.substring(4, 5);
                } else {
                    int newFile = (int) (move.charAt(0) - 97);
                    board[rank + 1][newFile] = "1";
                    board[rank][file] = move.substring(5, 6);
                }
            } else if (move.contains("x")) {
                if (rank + 1 == 3) {
                    if (board[rank + 1][file].equals("P")) {
                        board[rank + 1][file] = "1";
                    }
                }
                if ((move.charAt(0)) == ('x')) {
                    if (file != 0 && board[rank + 1][file - 1].equals("p")) {
                        board[rank + 1][file - 1] = "1";
                    } else {
                        board[rank + 1][file + 1] = "1";
                    }
                    board[rank][file] = "p";
                } else {
                    int newFile = (int) (move.charAt(0) - 97);
                    board[rank + 1][newFile] = "1";
                    board[rank][file] = "p";
                }
            } else if (move.contains("=")) {
                board[rank + 1][file] = "1";
                board[rank][file] = move.substring(3, 4);
            } else {
                board[rank][file] = "p";
                board[rank + 1][file] = "1";
            }
        } else {
            if (rank == 3 && board[rank - 2][file].equals("P")) {
                board[rank - 2][file] = "1";
                board[rank][file] = "P";
            } else if (move.contains("x") && move.contains("=")) {
                if ((move.charAt(0)) == ('x')) {
                    if (file != 0 && board[rank - 1][file - 1].equals("P")) {
                        board[rank - 1][file - 1] = "1";
                    } else {
                        board[rank - 1][file + 1] = "1";
                    }
                    board[rank][file] = move.substring(4, 5);
                } else {
                    int newFile = (int) (move.charAt(0) - 97);
                    board[rank - 1][newFile] = "1";
                    board[rank][file] = move.substring(5, 6);
                }
            } else if (move.contains("x")) {
                if (rank - 1 == 4) {
                    if (board[rank - 1][file].equals("p")) {
                        board[rank - 1][file] = "1";
                    }
                }
                if ((move.charAt(0)) == ('x')) {
                    if (file != 0 && board[rank - 1][file - 1].equals("P")) {
                        board[rank - 1][file - 1] = "1";
                    } else {
                        board[rank - 1][file + 1] = "1";
                    }
                    board[rank][file] = "P";
                } else {
                    int newFile = (int) (move.charAt(0) - 97);
                    board[rank - 1][newFile] = "1";
                    board[rank][file] = "P";
                }
            } else if (move.contains("=")) {
                board[rank - 1][file] = "1";
                board[rank][file] = move.substring(3, 4);
            } else {
                board[rank][file] = "P";
                board[rank - 1][file] = "1";
            }
        }
    }

    public static void rookMover(String[][] board, String color, int file,
        int rank) {
        Boolean in = true;
        String piece;
        if (color.equals("Black")) {
            piece = "r";
        } else {
            piece = "R";
        }
        for (int row = rank + 1; row < 8; row++) {
            if (!board[row][file].equals(piece)
                && !board[row][file].equals("1")) {
                in = false;
            } else if (board[row][file].equals(piece) && in.equals(true)) {
                board[row][file].equals("1");
                in = false;
            }
        }
        in = true;
        for (int row = rank - 1; row >= 0; row--) {
            if (!board[row][file].equals(piece)
                && !board[row][file].equals("1")) {
                in = false;
            } else if (board[row][file].equals(piece) && in.equals(true)) {
                board[row][file].equals("1");
                in = false;
            }
        }
        in = true;
        for (int col = file + 1; col < 8; col++) {
            if (!board[rank][col].equals(piece)
                && !board[rank][col].equals("1")) {
                in = false;
            } else if (board[rank][col].equals(piece) && in.equals(true)) {
                board[rank][col].equals("1");
                in = false;
            }
        }
        in = true;
        for (int col = file - 1; col >= 0; col--) {
            if (!board[rank][col].equals(piece)
                && !board[rank][col].equals("1")) {
                in = false;
            } else if (board[rank][col].equals(piece) && in.equals(true)) {
                board[rank][col].equals("1");
                in = false;
            }
        }
        board[rank][file] = piece;
    }

    public static void bishopMover(String[][] board, String color, int file,
        int rank) {
        String piece;
        Boolean in = true;
        if (color.equals("Black")) {
            piece = "b";
        } else {
            piece = "B";
        }
        for (int i = rank + 1, j = file + 1; i < 8 && j < 8; i++, j++) {
            if (!board[i][j].equals(piece) && !board[i][j].equals("1")) {
                in = false;
            } else if (board[i][j].equals(piece) && in.equals(true)) {
                board[i][j] = "1";
                in = false;
            }
        }
        in = true;
        for (int i = rank - 1, j = file - 1; i >= 0 && j >= 0; i--, j--) {
            if (!board[i][j].equals(piece) && !board[i][j].equals("1")) {
                in = false;
            } else if (board[i][j].equals(piece) && in.equals(true)) {
                board[i][j] = "1";
                in = false;
            }
        }
        in = true;
        for (int i = rank + 1, j = file - 1; i < 8 && j >= 0; i++, j--) {
            if (!board[i][j].equals(piece) && !board[i][j].equals("1")) {
                in = false;
            } else if (board[i][j].equals(piece) && in.equals(true)) {
                board[i][j] = "1";
                in = false;
            }
        }
        in = true;
        for (int i = rank - 1, j = file + 1; i >= 0 && j < 8; i--, j++) {
            if (!board[i][j].equals(piece) && !board[i][j].equals("1")) {
                in = false;
            } else if (board[i][j].equals(piece) && in.equals(true)) {
                board[i][j] = "1";
                in = false;
            }
        }
        board[rank][file] = piece;
    }

    public static void knightMover(String[][] board, String color, int file,
        int rank) {
        String piece;
        if (color.equals("Black")) {
            piece = "n";
        } else {
            piece = "N";
        }
        if (rank + 2 < 8 && file - 1 >= 0) {
            if (board[rank + 2][file - 1].equals(piece)) {
                board[rank + 2][file - 1] = "1";
            } else {
                if (board[rank + 2][file + 1].equals(piece)) {
                    board[rank + 2][file + 1] = "1";
                }
            }
        }
        if (rank - 2 >= 0 && file - 1 >= 0) {
            if (board[rank - 2][file - 1].equals(piece)) {
                board[rank - 2][file - 1] = "1";
            } else {
                if (board[rank - 2][file + 1].equals(piece)) {
                    board[rank - 2][file + 1] = "1";
                }
            }
        }
        if (file + 2 < 8 && rank + 1 < 8) {
            if (board[rank + 1][file + 2].equals(piece)) {
                board[rank + 1][file + 2] = "1";
            } else {
                if (board[rank - 1][file + 2].equals(piece)) {
                    board[rank - 1][file + 2] = "1";
                }
            }
        }
        if (file - 2 >= 0 && rank + 1 < 8) {
            if (board[rank + 1][file - 2].equals(piece)) {
                board[rank + 1][file - 2] = "1";
            } else {
                if (board[rank - 1][file - 2].equals(piece)) {
                    board[rank - 1][file - 2] = "1";
                }
            }
        }
        board[rank][file] = piece;
    }

    public static void kingMover(String[][] board, String color, int file,
        int rank) {
        String piece;
        if (color.equals("Black")) {
            piece = "k";
        } else {
            piece = "K";
        }
        if (rank + 1 < 8) {
            if (board[rank + 1][file].equals(piece)) {
                board[rank + 1][file] = "1";
            } else if (file + 1 < 8) {
                if (board[rank + 1][file + 1].equals(piece)) {
                    board[rank + 1][file + 1] = "1";
                }
            } else if (file - 1 >= 0) {
                if (board[rank + 1][file - 1].equals(piece)) {
                    board[rank + 1][file - 1] = "1";
                }
            }
        } else if (rank - 1 >= 0) {
            if (board[rank - 1][file].equals(piece)) {
                board[rank - 1][file] = "1";
            } else if (file + 1 < 8) {
                if (board[rank - 1][file + 1].equals(piece)) {
                    board[rank - 1][file + 1] = "1";
                }
            } else if (file - 1 >= 0) {
                if (board[rank - 1][file - 1].equals(piece)) {
                    board[rank - 1][file - 1] = "1";
                }
            }
        } else if (file + 1 < 8) {
            if (board[rank][file + 1].equals(piece)) {
                board[rank][file + 1] = "1";
            } else if (file - 1 >= 0) {
                board[rank][file - 1] = "1";
            }
        }
        board[rank][file] = piece;
    }

    public static void queenMover(String[][] board, String color, int file,
        int rank) {
        String piece;
        Boolean in = true;
        if (color.equals("Black")) {
            piece = "q";
        } else {
            piece = "Q";
        }
        for (int i = rank + 1, j = file + 1; i < 8 && j < 8; i++, j++) {
            if (!board[i][j].equals(piece) && !board[i][j].equals("1")) {
                in = false;
            } else if (board[i][j].equals(piece) && in.equals(true)) {
                board[i][j] = "1";
                in = false;
            }
        }
        in = true;
        for (int i = rank - 1, j = file - 1; i >= 0 && j >= 0; i--, j--) {
            if (!board[i][j].equals(piece) && !board[i][j].equals("1")) {
                in = false;
            } else if (board[i][j].equals(piece) && in.equals(true)) {
                board[i][j] = "1";
                in = false;
            }
        }
        in = true;
        for (int i = rank + 1, j = file - 1; i < 8 && j >= 0; i++, j--) {
            if (!board[i][j].equals(piece) && !board[i][j].equals("1")) {
                in = false;
            } else if (board[i][j].equals(piece) && in.equals(true)) {
                board[i][j] = "1";
                in = false;
            }
        }
        in = true;
        for (int i = rank - 1, j = file + 1; i >= 0 && j < 8; i--, j++) {
            if (!board[i][j].equals(piece) && !board[i][j].equals("1")) {
                in = false;
            } else if (board[i][j].equals(piece) && in.equals(true)) {
                board[i][j] = "1";
                in = false;
            }
        }
        for (int row = rank + 1; row < 8; row++) {
            if (!board[row][file].equals(piece)
                && !board[row][file].equals("1")) {
                in = false;
            } else if (board[row][file].equals(piece) && in.equals(true)) {
                board[row][file].equals("1");
                in = false;
            }
        }
        in = true;
        for (int row = rank - 1; row >= 0; row--) {
            if (!board[row][file].equals(piece)
                && !board[row][file].equals("1")) {
                in = false;
            } else if (board[row][file].equals(piece) && in.equals(true)) {
                board[row][file].equals("1");
                in = false;
            }
        }
        in = true;
        for (int col = file + 1; col < 8; col++) {
            if (!board[rank][col].equals(piece)
                && !board[rank][col].equals("1")) {
                in = false;
            } else if (board[rank][col].equals(piece) && in.equals(true)) {
                board[rank][col].equals("1");
                in = false;
            }
        }
        in = true;
        for (int col = file - 1; col >= 0; col--) {
            if (!board[rank][col].equals(piece)
                && !board[rank][col].equals("1")) {
                in = false;
            } else if (board[rank][col].equals(piece) && in.equals(true)) {
                board[rank][col].equals("1");
                in = false;
            }
        }
        board[rank][file] = piece;
    }

    public static void castleMover(String[][] board, String color, String move)
    {
        if (color.equals("Black")) {
            if (move.length() == 3) {
                board[7][4] = "1";
                board[7][7] = "1";
                board[7][5] = "r";
                board[7][6] = "k";
            } else {
                board[7][4] = "1";
                board[7][0] = "1";
                board[7][3] = "r";
                board[7][2] = "k";
            }
        } else {
            if (move.length() == 3) {
                board[0][4] = "1";
                board[0][7] = "1";
                board[0][6] = "K";
                board[0][5] = "R";

            } else {
                board[0][4] = "1";
                board[0][0] = "1";
                board[0][2] = "K";
                board[0][3] = "R";
            }
        }
    }

    public static String converter(String[][] board) {
        String boardPos = "";
        for (int x = 7; x >= 0; x--) {
            int count = 0;
            for (int y = 0; y < 8; y++) {
                if (board[x][y].equals("1")) {
                    count++;
                    if (y != 7) {
                        int haha = 4;
                    } else {
                        boardPos = boardPos + count;
                    }
                } else if (count != 0) {
                    boardPos = boardPos + count + board[x][y];
                    count = 0;
                } else {
                    boardPos = boardPos + board[x][y];
                }
            }
            if (x != 0) {
                boardPos = boardPos + "/";
            }
        }
        return boardPos;
    }

    public static String fileContent(String path) {
        Path file = Paths.get(path);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                // Add the \n that's removed by readline()
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            System.exit(1);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String game = fileContent(args[0]);
        System.out.format("Event: %s%n", tagValue("Event", game));
        System.out.format("Site: %s%n", tagValue("Site", game));
        System.out.format("Date: %s%n", tagValue("Date", game));
        System.out.format("Round: %s%n", tagValue("Round", game));
        System.out.format("White: %s%n", tagValue("White", game));
        System.out.format("Black: %s%n", tagValue("Black", game));
        System.out.format("Result: %s%n", tagValue("Result", game));
        System.out.println("Final Position:");
        System.out.println(finalPosition(game));
    }
}