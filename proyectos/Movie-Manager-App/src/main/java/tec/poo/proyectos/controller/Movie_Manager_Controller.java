package tec.poo.proyectos.controller;

import picocli.CommandLine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static picocli.CommandLine.*;

@Command(name = "MovieManagerApp", mixinStandardHelpOptions = true, version = "1.0",
        description = "Manage movies with ratings and reviews")
public class Movie_Manager_Controller implements Runnable {

    static MovieManagement movieManager = new MovieManagement();



    Scanner scanner = new Scanner(System.in);

    //**
    // Here we add all the commands with pico cli
    //
    // */
    public static void main(String[] args) {
        CommandLine cmd = new CommandLine(new Movie_Manager_Controller());
        cmd.addSubcommand("list-movies", new ListMoviesCommand());
        cmd.addSubcommand("list-movies-filter-by-category", new ListMoviesByCategoryCommand());
        cmd.addSubcommand("list-movies-sort-by-name", new ListMoviesSortNameCommand());
        cmd.addSubcommand("list-movies-sort-by-rating", new ListMoviesSortRatingCommand());
        cmd.addSubcommand("movie", new MovieDetailsCommand());
        cmd.addSubcommand("new-movie", new NewMovieCommand());
        cmd.addSubcommand("rename-movie", new RenameMovieCommand());
        cmd.addSubcommand("update-movie-rating", new UpdateRatingMovieCommand());
        cmd.addSubcommand("update-movie-category", new UpdateCategoryMovieCommand());
        cmd.addSubcommand("update-movie-review", new UpdateReviewMovieCommand());
        cmd.addSubcommand("save",new SaveCommand());
        cmd.addSubcommand("load",new LoadCommand());
        cmd.addSubcommand("-gui", new GuiCommand());

        cmd.execute(args);
    }


    //**
    // This method save in a path
    //
    // */
    @Command(name = "-gui", description = "Open GUI")
    static class GuiCommand implements Runnable {
        public void run() {
            System.out.println("xd");
            movieManager.showGui(); // Llamar al método de la clase MovieManagerApp para mostrar la GUI
        }
    }
    //**
    // This method display the movies by All
    //
    // */
    @Command(name = "list-movies", description = "List all movies")
    static class ListMoviesCommand implements Runnable {
        public void run() {
            System.out.println("Do you select Listing all movies \n");
            movieManager.displayMovies();
        }
    }
    //**
    // This method display the movies by category
    //
    // */
    @Command(name = "list-movies-filter-by-category", description = "filter by category")

    static class ListMoviesByCategoryCommand implements Runnable {
        @Parameters(index = "0", description = "by category")
        private String category;
        public void run() {
            System.out.println("Do you select listing by Category \n");
            movieManager.displayMoviesByCategory(category);
        }
    }
    //**
    // This method display the movies sort by name
    //
    // */
    @Command(name = "list-movies-sort-by-name", description = "sort by name")
    static class ListMoviesSortNameCommand implements Runnable {
        public void run() {
            System.out.println("Dou you select sorting by name \n");
            movieManager.displayMoviesByNameAscending();
        }
    }
    //**
    // This method display the movies by rating
    //
    // */
    @Command(name = "list-movies-sort-by-rating", description = "sort by rating")

    static class ListMoviesSortRatingCommand implements Runnable {
        public void run() {
            System.out.println("Do you select sorting by rating \n");
            movieManager.displayMoviesByRatingDescending();
        }
    }

    //**
    // This method display the details of a movie
    //
    // */
    @Command(name = "movie", description = "Movie details")
    static class MovieDetailsCommand implements Runnable {
        @Parameters(index = "0", description = "Movie name")
        private String movieName;
        public void run() {
            System.out.println("Do you select movie details \n");
            movieManager.displayDetails(movieName);
        }
    }

    //**
    // This method add a movie
    //
    // */
    @Command(name = "new-movie", description = "add new movie")
    static class NewMovieCommand implements Runnable {
        @Parameters(index = "0", description = "Movie name")
        private String movieName;
        @Parameters(index = "1", description = "Movie category",defaultValue = "Uncategorized")
        private String category = "Uncategorized";
        @Parameters(index = "2", description = "Movie rating",defaultValue = "-1")
        private String ratingStr="-1";


        public void run() {
            System.out.println("Do you select add new movie \n");

            double rating;
            try {
                rating = Double.parseDouble(ratingStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid rating format. Please enter a valid number.");
                return;
            }

            movieManager.addMovie(movieName, category, rating, "", "");
        }
    }

