package by.vladpr.movieratingsystem.entity;

public class Movie {

    private int id;
    private String title;
    private String description;
    private int yearOfRelease;
    private double rating;

    public Movie() {
    }

    public Movie(int id, String title, String description, int yearOfRelease, double rating) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.yearOfRelease = yearOfRelease;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", yearOfRelease=" + yearOfRelease +
                '}';
    }
}
