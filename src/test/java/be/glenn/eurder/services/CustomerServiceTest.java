package be.glenn.eurder.services;

import be.glenn.eurder.domain.Address;
import be.glenn.eurder.domain.Customer;
import be.glenn.eurder.domain.dtos.CreateCustomerDto;
import be.glenn.eurder.mappers.CustomerMapper;
import be.glenn.eurder.repos.CustomerRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerServiceTest {

    private CustomerMapper mapper;
    private CustomerRepo repo;
    private CustomerService service;

    @BeforeAll
    void setup() {
         mapper = new CustomerMapper();
         repo = new CustomerRepo();
         service = new CustomerService(repo, mapper);
    }

    @Test
    void createCustomerWithValidDataResultsInTheCreationOfACustomer() {
        //given
        CreateCustomerDto dto = new CreateCustomerDto("Tom", "Hanks", "forrest@gump.com",
                new Address("SomeStreet", "25", "45896", "SomeCity"), "123456789");
        Customer customer = mapper.createDtoToCustomer(dto);
        //when
        service.createCustomer(dto);
        //then
        assertTrue(repo.contains(customer));
    }

    @Test
    void createCustomerWithInvalidDataDoesNotResultInTheCreationOfACustomer() {
        //given
        CreateCustomerDto dto = new CreateCustomerDto(null, "Hanks", "forrest@gump.com",
                new Address("SomeStreet", "25", "45896", "SomeCity"), "123456789");
        Customer customer = mapper.createDtoToCustomer(dto);
        // then
        assertThrows(IllegalArgumentException.class, () -> service.createCustomer(dto));
    }
}