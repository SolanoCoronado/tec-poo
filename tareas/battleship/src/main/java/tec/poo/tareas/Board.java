package tec.poo.tareas;


public class Board {
    private int ROW = 7;
    private int COLUMN = 7;
    private String[][] boardArray;



    public Board(){
        boardArray = new String[ROW][COLUMN];
        fullArray();

    }

    public void fullArray(){
        for (int i=0; i< ROW;i++){
            for (int j = 0; j< COLUMN; j++){
                this.boardArray[i][j]=letter(i)+(j);
            }
        }

    }
    public static String letter(int index) {
        return Character.toString((char) ('A' + index));
    }

    public String[][] getBoardArray() {

        return this.boardArray;
    }
}
