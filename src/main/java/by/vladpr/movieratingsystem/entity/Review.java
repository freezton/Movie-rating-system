package by.vladpr.movieratingsystem.entity;

public class Review {

    private int id;
    private int userId;
    private int movieId;
    private int rate;
    private String reviewText;

    public Review() {
    }

    public Review(int id, int userId, int movieId, int rate, String reviewText) {
        this.id = id;
        this.userId = userId;
        this.movieId = movieId;
        this.rate = rate;
        this.reviewText = reviewText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", userId=" + userId +
                ", movieId=" + movieId +
                ", rate=" + rate +
                ", reviewText='" + reviewText + '\'' +
                '}';
    }
}
