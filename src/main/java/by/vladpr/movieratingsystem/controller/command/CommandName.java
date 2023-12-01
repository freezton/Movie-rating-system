package by.vladpr.movieratingsystem.controller.command;

public abstract class CommandName {

    public static final String GO_TO_LOGIN_PAGE_COMMAND = "/login-form";
    public static final String GO_TO_REGISTRATION_PAGE_COMMAND = "/registration-form";
    public static final String LOGIN_COMMAND = "/login";
    public static final String REGISTER_COMMAND = "/registration";
    public static final String GO_TO_MOVIES_PAGE_COMMAND = "/movies";
    public static final String GO_TO_ADMIN_PAGE_COMMAND = "/admin-page";
    public static final String ADD_MOVIE_COMMAND = "/add-movie";
    public static final String UPDATE_MOVIE_COMMAND = "/update-movie";
    public static final String ADD_REVIEW_COMMAND = "/add-review";
    public static final String DELETE_REVIEW_COMMAND = "/delete-review";
    public static final String GET_MOVIES_COMMAND = "/get-movies";
    public static final String GET_MOVIE_COMMAND = "/get-movie";
    public static final String GO_TO_REVIEWS_PAGE = "/reviews-page";
    public static final String GET_USERS_COMMAND = "/get-users";
    public static final String UPDATE_USER_COMMAND = "/update-user";
    public static final String CHANGE_LOCALE_COMMAND = "/change-locale";
    public static final String LOGOUT_COMMAND = "/logout";



    public static final String ERROR404_COMMAND = "/error404";
    public static final String ERROR503_COMMAND = "/error503";

}
