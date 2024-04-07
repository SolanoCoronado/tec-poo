package tec.poo.proyectos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MyWriter {
    private BufferedWriter bufferedWriter;

    //**
    // This method is for write the information in a file
    //
    //
    // */
    public void writeToFile(String fileName, ArrayList<Movie> movies) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (Movie movie : movies) {
                String formattedLine = String.format("%s|%s|%f::%s:%s",
                        movie.getTitle(), movie.getCategory(),
                        movie.getRating(), movie.getReview(), movie.getSource());
                writer.println(formattedLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //**
    //The constructor
    // */
    public MyWriter(String fileName) {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}