package collections_practice.streams.exercise_2;

public class Movie {
    private String name;
    private int rating;
    private int year;
    private String genre;
    private String director;
    private String Hero;

    public Movie(String name, int rating, int year, String genre, String director, String hero) {
        this.name = name;
        this.rating = rating;
        this.year = year;
        this.genre = genre;
        this.director = director;
        Hero = hero;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getHero() {
        return Hero;
    }

    public void setHero(String hero) {
        Hero = hero;
    }

}
