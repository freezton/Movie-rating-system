package by.vladpr.movieratingsystem.dao.impl;

import by.vladpr.movieratingsystem.dao.ReviewDao;
import by.vladpr.movieratingsystem.database.QueryProvider;
import by.vladpr.movieratingsystem.entity.Movie;
import by.vladpr.movieratingsystem.entity.Review;
import by.vladpr.movieratingsystem.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReviewDaoImpl extends QueryProvider implements ReviewDao {

    private static final String GET_REVIEWS_QUERY = "SELECT * FROM reviews";
    private static final String FIND_REVIEW_BY_ID_QUERY = "SELECT * FROM reviews WHERE id=?";
    private static final String SAVE_REVIEW_QUERY = "INSERT INTO reviews (user_id, movie_id, rate, review_text) VALUES (?, ?, ?, ?)";
    private static final String REMOVE_REVIEW_BY_ID_QUERY = "DELETE FROM reviews WHERE id=?";

    @Override
    public Optional<Review> findById(long id) throws DaoException {
        Review review;
        try (PreparedStatement preparedStatement = createStatement(FIND_REVIEW_BY_ID_QUERY, id)) {
            review = findReview(preparedStatement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(review);
    }

    private Review findReview(PreparedStatement preparedStatement) throws DaoException {
        Review review = null;
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                review = new Review();
                review.setId(resultSet.getInt("id"));
                review.setUserId(resultSet.getInt("user_id"));
                review.setMovieId(resultSet.getInt("movie_id"));
                review.setRate(resultSet.getInt("rate"));
                review.setReviewText(resultSet.getString("review_text"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return review;
    }

    @Override
    public void save(Review review) throws DaoException {
        try (PreparedStatement preparedStatement = createStatement(
                SAVE_REVIEW_QUERY,
                review.getUserId(),
                review.getMovieId(),
                review.getRate(),
                review.getReviewText()
                )) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void removeById(long id) throws DaoException {
        try (PreparedStatement preparedStatement = createStatement(REMOVE_REVIEW_BY_ID_QUERY, id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Review> findAll() throws DaoException {
        List<Review> list;
        try (PreparedStatement preparedStatement = createStatement(GET_REVIEWS_QUERY)) {
            list = getReviews(preparedStatement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return list;
    }

    private List<Review> getReviews(PreparedStatement preparedStatement) throws DaoException {
        List<Review> list = new ArrayList<>();
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Review review = new Review();
                review.setId(resultSet.getInt("id"));
                review.setUserId(resultSet.getInt("user_id"));
                review.setMovieId(resultSet.getInt("movie_id"));
                review.setRate(resultSet.getInt("rate"));
                review.setReviewText(resultSet.getString("review_text"));
                list.add(review);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return list;
    }
}
