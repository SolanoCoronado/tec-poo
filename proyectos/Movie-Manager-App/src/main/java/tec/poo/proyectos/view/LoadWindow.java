package tec.poo.proyectos.view;

import tec.poo.proyectos.controller.Movie;
import tec.poo.proyectos.model.MyReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoadWindow extends JFrame {

    private MyReader reader;
    private JTextField pathField;
    private JButton loadButton;
    private JButton backButton;

    public LoadWindow(ArrayList<Movie> movies) {
        setTitle("Load Data");
        setSize(400, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear etiquetas y campo de texto
        JLabel pathLabel = new JLabel("File Path:");
        pathField = new JTextField(20);

        // Crear botón de cargar
        loadButton = new JButton("Load");
        loadButton.addActionListener(new ActionListener() {
            private MyReader reader;

            @Override
            public void actionPerformed(ActionEvent e) {
                String path = pathField.getText().trim(); // Obtener la ruta del campo de texto y eliminar espacios en blanco al inicio y al final
                if (path.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "File path cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    movies.clear();
                    this.reader = new MyReader(path);
                    movies.addAll(reader.readFromFile());
                    JOptionPane.showMessageDialog(null, "Load successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    SwingUtilities.invokeLater(() -> {
                        MovieManagerView initialScreen = new MovieManagerView(movies);
                        initialScreen.setVisible(true);
                    });
                }
            }
        });

        // Crear botón de volver
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(() -> {
                    MovieManagerView initialScreen = new MovieManagerView(movies);
                    initialScreen.setVisible(true);
                });
            }
        });

        // Crear panel y establecer layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        // Agregar componentes al panel
        panel.add(pathLabel);
        panel.add(pathField);
        panel.add(loadButton);
        panel.add(backButton);

        // Agregar el panel al content pane
        getContentPane().add(panel, BorderLayout.CENTER);
    }
}
