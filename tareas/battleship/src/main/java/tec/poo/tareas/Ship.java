package tec.poo.tareas;

import java.util.Random;

public class Ship {
    private int randomNumber;
    String name;


    public Ship(String name) {
        this.name = name;
        randomNumber = generateRandomNumber();
    }
    public String getName() {
        return name;
    }

    public int generateRandomNumber(){
        Random random = new Random();
        return random.nextInt(7); //
    }

    public int getRandomNumber(){
        return randomNumber;
    }

    public int[] createShipPosition(int number) {
        int[] ship_position= new int[3];
        if (number>4){
            ship_position[2]=number;
            ship_position[1]=number-1;
            ship_position[0]=number-2;
        } else{
            ship_position[0] = number;
            ship_position[1] = number + 1;
            ship_position[2] = number + 2;
        }
        for (int i=0; i<3; i++){
           // System.out.println(ship_position[i]);
        }

        return ship_position;



    }

}
