import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class MusicApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        songLibrary library = new songLibrary();
        Curator curator = new Curator(library);
        songLibrary songLibrary = new songLibrary();
        User user = new User(songLibrary.getSongList(), songLibrary.categorizedSongs());
        System.out.println("Welcome to the Music App!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Curator Menu");
            System.out.println("2. User Menu");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            if (choice == 1) {
                System.out.println("Curator Menu:");
                System.out.println("1. Add a Song");
                System.out.println("2. Modify a Song");
                System.out.println("3. Remove a Song");
                System.out.println("4. Categorize Songs");
                System.out.println("5. Generate Reports");
                System.out.println("6. Record User Review");
                System.out.print("Enter your choice: ");
                int curatorChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (curatorChoice) {
                    case 1:
                        Object newSong;
                        curator.addSong();
                        break;
                    case 2:
                        library = null;
                        curator.modifySong(library);
                        break;
                    case 3:
                        curator.removeSong();
                        break;
                    case 4:
                        curator.categorizeSongs();
                        break;
                    case 5:
                        curator.generateReports();
                        break;
                    case 6:
                        curator.recordUserReview();
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                        break;
                }
            } else if (choice == 2) {
                System.out.println("User Menu:");
                System.out.println("1. Access Song List");
                System.out.println("2. Search Songs by Genre");
                System.out.println("3. Search Songs by Artist");
                System.out.println("4. Access Song Details");
                System.out.println("5. Create Playlist");
                System.out.println("6. View Playlist");
                System.out.println("7. Add to Playlist");
                System.out.println("8. Mark as Favorite");
                System.out.println("9. View Favorites");
                System.out.println("10. Rate and Review");
                System.out.println("11. Listen to Preview");
                System.out.println("12. Shuffle Playlist");
                System.out.println("13. Play from Playlist");
                System.out.println("14. View Play History");
                System.out.println("15. Find Related Songs");
                System.out.print("Enter your choice: ");
                int userChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (userChoice) {
                    case 1:
                        user.displaySongList();
                        break;
                    case 2:
                        System.out.print("Enter genre to search: ");
                        String genre = scanner.nextLine();
                        user.searchSongsByGenre(genre);
                        break;
                    case 3:
                        System.out.print("Enter artist to search: ");
                        String artist = scanner.nextLine();
                        user.searchSongsByArtist(artist);
                        break;
                    case 4:
                        System.out.print("Enter song title to access details: ");
                        String songTitle = scanner.nextLine();
                        user.accessSongDetails(songTitle);
                        break;
                    case 5:
                        System.out.print("Enter playlist name: ");
                        String playlistName = scanner.nextLine();
                        user.createPlaylist(playlistName);
                        break;
                    case 6:
                        System.out.print("Enter playlist name to view: ");
                        String viewPlaylistName = scanner.nextLine();
                        user.viewPlaylist(viewPlaylistName);
                        break;
                    case 7:
                        System.out.print("Enter song title to add to the playlist: ");
                        String addToPlaylistTitle = scanner.nextLine();
                        user.addToPlaylist(addToPlaylistTitle);
                        break;
                    case 8:
                        System.out.print("Enter song title to mark as favorite: ");
                        String markAsFavoriteTitle = scanner.nextLine();
                        user.markAsFavorite(markAsFavoriteTitle);
                        break;
                    case 9:
                        user.viewFavorites();
                        break;
                    case 10:
                        System.out.print("Enter song title to rate and review: ");
                        String rateAndReviewTitle = scanner.nextLine();
                        user.rateAndReview(rateAndReviewTitle);
                        break;
                    case 11:
                        System.out.print("Enter song title to listen to preview: ");
                        String listenToPreviewTitle = scanner.nextLine();
                        user.listenToPreview(listenToPreviewTitle);
                        break;
                    case 12:
                        user.shufflePlaylist();
                        break;
                    case 13:
                        user.playFromPlaylist();
                        break;
                    case 14:
                        user.viewPlayHistory();
                        break;
                    case 15:
                        System.out.print("Enter song title to find related songs: ");
                        String findRelatedSongsTitle = scanner.nextLine();
                        user.findRelatedSongs(findRelatedSongsTitle);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                        break;
                }
            } else if (choice == 3) {
                System.out.println("Goodbye!");
                scanner.close();
                System.exit(0);
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