    //**
    // This method rename a movie
    //
    // */
    @Command(name = "rename-movie", description = "rename a movie")
    static class RenameMovieCommand implements Runnable {
        @Parameters(index = "0", description = "Movie name")
        private String oldMovieName;
        @Parameters(index = "1", description = "Movie category")
        private String newMovieName;

        public void run() {
            System.out.println("Do you select rename a movie \n");

            movieManager.renameMovie(oldMovieName,newMovieName);
        }
    }

    //**
    // This method update rating of the movie
    //
    // */
    @Command(name = "update-movie-rating", description = "update rating")
    static class UpdateRatingMovieCommand implements Runnable {
        @Parameters(index = "0", description = "Movie name")
        private String movieName;
        @Parameters(index = "1", description = "New rating")
        private String newRatingStr;

        public void run() {
            try {
                double newRating = Double.parseDouble(newRatingStr);
                movieManager.updateRating(movieName, newRating);
            } catch (NumberFormatException e) {
                System.out.println("Invalid value for newRating: '" + newRatingStr + "' is not a valid double");
            }
        }
    }

    //**
    // This method update the category of a movie
    //
    // */
    @Command(name = "update-movie-category", description = "update rating")
    static class UpdateCategoryMovieCommand implements Runnable {
        @Parameters(index = "0", description = "Movie name")
        private String MovieName;
        @Parameters(index = "1", description = "Movie category")
        private String newCategory;

        public void run() {
            System.out.println("The category has been changed \n");

            movieManager.updateCategory(MovieName,newCategory);
        }
    }

    //**
    // This method update review of a movie just with a source
    //
    // */
    @Command(name = "update-movie-review", description = "update rating")
    static class UpdateReviewMovieCommand implements Runnable {
        @Parameters(index = "0", description = "Movie name")
        private String MovieName;
        @Parameters(index = "1", description = "Movie review")
        private String newReview;

        public void run() {
            System.out.println("Do you select updating movie review \n");

            movieManager.updateReview(MovieName,newReview);
        }
    }

    //**
    // This method save in a path
    //
    // */
    @Command(name = "save", description = "save")
    static class SaveCommand implements Runnable {
        @Parameters(index = "0", description = "path", defaultValue = "movies.txt")
        private String path;

        public void run() {

            movieManager.saveDocument(path);
        }
    }

    //**
    // This method load in a path
    //
    // */
    @Command(name = "load", description = "load")
    static class LoadCommand implements Runnable {
        @Parameters(index = "0", description = "path", defaultValue = "movies.txt")
        private String path;

        public void run() {
            try {
                System.out.println("Document has been loaded \n");
                movieManager.loadDocument(path);
            } catch (FileNotFoundException e) {
                System.out.println("Error loading document: " + e.getMessage());
            }
        }

    }




