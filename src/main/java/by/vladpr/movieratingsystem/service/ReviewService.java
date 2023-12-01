package by.vladpr.movieratingsystem.service;

import by.vladpr.movieratingsystem.entity.Review;
import by.vladpr.movieratingsystem.exception.ServiceException;

import java.util.List;

public interface ReviewService {

    void addReview(Review review) throws ServiceException;


    List<Review> getReviewList() throws ServiceException;

    void removeReview(long id) throws ServiceException;
}
