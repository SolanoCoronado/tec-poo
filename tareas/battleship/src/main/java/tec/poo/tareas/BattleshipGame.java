package tec.poo.tareas;

import java.util.Scanner;
import java.util.ArrayList;

import static tec.poo.tareas.Board.letter;

//Main class
public class BattleshipGame {


    //create a instance
    Board board;
    BattleShip battleShip1;
    BattleShip battleShip2;

    BattleShip battleShip3;
    Scanner sc = new Scanner(System.in);
    String[] shipPosition;
    String[] shipPosition2;
    String[] shipPosition3;
    ArrayList<String> abcd = new ArrayList<>();
    ArrayList<String> numList = new ArrayList<>();
    int points = 0;
    int numOfHits=0;
    int numOfHits1 = 0;
    int numOfHits2 =0;
    int numOfHits3=0;
    boolean playerLose = false;
    String message_one = "Welcome to sink the ship, in this you have to shoot down the ships: C-K7, Slaze, Poniez ";
    String message_two = "In this game you could select just a box between A and G for the rows 0 and 6 for columns, this way C1, B3, G7 ";
    String[][] board1;
    ArrayList<String> repeats = new ArrayList<>();
    int contador = 0;
    public BattleshipGame(){
        this.numOfHits = 0;
        this.points = 0;
        this.board = new Board();
        this.battleShip1 = new BattleShip(" CK-7");
        this.shipPosition = battleShip1.GetShipPosition();
        this.battleShip2 = new BattleShip("Slaze");
        this.battleShip3 = new BattleShip("Poniez");

        validateShipPosition2();
        validateShipPosition3();

        //System.out.println(shipPosition[0]+ " " + shipPosition[1]  +" "+ shipPosition[2]);
        //System.out.println(shipPosition2[0]+ " " + shipPosition2[1]  +" "+ shipPosition2[2]);
        //System.out.println(shipPosition3[0]+ " " + shipPosition3[1]  +" "+ shipPosition3[2]);

        this.board1 = board.getBoardArray();
        this.game();
    }

    //Game metod, there is the main logic
    public void game(){

        for(int i=0; i<=6; i++){

            abcd.add(letter(i));

        }
        for(int i=0; i<=6; i++){

            numList.add(Integer.toString(i));

        }

        while (!playerLose){

            System.out.println(message_one);

            System.out.println(message_two);

            System.out.println("Select a guess");

            while (this.numOfHits < 9 ){

                String answer = sc.nextLine().toUpperCase();

                if (repeats.contains(answer)){

                    System.out.println("Don't repeat numbers");

                } else if (answer.isBlank()) {

                    System.out.println("Don't put blank ");

                } else if (answer.length()!=2) {

                    System.out.println("Put only 2 characters");

                }

                else if(!abcd.contains(Character.toString(answer.charAt(0)))){

                    System.out.println("The letter first: available are A,B,C,D,E,F,G");

                } else if(!numList.contains(Character.toString(answer.charAt(1)))){

                    System.out.println("The numbers second: Available are 0,1,2,3,4,5,6");

                } else{

                    if ((answer.equals(shipPosition[0])) || (answer.equals(shipPosition[1])) || (answer.equals(shipPosition[2]))) {

                        if (numOfHits1<2){

                            System.out.println("You hit " + this.battleShip1.getName());

                        }else{

                            System.out.println("You kill " + this.battleShip1.getName());

                        }

                        points++;

                        numOfHits1++;

                        numOfHits++;

                        repeats.add(answer);

                    } else if ((answer.equals(shipPosition2[0])) || (answer.equals(shipPosition2[1])) || (answer.equals(shipPosition2[2]))) {


                        if (numOfHits2<2){
                            System.out.println("You hit " + this.battleShip2.getName());
                        }else{
                            System.out.println("You kill " + this.battleShip2.getName());
                        }
                        points++;
                        numOfHits2++;
                        numOfHits++;
                        repeats.add(answer);

                    } else if ((answer.equals(shipPosition3[0])) || (answer.equals(shipPosition3[1])) || (answer.equals(shipPosition3[2])))  {



                        if (numOfHits3<2){
                            System.out.println("You hit " + this.battleShip3.getName());
                        }else{
                            System.out.println("You kill " + this.battleShip3.getName());
                        }
                        points++;
                        numOfHits3++;
                        numOfHits++;
                        repeats.add(answer);

                    } else {
                        System.out.println("You failed");
                        points++;

                    }

                }

            }
            playerLose=true;
            System.out.println("YOU TOOK: "+this.points + " Guesses and get " + this.points*10 + " points" );

        }
    }

    private String[] validateShipPosition2(){

        boolean flag = true;

        while (flag){

            boolean allNotInArray2=true;
            this.shipPosition2 = this.battleShip2.GetShipPosition();

            boolean find= false;
            for (int i=0; i<this.shipPosition2.length;i++){

                for (int j=0; j<this.shipPosition.length;j++){
                    //System.out.println("2 : " + shipPosition2[i] + " 1 :" +shipPosition[j]);
                    if(this.shipPosition2[i].equals(this.shipPosition[j])){
                        //System.out.println("1 : " +shipPosition[i] +"2 :" +shipPosition2[j]);
                        find=true;
                        break;
                    }
                }
                if (find){
                    allNotInArray2=false;
                    break;
                }
            }
            if (allNotInArray2){
                flag=false;
            }

        }
        return this.shipPosition2;
    }

    private String[] validateShipPosition3(){
        boolean flag=true;
        while (flag) {

            boolean allNotInArray3 = true;
            boolean allNotInArray4 = true;

            //System.out.println("TERCER");
            this.shipPosition3 = this.battleShip3.GetShipPosition();

            boolean find1 = false;
            for (int i=0; i<this.shipPosition3.length;i++){
                for (int j=0; j<this.shipPosition.length;j++){
                    if(this.shipPosition3[i].equals(this.shipPosition[j])){
                        //System.out.println("1: " +this.shipPosition +" 3:" +this.shipPosition3);
                        find1=true;
                        break;
                    }
                }
                if (find1){
                    allNotInArray4 =false;
                    break;
                }
            }
            boolean find2= false;

            for (int i=0; i<this.shipPosition3.length;i++) {
                for (int j = 0; j < this.shipPosition2.length; j++) {
                    if (this.shipPosition3[i].equals(this.shipPosition2[j])) {
                        //System.out.println("2 : " + this.shipPosition2 + "3 :" + this.shipPosition3);
                        find2 = true;
                        break;
                    }
                }
                if (find2) {
                    allNotInArray3 = false;
                    break;
                }
            }
            if (allNotInArray4 && allNotInArray3) {
                flag=false;
            }

        }
        return this.shipPosition3;
    }



    public static void main(String[] args) {

        new BattleshipGame();

    }
}
