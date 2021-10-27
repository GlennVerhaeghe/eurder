package be.glenn.eurder.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void givenACompleteAddressTheAddressIsValid() {
        //given
        Address address = new Address("Street", "253", "1000", "Bxl");
        //then
        assertTrue(address.allInputIsValid());
    }

    void givenAnIncompleteAddressTheAddressIsInvalid() {
        //given
        Address address = new Address(null, "253", "1000", "Bxl");
        //then
        assertFalse(address.allInputIsValid());
    }

}