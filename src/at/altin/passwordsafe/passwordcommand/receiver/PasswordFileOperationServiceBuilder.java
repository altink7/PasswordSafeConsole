package at.altin.passwordsafe.passwordcommand.receiver;

/**
 * this is a builder for PasswordFileOperationService <br>
 * +Builder Pattern is used here to create PasswordFileOperationService <br>
 * can be used like this:
 * <pre>
 *  {@code
 *          PasswordFileOperationService passwordFileOperationService = new PasswordFileOperationServiceBuilder()
 *         .setPassword("password")
 *         .setCypher("cypher")
 *         .create();
 * }
 * </pre>
 *
 * @see PasswordFileOperationService
 */
public class PasswordFileOperationServiceBuilder {
    private String password;
    private String cypher;

    public PasswordFileOperationServiceBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public PasswordFileOperationServiceBuilder setCypher(String cypher) {
        this.cypher = cypher;
        return this;
    }

    public PasswordFileOperationService create() {
        return new PasswordFileOperationService(password, cypher);
    }
}