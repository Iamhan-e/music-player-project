//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package gui;

import backend.Artist;
import backend.MusicLibraryManager;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ArtistGUI {
    private JFrame frame;
    private JTextField nameField;
    private JTextArea biographyField;
    private JTextField genreField;
    private JTextField socialMediaField;
    private JTextField collaboratorField;
    private JButton saveButton;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                ArtistGUI window = new ArtistGUI();
                window.frame.setVisible(true);
            } catch (Exception var1) {
                Exception e = var1;
                e.printStackTrace();
            }

        });
    }

    public ArtistGUI() {
        this.initialize();
    }

    private void initialize() {
        this.frame = new JFrame();
        this.frame.setBounds(100, 100, 500, 500);
        this.frame.setDefaultCloseOperation(3);
        this.frame.getContentPane().setLayout(new FlowLayout());
        JLabel nameLabel = new JLabel("Artist Name:");
        this.nameField = new JTextField();
        this.nameField.setColumns(20);
        JLabel biographyLabel = new JLabel("Biography:");
        this.biographyField = new JTextArea();
        this.biographyField.setRows(5);
        this.biographyField.setColumns(20);
        JScrollPane biographyScrollPane = new JScrollPane(this.biographyField);
        JLabel genreLabel = new JLabel("Genres (comma separated):");
        this.genreField = new JTextField();
        this.genreField.setColumns(20);
        JLabel socialMediaLabel = new JLabel("Social Media Links (comma separated):");
        this.socialMediaField = new JTextField();
        this.socialMediaField.setColumns(20);
        JLabel collaboratorLabel = new JLabel("Collaborators (comma separated):");
        this.collaboratorField = new JTextField();
        this.collaboratorField.setColumns(20);
        this.saveButton = new JButton("Save Artist");
        this.saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArtistGUI.this.saveArtist();
            }
        });
        this.frame.getContentPane().add(nameLabel);
        this.frame.getContentPane().add(this.nameField);
        this.frame.getContentPane().add(biographyLabel);
        this.frame.getContentPane().add(biographyScrollPane);
        this.frame.getContentPane().add(genreLabel);
        this.frame.getContentPane().add(this.genreField);
        this.frame.getContentPane().add(socialMediaLabel);
        this.frame.getContentPane().add(this.socialMediaField);
        this.frame.getContentPane().add(collaboratorLabel);
        this.frame.getContentPane().add(this.collaboratorField);
        this.frame.getContentPane().add(this.saveButton);
    }

    private void saveArtist() {
        String name = this.nameField.getText();
        String biography = this.biographyField.getText();
        String[] genres = this.genreField.getText().split(",");
        String[] socialMediaLinks = this.socialMediaField.getText().split(",");
        String[] collaborators = this.collaboratorField.getText().split(",");
        if (!name.isEmpty() && !biography.isEmpty()) {
            Artist artist = new Artist(name, biography);
            String[] var7 = genres;
            int var8 = genres.length;

            int var9;
            String collaborator;
            for(var9 = 0; var9 < var8; ++var9) {
                collaborator = var7[var9];
                artist.addGenre(collaborator.trim());
            }

            var7 = socialMediaLinks;
            var8 = socialMediaLinks.length;

            for(var9 = 0; var9 < var8; ++var9) {
                collaborator = var7[var9];
                artist.addSocialMediaLink(collaborator.trim());
            }

            var7 = collaborators;
            var8 = collaborators.length;

            for(var9 = 0; var9 < var8; ++var9) {
                collaborator = var7[var9];
                artist.addCollaborator(new Artist(collaborator.trim()));
            }

            try {
                artist.saveToFile("Artist.txt");
                JOptionPane.showMessageDialog(this.frame, "Artist saved successfully!", "Success", 1);
                this.clearFields();
            } catch (IOException var11) {
                JOptionPane.showMessageDialog(this.frame, "Error saving artist: " + var11.getMessage(), "Error", 0);
            }

        } else {
            JOptionPane.showMessageDialog(this.frame, "Name and biography cannot be empty!", "Error", 0);
        }
    }

    private void clearFields() {
        this.nameField.setText("");
        this.biographyField.setText("");
        this.genreField.setText("");
        this.socialMediaField.setText("");
        this.collaboratorField.setText("");
    }
}
