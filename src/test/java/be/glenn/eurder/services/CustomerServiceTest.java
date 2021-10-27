package be.glenn.eurder.services;

import be.glenn.eurder.domain.Address;
import be.glenn.eurder.domain.Customer;
import be.glenn.eurder.domain.dtos.CreateCustomerDto;
import be.glenn.eurder.exceptions.AuthorisationException;
import be.glenn.eurder.exceptions.EmptyCollectionException;
import be.glenn.eurder.mappers.CustomerMapper;
import be.glenn.eurder.repos.CustomerRepo;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerServiceTest {

    private CustomerMapper mapper;
    private CustomerRepo repo;
    private CustomerService service;

    @BeforeEach
    void setup() {
        mapper = new CustomerMapper();
        repo = new CustomerRepo();
        service = new CustomerService(repo, mapper);
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class CreateCustomerTesting {

        @Test
        void createCustomerWithValidDataResultsInTheCreationOfACustomer() {
            //given
            CreateCustomerDto dto = new CreateCustomerDto("Tom", "Hanks", "forrest@gump.com",
                    new Address("SomeStreet", "25", "45896", "SomeCity"), "123456789");
            //when
            Customer customer = service.createCustomer(dto);
            //then
            assertTrue(repo.contains(customer));
        }

        @Test
        void createCustomerWithInvalidDataDoesNotResultInTheCreationOfACustomerButThrowsException() {
            //given
            CreateCustomerDto dto = new CreateCustomerDto(null, "Hanks", "forrest@gump.com",
                    new Address("SomeStreet", "25", "45896", "SomeCity"), "123456789");
            // then
            assertThrows(IllegalArgumentException.class, () -> service.createCustomer(dto));
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class GetCustomerTesting {

        @Test
        void whenRepositoryIsEmptyGetAllCustomersThrowsException() {
            //then
            assertThrows(EmptyCollectionException.class, () -> service.getAllCustomers("666"));
        }

        @Test
        void whenUserWithoutAuthorisationTriesToGetCustomerListThrowsAuthorisationException() {
            //then
            assertThrows(AuthorisationException.class, () -> service.getAllCustomers("wrong id"));
        }

        @Test
        void whenTwoCustomersAreRegisteredGetAllCustomersReturnsAListOfTwoCustomer() {
            //given
            Address address = new Address("SomeStreet", "25", "45896", "SomeCity");
            CreateCustomerDto dto1 = new CreateCustomerDto("Tom", "Hanks", "forrest@gump.com",
                    address, "123456789");
            CreateCustomerDto dto2 = new CreateCustomerDto("Jack", "Daniels", "very@drunk.com",
                    address, "234567890");
            //when
            service.createCustomer(dto1);
            service.createCustomer(dto2);
            List<Customer> values = service.getAllCustomers("666");
            //then
            assertTrue(values.get(0).getFirstName().equals("Tom") || values.get(0).getFirstName().equals("Jack"));
            assertTrue(values.get(1).getFirstName().equals("Tom") || values.get(1).getFirstName().equals("Jack"));
            assertThrows(IndexOutOfBoundsException.class, () -> values.get(2));
        }
    }
}