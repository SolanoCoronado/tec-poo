package tec.poo.proyectos.controller;


import java.util.ArrayList;


public class Movie {


    //Declarate isntance
    private String title;
    private double  rating;
    private String category;
    private String review;
    private String source;

    /***
     * Constructor method
     *
     *
     */

    public Movie(String title, String category, double rating, String review,String source){
        this.title = title;
        this.rating=rating;
        this.review=review;
        this.category=category;
        this.source=source;

    }
    /***
     * Getter and setter all of instances
     */
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }


}
