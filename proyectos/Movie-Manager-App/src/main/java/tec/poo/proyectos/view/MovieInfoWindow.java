package tec.poo.proyectos.view;

import tec.poo.proyectos.controller.Movie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MovieInfoWindow extends JFrame {
    private JTextField titleField;
    private JTextField ratingField;
    private JTextField sourceField;
    private JTextArea reviewArea;
    private JComboBox<String> categoryComboBox;
    private Movie movie;
    private ArrayList<Movie> movies;

    public MovieInfoWindow(Movie movie, ArrayList<String> moviesCategory, ArrayList<Movie> movies, MoviesWindow parentWindow) {
        this.movie = movie;
        this.movies = movies;
        setTitle("Movie Info");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        formPanel.add(new JLabel("Title:"));
        titleField = new JTextField(movie.getTitle());
        formPanel.add(titleField);

        formPanel.add(new JLabel("Rating:"));
        ratingField = new JTextField(String.valueOf(movie.getRating()));
        formPanel.add(ratingField);

        formPanel.add(new JLabel("Source:"));
        sourceField = new JTextField(movie.getSource());
        formPanel.add(sourceField);

        formPanel.add(new JLabel("Review:"));
        reviewArea = new JTextArea(movie.getReview());
        formPanel.add(new JScrollPane(reviewArea));

        formPanel.add(new JLabel("Category:"));
        categoryComboBox = new JComboBox<>(moviesCategory.toArray(new String[0]));
        categoryComboBox.setSelectedItem(movie.getCategory());
        formPanel.add(categoryComboBox);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton cancelButton = new JButton("Back");

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!titleField.getText().isEmpty() &&
                        !ratingField.getText().isEmpty() &&
                        !sourceField.getText().isEmpty() &&
                        !reviewArea.getText().isEmpty() &&
                        categoryComboBox.getSelectedItem() != null) {

                    String ratingStr = ratingField.getText();
                    if (isValidRating(ratingStr)) {
                        double rating = Double.parseDouble(ratingStr);
                        String newTitle = titleField.getText();

                        // Check if a movie with the same title already exists
                        boolean movieExists = false;
                        for (Movie m : movies) {
                            if (m != movie && m.getTitle().equalsIgnoreCase(newTitle)) {
                                movieExists = true;
                                break;
                            }
                        }

                        if (movieExists) {
                            JOptionPane.showMessageDialog(null, "The movie already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            movie.setTitle(newTitle);
                            movie.setRating(rating);
                            movie.setSource(sourceField.getText());
                            movie.setReview(reviewArea.getText());
                            movie.setCategory((String) categoryComboBox.getSelectedItem());
                            parentWindow.refreshTable();
                            JOptionPane.showMessageDialog(null, "Movie updated", "Success", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                            MovieManagerView initialScreen = new MovieManagerView(movies);
                            initialScreen.setVisible(true);

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Rating must be a number between 1 and 5.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this movie?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    movies.remove(movie);
                    parentWindow.refreshTable();
                    JOptionPane.showMessageDialog(null, "Delete successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    MovieManagerView initialScreen = new MovieManagerView(movies);
                    initialScreen.setVisible(true);

                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MovieManagerView initialScreen = new MovieManagerView(movies);
                initialScreen.setVisible(true);
            }
        });

        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private boolean isValidRating(String ratingStr) {
        try {
            double rating = Double.parseDouble(ratingStr);
            return rating >= 1.0 && rating <= 5.0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
