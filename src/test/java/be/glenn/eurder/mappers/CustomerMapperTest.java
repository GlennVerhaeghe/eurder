package be.glenn.eurder.mappers;

import be.glenn.eurder.domain.Address;
import be.glenn.eurder.domain.Customer;
import be.glenn.eurder.domain.dtos.CreateCustomerDto;
import be.glenn.eurder.domain.dtos.CustomerDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerMapperTest {

    private CustomerMapper mapper;
    private Address address;

    @BeforeAll
    void setup() {
        mapper = new CustomerMapper();
        address = new Address("SomeStreet", "25", "45896", "SomeCity");
    }

    @Test
    void createDtoToCustomerWorksAsExpected() {
        //given
        CreateCustomerDto dto = new CreateCustomerDto("Tom", "Hanks", "forrest@gump.com",
                address, "123456789");
        //when
        Customer customer = mapper.createDtoToCustomer(dto);
        //then
        assertEquals(dto.getFirstName(), customer.getFirstName());
        assertEquals(dto.getLastName(), customer.getLastName());
        assertEquals(dto.getAddress(), customer.getAddress());
        assertEquals(dto.getEmail(), customer.getEmail());
        assertEquals(dto.getPhoneNumber(), customer.getPhoneNumber());
        assertNotNull(customer.getId());
    }

    @Test
    void customerToDtoWorksAsExpected() {
        //given
        Customer customer = new Customer().setFirstName("A")
                .setLastName("B")
                .setEmail("a@b.cd")
                .setAddress(address)
                .setPhoneNumber("12345");
        //when
        CustomerDto dto = mapper.customerToDto(customer);
        //then
        assertEquals(customer.getFirstName(), dto.getFirstName());
        assertEquals(customer.getLastName(), dto.getLastName());
        assertEquals(customer.getEmail(), dto.getEmail());
        assertEquals(customer.getAddress(), dto.getAddress());
        assertEquals(customer.getPhoneNumber(), dto.getPhoneNumber());
    }

    @Test
    void createDtoToDtoWorksAsExpected() {
        //given
        CreateCustomerDto dto = new CreateCustomerDto("Tom", "Hanks", "forrest@gump.com",
                address, "123456789");
        //when
        CustomerDto result = mapper.createDtoToDto(dto);
        //then
        assertEquals(dto.getFirstName(), result.getFirstName());
        assertEquals(dto.getLastName(), result.getLastName());
        assertEquals(dto.getAddress(), result.getAddress());
        assertEquals(dto.getEmail(), result.getEmail());
        assertEquals(dto.getPhoneNumber(), result.getPhoneNumber());
    }
}