    //**
    // This method is the interactiove
    //
    // */
    public void run() {


        while (true) {


            System.out.println(" ");
            System.out.println("\u001B[31mIf you are in interactive mode and you select an option, \n " +
                    "and you don't get a message but you get the option to type PRESS ENTER, \n " +
                    "it's because the buffer is being cleared, we are working on fixing it.");
            System.out.println(" ");
            System.out.println("\u001B[0m");
            System.out.println("Select an option:");
            System.out.println("1. Movies");
            System.out.println("2. Movies sorted by name");
            System.out.println("3. Movies sorted by rating");
            System.out.println("4. Movie details");
            System.out.println("5. Add new movie");
            System.out.println("6. Rename movie");
            System.out.println("7. Update rating");
            System.out.println("8. Update category");
            System.out.println("9. Update review");
            System.out.println("10. Save to file");
            System.out.println("11. Load from file");
            System.out.println("12. Exit");
            System.out.println("13.Delete Movies");
            String input = scanner.nextLine();

            try {
                int option = Integer.parseInt(input);
                switch (option) {
                    case 1:
                        movieManager.displayMoviesByCategoryInteractive();
                        break;
                    case 2:

                        movieManager.displayMoviesByNameAscending();
                        break;
                    case 3:
                        movieManager.displayMoviesByRatingDescending();
                        break;
                    case 4:

                        System.out.println("Enter the title of the movie");

                        String movieTit = scanner.nextLine();

                        movieManager.displayDetails(movieTit);

                        break;

                    case 5:

                        boolean flags = false;
                        String category = "";
                        String sourceAdd="";
                        String reviewAdd="";
                        while (!flags) {
                            try {
                                System.out.println("Select your category \n");

                                System.out.println("1. Horror");
                                System.out.println("2. Action");
                                System.out.println("3. Comedy");
                                System.out.println("4. Drama");
                                System.out.println("5. Sci-Fi");
                                System.out.println("6. Exit");
                                int opt = scanner.nextInt();
                                scanner.nextLine();

                                switch (opt) {
                                    case 1:
                                        category = "Horror";
                                        flags = true;
                                        break;
                                    case 2:
                                        category = "Action";
                                        flags = true;
                                        break;
                                    case 3:
                                        category = "Comedy";
                                        flags = true;
                                        break;
                                    case 4:
                                        category = "Drama";
                                        flags = true;
                                        break;
                                    case 5:
                                        category = "Sci-Fi";
                                        flags = true;
                                        break;
                                    case 6:
                                        flags = true;
                                        category="exit";
                                        break;
                                    default:
                                        System.out.println("Opción no válida. Inténtelo de nuevo.");
                                        break;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Please, select a valid numer");
                                scanner.next(); // Limpiar el búfer de entrada
                            }
                        }

                        if (category.equalsIgnoreCase("Exit")){
                            break;
                        }else{

                            System.out.println("Enter the title of the new movie");

                            String movieTitle = scanner.nextLine();

                            System.out.println("Select the rating of the movie 0-5");
                            double rating = 0;
                            try {
                                rating = scanner.nextDouble();
                                scanner.nextLine(); // Consumir la nueva línea
                                if (rating < 0 || rating > 5) {
                                    System.out.println("Please, select a number between 0 - 5");

                                }else{

                                    Movie movie = new Movie(movieTitle, category, rating, reviewAdd, sourceAdd);

                                    movieManager.addMovieInteractive(movie);
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Select a number");
                            }
                            break;
                        }



                    case 6:
                        scanner.nextLine();
                        System.out.println("Select the name of the movie that you want to rename");

                        String movieOriginal = scanner.nextLine();

                        System.out.println("Select the new name");

                        String movieReplace = scanner.nextLine();

                        movieManager.renameMovie(movieOriginal, movieReplace);
                        break;
                    case 7:
                        System.out.println("Write the name of the movie you want to update the Rating");
                        String movieToUpdate = scanner.nextLine();

                        movieManager.updateRatingInteractive(movieToUpdate);
                        break;

                    case 8:
                        System.out.println("Write the name of the movie you want to update the Category");
                        String movieCategoryOld = scanner.nextLine();
                        System.out.println("Write the new Category");
                        String movieCategoryNew = scanner.nextLine();

                        movieManager.updateCategoryInteractive(movieCategoryOld,movieCategoryNew);

                        break;

                    case 9:
                        System.out.println("Write the name of the movie you want to update the review");
                        String movieUpdate = scanner.nextLine();

                        System.out.println("Write the new Review");
                        String newReview = scanner.nextLine();

                        movieManager.updateReviewInteractive(movieUpdate, newReview);
                        break;
                    case 10:

                        System.out.println("Write the path where you want to save");
                        String pathSave = scanner.nextLine();


                        try {
                            if (pathSave.startsWith("\"") || pathSave.endsWith("\"")) {
                                throw new IllegalArgumentException("La ruta no debe estar entre comillas dobles");
                            }
                            movieManager.saveDocumentInteractive(pathSave);
                        } catch (IllegalArgumentException e) {
                            System.err.println("Error al guardar el archivo: " + e.getMessage());
                        }
                        break;

                    case 11:
                        try {
                            System.out.println("Write the path where you want to load");
                            String pathLoad = scanner.nextLine();
                            File file = new File(pathLoad);
                            if (!file.exists()) {
                                throw new FileNotFoundException("El archivo no existe: Verifica que el archivo movie exista en la ruta especificada.");
                            }
                            movieManager.loadDocumentInteractive(pathLoad);
                        } catch (FileNotFoundException e) {
                            System.out.println("Error: " + e.getMessage());
                        
                        }
                        break;

                    case 12:
                        System.out.println("Saliendo del programa...");
                        scanner.close();
                        System.exit(0);

                    case 13:
                        System.out.println("Enter the title of the new movie, you want to delete");

                        String movieDelete = scanner.nextLine();
                        System.out.println(movieDelete);

                        movieManager.removeMovie(movieDelete);
                        break;
                    default:
                        System.out.println("Opción no válida. Inténtelo de nuevo.");
                        break;
                }
            }catch (NumberFormatException e) {
                System.out.println("Please enter a valid number (1-13)");
            }
        }


    }


    //**
    // This method call the interactive
    //
    // */
    @Command(name = "-interactive", description = "Run the application in interactive mode")
    public void interactive() {
        run();
    }
}