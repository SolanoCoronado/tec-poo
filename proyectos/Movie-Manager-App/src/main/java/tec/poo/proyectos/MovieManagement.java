package tec.poo.proyectos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MovieManagement {

    private ArrayList<Movie> movies;
    private MyReader reader;
    private MyWriter writer;
    private ArrayList<String> moviesCategory = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    //**
    // The constructor
    //
    // */
    public MovieManagement() {


        this.movies = new ArrayList<>();

        this.moviesCategory.add("Action");
        this.moviesCategory.add("Comedia");
        this.moviesCategory.add("Drama");
        this.moviesCategory.add("Sci-Fi");
        this.moviesCategory.add("Horror");
        this.moviesCategory.add("Uncategorized");

        Movie movie1 = new Movie("Piratas del caribe", "Sci-Fi", 0,"Me gusta mucho","PelisMas");
        Movie movie2 = new Movie("Cars", "Action", 3,"Excelente","PelisNet");
        Movie movie3 = new Movie("Conjuro 2", "horror", 4.5,"No da miedo","Wiki");


        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);

    }

    //This method add movie to arrayList
    public void addMovie(String movieTitle, String category, Double rating, String review, String source) {



        if (verificateIfMovieExist(movieTitle)) {

            System.out.println("Movie Duplicate");

        } else {


            boolean found = false;
            for (String cat : moviesCategory) {
                if (cat.equalsIgnoreCase(category)) {
                    found = true;
                    break;
                }
            }

            if (found) {

                if (rating>=0 && rating<=5){
                    Movie movie = new Movie(movieTitle, category, rating, review, source);
                    movies.add(movie);
                    System.out.println("Movie added");

                }else if(rating==-1){
                    Movie movie = new Movie(movieTitle, category, rating, review, source);
                    movies.add(movie);
                    System.out.println("Movie added");

                }else{
                    System.out.println("select number beetwen 0-5");
                }

            } else {
                System.out.println("Select a existent category");

            }
        }

    }
    //**
    // This method add a movie in the array, interactive mode
    //
    // */

    public void addMovieInteractive(Movie movie) {

        if (verificateIfMovieExist(movie.getTitle())) {

            System.out.println("Movie Duplicate");

        } else {

            movies.add(movie);

        }

    }

    //**
    // This method remove or delete a movie
    //
    // */
    public void removeMovie(String movieDelete) {
        Boolean flag = false;
        int contador = 0;
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(movieDelete)) {
                flag = true;
                break;
            } else {
                contador++;
            }
        }
        if (flag) {
            movies.remove(contador);
            System.out.println("delete: " + movieDelete);
        } else {
            System.out.println("Flag false");
        }
    }

    //**
    // This method update the name pf a movie
    //
    // */
    public void renameMovie(String originalMovie, String movieReplace) {

        Boolean flag = false;
        int contador = 0;
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(originalMovie)) {
                flag = true;

                break;
            } else {
                contador++;
            }
        }
        if (flag) {
            if(verificateIfMovieExist(movieReplace)){
                System.out.println("Movie Duplicate");
            }else{
                movies.get(contador).setTitle(movieReplace);
                System.out.println("Movie name updated");
            }

        } else {
            System.out.println("The movie: " + originalMovie + " doesn't exit");
        }
    }

    //**
    // This method update the rating of a movie interactive
    //
    // */
    public void updateRatingInteractive(String movieToReplace){
        Boolean flag = false;
        int contador = 0;
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(movieToReplace)) {
                flag = true;

                break;
            } else {
                contador++;
            }
        }
        if (flag) {
            double newRating;
            boolean validRating = false;
            System.out.println("Enter a rating between 0 and 5:");
            while (!validRating) {
                try {
                    newRating = scanner.nextDouble();
                    if (newRating >= 0 && newRating <= 5) {
                        movies.get(contador).setRating(newRating);

                        validRating = true;
                    } else {
                        System.out.println("Please enter a rating between 0 and 5:");
                    }
                } catch (InputMismatchException ime) {
                    System.out.println("Invalid input. Please enter a number between 0 and 5.");
                    scanner.nextLine();
                }
            }
        } else {
            System.out.println("Movie doesn't exist");
        }


    }

    //**
    // This method update the ratingof a movie
    //
    // */
    public void updateRating(String movieToReplace, double newRating){
        Boolean flag = false;
        int contador = 0;
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(movieToReplace)) {
                flag = true;

                break;
            } else {
                contador++;
            }
        }
        if (flag) {

            if (newRating >= 0 && newRating <= 5) {
                movies.get(contador).setRating(newRating);
                System.out.println("Rating updated");


            } else {
                System.out.println("Enter a rating between 0 and 5:");
            }

        } else {
            System.out.println("Movie doesn't exist");
        }


    }
    //**
    // This method update the category of a movie
    //
    // */
    public void updateCategory(String movieToReplace, String newCategory){
        Boolean flag = false;
        int contador = 0;
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(movieToReplace)) {
                flag = true;

                break;
            } else {
                contador++;
            }
        }
        if (flag) {
            boolean categoryExists = false;

            for (String cat : moviesCategory) {
                if (cat.equalsIgnoreCase(newCategory)) {
                    categoryExists = true;
                    break;
                }
            }

            if (!categoryExists) {
                System.out.println("Category doesn't exist");
                return;
            } else{
                movies.get(contador).setCategory(newCategory);
                


                }

        } else {
            System.out.println("Movie doesn't exist");
        }


    }
    //**
    // This method update the category of a movie interactive
    //
    // */
    public void updateCategoryInteractive(String movieToReplace, String newCategory){
        Boolean flag = false;
        int contador = 0;
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(movieToReplace)) {
                flag = true;
                break;
            } else {
                contador++;
            }
        }
        if (flag) {

            boolean categoryExists = false;
            for (String cat : moviesCategory) {
                if (cat.equalsIgnoreCase(newCategory)) {
                    categoryExists = true;
                    break;
                }
            }
            if (!categoryExists) {
                System.out.println("Category doesn't exist");
                return;
            } else{
                movies.get(contador).setCategory(newCategory);
            }

        } else {
            System.out.println("Movie doesn't exist");
        }


    }
    //**
    // This method update the review of a movie
    //
    // */
    public void updateReview(String movieToReplace, String newReview){
        Boolean flag = false;
        int contador = 0;
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(movieToReplace)) {
                flag = true;

                break;
            } else {
                contador++;
            }
        }
        if (flag) {


            System.out.println("Write the source of  the review");

            String source = scanner.nextLine();

            if (source.isEmpty()) {
                System.out.println("You must add a source for your review");

            }else{
                movies.get(contador).setReview(newReview);
                movies.get(contador).setSource(source);
                System.out.println("Review updated");
            }

        }else {
            System.out.println("Movie doesn't exist");

        }
    }
    //**
    // This method update the review of a movie interactive
    //
    // */
    public void updateReviewInteractive(String movieToReplace, String newReview){
        Boolean flag = false;

        int contador = 0;
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(movieToReplace)) {
                flag = true;

                break;
            } else {
                contador++;
            }
        }
        if (flag) {
            scanner.nextLine();
            System.out.println("Write the Source of your review");
            String source = scanner.nextLine();

            if (source.isEmpty()){
                System.out.println("The review must has a source");
            }else {
                movies.get(contador).setReview(newReview);
                movies.get(contador).setSource(source);

            }

        }else {
            System.out.println("Movie doesn't exist");

        }
    }

    //This method verificate if the movie with the name movieVerificate exist
    public boolean verificateIfMovieExist(String movieVerificate) {

        Boolean flag = false;
        int contador = 0;
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(movieVerificate)) {
                flag = true;

                break;
            }
        }

        return flag;
    }

    //**
    // This method display movies
    //
    // */
    public void displayMovies() {
        for (Movie movie : movies) {
            System.out.println(movie.getTitle());
        }
    }

    //**
    // This method display movies interactive
    //
    // */
    public void displayMoviesInteractive() {
        System.out.println("\n");
        for (Movie movie : movies) {
            System.out.println(movie.getTitle());
        }
    }

    //**
    // This method display movies sort ascending by name
    //
    // */
    public void displayMoviesByNameAscending() {
        System.out.println("List of Movies by Name (Ascending): \n");

        // Ordena la lista de películas en orden ascendente por el título de la película
        movies.sort(Comparator.comparing(Movie::getTitle));
        displayMovies();
    }


    //**
    // This method display movies sort descending by rating
    //
    // */
    public void displayMoviesByRatingDescending() {
        System.out.println("List of Movies by Rating (Descending): \n");

        // Ordena la lista de películas en orden descendente por el rating
        movies.sort(Comparator.comparing(Movie::getRating).reversed());

        // Imprime los títulos de las películas ordenadas
        displayMovies();
    }

    //**
    // This method display movies in different categories or all the movies, interactive mode
    //
    // */
    public void displayMoviesByCategoryInteractive() {

        boolean flag = false;
        String category = "";
        while (!flag) {
            System.out.println("1. Horror");
            System.out.println("2. Action");
            System.out.println("3. Comedy");
            System.out.println("4. Drama");
            System.out.println("5. Sci-Fi");
            System.out.println("6. All");
            System.out.println("7.Exit");
            try {
                int opt = scanner.nextInt();
                switch (opt) {
                    case 1:
                        category="Horror";

                        flag = true;
                        break;
                    case 2:
                        category="Action";

                        flag = true;
                        break;
                    case 3:
                        category="Comedy";
                        flag = true;
                        break;
                    case 4:
                        category="Drama";
                        flag = true;
                        break;
                    case 5:
                        category="Sci-fi";
                        flag = true;
                        break;
                    case 6:
                        category="All";
                        flag = true;
                        break;
                    case 7:
                        flag = true;
                        category="Exit";
                        break;
                    default:
                        System.out.println("Opción no válida. Inténtelo de nuevo.");
                        break;
                }
            }catch (InputMismatchException e) {
                System.out.println("Please enter a valid number");
                scanner.next(); // Limpiar el búfer de entrada
            }

        }

        if (category.equalsIgnoreCase("Exit")){
            System.out.println("Exit no the main window");
        }else{
            System.out.println("List of Movies by : "+ category+"\n");


            if(category.equalsIgnoreCase("All")){
                displayMovies();
            }else{
                for (Movie movie : movies) {
                    if (movie.getCategory().equalsIgnoreCase(category))
                        System.out.println(movie.getTitle());
                }

            }
        }

    }

    //**
    // This method display movies in different categories or all the movies
    //
    // */
    public void displayMoviesByCategory(String category) {

        boolean categoryExists = false;

        for (String cat : moviesCategory) {
            if (cat.equalsIgnoreCase(category)) {
                categoryExists = true;
                break;
            }
        }

        if (!categoryExists) {
            System.out.println("Category doesn't exist");
            return;
        } else{
            System.out.println("Listing by: "+ category);
            for (Movie movie : movies) {
                if (movie.getCategory().equalsIgnoreCase(category))
                    System.out.println(movie.getTitle());
            }
        }
    }

    //**
    // This method display details of a movvie
    //
    // */
    public void displayDetails(String movieDetails){

        boolean movieFound = false;
        for (Movie movie : movies) {
            if(movie.getTitle().equalsIgnoreCase(movieDetails)) {
                movieFound = true;
                String category = movie.getCategory().isEmpty() ? "Uncategorized" : movie.getCategory();
                System.out.println("Details of the movie: " + movieDetails );
                System.out.println("The rating: "+ movie.getRating() +
                        "\nThe category: "+category+
                        "\nThe review : "+movie.getReview()+
                        "\nThe source: "+ movie.getSource());
            }

        }
        if (!movieFound) {
            System.out.println("The movie " + movieDetails + " doesn't exist");
        }
    }

    //**
    // This method display details of a movie interactive mode
    //
    // */
    public void displayDetailsInteractive(){
        System.out.println("Select your movie for details");
        String movieName = scanner.nextLine();
        System.out.println("Details of the movie: " + movieName );
        for (Movie movie : movies) {
            if(movie.getTitle().equalsIgnoreCase(movieName)) {
                System.out.println("The rating: "+ movie.getRating() +
                        "\nThe category: "+movie.getCategory()+
                        "\nThe review : "+movie.getReview()+
                        "\nThe source: "+ movie.getSource());
            }

        }
    }
    //**
    // This method save the file with a path in interactive mode
    //
    // */
    public void saveDocumentInteractive(String path){
        this.writer = new MyWriter(path);

        this.writer.writeToFile(path, movies);
    }
    //**
    // This method save the file with a path
    //
    // */
    public void saveDocument(String path){
        this.writer = new MyWriter(path);

        this.writer.writeToFile(path, movies);
    }
    //**
    // This method load the file with a path
    //
    // */
    public void loadDocument(String path) throws FileNotFoundException {
        try {
            File file = new File(path);
            if (!file.exists()) {
                throw new FileNotFoundException("File does not exist: Verify that the movie file exists in the specified path..");
            }
            this.reader = new MyReader(path);
            this.movies = reader.readFromFile();
            displayMovies();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    //**
    // This method load the file with a path, in interactive mode
    //
    // */
    public void loadDocumentInteractive(String path) throws FileNotFoundException {
        this.reader = new MyReader(path);
        this.movies = reader.readFromFile();

        displayMovies();

    }
}
