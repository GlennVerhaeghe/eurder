package be.glenn.eurder.domain.dtos;

import be.glenn.eurder.domain.Address;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateCustomerDtoTest {

    Address validAddress = new Address("Street", "25", "1000", "Bxl");
    Address invalidAddress = new Address(null, "25", "1000", "Bxl");

    @Test
    void givenACompleteCreateCustomerDtoTheCreateCustomerDtoIsValid() {
        //when
        CreateCustomerDto dto = new CreateCustomerDto("J", "P", "j@p.cd", validAddress, "123");
        //then
        assertTrue(dto.allInputIsValid());
    }

    @Test
    void givenAnIncompleteCreateCustomerDtoTheCreateCustomerDtoIsInvalid() {
        //when
        CreateCustomerDto dto = new CreateCustomerDto(null, "P", "j@p.cd", validAddress, "123");
        //then
        assertFalse(dto.allInputIsValid());
    }

    @Test
    void givenAnInvalidAddressTheCreateCustomerDtoIsInvalid() {
        //when
        CreateCustomerDto dto = new CreateCustomerDto("J", "P", "j@p.cd", invalidAddress, "123");
        //then
        assertFalse(dto.allInputIsValid());
    }

}