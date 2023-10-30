import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

public class MusicApplication extends JFrame {
    private JPanel welcomePanel;
    private JPanel userPanel;
    private JPanel songPanel;
    private JPanel songLibraryPanel;
    private JPanel curatorPanel;
    private User user;
    private songLibrary songLibrary = new songLibrary();
    private Curator curator;
    private UserMenu usermenu;
    private CuratorMenu curatormenu;
    private Map<String, User> registeredUsers = new HashMap<>();

    public MusicApplication() {
        setTitle("Music Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new CardLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu navigationMenu = new JMenu("Navigation");
        JMenuItem welcomeItem = new JMenuItem("Welcome");
        JMenuItem userItem = new JMenuItem("User");
        JMenuItem songItem = new JMenuItem("Song");
        JMenuItem songLibraryItem = new JMenuItem("Song Library");
        JMenuItem curatorItem = new JMenuItem("Curator");

        welcomeItem.addActionListener(e -> showPanel("Welcome"));
        userItem.addActionListener(e -> showPanel("User"));
        songItem.addActionListener(e -> showPanel("Song"));
        songLibraryItem.addActionListener(e -> showPanel("Song Library"));
        curatorItem.addActionListener(e -> showPanel("Curator"));



        navigationMenu.add(welcomeItem);
        navigationMenu.add(userItem);
        navigationMenu.add(songItem);
        navigationMenu.add(songLibraryItem);
        navigationMenu.add(curatorItem);
        menuBar.add(navigationMenu);
        setJMenuBar(menuBar);

        welcomePanel = createWelcomePanel();
        userPanel = createUserPanel();
        songPanel = createSongPanel();
        songLibraryPanel = createSongLibraryPanel();
        curatorPanel = createCuratorPanel();

        add(welcomePanel, "Welcome");
        add(userPanel, "User");
        add(songPanel, "Song");
        add(songLibraryPanel, "Song Library");
        add(curatorPanel, "Curator");


        usermenu = new UserMenu(songLibrary);
        curatormenu = new CuratorMenu(songLibrary);

        // Add action listeners to the "User" and "Curator" menu items
        userItem.addActionListener(e -> {
            // Display the User Menu window
            usermenu.setVisible(true);
        });

        curatorItem.addActionListener(e -> {
            // Display the Curator Menu window
            curatormenu.setVisible(true);
        });



        // Display the welcome panel initially
        showPanel("Welcome");
    }

    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Welcome to the Music Application!"));
        return panel;
    }

    private JPanel createUserPanel() {
        JPanel panel = new JPanel();
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);
        JPanel genderPanel = new JPanel();
        genderPanel.setBorder(BorderFactory.createTitledBorder("Gender"));
        JRadioButton maleRadioButton = new JRadioButton("Male");
        JRadioButton femaleRadioButton = new JRadioButton("Female");
        JRadioButton otherRadioButton = new JRadioButton("Other");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderGroup.add(otherRadioButton);

        // Add radio buttons to the gender panel
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        genderPanel.add(otherRadioButton);

