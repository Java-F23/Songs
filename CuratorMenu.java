import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CuratorMenu extends JFrame {
    private songLibrary library;

    public CuratorMenu(songLibrary library) {
        this.library = library;

        setTitle("Curator Menu");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Close only this window, not the entire app
        setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel titleLabel = new JLabel("Curator Menu");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        constraints.gridx = 0;
        constraints.gridy = 0;
        contentPanel.add(titleLabel, constraints);

        // Create buttons for various curator actions
        JButton addSongButton = new JButton("Add a Song");
        JButton modifySongButton = new JButton("Modify a Song");
        JButton removeSongButton = new JButton("Remove a Song");

        // Add action listeners to the buttons
        addSongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement the action to add a new song
                // You can open a form or dialog for adding a song
            }
        });

        modifySongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement the action to modify a song
                // You can open a form or dialog for modifying a song
            }
        });

        removeSongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement the action to remove a song
                // You can open a dialog to select the song to be removed
            }
        });

        constraints.gridy = 1;
        contentPanel.add(addSongButton, constraints);

        constraints.gridy = 2;
        contentPanel.add(modifySongButton, constraints);

        constraints.gridy = 3;
        contentPanel.add(removeSongButton, constraints);

        setContentPane(contentPanel);
    }
}
