package by.vladpr.movieratingsystem.controller.command.impl;

import by.vladpr.movieratingsystem.controller.command.Command;
import by.vladpr.movieratingsystem.controller.command.CommandName;
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
import java.util.List;

public class GetMoviesCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(GetMoviesCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MovieService movieService = ServiceFactory.getInstance().getMovieService();
        List<Movie> list;
        try {
            list = movieService.getMovieList();
            request.getSession().setAttribute("moviesList", list);
            response.sendRedirect(request.getContextPath().concat(CommandName.GO_TO_MOVIES_PAGE_COMMAND));
        } catch (ServiceException e) {
            request.setAttribute("error", e.getMessage());
            LOGGER.error("Error while getting list of movies", e);
            response.sendRedirect(request.getContextPath().concat(ViewPath.ERROR503_PAGE));
        }
    }
}
