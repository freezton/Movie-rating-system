package by.vladpr.movieratingsystem.controller.command.impl;

import by.vladpr.movieratingsystem.controller.command.Command;
import by.vladpr.movieratingsystem.controller.command.ViewPath;
import by.vladpr.movieratingsystem.entity.Movie;
import by.vladpr.movieratingsystem.exception.ServiceException;
import by.vladpr.movieratingsystem.service.MovieService;
import by.vladpr.movieratingsystem.service.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class AddMovieCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(AddMovieCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MovieService movieService = ServiceFactory.getInstance().getMovieService();
        Movie movie = new Movie();
        movie.setTitle(request.getParameter("movieTitle"));
        movie.setDescription(request.getParameter("movieDescription"));
        movie.setYearOfRelease(Integer.parseInt(request.getParameter("movieYear")));
        try {
            movieService.addMovie(movie);
            response.sendRedirect(request.getHeader("referer"));
        } catch (ServiceException e) {
            request.setAttribute("error", e.getMessage());
            LOGGER.error("Error while adding movie", e);
            response.sendRedirect(request.getContextPath().concat(ViewPath.ERROR503_PAGE));
        }
    }
}
