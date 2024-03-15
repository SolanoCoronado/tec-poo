package tec.poo.tareas;

import java.util.Random;

public class BattleShip {

    Board boardArray;

    private int rowRandom;
    private int columnRandom;

    String randomPos;
    String name;




    public BattleShip(String name) {
        this.name = name;
        this.boardArray = new Board();

    }
    public String[] GetShipPosition(){

        String[] shipPos= new String[3];

        columnRandom = generateRandomNumber();
        rowRandom = generateRandomNumber();
        randomPos = boardArray.getBoardArray()[rowRandom][columnRandom];
        //System.out.println("Posición aleatoria: " + randomPos);

        int count=0;
        int sum=0;
        int rest=0;

        // Decidir si crear una fila o columna
        boolean createRow = Math.random() < 0.5; // 50% de probabilidad de elegir fila o columna
        if (createRow) {
            if (columnRandom<=4){
                while (count<3){
                    //System.out.println(boardArray.getBoardArray()[rowRandom][columnRandom+sum]);
                    shipPos[count]=boardArray.getBoardArray()[rowRandom][columnRandom+sum];
                    count++;
                    sum++;
                }

            }else{
                while (count<3){
                    //System.out.println(boardArray.getBoardArray()[rowRandom][columnRandom-rest]);
                    shipPos[count]=boardArray.getBoardArray()[rowRandom][columnRandom-rest];
                    count++;
                    rest++;
                }
            }

        } else {

            if(rowRandom<=4){
                while (count<3){
                    //System.out.println(boardArray.getBoardArray()[rowRandom+sum][columnRandom]);
                    shipPos[count]=boardArray.getBoardArray()[rowRandom+sum][columnRandom];
                    count++;
                    sum++;
                }

            }else{
                while (count<3){
                    //System.out.println(boardArray.getBoardArray()[rowRandom-rest][columnRandom]);
                    shipPos[count]=boardArray.getBoardArray()[rowRandom-rest][columnRandom];
                    count++;
                    rest++;
                }

            }

        }
        return shipPos;
    }

    public String getName() {
        return name;
    }

    public int generateRandomNumber(){
        Random random = new Random();
        return random.nextInt(7); //

    }


}
