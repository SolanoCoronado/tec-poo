package tec.poo.proyectos.view;

import tec.poo.proyectos.controller.Movie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MovieDetailsWindows extends JFrame {
    private JTextField textField;
    private JButton searchButton;

    public MovieDetailsWindows(ArrayList<Movie> movies) {
        setTitle("Search Window");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el campo de texto y el botón
        textField = new JTextField(20);
        searchButton = new JButton("Buscar");

        // Configurar el layout
        setLayout(new FlowLayout());

        // Agregar componentes a la ventana
        add(textField);
        add(searchButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana actual y mostrar la pantalla anterior
                dispose();
                // Aquí asumo que la clase anterior se llama InitialScreen
                MovieManagerView initialScreen = new MovieManagerView(movies);
                initialScreen.setVisible(true);
            }
        });
        getContentPane().add(backButton, BorderLayout.SOUTH);

        // Agregar evento al botón
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = textField.getText();

                for (Movie movie : movies) {
                    if (movie.getTitle().equalsIgnoreCase(searchText)) {
                        JOptionPane.showMessageDialog(MovieDetailsWindows.this, " Movie: " + movie.getTitle()
                        +"\n Rating: " + movie.getRating() + "\n Category: "+ movie.getCategory() + "\n Review: " + movie.getReview() + " \n Source of review: " + movie.getSource());
                        return; // Terminar la búsqueda después de encontrar la primera coincidencia
                    }
                }

                JOptionPane.showMessageDialog(MovieDetailsWindows.this, "Texto ingresado: " + searchText);
            }
        });
    }



}