        JButton registerButton = new JButton("Register");

        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            if (!registeredUsers.containsKey(username)) {
                User user=null;
                registeredUsers.put(username, user);
                JOptionPane.showMessageDialog(this, "User " + username + " registered!");
            } else {
                JOptionPane.showMessageDialog(this, "Username " + username + " is already taken.");
            }

        });
        registerButton.setToolTipText("Click to register your username");
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(genderPanel);
        panel.add(registerButton);
        return panel;
    }

    private JPanel createSongPanel() {
        JPanel panel = new JPanel();
        JLabel songNameLabel = new JLabel("Song Name:");
        JTextField songNameField = new JTextField(20);
        JPanel genrePanel = new JPanel();
        genrePanel.setBorder(BorderFactory.createTitledBorder("Genre Selection"));
        JCheckBox rockCheckbox = new JCheckBox("Rock");
        JCheckBox popCheckbox = new JCheckBox("Pop");
        JCheckBox hipHopCheckbox = new JCheckBox("Hip Hop");
        // Add more checkboxes for other genres

        // Add checkboxes to the genre selection panel
        genrePanel.add(rockCheckbox);
        genrePanel.add(popCheckbox);
        genrePanel.add(hipHopCheckbox);


        JButton searchButton = new JButton("Search");

        searchButton.addActionListener(e -> {
            String songName = songNameField.getText();
            boolean found = false;

            for (int i = 0; i< songLibrary.getSongList().size() ; i++) {
                Song song = songLibrary.getSongList().get(i);
                if (song.getTitle().equalsIgnoreCase(songName)) {
                    songLibrary.printSongDetails(i);
                    found = true;
                    break; // Stop searching after the first match
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "Song not found: " + songName);
            }
        });

        panel.add(songNameLabel);
        panel.add(songNameField);
        panel.add(genrePanel);
        panel.add(searchButton);
        return panel;
    }

    private JPanel createSongLibraryPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        if (songLibrary != null && songLibrary.getSongList() != null) {
        // Create a table model with columns
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Title");
        tableModel.addColumn("Artist");
        tableModel.addColumn("Release Year");
        tableModel.addColumn("Genre");
        tableModel.addColumn("Popularity");
        tableModel.addColumn("User Rating");

        // Populate the table model with data
        List<Song> songs = songLibrary.getSongList();
        for (Song song : songs) {
            tableModel.addRow(new Object[]{
                    song.getTitle(),
                    song.getArtist(),
                    song.getReleaseYear(),
                    song.getGenre(),
                    song.getPopularity(),
                    song.getUserRating()
            });
        }


        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);


        panel.add(scrollPane, BorderLayout.CENTER);
        } else {

            JLabel noSongsLabel = new JLabel("No songs in the library.");
            panel.add(noSongsLabel, BorderLayout.CENTER);
        }
        return panel;
    }


    private JPanel createCuratorPanel() {
        JPanel panel = new JPanel();
        JLabel usernameLabel = new JLabel("Curator:");
        JTextField usernameField = new JTextField(20);
        JPanel genderPanel = new JPanel();
        genderPanel.setBorder(BorderFactory.createTitledBorder("Gender"));
        JRadioButton maleRadioButton = new JRadioButton("Male");
        JRadioButton femaleRadioButton = new JRadioButton("Female");
        JRadioButton otherRadioButton = new JRadioButton("Other");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderGroup.add(otherRadioButton);

        // Add radio buttons to the gender panel
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        genderPanel.add(otherRadioButton);

        JButton registerButton = new JButton("Register");

        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            if (!registeredUsers.containsKey(username)) {
                User user=null;
                registeredUsers.put(username, user);
                JOptionPane.showMessageDialog(this, "Curator " + username + " registered!");
            } else {
                JOptionPane.showMessageDialog(this, "Curator name " + username + " is already taken.");
            }

        });
        registerButton.setToolTipText("Click to register your curator");
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(genderPanel);
        panel.add(registerButton);
        return panel;
    }


    void showPanel(String panelName) {
        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        cardLayout.show(getContentPane(), panelName);
    }
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
    class FrontPage extends JFrame {
        private final MusicApplication musicApp;
        private GridBagConstraints constraints;
        public FrontPage(MusicApplication musicApp) {
            this.musicApp = musicApp;

            // Create a title label and set its position (top left)
            JLabel titleLabel = new JLabel("Welcome to the Music App");
            titleLabel.setFont(new Font("Times New Romans", Font.BOLD, 50));

            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new GridBagLayout());
            GridBagConstraints buttonConstraints = new GridBagConstraints();
            GridBagLayout layout = new GridBagLayout();



            class CustomButton extends JButton {
                public CustomButton(String label, Color bgColor) {
                    super(label);
                    setBackground(bgColor);
                    setForeground(Color.WHITE);
                    setFocusPainted(false);
                    setFont(new Font("Times New Romans", Font.BOLD, 18));
                    setBorderPainted(false);
                    setOpaque(true);
                }
            }

            // Sign In and Sign Up buttons

            CustomButton signInButton = new CustomButton("Sign In", new Color(0, 202, 204));
            CustomButton signUpButton = new CustomButton("Sign Up", new Color(0, 202, 204));
            CustomButton launchMusicAppButton = new CustomButton("Launch Music App", new Color(24, 202, 20));
            CustomButton browseMusicButton = new CustomButton("Browse Music", new Color(24, 202, 20));
            CustomButton createPlaylistButton = new CustomButton("Create Playlist", new Color(24, 202, 20));

            // Add action listeners to buttons
            launchMusicAppButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    musicApp.showPanel("Welcome");
                }
            });

            browseMusicButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Browsing music...");
                }
            });

            createPlaylistButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Creating a playlist...");
                }
            });

            // Add buttons to the buttonsPanel
            buttonConstraints.gridx = 0;
            buttonConstraints.gridy = 0;
            buttonsPanel.add(signInButton, buttonConstraints);

            buttonConstraints.gridx = 1;
            buttonsPanel.add(signUpButton, buttonConstraints);

            buttonConstraints.gridx = 0;
            buttonConstraints.gridy = 1;
            buttonsPanel.add(browseMusicButton, buttonConstraints);

            buttonConstraints.gridx = 1;
            buttonsPanel.add(createPlaylistButton, buttonConstraints);

            buttonConstraints.gridx = 0;
            buttonConstraints.gridwidth = 2;
            buttonConstraints.gridy = 2;
            buttonConstraints.anchor = GridBagConstraints.NORTH;
            buttonsPanel.add(launchMusicAppButton, buttonConstraints);

            // Set up the main content panel
            JPanel contentPanel = new JPanel(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();

            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.gridwidth = 2; // Span across two columns
            constraints.anchor = GridBagConstraints.NORTH;
            contentPanel.add(titleLabel, constraints);

            constraints.gridy = 1;
            contentPanel.add(buttonsPanel, constraints);

            setContentPane(contentPanel);
        }
    }
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
                    AddSongDialog dialog = new AddSongDialog();
                    dialog.setVisible(true);
                }
            });

            modifySongButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Implement the action to modify a song
                    // You can open a form or dialog for modifying a song

                    ModifySongDialog dialog = new ModifySongDialog();
                    dialog.setVisible(true);
                }
            });

            removeSongButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Implement the action to remove a song
                    // You can open a dialog to select the song to be removed
                    RemoveSongDialog dialog = new RemoveSongDialog();
                    dialog.setVisible(true);

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



        private class AddSongDialog extends JDialog {
            public AddSongDialog() {
                setTitle("Add a New Song");
                setSize(400, 200);
                setLocationRelativeTo(null);

            }
        }


        private class ModifySongDialog extends JDialog {
            public ModifySongDialog() {
                setTitle("Modify a Song");
                setSize(400, 200);
                setLocationRelativeTo(null);


            }
        }

        // Create a dialog for removing a song
        private class RemoveSongDialog extends JDialog {
            public RemoveSongDialog() {
                setTitle("Remove a Song");
                setSize(400, 100);
                setLocationRelativeTo(null);

            }
        }
    }



    private void showFrontPage() {
        FrontPage frontPage = new FrontPage(this);
        frontPage.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MusicApplication app = new MusicApplication();
            app.setVisible(true);
        });
    }
}




