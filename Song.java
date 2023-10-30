import java.util.ArrayList;
import java.util.List;

class Song {
    private String title;
    private String artist;
    private int releaseYear;
    private String genre;
    private int popularity;
    private double userRating;
    private List<Review> reviews;
    private String audioFile; // Path to audio file

    public Song(String title, String artist, int releaseYear, String genre, String audioFile) {
        this.title = title;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.popularity = 0;
        this.userRating = 0.0;
        this.reviews = new ArrayList<>();
        this.audioFile = audioFile;

    }

    public Song(String title, String artist, int releaseYear, String genre) {
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public int getPopularity() {
        return popularity;
    }

    public double getUserRating() {
        return userRating;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public String getAudioFile() {
        return audioFile;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void incrementPopularity() {
        popularity++;
    }

    public void updateUserRating(double rating) {
        userRating = (userRating + rating) / 2;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Artist: " + artist + ", Release Year: " + releaseYear + ", Genre: " + genre +
                ", Popularity: " + popularity + ", User Rating: " + userRating;
    }
}

class Review {
    private String user;
    private String text;
    private double rating;

    public Review(String user, String text, double rating) {
        this.user = user;
        this.text = text;
        this.rating = rating;
    }

    public String getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public double getRating() {
        return rating;
    }
}