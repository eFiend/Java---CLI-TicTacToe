/**
 * TicTacToe allows you to play a round of the timeless classic via CLI.
 * 
 * @author Jonathan Love
 * @version 5-6-15
 */

import java.util.*;

public class TicTacToe{
    private Scanner in = new Scanner(System.in);
    
    private ArrayList[][] gameBoard;
    private ArrayList<String> playerPositions = new ArrayList<String>();
    
    private int boardSize;          // user defined size of game board
    private int gameTurn;           // turn counter which works in conjunction with maxTurns
    private int maxTurns;           // maximum possible number of turrns / used to determine Cat's Games
    private int scorePlayerX = 0;   //
    private int scorePlayerO = 0;   //
    private boolean go = false;     // loop controller1
    private boolean win;            // loop controller2
    private String choice;          // user input var 1
    private int choiceInt;          // user input var 2
    
    /**
     * Constructor for objects of class TicTacToe
     */
    public TicTacToe(){
    }
       
    public static void main(String[] args)
    {
        System.out.print("Welcome to Tic Tac Toe coded by Jon Love");
        TicTacToe game = new TicTacToe();
        game.gameMenu();
    }
    
    /**
     * gameMenu prompts the player to play or quits the program, depending on user input.
     */
    public void gameMenu()
    {
        do{
            go = true;
            System.out.print("\n\nPlease enter P to play a new game or Q to quit: ");
            choice = in.next();
            if(choice.equalsIgnoreCase("q")){
                go = false;
            }
            else if(choice.equalsIgnoreCase("p")){
                win = false; // resets the win parameter from any previous games.
                gameSize();
                gameSession();
                gameScore();
            }
            else{
                System.out.print("Invalid character. Dont be a noob! ");
            }
        }while(go == true);  
    }
    
    /**
     * gameSize determines the size of the board desired by the players.
     */
    public void gameSize()
    {
        System.out.print("\nHow large of a board would you like to play on?");
        System.out.print("\nAvailable sizes:\n a. 3x3\n b. 4x4\n c. 5x5\n d. 6x6\n e. 7x7\n f. 8x8\n g. 9x9\n h. 10x10 (I dare you...)\nYour selection: ");
        do{
                go = true;
                choice = in.next();
                if(choice.equalsIgnoreCase("a")){
                    boardSize = 3;
                }
                else if(choice.equalsIgnoreCase("b")){
                    boardSize = 4;
                }
                else if(choice.equalsIgnoreCase("c")){
                    boardSize = 5;
                }
                else if(choice.equalsIgnoreCase("d")){
                    boardSize = 6;
                }
                else if(choice.equalsIgnoreCase("e")){
                    boardSize = 7;
                }
                else if(choice.equalsIgnoreCase("f")){
                    boardSize = 8;
                }
                else if(choice.equalsIgnoreCase("g")){
                    boardSize = 9;
                }
                else if(choice.equalsIgnoreCase("h")){
                    boardSize = 10;
                }
                else{
                    System.out.print("Invalid chracter. Dont be a noob!\nYour selection: ");
                    go = false;
                }
        }while(go == false);
    }
    
    /**
     * gameSession is where users interact with the game board and play the actual game.
     */
    public void gameSession()
    {
        while(!win){
            go = true;
            playerPositions();
            playerTurns();
        }
    }
    
    /**
     * playerPositions loads the possible positions player can take on the board.
     */
    public void playerPositions()
    {
        maxTurns = boardSize * boardSize;        
        
        for(int i = 0; i < maxTurns; i++){
            if(i < 9){
                playerPositions.add(i," " + String.valueOf(i + 1) + " ");
            }
            else if(i < 99){
                playerPositions.add(i," " + String.valueOf(i + 1));
            }
            else{
                playerPositions.add(i,String.valueOf(i + 1));
            }
        }
    }
    
    /**
     * playerTurns provides the functionality for players to interact with the board.
     * @return returns the value of the win condition.
     */
    public boolean playerTurns() 
    {
        gameTurn = 0;
        win = false;
        
        while(gameTurn < maxTurns && win != true){
            boardDraw();
            if(0 != gameTurn % 2){
                playerTurnsMarkings("X");                
            }
            else{
                playerTurnsMarkings("O");
            }
            gameTurn++;            
            win = winConditions();
        }
        boardDraw();
        return win;
    }
    
