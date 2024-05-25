package tec.poo.proyectos.view;

import tec.poo.proyectos.controller.Movie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;

public class MoviesWindow extends JFrame {

    private DefaultTableModel tableModel;
    private ArrayList<Movie> movies;
    private JComboBox<String> categoryComboBox;
    private ArrayList<String> moviesCategory;

    public MoviesWindow(ArrayList<Movie> movies, String state) {
        this.movies = movies;

        moviesCategory = new ArrayList<>();
        moviesCategory.add("Action");
        moviesCategory.add("Comedy");
        moviesCategory.add("Drama");
        moviesCategory.add("Sci-Fi");
        moviesCategory.add("Horror");
        moviesCategory.add("Uncategorized");

        setTitle("Movies List");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear panel para los botones de ordenamiento y filtrado
        JPanel sortPanel = new JPanel(new FlowLayout());
        JButton sortByNameButton = new JButton("Sort by Name");
        JButton sortByRatingButton = new JButton("Sort by Rating");
        JButton buttonAddMovie = new JButton("Add movie");

        sortByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortMoviesBy("name");
            }
        });

        sortByRatingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortMoviesBy("rating");
            }
        });

        buttonAddMovie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddMovie(movies);
                dispose();
            }
        });

        categoryComboBox = new JComboBox<>(moviesCategory.toArray(new String[0]));
        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterMoviesByCategory((String) categoryComboBox.getSelectedItem());
            }
        });

        sortPanel.add(new JLabel("Filter by Category:"));
        sortPanel.add(categoryComboBox);
        sortPanel.add(sortByNameButton);
        sortPanel.add(sortByRatingButton);
        sortPanel.add(buttonAddMovie);

        getContentPane().add(sortPanel, BorderLayout.NORTH);

        // Crear el modelo de tabla con las columnas Title, Rating, Category y Action
        tableModel = new DefaultTableModel(new Object[]{"Title", "Rating", "Category", "Action"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Hacer editable solo la columna de acción
            }
        };

        // Inicialmente ordenar y agregar las películas al modelo de tabla
        sortMoviesBy(state);

        // Crear la tabla de películas a partir del modelo
        JTable movieTable = new JTable(tableModel);
        movieTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Añadir el TableCellRenderer para la columna de botones
        movieTable.getColumn("Action").setCellRenderer(new ButtonRenderer());
        movieTable.getColumn("Action").setCellEditor(new ButtonEditor(new JTextField(), movies, this));

        // Agregar la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(movieTable);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Crear y agregar el botón de volver
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

    private void openAddMovie(ArrayList<Movie> movies) {
        AddMovieWindow window = new AddMovieWindow(movies);
        window.setVisible(true);
    }

    private void sortMoviesBy(String criteria) {
        ArrayList<Movie> sortedMovies = new ArrayList<>(movies);
        if (criteria.equalsIgnoreCase("name")) {
            sortedMovies.sort(Comparator.comparing(movie -> movie.getTitle().toLowerCase()));

        } else if (criteria.equalsIgnoreCase("rating")) {
            sortedMovies.sort(Comparator.comparing(Movie::getRating).reversed());
        }

        // Limpiar el modelo de tabla
        tableModel.setRowCount(0);

        // Agregar las películas ordenadas al modelo de tabla
        for (Movie movie : sortedMovies) {
            tableModel.addRow(new Object[]{movie.getTitle(), movie.getRating(), movie.getCategory(), "Info"});
        }
    }

    private void filterMoviesByCategory(String category) {
        ArrayList<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getCategory().equalsIgnoreCase(category)) {
                filteredMovies.add(movie);
            }
        }

        // Limpiar el modelo de tabla
        tableModel.setRowCount(0);

        // Agregar las películas filtradas al modelo de tabla
        for (Movie movie : filteredMovies) {
            tableModel.addRow(new Object[]{movie.getTitle(), movie.getRating(), movie.getCategory(), "Info"});
        }
    }

    public ArrayList<String> getMoviesCategory() {
        return moviesCategory;
    }

    public void refreshTable() {
        sortMoviesBy((String) categoryComboBox.getSelectedItem());
    }

    public void openMovieInfoWindow(Movie movie) {
        dispose();
        MovieInfoWindow infoWindow = new MovieInfoWindow(movie, moviesCategory, movies, this);
        infoWindow.setVisible(true);
    }

    // Método para validar el rating
    private boolean isValidRating(String ratingStr) {
        try {
            double rating = Double.parseDouble(ratingStr);
            return rating >= 1.0 && rating <= 5.0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Renderizador para la columna de botones
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    // Editor para la columna de botones
    class ButtonEditor extends DefaultCellEditor {
        private String label;
        private boolean clicked;
        private ArrayList<Movie> movies;
        private MoviesWindow parentWindow;

        public ButtonEditor(JTextField textField, ArrayList<Movie> movies, MoviesWindow parentWindow) {
            super(textField);
            this.movies = movies;
            this.parentWindow = parentWindow;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            JButton button = new JButton(label);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Movie movie = movies.get(row);
                    parentWindow.openMovieInfoWindow(movie);
                }
            });
            clicked = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            clicked = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
}
