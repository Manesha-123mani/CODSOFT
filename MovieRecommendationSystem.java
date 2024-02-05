import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieRecommendationSystem {
    private static Map<String, List<String>> movieDatabase = new HashMap<>();

    public static void main(String[] args) {
        // Populate movie database
        populateMovieDatabase();

        // Sample user preferences
        List<String> userPreferences = new ArrayList<>();
        userPreferences.add("Action");
        userPreferences.add("Adventure");

        // Recommend movies based on user preferences
        List<String> recommendedMovies = recommendMovies(userPreferences);

        // Display recommended movies
        System.out.println("Recommended Movies based on your preferences:");
        for (String movie : recommendedMovies) {
            System.out.println("- " + movie);
        }
    }

    private static void populateMovieDatabase() {
        // Add movies with their genres to the database
        List<String> movie1Genres = new ArrayList<>();
        movie1Genres.add("Action");
        movie1Genres.add("Adventure");
        movie1Genres.add("Sci-Fi");
        movieDatabase.put("Star Wars", movie1Genres);

        List<String> movie2Genres = new ArrayList<>();
        movie2Genres.add("Drama");
        movie2Genres.add("Romance");
        movieDatabase.put("Titanic", movie2Genres);

        List<String> movie3Genres = new ArrayList<>();
        movie3Genres.add("Action");
        movie3Genres.add("Thriller");
        movie3Genres.add("Mystery");
        movieDatabase.put("The Dark Knight", movie3Genres);

        // Add more movies to the database
        // Add as many movies and genres as you want
    }

    private static List<String> recommendMovies(List<String> userPreferences) {
        List<String> recommendedMovies = new ArrayList<>();

        // Iterate through the movie database
        for (Map.Entry<String, List<String>> entry : movieDatabase.entrySet()) {
            String movie = entry.getKey();
            List<String> genres = entry.getValue();

            // Check if the movie's genres match the user's preferences
            for (String genre : userPreferences) {
                if (genres.contains(genre)) {
                    recommendedMovies.add(movie);
                    break;
                }
            }
        }

        return recommendedMovies;
    }
}
