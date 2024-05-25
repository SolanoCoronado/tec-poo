package tec.poo.proyectos.view;

import tec.poo.proyectos.controller.Movie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddMovieWindow extends JFrame {

    private JTextField nameField, ratingField, reviewField, sourceField;
    private JComboBox<String> categoryComboBox;
    private JButton addButton;
    private ArrayList<String> moviesCategory;

    public AddMovieWindow(ArrayList<Movie> movies) {
        // Inicializar categorías de películas
        moviesCategory = new ArrayList<>();
        moviesCategory.add("Action");
        moviesCategory.add("Comedy");
        moviesCategory.add("Drama");
        moviesCategory.add("Sci-Fi");
        moviesCategory.add("Horror");
        moviesCategory.add("Uncategorized");

        setTitle("Add Movie");
        setSize(400, 300); // Incrementar tamaño para acomodar nuevos campos y botones
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear etiquetas y campos de texto
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);

        JLabel ratingLabel = new JLabel("Rating:");
        ratingField = new JTextField(5);

        JLabel categoryLabel = new JLabel("Category:");
        categoryComboBox = new JComboBox<>(moviesCategory.toArray(new String[0]));

        JLabel reviewLabel = new JLabel("Review:");
        reviewField = new JTextField(20);

        JLabel sourceLabel = new JLabel("Source:");
        sourceField = new JTextField(10);

        // Crear botón de agregar
        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!nameField.getText().isEmpty() &&
                        !ratingField.getText().isEmpty() &&
                        categoryComboBox.getSelectedItem() != null &&
                        !reviewField.getText().isEmpty() &&
                        !sourceField.getText().isEmpty()) {

                    String name = nameField.getText();
                    String category = (String) categoryComboBox.getSelectedItem();
                    String ratingStr = ratingField.getText();
                    String review = reviewField.getText();
                    String source = sourceField.getText();

                    if (isValidRating(ratingStr)) {
                        double rating = Double.parseDouble(ratingStr);

                        // Verificar si la película ya existe
                        if (movieExists(movies, name, category)) {
                            JOptionPane.showMessageDialog(null, "The movie already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            Movie newMovie = new Movie(name, category, rating, review, source);
                            movies.add(newMovie);
                            JOptionPane.showMessageDialog(null, "Movie added", "Success", JOptionPane.INFORMATION_MESSAGE);
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

        // Crear panel y configurar layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        // Agregar componentes al panel
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(ratingLabel);
        panel.add(ratingField);
        panel.add(categoryLabel);
        panel.add(categoryComboBox);
        panel.add(reviewLabel);
        panel.add(reviewField);
        panel.add(sourceLabel);
        panel.add(sourceField);
        panel.add(addButton);

        // Agregar panel al content pane
        getContentPane().add(panel, BorderLayout.CENTER);

        // Crear y agregar botón de volver
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MovieManagerView initialScreen = new MovieManagerView(movies);
                initialScreen.setVisible(true);
            }
        });
        getContentPane().add(backButton, BorderLayout.SOUTH);
    }

    private boolean isValidRating(String ratingStr) {
        try {
            double rating = Double.parseDouble(ratingStr);
            return rating >= 1.0 && rating <= 5.0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean movieExists(ArrayList<Movie> movies, String name, String category) {
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(name) && movie.getCategory().equalsIgnoreCase(category)) {
                return true;
            }
        }
        return false;
    }
}
