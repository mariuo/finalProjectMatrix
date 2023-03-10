package factory;


/**
 * Class Client.
 */
public class Client {
    private String email;
    private String password;
    private String areaCode;
    private String mobile;
    private String firstName;
    private String lastName;
    private String day;
    private String month;
    private String year;
    private String nationality;
    private String natIdNumber;
    private String countryAddress;
    private String city;
    private String street;
    private String streetNumber;
    private String postCode;
    private String taxResidence;
    private String taxIdNumber;

    public Client() {
    }

    public Client(String email, String password, String areaCode, String mobile, String firstName, String lastName, String day, String month, String year, String nationality, String natIdNumber, String countryAddress, String city, String street, String streetNumber, String postCode, String taxResidence, String taxIdNumber) {
        this.email = email;
        this.password = password;
        this.areaCode = areaCode;
        this.mobile = mobile;
        this.firstName = firstName;
        this.lastName = lastName;
        this.day = day;
        this.month = month;
        this.year = year;
        this.nationality = nationality;
        this.natIdNumber = natIdNumber;
        this.countryAddress = countryAddress;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.postCode = postCode;
        this.taxResidence = taxResidence;
        this.taxIdNumber = taxIdNumber;
    }

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

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNatIdNumber() {
        return natIdNumber;
    }

    public void setNatIdNumber(String natIdNumber) {
        this.natIdNumber = natIdNumber;
    }

    public String getCountryAddress() {
        return countryAddress;
    }

    public void setCountryAddress(String countryAddress) {
        this.countryAddress = countryAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getTaxResidence() {
        return taxResidence;
    }

    public void setTaxResidence(String taxResidence) {
        this.taxResidence = taxResidence;
    }

    public String getTaxIdNumber() {
        return taxIdNumber;
    }

    public void setTaxIdNumber(String taxIdNumber) {
        this.taxIdNumber = taxIdNumber;
    }

    @Override
    public String toString() {
        return "Client{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", mobile='" + mobile + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", day='" + day + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", nationality='" + nationality + '\'' +
                ", natIdNumber='" + natIdNumber + '\'' +
                ", countryAddress='" + countryAddress + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", postCode='" + postCode + '\'' +
                ", taxResidence='" + taxResidence + '\'' +
                ", taxIdNumber='" + taxIdNumber + '\'' +
                '}';
    }
}
