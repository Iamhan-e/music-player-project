package gui;

import backend.Genre;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GenreGUI {
    private JFrame frame;
    private JPanel mainPanel;
    private List<Genre> genres;
    private final String genreFilePath = "C:\\Users\\Nahom W\\Desktop\\Harmony OOP  Final version\\Genre.txt";

    // Constructor
    public GenreGUI(Genre genre) {
        this.genres = loadGenresFromFile();
        initialize();
    }

    // Initialize the GUI
    private void initialize() {
        // Create the main frame
        frame = new JFrame("Genre Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Main panel with a vertical layout
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        // Add sections
        addGenresSection();
        addAddGenreForm();

        // Add the main panel to the frame
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        frame.add(scrollPane);

        frame.setVisible(true);
    }

    // Add genres section
    private void addGenresSection() {
        mainPanel.removeAll(); // Clear existing content

        for (Genre genre : genres) {
            JPanel genrePanel = new JPanel();
            genrePanel.setLayout(new GridLayout(0, 1));
            genrePanel.setBorder(BorderFactory.createTitledBorder(genre.getName()));

            JLabel descriptionLabel = new JLabel("Description: " + genre.getDescription());
            JLabel songCountLabel = new JLabel("Number of Songs: " + genre.getSongCount());
            JLabel averageRatingLabel = new JLabel("Average Rating: " + genre.getAverageRating());

            genrePanel.add(descriptionLabel);
            genrePanel.add(songCountLabel);
            genrePanel.add(averageRatingLabel);

            mainPanel.add(genrePanel);
        }

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // Add genre form
    private void addAddGenreForm() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 2));
        formPanel.setBorder(BorderFactory.createTitledBorder("Add New Genre"));

        JLabel nameLabel = new JLabel("Genre Name:");
        JTextField nameField = new JTextField();
        JLabel descriptionLabel = new JLabel("Description:");
        JTextField descriptionField = new JTextField();
        JButton addButton = new JButton("Add Genre");

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(descriptionLabel);
        formPanel.add(descriptionField);
        formPanel.add(new JLabel()); // Placeholder
        formPanel.add(addButton);

        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String description = descriptionField.getText().trim();

            if (!name.isEmpty() && !description.isEmpty()) {
                Genre newGenre = new Genre(name, description);
                genres.add(newGenre);
                saveGenreToFile(newGenre);
                addGenresSection(); // Refresh display
                JOptionPane.showMessageDialog(frame, "Genre added successfully!");
            } else {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        mainPanel.add(formPanel);
    }

    // Load genres from file
    private List<Genre> loadGenresFromFile() {
        List<Genre> genres = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(genreFilePath))) {
            String line;
            Genre currentGenre = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Genre:")) {
                    String[] parts = line.substring(6).split(";", 2);
                    currentGenre = new Genre(parts[0], parts[1]);
                    genres.add(currentGenre);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading Genre.txt: " + e.getMessage());
        }
        return genres;
    }

    // Save a new genre to the file
    private void saveGenreToFile(Genre genre) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(genreFilePath, true))) {
            writer.write("Genre:" + genre.getName() + ";" + genre.getDescription());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to Genre.txt: " + e.getMessage());
        }
    }

    // Main method to test the GUI
    public static void main(String[] args) {
        Genre genre = new Genre();
        new GenreGUI(genre);
    }
}
