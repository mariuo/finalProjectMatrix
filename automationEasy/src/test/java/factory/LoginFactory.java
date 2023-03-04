package factory;

/**
 * This Class is a Design Patter Factory to initialise the objects to use in the
 * tests class.
 */
public class LoginFactory {

    public static Login createLogin() {
        return new Login();
    }

    public static Login createLoginMick() {
        return new Login("mick@gmail.com", "123", null);
    }

    public static Login createLoginRingo() {
        return new Login("ringo@gmail.com", "123", "1234");
    }

    public static Login createLoginJohn() {
        return new Login("john@gmail.com", "123", "1234");
    }

    public static Login createLoginGeorge() {
        return new Login("george@gmail.com", "123", "1234");
    }

    public static Login createLoginPaul() {
        return new Login("paul@gmail.com", "123", "1234");
    }

    public static Login createLoginInvalidEmail() {
        return new Login("abc@gmail.com", "123", "1234");
    }

    public static Login createLoginInvalidPass() {
        return new Login("paul@gmail.com", "123123", "1234");
    }

    public static Login createLoginInvalidPin() {
        return new Login("paul@gmail.com", "123", "4321");
    }

    public static Login createLoginInvalidPassPin() {
        return new Login("paul@gmail.com", "12345", "4321");
    }

}
