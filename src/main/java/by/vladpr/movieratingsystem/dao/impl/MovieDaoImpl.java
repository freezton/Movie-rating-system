package by.vladpr.movieratingsystem.dao.impl;

import by.vladpr.movieratingsystem.dao.MovieDao;
import by.vladpr.movieratingsystem.database.AbstractQueryCreator;
import by.vladpr.movieratingsystem.entity.Movie;
import by.vladpr.movieratingsystem.entity.User;
import by.vladpr.movieratingsystem.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MovieDaoImpl extends AbstractQueryCreator implements MovieDao {

    private static final String FIND_MOVIE_BY_ID_QUERY = "SELECT * FROM movies WHERE id=?";
    private static final String SAVE_MOVIE_QUERY = "INSERT INTO movies (title, description, year_of_release) VALUES (?, ?, ?)";
    private static final String REMOVE_MOVIE_BY_ID_QUERY = "DELETE FROM movies WHERE id=?";

    @Override
    public Optional<Movie> findById(long id) throws DaoException {
        Movie movie;
        try (PreparedStatement preparedStatement = createStatement(FIND_MOVIE_BY_ID_QUERY, id)) {
            movie = findMovie(preparedStatement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(movie);
    }

    private Movie findMovie(PreparedStatement preparedStatement) throws DaoException {
        Movie movie = null;
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                movie = new Movie();
                movie.setId(resultSet.getInt("id"));
                movie.setTitle(resultSet.getString("title"));
                movie.setDescription(resultSet.getString("description"));
                movie.setYearOfRelease(resultSet.getInt("year_of_release"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return movie;
    }

    @Override
    public void save(Movie movie) throws DaoException {
        try (PreparedStatement preparedStatement = createStatement(
                SAVE_MOVIE_QUERY,
                movie.getTitle(),
                movie.getDescription(),
                movie.getYearOfRelease()
                )) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void removeById(long id) throws DaoException {
        try (PreparedStatement preparedStatement = createStatement(REMOVE_MOVIE_BY_ID_QUERY, id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
