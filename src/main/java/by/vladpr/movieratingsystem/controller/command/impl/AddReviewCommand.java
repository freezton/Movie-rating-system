package by.vladpr.movieratingsystem.controller.command.impl;

import by.vladpr.movieratingsystem.controller.command.Command;
import by.vladpr.movieratingsystem.controller.command.ViewPath;
import by.vladpr.movieratingsystem.entity.Movie;
import by.vladpr.movieratingsystem.entity.Review;
import by.vladpr.movieratingsystem.exception.ServiceException;
import by.vladpr.movieratingsystem.service.MovieService;
import by.vladpr.movieratingsystem.service.ReviewService;
import by.vladpr.movieratingsystem.service.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.digester.Digester;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class AddReviewCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(AddReviewCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ReviewService reviewService = ServiceFactory.getInstance().getReviewService();
        Review review = new Review();
        review.setUserId(Integer.parseInt(request.getParameter("userId")));
        review.setMovieId(Integer.parseInt(request.getParameter("movieId")));
        review.setRate(Integer.parseInt(request.getParameter("rate")));
        review.setReviewText(request.getParameter("reviewText"));
        try {
            reviewService.addReview(review);
        } catch (ServiceException e) {
            request.setAttribute("error", e.getMessage());
            LOGGER.error("Error while adding review", e);
            response.sendRedirect(request.getContextPath().concat(ViewPath.ERROR503_PAGE));
        }
    }
}
