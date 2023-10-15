import java.util.*;

public class Curator {
    List<Song> songLibrary = new ArrayList<>();
    Map<String, List<Song> > categorizedSongs = new HashMap<>();

    private Scanner scanner = new Scanner(System.in);

    public Curator(songLibrary library) {
        this.songLibrary = library.songLibrary;
        this.categorizedSongs = library.categorizedSongs;
    }

    public void addSong() {
        Song newSong = null;
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
        System.out.println("Reports:");
        for (Song song : songLibrary) {
            System.out.println(song);
        }
    }

    public void recordUserReview() {
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
}