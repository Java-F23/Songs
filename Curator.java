import java.util.*;
import java.util.Scanner;
public class Curator {
    private songLibrary library;
    List<Song> songLibrary = new ArrayList<>();
    Map<String, List<Song> > categorizedSongs = new HashMap<>();

    private Scanner scanner = new Scanner(System.in);
    private Object songTitle;

    public Curator(songLibrary library) {

        this.library = library;
        this.songLibrary = library.songLibrary;
        this.categorizedSongs = library.categorizedSongs;
    }

    public void addSong() {
        Song newSong = null;
        library.addSong(newSong);
        newSong = null;
        songLibrary.add(newSong);
        System.out.print("Enter song title: ");
        String title = scanner.nextLine();
        System.out.print("Enter artist name: ");
        String artist = scanner.nextLine();
        System.out.print("Enter release year: ");
        int releaseYear = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();

        newSong = new Song(title, artist, releaseYear, genre);
        songLibrary.add(newSong);
        categorizedSongs.computeIfAbsent(genre, k -> new ArrayList<>()).add(newSong);

        System.out.println("Song added successfully!");

    }

    public void modifySong(songLibrary library) {

        Object songTitle;
        songTitle = null;
        library.modifySong(songTitle);
        System.out.print("Enter the title of the song you want to modify: ");
        String songTitleToModify = scanner.nextLine();

        for (Song song : library.songLibrary) {
            if (Objects.equals(song.getTitle(), songTitleToModify)) {
                System.out.println("Current details for the song:");
                System.out.println(song);

                System.out.print("Enter new title (or press Enter to keep current): ");
                String newTitle = scanner.nextLine();
                if (!newTitle.isEmpty()) {
                    song.setTitle(newTitle);
                }

                System.out.print("Enter new artist (or press Enter to keep current): ");
                String newArtist = scanner.nextLine();
                if (!newArtist.isEmpty()) {
                    song.setArtist(newArtist);
                }

                System.out.print("Enter new release year (or press Enter to keep current): ");
                String newReleaseYearStr = scanner.nextLine();
                if (!newReleaseYearStr.isEmpty()) {
                    try {
                        int newReleaseYear = Integer.parseInt(newReleaseYearStr);
                        song.setReleaseYear(newReleaseYear);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid release year format. No changes made.");
                    }
                }

                System.out.print("Enter new genre (or press Enter to keep current): ");
                String newGenre = scanner.nextLine();
                if (!newGenre.isEmpty()) {
                    song.setGenre(newGenre);
                }

                System.out.println("Song details updated!");
                return;
            }
        }

        System.out.println("Song not found in the library.");
    }

    public void removeSong() {
        Object songTitle;
        songTitle = null;
        library.removeSong((String) songTitle);
        System.out.print("Enter the title of the song you want to remove: ");
        String songTitleToRemove = scanner.nextLine();

        Song songToRemove = null;
        for (Song song : songLibrary) {
            if (song.getTitle().equals(songTitleToRemove)) {
                songToRemove = song;
                break;
            }
        }

        if (songToRemove != null) {
            songLibrary.remove(songToRemove);
            categorizedSongs.get(songToRemove.getGenre()).remove(songToRemove);
            System.out.println("Song removed successfully!");
        } else {
            System.out.println("Song not found in the library.");
        }
    }

    public void categorizeSongs() {
        Object newGenre;
        newGenre = null;
        library.categorizeSong((String) songTitle, (String) newGenre);
        System.out.print("Enter the genre to categorize songs under: ");
        String categoryGenre = scanner.nextLine();

        if (categorizedSongs.containsKey(categoryGenre)) {
            System.out.println("Songs in the " + categoryGenre + " genre:");
            List<Song> categorySongs = categorizedSongs.get(categoryGenre);
            for (Song song : categorySongs) {
                System.out.println(song);
            }
        } else {
            System.out.println("No songs found in the " + categoryGenre + " genre.");
        }
    }

    public void generateReports() {
        library.generateReports();
        System.out.println("Reports:");
        for (Song song : songLibrary) {
            System.out.println(song);
        }
    }

    public void recordUserReview() {
        Object reviewText;
        reviewText =null;
        Object user;
        user =null;
        library.recordUserReview((String) songTitle, (String) user, (String) reviewText);
    System.out.print("Enter the title of the song you want to review: ");
        String songTitleToReview = scanner.nextLine();

        for (Song song : songLibrary) {
            if (song.getTitle().equals(songTitleToReview)) {
                System.out.println("Current user rating: " + song.getUserRating());
                System.out.print("Enter your rating (1-5): ");
                double userRating = scanner.nextDouble();
                scanner.nextLine();
                if (userRating >= 1 && userRating <= 5) {
                    song.updateUserRating(userRating);
                    System.out.println("User rating recorded!");
                } else {
                    System.out.println("Invalid rating. Please enter a rating between 1 and 5.");
                }
                return;
            }
        }

        System.out.println("Song not found in the library.");
    }

    public void runCuratorMenu() {
        while (true) {
            System.out.println("Curator Menu:");
            System.out.println("1. Add a Song");
            System.out.println("2. Modify a Song");
            System.out.println("3. Remove a Song");
            System.out.println("4. Categorize Songs");
            System.out.println("5. Generate Reports");
            System.out.println("6. Record User Review");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addSong();
                    break;
                case 2:
                    Object songTitle = null;
                    modifySong((songLibrary) songTitle);
                    break;
                case 3:
                    removeSong();
                    break;
                case 4:
                    categorizeSongs();
                    break;
                case 5:
                    generateReports();
                    break;
                case 6:
                    recordUserReview();
                    break;
                case 0:
                    System.out.println("Exiting Curator Menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        songLibrary library = new songLibrary();
        Curator curator = new Curator(library);
        curator.runCuratorMenu();
    }


}