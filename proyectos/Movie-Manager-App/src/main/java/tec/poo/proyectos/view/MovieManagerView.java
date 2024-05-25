package tec.poo.proyectos.view;

import tec.poo.proyectos.controller.Movie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MovieManagerView extends JFrame {
    public MovieManagerView(ArrayList<Movie> movies) {
        // Configurar el título de la ventana
        setTitle("Movie Manager");

        // Configurar el comportamiento de cierre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configurar un layout para los componentes
        setLayout(new BorderLayout());

        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem loadMenuItem = new JMenuItem("Load");
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        JMenu movieMenu = new JMenu("Movie");
        JMenuItem viewMoviesMenuItem = new JMenuItem("View Movies");

        JMenu helpMenu = new JMenu("Help");
        JMenuItem personInfoItem = new JMenuItem("About Movie Manager");

        // Agregar ActionListener a los elementos del menú
        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                openSave(movies);
            }
        });

        loadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                openLoad(movies);
            }
        });

        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        viewMoviesMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                openMoviesWindow(movies, "normal");
            }
        });

        personInfoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "Creator: Gabriel Solano Coronado @GASC \n" +
                        "Course: Programación Orientada a Objetos TEC \n" +
                        "Carnet: 2019033687\n" +
                        "Software Version: 2.0";
                JOptionPane.showMessageDialog(MovieManagerView.this, message, "Personal Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Agregar elementos al menú
        fileMenu.add(saveMenuItem);
        fileMenu.add(loadMenuItem);
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        movieMenu.add(viewMoviesMenuItem);
        menuBar.add(movieMenu);

        helpMenu.add(personInfoItem);
        menuBar.add(helpMenu);

        // Establecer la barra de menú en el JFrame
        setJMenuBar(menuBar);

        // Crear un JPanel personalizado para el texto centrado
        JPanel centerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setFont(new Font("Serif", Font.BOLD, 36));
                FontMetrics fm = g.getFontMetrics();
                String text = "Movie Manager GUI";
                int x = (getWidth() - fm.stringWidth(text)) / 2;
                int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
                g.drawString(text, x, y);
            }
        };

        // Agregar el panel personalizado al JFrame
        add(centerPanel, BorderLayout.CENTER);

        // Configurar tamaño de la ventana y ubicación
        setSize(800, 600);
        setLocationRelativeTo(null); // Centrar la ventana
    }

    private void openMoviesWindow(ArrayList<Movie> movies, String state) {
        dispose();
        MoviesWindow window = new MoviesWindow(movies, state);
        window.setVisible(true);
    }

    private void openMoviesDetailsWindow(ArrayList<Movie> movies) {
        MovieDetailsWindows window = new MovieDetailsWindows(movies);
        window.setVisible(true);
    }

    private void openAddMovie(ArrayList<Movie> movies) {
        AddMovieWindow window = new AddMovieWindow(movies);
        window.setVisible(true);
    }

    private void openSave(ArrayList<Movie> movies) {
        dispose();
        SaveWindow window = new SaveWindow(movies);
        window.setVisible(true);
    }

    private void openLoad(ArrayList<Movie> movies) {
        dispose();
        LoadWindow window = new LoadWindow(movies);
        window.setVisible(true);
    }
}
