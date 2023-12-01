package by.vladpr.movieratingsystem.service.impl;

import by.vladpr.movieratingsystem.dao.DaoFactory;
import by.vladpr.movieratingsystem.dao.ReviewDao;
import by.vladpr.movieratingsystem.entity.Review;
import by.vladpr.movieratingsystem.exception.DaoException;
import by.vladpr.movieratingsystem.exception.ServiceException;
import by.vladpr.movieratingsystem.service.ReviewService;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {

    @Override
    public void addReview(Review review) throws ServiceException {
        ReviewDao reviewDao = DaoFactory.getInstance().getReviewDao();
        try {
            reviewDao.save(review);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Review> getReviewList() throws ServiceException {
        ReviewDao reviewDao = DaoFactory.getInstance().getReviewDao();
        List<Review> list;
        try {
            list = reviewDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return list;
    }

    @Override
    public void removeReview(long id) throws ServiceException {
        ReviewDao reviewDao = DaoFactory.getInstance().getReviewDao();
        try {
            reviewDao.removeById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
