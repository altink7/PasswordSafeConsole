package at.altin.passwordsafe.beta.annotation.validator;

/**
 * object which implements this interface can be used for validation
 */
public interface Validator<T> {
    /**
     * validate the given Object with the given validator
     * @param object
     */
    void validate(T object);
}
