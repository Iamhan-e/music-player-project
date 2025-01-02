package gui;

import backend.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MusicLibraryGUI {
    Artist artist=null;
    //    private AudioPlayer currentAudioPlayer = new AudioPlayer(); // Tracks the current audio player
    private final LoginManager loginManager;
    private final MusicLibraryManager musicLibraryManager;
    private JFrame frame;

    public MusicLibraryGUI() {

        // Initialize LoginManager and register a test user
        loginManager = new LoginManager();

        // Initialize MusicLibraryManager
        musicLibraryManager = new MusicLibraryManager();
        musicLibraryManager.addSong(new Song(
                "Hi",
                "Nahom",
                "Pop",
                new File("C:\\Users\\Admin\\Desktop\\Harmony OOP  Final version\\src\\songs\\spotifydown.com - Wede Teraraw Chaf.mp3"),
                new File("C:\\Users\\Admin\\Desktop\\Harmony OOP  Final version\\src\\resources\\top-section-bg.jpeg")
        ));
        musicLibraryManager.addSong(new Song(
                "Hi",
                "Nahom",
                "Rock",
                new File("C:\\Users\\Admin\\Desktop\\Harmony OOP  Final version\\src\\songs\\spotifydown.com - Wede Teraraw Chaf.mp3"),
                new File("C:\\Users\\Admin\\Desktop\\Harmony OOP  Final version\\src\\resources\\top-section-bg.jpeg")
        ));musicLibraryManager.addSong(new Song(
                "Hi",
                "Nahom",
                "Rock",
                new File("C:\\Users\\Admin\\Desktop\\Harmony OOP  Final version\\src\\songs\\spotifydown.com - Wede Teraraw Chaf.mp3"),
                new File("C:\\Users\\Admin\\Desktop\\Harmony OOP  Final version\\src\\resources\\top-section-bg.jpeg")
        ));musicLibraryManager.addSong(new Song(
                "Hi",
                "Nahom",
                "Rock",
                new File("C:\\Users\\Admin\\Desktop\\Harmony OOP  Final version\\src\\songs\\spotifydown.com - Wede Teraraw Chaf.mp3"),
                new File("C:\\Users\\Admin\\Desktop\\Harmony OOP  Final version\\src\\resources\\top-section-bg.jpeg")
        ));
        musicLibraryManager.addSong(new Song(
                "By",
                "Nahom",
                "Pop",
                new File("C:\\Users\\Admin\\Desktop\\Harmony OOP  Final version\\src\\songs\\spotifydown.com - Wede Teraraw Chaf.mp3"),
                new File("C:\\Users\\Admin\\Desktop\\Harmony OOP  Final version\\src\\resources\\istockphoto-1317323736-612x612.jpg")
        ));
        musicLibraryManager.addSong(new Song(
                "Damtew",
                "Nahom",
                "Pop",
                new File("C:\\Users\\Admin\\Desktop\\Harmony OOP  Final version\\src\\songs\\spotifydown.com - Wede Teraraw Chaf.mp3"),
                new File("C:\\Users\\Admin\\Desktop\\Harmony OOP  Final version\\src\\resources\\MainAfter.jpg")
        ));
        musicLibraryManager.addSong(new Song(
                "Gorilaa",
                "Nahom",
                "Pop",
                new File("C:\\Users\\Admin\\Desktop\\Harmony OOP  Final version\\src\\songs\\spotifydown.com - Wede Teraraw Chaf.mp3"),
                new File("C:\\Users\\Admin\\Desktop\\Harmony OOP  Final version\\src\\resources\\ai-generated-mysterious-dark-forest-with-stars-in-the-sky-night-forest-with-full-moon-and-stars-in-the-sky-photo.jpg")
        ));

        // Initialize the GUI
        initialize();
    }

    void initialize() {
        // Create the main frame
        JFrame frame = new JFrame("Melody Nexus");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Add theme toggle to the frame
        addThemeToggle(frame);
//

        // Main panel with CardLayout
        JPanel mainPanel = new JPanel(new CardLayout());
        frame.add(mainPanel);

        // Login Panel
        JPanel loginPanel = createLoginPanel(mainPanel, frame);
        mainPanel.add(loginPanel, "LoginPanel");

        // Library Panel
        JPanel libraryPanel = createLibraryPanel();
        mainPanel.add(libraryPanel, "LibraryPanel");

        frame.setVisible(true);
    }
    private void addThemeToggle(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        JMenu viewMenu = new JMenu("View");

        JToggleButton toggleThemeButton = new JToggleButton("🌞 Day Mode");
        toggleThemeButton.setFocusPainted(false);
        toggleThemeButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        toggleThemeButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        toggleThemeButton.setBackground(Color.DARK_GRAY);
        toggleThemeButton.setForeground(Color.WHITE);
        toggleThemeButton.setOpaque(true);

        // Action listener for the toggle button
        toggleThemeButton.addActionListener(e -> {
            if (toggleThemeButton.isSelected()) {
                toggleThemeButton.setText("🌙 Night Mode");
                switchToNightTheme(frame);
            } else {
                toggleThemeButton.setText("🌞 Day Mode");
                switchToDayTheme(frame);
            }
        });

        viewMenu.add(toggleThemeButton); // Add toggle button to the menu
        menuBar.add(viewMenu);
        frame.setJMenuBar(menuBar);
    }


    private JPanel createLoginPanel(JPanel mainPanel, JFrame frame) {
        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Melody Nexus: Music Collection Library");
        titleLabel.setFont(new Font("Georgia", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(titleLabel, gbc);

        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        loginPanel.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        loginPanel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(189, 154, 130));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        loginPanel.add(loginButton, gbc);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter both username and password.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (loginManager.authenticate(username, password)) {
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "LibraryPanel");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Register Button
        JButton registerButton = new JButton("Register");
        registerButton.setBackground(new Color(100, 180, 100));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        gbc.gridy = 4;
        loginPanel.add(registerButton, gbc);

        registerButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter both username and password.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (loginManager.isUserRegistered(username)) {
                JOptionPane.showMessageDialog(frame, "User already exists. Please try a different username.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            User newUser = new User(username, password);
            loginManager.registerUser(newUser);
            saveUserToFile(newUser);

            JOptionPane.showMessageDialog(frame, "Registration successful! You can now log in.", "Success", JOptionPane.INFORMATION_MESSAGE);
        });

        return loginPanel;
    }

    private void saveUserToFile(User user) {
        File file = new File("users.txt");
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(user.getUsername() + "," + user.getPassword() + "\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error saving user data. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel createLibraryPanel() {
        // Create the main library panel with BorderLayout
        JPanel libraryPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    // Load the background image
                    ImageIcon backgroundIcon = new ImageIcon("C:\\Users\\Admin\\Desktop\\Harmony OOP  Final version\\src\\resources\\DALL·E 2024-12-28 22.51.12 - A serene and artistic background design for a music library, featuring soft, flowing waves of various shades of blue, green, and purple, with abstract.jpeg");
                    Image backgroundImage = backgroundIcon.getImage();

                    if (backgroundImage != null) {
                        // Scale the image to fit the panel's size
                        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                    } else {
                        System.out.println("Failed to load image.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // Title for the music library
        JLabel libraryTitle = new JLabel("Music Library", SwingConstants.CENTER);
        libraryTitle.setFont(new Font("Georgia", Font.BOLD, 22));
        libraryPanel.add(libraryTitle, BorderLayout.NORTH);

        // Search area
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JTextField searchField = new JTextField();
        searchField.setFont(new Font("Verdana", Font.PLAIN, 16));
        searchField.setPreferredSize(new Dimension(300, 30));

        JButton searchByGenreButton = new JButton("Search by Genre");
        searchByGenreButton.setBackground(new Color(34, 139, 34));
        searchByGenreButton.setForeground(Color.WHITE);
        searchByGenreButton.setFocusPainted(false);

        JButton searchByArtistButton = new JButton("Search by Artist");
        searchByArtistButton.setBackground(new Color(70, 130, 180));
        searchByArtistButton.setForeground(Color.WHITE);
        searchByArtistButton.setFocusPainted(false);
        //manage genre
        // Add Manage Genres button
        JButton manageGenresButton = new JButton("Manage Genres");
        manageGenresButton.setBackground(new Color(128, 0, 128));
        manageGenresButton.setForeground(Color.WHITE);

        // Add action to open GenreGUI
        manageGenresButton.addActionListener(e -> {
            Genre genre = new Genre("Default Genre", "Description of the default genre"); // Placeholder genre
            new GenreGUI(genre);
        });
        //panel for genremanager
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        buttonPanel.add(searchByGenreButton);
        buttonPanel.add(searchByArtistButton);
        buttonPanel.add(manageGenresButton);
        JButton recentPlaysButton = new JButton();
//        buttonPanel.add(recentPlaysButton);
        JButton createPlaylistButton = new JButton();
//        buttonPanel.add(createPlaylistButton);
        JButton viewPlaylistsButton = new JButton();
//        buttonPanel.add(viewPlaylistsButton);


        JPanel buttonPanel1= new JPanel(new GridLayout(1, 2, 10, 0));
        buttonPanel.add(searchByGenreButton);
        buttonPanel.add(searchByArtistButton);

        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(buttonPanel, BorderLayout.EAST);

        // Panel to hold search results
        JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel to show selected song details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add search functionality
        searchByGenreButton.addActionListener(e -> {
            String genre = searchField.getText().trim();
            List<Song> songs = musicLibraryManager.searchByGenre(genre);
            displaySearchResults(songs, "genre", genre, resultPanel, detailsPanel);
        });

        searchByArtistButton.addActionListener(e -> {
            String artist = searchField.getText().trim();
            List<Song> songs = musicLibraryManager.searchByArtist(artist);
            displaySearchResults(songs, "artist", artist, resultPanel, detailsPanel);
        });

        recentPlaysButton = new JButton("Recent Plays");
        recentPlaysButton.setBackground(new Color(70, 130, 180));
        recentPlaysButton.setForeground(Color.WHITE);

        // Recent Plays button functionality
        recentPlaysButton.addActionListener(e -> {
            List<Song> recentSongs = musicLibraryManager.getRecentPlays();
            displaySearchResults(recentSongs, "recent plays", "", resultPanel, detailsPanel);
        });

        buttonPanel.add(recentPlaysButton);

        // Create scrollable area for the results
        JScrollPane resultScrollPane = new JScrollPane(resultPanel);
        resultPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        libraryPanel.add(searchPanel, BorderLayout.NORTH);
        libraryPanel.add(resultScrollPane, BorderLayout.CENTER);
        libraryPanel.add(detailsPanel, BorderLayout.SOUTH);

        // Playlist buttons
        createPlaylistButton = new JButton("Create Playlist");
        createPlaylistButton.setBackground(new Color(255, 165, 0));
        createPlaylistButton.setForeground(Color.WHITE);

        viewPlaylistsButton = new JButton("View Playlists");
        viewPlaylistsButton.setBackground(new Color(34, 139, 34));
        viewPlaylistsButton.setForeground(Color.WHITE);

        // Create Playlist functionality
        createPlaylistButton.addActionListener(e -> {
            String playlistName = JOptionPane.showInputDialog(frame, "Enter Playlist Name:");
            if (playlistName != null && !playlistName.trim().isEmpty()) {
                musicLibraryManager.createPlaylist(playlistName);

                // Save the playlist name to playlist.txt
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("playlist.txt", true))) {
                    writer.write(playlistName);
                    writer.newLine();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Failed to save playlist.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        // View Playlists functionality
        viewPlaylistsButton.addActionListener(e -> {
            Set<String> playlistNames = new HashSet<>();

            // Load playlists from playlist.txt
            try (BufferedReader reader = new BufferedReader(new FileReader("playlist.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    playlistNames.add(line.trim());
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Failed to load playlists.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            String selectedPlaylist = (String) JOptionPane.showInputDialog(
                    frame,
                    "Select a Playlist:",
                    "Playlists",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    playlistNames.toArray(),
                    null
            );

            if (selectedPlaylist != null) {
                List<Song> playlistSongs = musicLibraryManager.getPlaylist(selectedPlaylist);
                displaySearchResults(playlistSongs, "playlist", selectedPlaylist, resultPanel, detailsPanel);
            }
        });

        buttonPanel.add(createPlaylistButton);
        buttonPanel.add(viewPlaylistsButton);

        libraryPanel.repaint();

        return libraryPanel;
    }

    // Helper method to load playlist from file
    private Playlist loadPlaylist(String playlistName) {
        try {
            // Assuming playlists are saved in individual files, named after the playlist
            return Playlist.loadFromFile("C:\\Users\\Admin\\Desktop\\Harmony OOP  Final version\\playlist.txt" );
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Failed to load playlist.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    //now we will do
// Helper method to display the search results with "Genre's" title
// Helper method to display the search results with "Genre's" title
// Helper method to display the search results with "Genre's" title aligned left and songs listed side by side
    private void switchToDayTheme(JFrame frame) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            updateUI(frame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void switchToNightTheme(JFrame frame) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            UIManager.put("control", new Color(45, 45, 45));
            UIManager.put("text", Color.WHITE);
            UIManager.put("nimbusLightBackground", new Color(60, 63, 65));
            UIManager.put("info", new Color(30, 30, 30));
            UIManager.put("nimbusBase", new Color(18, 30, 49));
            UIManager.put("nimbusBlueGrey", new Color(45, 45, 45));
            UIManager.put("nimbusSelectionBackground", new Color(75, 110, 175));
            updateUI(frame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateUI(JFrame frame) {
        SwingUtilities.updateComponentTreeUI(frame);
        frame.repaint();
    }

    private void displaySearchResults(List<Song> songs, String searchType, String searchValue, JPanel resultPanel, JPanel detailsPanel) {
        // Clear the previous results
        resultPanel.removeAll();
        detailsPanel.removeAll();

        if (songs.isEmpty()) {
            JLabel noResultsLabel = new JLabel("No songs found for " + searchType + ": " + searchValue);
            resultPanel.add(noResultsLabel);
        } else {
            // Add "Genre's" title aligned to the left
            JLabel genreTitleLabel = new JLabel("Genre's");
            genreTitleLabel.setFont(new Font("Verdana", Font.BOLD, 22));
            genreTitleLabel.setForeground(new Color(255, 99, 71)); // Tomato color for the title
            resultPanel.add(genreTitleLabel);

            // Determine the grid dimensions based on the number of songs
            int columns = 4; // Number of columns for the grid
            int rows = (int) Math.ceil((double) songs.size() / columns);

            // Set GridLayout for resultPanel
            resultPanel.setLayout(new GridLayout(rows, columns, 15, 15)); // Gaps of 15px between rows and columns

            // Add each song with album cover
            for (Song song : songs) {
                // Load album cover image
                File albumCoverFile = song.getAlbumCover();
                ImageIcon albumCoverIcon = new ImageIcon();

                if (albumCoverFile != null && albumCoverFile.exists()) {
                    Image image = new ImageIcon(albumCoverFile.getAbsolutePath()).getImage();
                    albumCoverIcon = new ImageIcon(image.getScaledInstance(150, 150, Image.SCALE_SMOOTH));
                } else {
                    // Placeholder for missing images
                    albumCoverIcon = new ImageIcon(new ImageIcon("path_to_placeholder_image.jpg").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
                }

                // Panel for each song item
                JPanel songPanel = new JPanel();
                songPanel.setLayout(new BorderLayout());
                songPanel.setBackground(Color.BLACK); // Make background black
                songPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // Border to indicate clickability

                // Album cover image
                JLabel imageLabel = new JLabel(albumCoverIcon);
                imageLabel.setHorizontalAlignment(JLabel.CENTER);
                songPanel.add(imageLabel, BorderLayout.CENTER);

                // Title label
                JLabel titleLabel = new JLabel(song.getTitle());
                titleLabel.setHorizontalAlignment(JLabel.CENTER);
                titleLabel.setForeground(Color.BLACK); // Set title text to black
                titleLabel.setBackground(Color.WHITE); // Optional: Add a white background for contrast
                titleLabel.setOpaque(true); // Ensure background is visible
                songPanel.add(titleLabel, BorderLayout.SOUTH);

                // Add MouseListener to make the song panel clickable
                songPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent evt) {
                        // Display song details in the details panel when clicked
                        showSongDetails(song, detailsPanel);

                        // Add the "Add to Playlist" button after a song is selected
                        JButton addToPlaylistButton = new JButton("Add to Playlist");
                        addToPlaylistButton.setBackground(new Color(255, 69, 0));
                        addToPlaylistButton.setForeground(Color.WHITE);

                        // Add action listener for the "Add to Playlist" button
                        addToPlaylistButton.addActionListener(e -> {
                            Set<String> playlistNames = new HashSet<>();

                            // Load playlists from playlist.txt
                            try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Admin\\Desktop\\Harmony OOP  Final version\\playlist.txt"))) {
                                String line;
                                while ((line = reader.readLine()) != null) {
                                    playlistNames.add(line.trim());
                                }
                            } catch (IOException ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(frame, "Failed to load playlists.", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            // Show dialog to select a playlist
                            String selectedPlaylist = (String) JOptionPane.showInputDialog(
                                    frame,
                                    "Select a Playlist to Add Song:",
                                    "Add to Playlist",
                                    JOptionPane.PLAIN_MESSAGE,
                                    null,
                                    playlistNames.toArray(),
                                    null
                            );

                            if (selectedPlaylist != null) {
                                // Load the selected playlist from a file
                                Playlist playlist = loadPlaylist(selectedPlaylist);

                                if (playlist != null) {
                                    // Add the song to the selected playlist
                                    playlist.addSong(song);

                                    // Optionally, save the updated playlist back to the file
                                    try {
                                        playlist.saveToFile("C:\\Users\\Admin\\Desktop\\Harmony OOP  Final version\\playlist.txt" + selectedPlaylist + ".txt");
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                        JOptionPane.showMessageDialog(frame, "Failed to save playlist.", "Error", JOptionPane.ERROR_MESSAGE);
                                    }

                                    JOptionPane.showMessageDialog(frame,
                                            "Song added to playlist: " + selectedPlaylist,
                                            "Success",
                                            JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                        });

                        // Add the "Add to Playlist" button to the details panel
                        detailsPanel.add(addToPlaylistButton);
                        detailsPanel.revalidate();
                        detailsPanel.repaint();
                    }
                });

                // Add the song panel to the result panel
                resultPanel.add(songPanel);
            }
        }

        // Refresh the panel to show the new results
        resultPanel.revalidate();
        resultPanel.repaint();
    }

    // Method to display song details and buttons
    private AudioPlayer currentAudioPlayer = null;

    private void showSongDetails(Song song, JPanel detailsPanel) {

        detailsPanel.removeAll();

        // Stop the currently playing song before playing a new one
        if (currentAudioPlayer != null) {
            currentAudioPlayer.stopSong();
        }

        // Create a new AudioPlayer instance for the selected song
        currentAudioPlayer = new AudioPlayer();

        // Panel for song information (horizontal layout)
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel titleLabel = new JLabel("Title: " + song.getTitle());
        titleLabel.setFont(new Font("Georgia", Font.BOLD, 16));

        JLabel artistLabel = new JLabel("Artist: " + song.getArtist());
        artistLabel.setFont(new Font("Georgia", Font.PLAIN, 14));

        JLabel genreLabel = new JLabel("Genre: " + song.getGenre());
        genreLabel.setFont(new Font("Georgia", Font.PLAIN, 14));

        infoPanel.add(titleLabel);
        infoPanel.add(Box.createHorizontalStrut(10)); // Spacer
        infoPanel.add(artistLabel);
        infoPanel.add(Box.createHorizontalStrut(10)); // Spacer
        infoPanel.add(genreLabel);
//

        JSlider musicSlider= new JSlider(JSlider.HORIZONTAL, 0,100, 0);
        musicSlider.setSize(new Dimension(50, 40));
        musicSlider.setBackground(null);
        infoPanel.add(musicSlider);  // trying to add a slider element

        // Panel for buttons (horizontal layout)
        JPanel buttonPanel = new JPanel();


        JButton playButton = new JButton();

        playButton.setIcon(new ImageIcon("C:\\Users\\Admin\\Desktop\\Harmony OOP  Final version\\src\\assets\\playBtn.png"));
        playButton.setPreferredSize(new Dimension(40, 55) );
        playButton.setBorder(null);
        playButton.setBackground(null);
        playButton.setContentAreaFilled(false);
        playButton.setForeground(Color.black);


        JButton stopButton = new JButton();
        stopButton.setIcon(new ImageIcon("C:\\Users\\Admin\\Desktop\\Harmony OOP  Final version\\src\\assets\\pauseBtn.png"));
        stopButton.setPreferredSize(new Dimension(40, 55) );
        stopButton.setBorder(null);
       stopButton.setBackground(null);
        stopButton.setContentAreaFilled(false);
        stopButton.setForeground(Color.black);

        stopButton.setEnabled(false); // Initially disabled until playback starts
//
        JButton viewPlaylistButton = new JButton("View Playlist");
        viewPlaylistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPlaylist();  // Show the playlist in a dialog
            }

            private void showPlaylist() {

            }
        });
        buttonPanel.add(viewPlaylistButton);

        // Play button functionality
        playButton.addActionListener(e -> {
            currentAudioPlayer.playSong(song.getFile()); // Play the selected song
            stopButton.setEnabled(true);
            playButton.setEnabled(false);
        });
        //
        playButton.addActionListener(e -> {
            currentAudioPlayer.playSong(song.getFile());
            musicLibraryManager.addToRecentPlays(song);
            stopButton.setEnabled(true);
            playButton.setEnabled(false);
        });

//buttton
        JButton favoriteButton = new JButton("Favorite");
        favoriteButton.setBackground(new Color(255, 223, 0));
        favoriteButton.setForeground(Color.BLACK);
//


// Favorite button functionality
        favoriteButton.addActionListener(e -> {
            if (musicLibraryManager.getFavoriteSongs().contains(song)) {
                musicLibraryManager.removeFromFavorites(song);
                favoriteButton.setText("Favorite");
            } else {
                musicLibraryManager.addToFavorites(song);
                favoriteButton.setText("Unfavorite");
            }
        });

        buttonPanel.add(Box.createHorizontalStrut(10)); // Spacer between buttons
        buttonPanel.add(favoriteButton);

        // Stop button functionality
        stopButton.addActionListener(e -> {
            currentAudioPlayer.stopSong(); // Stop playback
            stopButton.
                    setEnabled(false);
            playButton.setEnabled(true);
        });
//
        // Add a button for "Add to Playlist"




// Optional: Add a JList to display playlist songs


        buttonPanel.add(playButton);
        buttonPanel.add(Box.createHorizontalStrut(10)); // Spacer between buttons
        buttonPanel.add(stopButton);
//
        JProgressBar progressBar = new JProgressBar();
        progressBar.setMaximum(song.getDuration()); // Assuming Song class has getDuration
        progressBar.setStringPainted(true);

// Timer to update progress bar
        Timer timer = new Timer(1000, e -> {
            if (currentAudioPlayer != null && currentAudioPlayer.isPlaying()) {
                progressBar.setValue((int) currentAudioPlayer.getPlaybackPosition());
                progressBar.setString(formatTime(currentAudioPlayer.getPlaybackPosition()) + " / " + formatTime(song.getDuration()));
            }
        });

// Start/Stop timer based on playback state
        playButton.addActionListener(e -> {
            timer.start();
            currentAudioPlayer.playSong(song.getFile());
        });
        stopButton.addActionListener(e -> {
            timer.stop();
            progressBar.setValue(0);
            currentAudioPlayer.stopSong();
        });

// Add the progress bar to the details panel
        detailsPanel.add(progressBar);

        // Add components to details panel
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.add(infoPanel);
        detailsPanel.add(buttonPanel);

        detailsPanel.revalidate();
        detailsPanel.repaint();
    }



    private void getClass(Object currentSong) {
    }

    private String formatTime(long seconds) {
        long minutes = seconds / 60;
        long secs = seconds % 60;
        return String.format("%02d:%02d", minutes, secs);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MusicLibraryGUI::new);
    }
}