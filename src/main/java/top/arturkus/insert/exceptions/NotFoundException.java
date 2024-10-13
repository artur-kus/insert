package top.arturkus.insert.exceptions;

public class NotFoundException extends Exception {

    private String errorMessage;

    public NotFoundException() {
        super();
    }

    public <T> NotFoundException(Class<T> objectClass) {
        this();
        this.errorMessage = "not.found." +  objectClass.getSimpleName();
    }

    public <T> NotFoundException(Class<T> objectClass, Long id) {
        this(objectClass);
        this.errorMessage += ".id." + id;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
