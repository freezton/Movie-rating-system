package by.vladpr.movieratingsystem.dao.impl;

import by.vladpr.movieratingsystem.dao.MovieDao;
import by.vladpr.movieratingsystem.database.QueryProvider;
import by.vladpr.movieratingsystem.entity.Movie;
import by.vladpr.movieratingsystem.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieDaoImpl extends QueryProvider implements MovieDao {

    private static final String GET_MOVIES_QUERY = "SELECT * FROM movies";
    private static final String FIND_MOVIE_BY_ID_QUERY = "SELECT * FROM movies WHERE id=?";
    private static final String SAVE_MOVIE_QUERY = "INSERT INTO movies (title, description, year_of_release, rating) VALUES (?, ?, ?, ?)";
    private static final String REMOVE_MOVIE_BY_ID_QUERY = "DELETE FROM movies WHERE id=?";
    private static final String UPDATE_MOVIE_INFO = "UPDATE movies SET title=?, description=?, year_of_release=?, rating=? WHERE id=?";

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
                movie.setRating(resultSet.getDouble("rating"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return movie;
    }

    @Override
    public void updateMovieInfo(Movie movie) throws DaoException {
        try (PreparedStatement preparedStatement = createStatement(
                UPDATE_MOVIE_INFO,
                movie.getTitle(),
                movie.getDescription(),
                movie.getYearOfRelease(),
                movie.getRating(),
                movie.getId()
        )) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
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

    @Override
    public List<Movie> findAll() throws DaoException {
        List<Movie> list;
        try (PreparedStatement preparedStatement = createStatement(GET_MOVIES_QUERY)) {
            list = getMovies(preparedStatement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return list;
    }

    private List<Movie> getMovies(PreparedStatement preparedStatement) throws DaoException {
        List<Movie> list = new ArrayList<>();
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getInt("id"));
                movie.setTitle(resultSet.getString("title"));
                movie.setDescription(resultSet.getString("description"));
                movie.setYearOfRelease(resultSet.getInt("year_of_release"));
                list.add(movie);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return list;
    }
}
