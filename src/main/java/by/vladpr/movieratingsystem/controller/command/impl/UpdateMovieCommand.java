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
import java.util.Optional;

public class UpdateMovieCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(UpdateMovieCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MovieService movieService = ServiceFactory.getInstance().getMovieService();
        try {
            Optional<Movie> movie = movieService.getById(Long.parseLong(request.getParameter("movieId")));
            if (movie.isPresent()) {
                movie.get().setTitle(request.getParameter("movieTitle"));
                movie.get().setDescription(request.getParameter("movieDescription"));
                movie.get().setYearOfRelease(Integer.parseInt(request.getParameter("movieYear")));
                response.sendRedirect(request.getHeader("referer"));
            }
        } catch (ServiceException e) {
            request.setAttribute("error", e.getMessage());
            LOGGER.error("Error while adding movie", e);
            response.sendRedirect(request.getContextPath().concat(ViewPath.ERROR503_PAGE));
        }
    }
}
