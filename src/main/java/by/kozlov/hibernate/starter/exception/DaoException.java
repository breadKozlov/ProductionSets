package by.kozlov.hibernate.starter.exception;

public class DaoException extends RuntimeException{

    public DaoException(Throwable ex) {
        super(ex);
    }
}
