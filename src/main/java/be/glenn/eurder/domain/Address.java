package be.glenn.eurder.domain;

public class Address {

    private final String street;
    private final String houseNr;
    private final String zipCode;
    private final String city;

    public Address(String street, String houseNr, String zipCode, String city) {
        this.street = street;
        this.houseNr = houseNr;
        this.zipCode = zipCode;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNr() {
        return houseNr;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public boolean allInputIsValid() {
        return street != null && houseNr != null && zipCode != null && city != null;
    }
}
