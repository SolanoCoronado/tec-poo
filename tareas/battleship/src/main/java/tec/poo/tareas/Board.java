package tec.poo.tareas;

public class Board {
    private int[] boardArray;

    public Board(){
        boardArray = new int[7];
        fullArray();

    }

    public void fullArray(){
        for (int i=0; i< boardArray.length;i++){
            this.boardArray[i]= i;


        }
    }

    public int[] getBoardArray() {
        return boardArray;
    }
}
