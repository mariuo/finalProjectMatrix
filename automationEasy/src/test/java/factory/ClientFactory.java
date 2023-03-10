package factory;

/**
 * This Class is a Design Patter Factory to initialise the objects to use in the
 * tests class.
 */
public class ClientFactory {

    public static Client createClient() {
        return new Client();
    }

    public static Client createClientMick() {
        return new Client("mick@gmail.com", "123", "55", "123321",
                "mick", "jagger", "26", "07", "1943", "UK", "123321",
                "UK", "Dartford", "kent", "2000", "51144", "UK", "123456"

        );
    }

    public static Client createClientJohn() {
        return new Client("john@gmail.com", "123", "55", "123321",
                "john", "lenon", "09", "10", "1940", "UK", "123321",
                "UK", "liverpool", "winston", "321", "12333", "UK", "123321"

        );
    }

}
