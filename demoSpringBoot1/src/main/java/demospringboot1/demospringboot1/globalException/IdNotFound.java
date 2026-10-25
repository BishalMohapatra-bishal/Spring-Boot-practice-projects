package demospringboot1.demospringboot1.globalException;

public class IdNotFound extends RuntimeException {
    public IdNotFound(String message) {
        super(message);
    }
}
