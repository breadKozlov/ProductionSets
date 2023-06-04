package by.kozlov.spring.exception;

public class DaoException extends RuntimeException{

    public DaoException(Throwable ex) {
        super(ex);
    }
}
