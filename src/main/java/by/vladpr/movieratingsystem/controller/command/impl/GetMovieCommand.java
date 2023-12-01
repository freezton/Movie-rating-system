package by.vladpr.movieratingsystem.controller.command.impl;

import by.vladpr.movieratingsystem.controller.command.Command;
import by.vladpr.movieratingsystem.controller.command.CommandName;
import by.vladpr.movieratingsystem.entity.Movie;
import by.vladpr.movieratingsystem.exception.ServiceException;
import by.vladpr.movieratingsystem.service.MovieService;
import by.vladpr.movieratingsystem.service.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Optional;

public class GetMovieCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MovieService movieService = ServiceFactory.getInstance().getMovieService();
        long movieId = Long.parseLong(request.getParameter("movie_id"));
        Optional<Movie> movie;
        try {
            movie = movieService.getById(movieId);
            request.getSession().setAttribute("movie", movie);
            response.sendRedirect(request.getContextPath().concat(CommandName.GO_TO_REVIEWS_PAGE));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
