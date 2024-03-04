package tec.poo.tareas;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

//Main class
public class BattleshipGame {


    //create a instance
    Board board;
    Ship ship;
    Scanner sc = new Scanner(System.in);
    int[] shipPosition;

    int points = 0;
    int numOfHits = 0;
    boolean playerLose = false;
    String message_one = "Welcome to sink the ship, in this you have to shoot down the ship CK-7, it has a size of 3 rows ";
    String message_two = "In this game you could select just a number between 0 and 6";

    int[] board1;

    ArrayList<Integer> repeats = new ArrayList<>();
    int contador = 0;
    public BattleshipGame(){
        this.numOfHits = 0;
        this.points = 0;
        this.board = new Board();
        this.ship = new Ship(" CK-7");
        this.shipPosition = ship.createShipPosition(ship.generateRandomNumber());
        this.board1 = board.getBoardArray();
        this.game();

    }

    //Game metod, there is the main logic
    public void game(){

        while (!playerLose){
            System.out.println(message_one);
            System.out.println(message_two);
            System.out.println("Select a number between 0 and 6");
            while (this.numOfHits <3){
                try {

                    int answer = sc.nextInt();

                    if(answer<0 || answer>6 ) {
                        System.out.println("you should select a number out of 0 and 6");
                    }else{
                        if(repeats.contains(answer)){
                            System.out.println("Don't repeat numbers");
                        }
                        else{
                            if (this.validateNumber(answer)){
                                this.numOfHits++;
                                System.out.println("You hit"+ ship.getName()+"\nenter a guess");
                                this.points+=1;
                            }else {
                                System.out.println("You miss \nenter a guess");
                                this.points+=1;
                            }
                        }repeats.add(answer);

                    }
                }catch (InputMismatchException error){
                    System.out.println("You should select a int number");
                    sc.next();
                }
            }


            System.out.println("CONGRATS! YOU KILL "+ ship.getName());
            playerLose=true;
            System.out.println("YOU TOOK: "+this.points + " Guesses and get " + this.points*10 + " points" );

        }

    }
    //validate the number
    public boolean validateNumber(int number){
        if (board1 != null) {
            for (int i = 0; i < shipPosition.length; i++) {
                if (shipPosition[i] == number) {
                    return true;
                }
            }
        } else {
            System.out.println("Error: board1 is null");
        }
        return false;
    }


    public static void main(String[] args) {

        new BattleshipGame();

    }
}