     /**
     * This exists solely to simplify the code in the playerTurns method.
     */
    public void playerTurnsMarkings(String s)
    {
        String str = s;
        boolean listCheck; // This var is used to verify if a user input value is in the 1d ArrayList.
        
        do{
            System.out.print("\n\nPlayer " + str + " - Specify a number to mark your spot: ");
            try{
                choiceInt = in.nextInt();
            } catch (InputMismatchException e)
            {
                choice = in.next();
            }
            
            if(choiceInt <= 9){
                choice = " " + String.valueOf(choiceInt) + " ";
            }
            else if(choiceInt <= 99){
                choice = " " + String.valueOf(choiceInt);
            }
            else if(choiceInt == 100){
                choice = String.valueOf(choiceInt);
            }
            
            listCheck = playerPositions.contains(choice);
            if(!listCheck){
                System.out.print("Don't be a noob! Try again.");
            }
        }while(!listCheck);
            
        if(choiceInt <= 100){
            playerPositions.set(choiceInt - 1, " " + str + " ");
        }else{} // This is to account for when a user inputs Y to attempt overwriting all of the X markings & vice versa.
    }
    
    /**
     * boardDraw draws the board initially, then everytime before each players turn.
     */
    public void boardDraw()
    {
        int x = (boardSize * 2) - 1;
        gameBoard = new ArrayList[x][x];
                
        int i = 0; // Player Positions counter, used for iterating the PlayerPositions list.        
        for(int col = 0; col < x; col++){
            System.out.print("\n ");
            for(int row = 0; row < x; row++){
                if(0 != row % 2){ // the value is odd
                    if(0 != col %2){
                        System.out.print("+");
                    }
                    else{
                        System.out.print("|");
                    }
                }
                else{ // the value is even
                    if(0 != col %2){
                       System.out.print("---"); 
                    }
                    else{
                        System.out.print(playerPositions.get(i));
                        i++;
                    }
                }                
            }
        }        
    }
    
    /**
     * winConditions checks to see if a player won the game, and if so ends that game.
     * @return the boolean value which indicates if there was a win.
     */
    public boolean winConditions()
    {
        String winStringCOL = "";
        String winStringROW = "";
        String winStringDIAG = "";
        String subX = "";
        String subO = "";
        int i;
        int j;
        int k = 0;
        int direction = 1;
        
        // Column Win Conditions
        for(i = 0; i < boardSize; i++){
            for(j = k; j < maxTurns; j += boardSize){
                winStringCOL += playerPositions.get(j); // line up the values to be scanned.
            }
            winStringCOL += ","; // delimit the string so I can scan through for a winning sequence.
            k += 1;
        }
        winStringCOL = winStringCOL.replaceAll("\\s+","");
        k = 0;
        
        // Row Win Conditions
        for(i = 0; i < boardSize; i++){
            for(j = k; j < boardSize + k; j++){
                winStringROW += playerPositions.get(j);
            }
            winStringROW  += ",";
            k += boardSize;
        }
        winStringROW = winStringROW.replaceAll("\\s+","");
        k = 0;
        
        // Diagonal Win Conditions
        for(i = k; i < 2; i++){
            if(direction == 1){
                for(j = 0; j < maxTurns; j += (boardSize + direction)){
                    winStringDIAG += playerPositions.get(j); 
                }
            }
            else if(direction == -1){
                for(j = boardSize - 1; j < maxTurns - 1; j += (boardSize + direction)){
                    winStringDIAG += playerPositions.get(j); 
                }
            }
            winStringDIAG  += ",";
            k += 1;
            direction = -1;
        }
        winStringDIAG = winStringDIAG.replaceAll("\\s+","");
        k = 0;
        
        // Define the winning substrings
        for(i = 0; i < boardSize; i++){
            subX += 'x';
            subO += 'o';
        }
        
        // Search for a match between current board strings and winning substrings
        if(winStringCOL.toLowerCase().contains(subX) || winStringROW.toLowerCase().contains(subX) || winStringDIAG.toLowerCase().contains(subX)){
            scorePlayerX++;
            win = true;
            System.out.print("\n\nThe game has now ended. Congratulations Player X! You won!!!");
            return win;
        }
        else if(winStringCOL.toLowerCase().contains(subO) || winStringROW.toLowerCase().contains(subO) || winStringDIAG.toLowerCase().contains(subO)){
            scorePlayerO++;
            win = true;
            System.out.print("\n\nThe game has now ended. Congratulations Player O! You won!!!");
            return win;
        }else if(gameTurn == maxTurns)
        {
            win = true;
            System.out.print("\n\nThe game has now ended. Cat's Game!!! You both tied!");
            return win;
        }
        return win;
    }
    
    /**
     * gameScore outputs the running score of the two players. 
     */
    public void gameScore()
    {
        System.out.print("\n\nThe score for the current game session is:\nPlayer X: " + scorePlayerX + "\nPlayer O: " + scorePlayerO);
    }
}