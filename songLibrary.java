import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class songLibrary {
    ArrayList<Song> songLibrary = new ArrayList<>();
    Map<String, List<Song>> categorizedSongs = new HashMap<>();

    public Song song1;
    public Song song2;
    public Song song3;
    public Song song4;
    public Song song5;
    public Song song6;
    public Song song7;
    public Song song8;
    public Song song9;
    public Song song10;
    public Song song11;
    public Song song12;
    public Song song13;
    public Song song14;
    public static ArrayList<Song> songList;
    public ArrayList<Song> favoriteSongs = new ArrayList<>();


    public songLibrary() {
        ArrayList<Review> reviews1, reviews2, reviews3, reviews4, reviews5, reviews6, reviews7, reviews8, reviews9, reviews10, reviews11, reviews12, reviews13, reviews14;

        song1 = new Song("Shape of You", "Ed Sheeran", 2017, "Pop", "audio/shape_of_you.mp3");
        song2 = new Song("Bohemian Rhapsody", "Queen", 1975, "Rock", "audio/bohemian_rhapsody.mp3");
        song3 = new Song("Billie Jean", "Michael Jackson", 1982, "Pop", "audio/billie_jean.mp3");
        song4 = new Song("Hotel California", "Eagles", 1976, "Rock", "audio/hotel_california.mp3");
        song5 = new Song("Imagine", "John Lennon", 1971, "Pop", "audio/imagine.mp3");
        song6 = new Song("Uptown Funk", "Mark Ronson ft. Bruno Mars", 2014, "Pop", "audio/uptown_funk.mp3");
        song7 = new Song("Yesterday", "The Beatles", 1965, "Rock", "audio/yesterday.mp3");
        song8 = new Song("Rolling in the Deep", "Adele", 2010, "Pop", "audio/rolling_in_the_deep.mp3");
        song9 = new Song("Smells Like Teen Spirit", "Nirvana", 1991, "Rock", "audio/smells_like_teen_spirit.mp3");
        song10 = new Song("Sweet Child o' Mine", "Guns N' Roses", 1987, "Rock", "audio/sweet_child_o_mine.mp3");
        song11 = new Song("Like a Rolling Stone", "Bob Dylan", 1965, "Rock", "audio/like_a_rolling_stone.mp3");
        song12 = new Song("Purple Haze", "Jimi Hendrix", 1967, "Rock", "audio/purple_haze.mp3");
        song13 = new Song("Thriller", "Michael Jackson", 1982, "Pop", "audio/thriller.mp3");
        song14 = new Song("Wonderwall", "Oasis", 1995, "Rock", "audio/wonderwall.mp3");

        // Initialize reviews for each song
        reviews1 = new ArrayList<>();
        reviews2 = new ArrayList<>();
        reviews3 = new ArrayList<>();
        reviews4 = new ArrayList<>();
        reviews5 = new ArrayList<>();
        reviews6 = new ArrayList<>();
        reviews7 = new ArrayList<>();
        reviews8 = new ArrayList<>();
        reviews9 = new ArrayList<>();
        reviews10 = new ArrayList<>();
        reviews11 = new ArrayList<>();
        reviews12 = new ArrayList<>();
        reviews13 = new ArrayList<>();
        reviews14 = new ArrayList<>();

        // Add songs to the list
        songList = new ArrayList<>();
        songList.add(song1);
        songList.add(song2);
        songList.add(song3);
        songList.add(song4);
        songList.add(song5);
        songList.add(song6);
        songList.add(song7);
        songList.add(song8);
        songList.add(song9);
        songList.add(song10);
        songList.add(song11);
        songList.add(song12);
        songList.add(song13);
        songList.add(song14);

        // Add initial reviews for each song (You can modify or add more reviews)
        song1.addReview(new Review("User1", "Great song!", 5.0));
        song2.addReview(new Review("User2", "A classic!", 4.5));
        song3.addReview(new Review("User3", "Iconic MJ!", 4.8));
        song4.addReview(new Review("User4", "Love this track!", 4.6));
        song5.addReview(new Review("User5", "Timeless.", 4.7));
        song6.addReview(new Review("User6", "Funky and fun!", 4.3));
        song7.addReview(new Review("User7", "Beautiful melody.", 4.4));
        song8.addReview(new Review("User8", "Adele's best.", 4.9));
        song9.addReview(new Review("User9", "Nirvana rocks!", 4.5));
        song10.addReview(new Review("User10", "Guitar masterpiece.", 4.7));
        song11.addReview(new Review("User11", "Bob Dylan's genius.", 4.2));
        song12.addReview(new Review("User12", "Jimi's guitar magic.", 4.6));
        song13.addReview(new Review("User13", "Thrilling indeed.", 4.8));
        song14.addReview(new Review("User14", "Oasis at its best.", 4.5));




    }

    public List<Song> getSongList() {
        return songList;
    }

    public void printSongTitles() {
        for (int i = 0; i < songList.size(); i++) {
            System.out.println((i + 1) + ". " + "Song: " + songList.get(i).getTitle() + " - Artist: " + songList.get(i).getArtist());
        }
    }

    public static void printSongDetails(int songIndex) {
        if (songIndex >= 0 && songIndex < songList.size()) {
            Song song = songList.get(songIndex);
            System.out.println("Song Title: " + song.getTitle());
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
        } else {
            System.out.println("Invalid song index.");
        }
    }

    public static Map<String, List<Song>> categorizedSongs() {
        Map<String, List<Song>> categorizedSongs = new HashMap<>();

        for (Song song : songList) {
            String genre = song.getGenre();
            List<Song> genreSongs = categorizedSongs.getOrDefault(genre, new ArrayList<>());
            genreSongs.add(song);
            categorizedSongs.put(genre, genreSongs);
        }

        return categorizedSongs;
    }

    public void addSong(Song newSong) {
        songList.add(newSong);

        // Categorize the new song
        categorizedSongs.computeIfAbsent(newSong.getGenre(), k -> new ArrayList<>()).add(newSong);

        System.out.println("Song added successfully!");
    }

    public void removeSong(String songTitle) {
        Song songToRemove = null;
        for (Song song : songList) {
            if (song.getTitle().equals(songTitle)) {
                songToRemove = song;
                break;
            }
        }

        if (songToRemove != null) {
            songList.remove(songToRemove);
            categorizedSongs.get(songToRemove.getGenre()).remove(songToRemove);
            System.out.println("Song removed successfully!");
        } else {
            System.out.println("Song not found in the library.");
        }
    }

    public void modifySong(Object songTitle) {
        for (int i = 0; i < songList.size(); i++) {
            Song song = songList.get(i);
            if (song.getTitle().equals(songTitle)) {
                // Update the song with the new details
                Song updatedSong = null;
                songList.set(i, updatedSong);

                // Re-categorize the song under the updated genre
                String oldGenre = song.getGenre();
                String newGenre = updatedSong.getGenre();
                if (!oldGenre.equals(newGenre)) {
                    categorizedSongs.get(oldGenre).remove(song);
                    categorizedSongs.computeIfAbsent(newGenre, k -> new ArrayList<>()).add(updatedSong);
                }

                System.out.println("Song details updated!");
                return;
            }
        }

        System.out.println("Song not found in the library.");
    }

    public void categorizeSong(String songTitle, String newGenre) {
        for (Song song : songList) {
            if (song.getTitle().equals(songTitle)) {
                // Re-categorize the song under the new genre
                String oldGenre = song.getGenre();
                song.setGenre(newGenre);
                if (!oldGenre.equals(newGenre)) {
                    categorizedSongs.get(oldGenre).remove(song);
                    categorizedSongs.computeIfAbsent(newGenre, k -> new ArrayList<>()).add(song);
                }
                System.out.println("Song categorized successfully!");
                return;
            }
        }

        System.out.println("Song not found in the library.");
    }

    public void generateReports() {
        System.out.println("Reports:");
        for (Song song : songList) {
            System.out.println(song);
        }
    }

    public void recordUserReview(String songTitle, String user, String reviewText) {
        for (Song song : songList) {
            if (song.getTitle().equals(songTitle)) {
                double userRating = 0;
                Review review = new Review(user, reviewText, userRating);
                song.addReview(review);
                System.out.println("User review recorded!");
                return;
            }
        }

        System.out.println("Song not found in the library.");
    }

    // Other existing methods...
}