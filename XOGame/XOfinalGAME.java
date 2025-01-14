import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class XOfinalGAME {
    private String[][] board;
    private String playerSymbole;
    private String computerSymbole;
    private int linechoice;
    private int columnchoice;
    private boolean caseFull =true;
    private int freeCases = 0;
    private boolean stillGame = true;
    public void playGame(){
        board = new String[3][3];
        Scanner write = new Scanner(System.in);
        System.out.print("couse the symbole you want to play with between X & O : ");
        playerSymbole = write.nextLine().toUpperCase();
        computerSymbole = playerSymbole.equals("X") ? "O" : "X";
        System.out.println("GREAT! you choused \" "+playerSymbole+" \" ,so the computer will play as \" "+computerSymbole+" \".");
        for(String[] game : board){
            System.out.println(Arrays.toString(game).replace("null", "    "));
        }
        while (stillGame) {
            System.out.print("enter the line number between (1-2-3) : ");
            linechoice = write.nextInt()-1;
            try{
                if (linechoice > 2) {
                    write.close();
                    throw new ChoiceOuOfBoundException("value is out of the bound : "+linechoice);
                }
                System.out.print("enter the column number between (1-2-3) : ");
                columnchoice = write.nextInt()-1;
                if (columnchoice > 2) {
                    write.close();
                    throw new ChoiceOuOfBoundException("value is out of the bound : "+columnchoice);
                }
                while (caseFull) {
                    if (board[linechoice][columnchoice] == null) {
                        board[linechoice][columnchoice] = playerSymbole;
                        // caseFull = false;
                        break;
                    } else {
                        System.out.print("this case is already full ,chouse an other one : ");
                        System.out.print("enter the line number between (1-2-3) : ");
                        linechoice = write.nextInt()-1;
                        System.out.print("enter the column number between (1-2-3) : ");
                        columnchoice = write.nextInt()-1;
                    }
                }
                // Thread.sleep(1000);
                Random computerRound = new Random();
                int randomline = computerRound.nextInt(3);
                int randomcolumn = computerRound.nextInt(3);
                if (board[randomline][randomcolumn] == null) {
                    board[randomline][randomcolumn] = computerSymbole;
                    System.out.println("the computer choused line \""+(randomline+1)+"\" and column \""+(randomcolumn+1)+"\".");
                }
                for(String[] elements : board){
                    for(String element : elements){
                        if (element == null) {
                            freeCases++;
                        }
                    }
                }
                System.out.println("still "+freeCases+" cases to play !");
                // Thread.sleep(2000);
                for(String[] game : board){
                    System.out.println(Arrays.toString(game).replace("null", "    "));
                }
                for(int i = 0 ; i < board.length ; i++){
                    if (((board[i][0] != null && board[i][0].equals(playerSymbole)) &&
                        (board[i][1] != null && board[i][1].equals(playerSymbole)) &&
                        (board[i][2] != null && board[i][2].equals(playerSymbole)))  ||
                        ((board[0][i] != null && board[0][i].equals(playerSymbole)) &&
                        (board[1][i] != null && board[1][i].equals(playerSymbole)) &&
                        (board[2][i] != null && board[2][i].equals(playerSymbole)))  ||
                        ((board[0][0] != null && board[0][0].equals(playerSymbole)) &&
                        (board[1][1] != null && board[1][1].equals(playerSymbole)) &&
                        (board[2][2] != null && board[2][2].equals(playerSymbole)))) {
                        // Thread.sleep(1000);
                        System.out.print("CONGRATULATIONS! you won the game .want to play again ?");
                        stillGame = askReplay(write);
                        break; 
                    }
                    else if(((board[i][0] != null && board[i][0].equals(computerSymbole)) &&
                            (board[i][1] != null && board[i][1].equals(computerSymbole)) &&
                            (board[i][2] != null && board[i][2].equals(computerSymbole)))  ||
                            ((board[0][i] != null && board[0][i].equals(computerSymbole)) &&
                            (board[1][i] != null && board[1][i].equals(computerSymbole)) &&
                            (board[2][i] != null && board[2][i].equals(computerSymbole)))  ||
                            ((board[0][0] != null && board[0][0].equals(computerSymbole)) &&
                            (board[1][1] != null && board[1][1].equals(computerSymbole)) &&
                            (board[2][2] != null && board[2][2].equals(computerSymbole)))){
                        // Thread.sleep(1000);
                        System.out.print("OUPS! you lost the game .want to play again ?");
                        stillGame = askReplay(write);
                        break;
                    }
                    else if (freeCases == 0){
                        // Thread.sleep(1000);
                        System.out.print("it's a DRAW .want to play again ?");
                        stillGame = askReplay(write);
                        break;
                    }
                }
            }
            catch(ChoiceOuOfBoundException e){
                System.out.println("Exception : "+e.getMessage());
            }
            finally{
                System.out.println("\n");
            }
        }
        write.close();
    }
    private boolean askReplay(Scanner write) {
        System.out.print("Do you want to play again? (yes/no): ");
        String choice = write.next();
        if (choice.equalsIgnoreCase("yes")) {
            board = new String[3][3];
            freeCases = 0;
            return true;
        } else {
            System.out.println("Thanks for playing!");
            return false;
        }
    }
}
