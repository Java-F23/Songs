import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class UserMenu extends JFrame {
    private User user;
    private songLibrary library;

    public UserMenu(songLibrary library) {
        this.library = library;
        user = new User(library.getSongList(), library.categorizedSongs());

        setTitle("User Menu");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Close only this window, not the entire app
        setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel titleLabel = new JLabel("User Menu");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        constraints.gridx = 0;
        constraints.gridy = 0;
        contentPanel.add(titleLabel, constraints);

        // Create buttons for various user actions
        JButton displaySongListButton = new JButton("Display Song List");
        JButton searchByGenreButton = new JButton("Search Songs by Genre");
        JButton searchByArtistButton = new JButton("Search Songs by Artist");

        // Add action listeners to the buttons
        displaySongListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.displaySongList();
            }
        });

        searchByGenreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt the user to enter a genre and then call the searchSongsByGenre method
                String genre = JOptionPane.showInputDialog("Enter a genre:");
                user.searchSongsByGenre(genre);
            }
        });

        searchByArtistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt the user to enter an artist and then call the searchSongsByArtist method
                String artist = JOptionPane.showInputDialog("Enter an artist:");
                user.searchSongsByArtist(artist);
            }
        });

        constraints.gridy = 1;
        contentPanel.add(displaySongListButton, constraints);

        constraints.gridy = 2;
        contentPanel.add(searchByGenreButton, constraints);

        constraints.gridy = 3;
        contentPanel.add(searchByArtistButton, constraints);

        setContentPane(contentPanel);
    }
}
