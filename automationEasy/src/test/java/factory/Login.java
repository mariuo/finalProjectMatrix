package factory;

/**
 * Class Login
 */
public class Login {
    /*
     * Attributes from the class.
     */
    private String email;
    private String password;
    private String pin;

    /*
     * Empty constructor
     */
    public Login() {
    }

    /**
     * Constructor
     * 
     * @param email
     * @param password
     * @param pin
     */
    public Login(String email, String password, String pin) {
        this.email = email;
        this.password = password;
        this.pin = pin;
    }

    /*
     * Methods Getters and Setters
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "Login{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", pin='" + pin + '\'' +
                '}';
    }
}
