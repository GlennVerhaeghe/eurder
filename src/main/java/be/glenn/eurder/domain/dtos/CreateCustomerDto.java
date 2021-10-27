package be.glenn.eurder.domain.dtos;

import be.glenn.eurder.domain.Address;

public class CreateCustomerDto {

    private final String firstName;
    private final String lastName;
    private String email;
    private Address address;
    private String phoneNumber;

    public CreateCustomerDto(String firstName, String lastName, String email, Address address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean allInputIsValid() {
        return firstName != null
                && lastName != null
                && email != null
                && address != null
                && address.allInputIsValid()
                && phoneNumber != null;
    }
}
