package tec.poo.proyectos.model;

import tec.poo.proyectos.controller.Movie;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MyReader {
    private BufferedReader bufferedReader;

    public MyReader(String fileName) {
        try {
            this.bufferedReader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + fileName);
            e.printStackTrace();
        }
    }

    public ArrayList<Movie> readFromFile() {
        ArrayList<Movie> movies = new ArrayList<>();

        try {
            String line;
            while ((line = this.bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 3) {
                    String title = parts[0];
                    String category = parts[1];
                    String[] ratingAndRest = parts[2].split("::");
                    float rating = Float.parseFloat(ratingAndRest[0]);

                    String review = "";
                    String source = "";
                    if (ratingAndRest.length > 1) {
                        String[] reviewAndSource = ratingAndRest[1].split(":");
                        if (reviewAndSource.length > 0) review = reviewAndSource[0];
                        if (reviewAndSource.length > 1) source = reviewAndSource[1];
                    }

                    Movie movie = new Movie(title, category, rating, review, source);
                    movies.add(movie);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer del archivo.");
            e.printStackTrace();
        }
        return movies;
    }

    public void close() {
        try {
            if (this.bufferedReader != null) {
                this.bufferedReader.close();
            }
        } catch (IOException e) {
            System.err.println("Error al cerrar el archivo.");
            e.printStackTrace();
        }
    }
}