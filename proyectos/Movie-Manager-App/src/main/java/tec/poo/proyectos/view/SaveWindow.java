package tec.poo.proyectos.view;

import tec.poo.proyectos.controller.Movie;
import tec.poo.proyectos.model.MyWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SaveWindow extends JFrame {

    private JTextField pathField;
    private JButton saveButton;
    private JButton exitButton;

    public SaveWindow(ArrayList<Movie> movies) {
        setTitle("Save Data");
        setSize(400, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear etiquetas y campo de texto
        JLabel pathLabel = new JLabel("File Path:");
        pathField = new JTextField(20);

        // Crear botón de guardar
        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            private MyWriter writer;

            @Override
            public void actionPerformed(ActionEvent e) {
                String path = pathField.getText(); // Obtener la ruta del campo de texto
                if (path.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(SaveWindow.this, "File path cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    this.writer = new MyWriter(path);
                    this.writer.writeToFile(path, movies);
                    JOptionPane.showMessageDialog(SaveWindow.this, "File saved in " + path, "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    MovieManagerView initialScreen = new MovieManagerView(movies);
                    initialScreen.setVisible(true);
                }
            }
        });

        // Crear botón de salir
        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MovieManagerView initialScreen = new MovieManagerView(movies);
                initialScreen.setVisible(true);
            }
        });

        // Crear panel y establecer layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        // Agregar componentes al panel
        panel.add(pathLabel);
        panel.add(pathField);
        panel.add(saveButton);
        panel.add(exitButton);

        // Agregar el panel al content pane
        getContentPane().add(panel, BorderLayout.CENTER);
    }
}
