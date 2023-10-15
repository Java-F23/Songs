import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


class User {
    private List<Song> songLibrary;
    private Map<String, List<Song>> categorizedSongs;
    private List<Song> favoriteSongs;
    private Map<String, List<Song>> playlists;
    private List<Song> currentPlaylist;
    private List<Song> playHistory;
    private Scanner scanner = new Scanner(System.in);

    public User(List<Song> songLibrary, Map<String, List<Song>> categorizedSongs) {
        this.songLibrary = songLibrary;
        this.categorizedSongs = categorizedSongs;
        this.favoriteSongs = new ArrayList<>();
        this.playlists = new HashMap<>();
        this.currentPlaylist = new ArrayList<>();
        this.playHistory = new ArrayList<>();
    }

    public void displaySongList() {
        System.out.println("List of Songs:");
        for (Song song : songLibrary) {
            System.out.println(song.getTitle() + " by " + song.getArtist());
        }
    }

    public void searchSongsByGenre(String genre) {
        if (categorizedSongs.containsKey(genre)) {
            System.out.println("Songs in the " + genre + " genre:");
            List<Song> genreSongs = categorizedSongs.get(genre);
            for (Song song : genreSongs) {
                System.out.println(song.getTitle() + " by " + song.getArtist());
            }
        } else {
            System.out.println("No songs found in the " + genre + " genre.");
        }
    }

    public void searchSongsByArtist(String artist) {
        System.out.println("Songs by " + artist + ":");
        for (Song song : songLibrary) {
            if (song.getArtist().equalsIgnoreCase(artist)) {
                System.out.println(song.getTitle());
            }
        }
    }

    public void accessSongDetails(String songTitle) {
        for (Song song : songLibrary) {
            if (song.getTitle().equals(songTitle)) {
                System.out.println("Title: " + song.getTitle());
                System.out.println("Artist: " + song.getArtist());
                System.out.println("Release Year: " + song.getReleaseYear());
                System.out.println("Genre: " + song.getGenre());
                System.out.println("Popularity: " + song.getPopularity());
                System.out.println("User Rating: " + song.getUserRating());

                System.out.println("Reviews:");
                for (Review review : song.getReviews()) {
                    System.out.println("User: " + review.getUser());
                    System.out.println("Rating: " + review.getRating());
                    System.out.println("Review: " + review.getText());
                }

                System.out.println("Audio Preview: " + song.getAudioFile());
                return;
            }
        }
        System.out.println("Song not found.");
    }

    public void createPlaylist(String playlistName) {
        if (!playlists.containsKey(playlistName)) {
            playlists.put(playlistName, new ArrayList<>());
            System.out.println("Playlist '" + playlistName + "' created.");
        } else {
            System.out.println("Playlist with the same name already exists.");
        }
    }

    public void viewPlaylist(String playlistName) {
        if (playlists.containsKey(playlistName)) {
            currentPlaylist = playlists.get(playlistName);
            System.out.println("Viewing playlist '" + playlistName + "'.");
        } else {
            System.out.println("Playlist not found.");
        }
    }

    public void addToPlaylist(String songTitle) {
        Song selectedSong = findSongByTitle(songTitle);
        if (selectedSong != null) {
            currentPlaylist.add(selectedSong);
            System.out.println("Song added to the current playlist.");
        } else {
            System.out.println("Song not found.");
        }
    }

    public void markAsFavorite(String songTitle) {
        Song selectedSong = findSongByTitle(songTitle);
        if (selectedSong != null) {
            favoriteSongs.add(selectedSong);
            System.out.println("Song marked as a favorite.");
        } else {
            System.out.println("Song not found.");
        }
    }

    public void viewFavorites() {
        if (!favoriteSongs.isEmpty()) {
            System.out.println("Favorite Songs:");
            for (Song song : favoriteSongs) {
                System.out.println(song.getTitle() + " by " + song.getArtist());
            }
        } else {
            System.out.println("No favorite songs found.");
        }
    }

    public void rateAndReview(String songTitle) {
        Song selectedSong = findSongByTitle(songTitle);
        if (selectedSong != null) {
            System.out.print("Enter your rating (1-5): ");
            double userRating = scanner.nextDouble();
            scanner.nextLine();
            if (userRating >= 1 && userRating <= 5) {
                System.out.print("Write a review: ");
                String reviewText = scanner.nextLine();
                Review review = new Review("User", reviewText, userRating);
                selectedSong.addReview(review);
                selectedSong.updateUserRating(userRating);
                System.out.println("Your rating and review have been recorded.");
            } else {
                System.out.println("Invalid rating. Please enter a rating between 1 and 5.");
            }
        } else {
            System.out.println("Song not found.");
        }
    }

    public void listenToPreview(String songTitle) {
        Song selectedSong = findSongByTitle(songTitle);
        if (selectedSong != null) {
            System.out.println("Listening to the preview of " + selectedSong.getTitle() + " by " + selectedSong.getArtist());
            System.out.println("Audio Preview: " + selectedSong.getAudioFile());
            // Play audio preview (you may need to implement audio playback)
        } else {
            System.out.println("Song not found.");
        }
    }

    public void shufflePlaylist() {
        if (!currentPlaylist.isEmpty()) {
            // Shuffle the playlist (you may need to implement shuffle functionality)
            System.out.println("Shuffled the current playlist.");
        } else {
            System.out.println("The current playlist is empty.");
        }
    }

    public void playFromPlaylist() {
        if (!currentPlaylist.isEmpty()) {
            for (Song song : currentPlaylist) {
                System.out.println("Now playing: " + song.getTitle() + " by " + song.getArtist());
                playHistory.add(song);
            }
        } else {
            System.out.println("The current playlist is empty.");
        }
    }

    public void viewPlayHistory() {
        if (!playHistory.isEmpty()) {
            System.out.println("Play History:");
            for (Song song : playHistory) {
                System.out.println(song.getTitle() + " by " + song.getArtist());
            }
        } else {
            System.out.print(("No play history found."));
        }
    }

    public void findRelatedSongs(String songTitle) {
        Song selectedSong = findSongByTitle(songTitle);
        if (selectedSong != null) {
            String genre = selectedSong.getGenre();
            if (categorizedSongs.containsKey(genre)) {
                List<Song> relatedSongs = categorizedSongs.get(genre);
                System.out.println("Related Songs in the " + genre + " genre:");
                for (Song song : relatedSongs) {
                    if (!song.getTitle().equals(songTitle)) {
                        System.out.println(song.getTitle() + " by " + song.getArtist());
                    }
                }
            } else {
                System.out.println("No related songs found in the same genre.");
            }
        } else {
            System.out.println("Song not found.");
        }
    }

    private Song findSongByTitle(String songTitle) {
        for (Song song : songLibrary) {
            if (song.getTitle().equals(songTitle)) {
                return song;
            }
        }
        return null;
    }
}
