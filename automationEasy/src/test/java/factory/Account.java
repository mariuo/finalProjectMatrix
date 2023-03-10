package factory;

/**
 * Class Account
 */
public class Account {
    private String email;
    private String firstName;
    private String lastName;
    private String accNum;
    private String iban;
    private String totalBalance;

    public Account() {
    }

    public Account(String email, String firstName, String lastName, String accNum, String iban, String totalBalance) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accNum = accNum;
        this.iban = iban;
        this.totalBalance = totalBalance;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAccNum() {
        return accNum;
    }

    public String getTotalBalance() {
        return totalBalance;
    }


}
