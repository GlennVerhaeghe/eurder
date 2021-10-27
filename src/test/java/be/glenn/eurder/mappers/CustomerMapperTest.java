package be.glenn.eurder.mappers;

import be.glenn.eurder.domain.Address;
import be.glenn.eurder.domain.Customer;
import be.glenn.eurder.domain.dtos.CreateCustomerDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerMapperTest {

    private CustomerMapper mapper;

    @BeforeAll
    void setup() {
        mapper = new CustomerMapper();
    }

    @Test
    void createDtoToCustomerWorksAsExpected() {
        //given
        CreateCustomerDto dto = new CreateCustomerDto("Tom", "Hanks", "forrest@gump.com",
                new Address("SomeStreet", "25", "45896", "SomeCity"), "123456789");
        //when
        Customer customer = mapper.createDtoToCustomer(dto);
        //then
        assertEquals(dto.getFirstName(), customer.getFirstName());
        assertEquals(dto.getLastName(), customer.getLastName());
        assertEquals(dto.getAddress(), customer.getAddress());
        assertEquals(dto.getEmail(), customer.getEmail());
        assertEquals(dto.getPhoneNumber(), customer.getPhoneNumber());
    }
}