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
    class GetAllCustomersTesting {

        /*@Test
        void whenRepositoryIsEmptyGetAllCustomersThrowsException() {
            //then
            assertThrows(EmptyCollectionException.class, () -> service.getAllCustomers("666"));
        }*/

        @Test
        void whenUserWithoutAuthorisationTriesToGetCustomerListThrowsAuthorisationException() {
            //then
            assertThrows(AuthorisationException.class, () -> service.getAllCustomers("wrong id"));
        }

        /*@Test
        void whenTwoCustomersAreRegisteredGetAllCustomersReturnsAListOfTwoCustomer() {
            //given
            Address address = new Address("SomeStreet", "25", "45896", "SomeCity");
            Customer customer1 = new Customer().setFirstName("Tom")
                    .setLastName("B")
                    .setEmail("a@b.cd")
                    .setAddress(address)
                    .setPhoneNumber("12345");
            Customer customer2 = new Customer().setFirstName("Jack")
                    .setLastName("B")
                    .setEmail("a@b.cd")
                    .setAddress(address)
                    .setPhoneNumber("12345");
            //when
            repo.add(customer1);
            repo.add(customer2);
            List<Customer> values = service.getAllCustomers("666");
            //then
            assertTrue(values.get(0).getFirstName().equals("Tom") || values.get(0).getFirstName().equals("Jack"));
            assertTrue(values.get(1).getFirstName().equals("Tom") || values.get(1).getFirstName().equals("Jack"));
            assertThrows(IndexOutOfBoundsException.class, () -> values.get(2));
        }*/
    }
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class getCustomerByIdTesting {

        @Test
        void whenUserWithoutAuthorisationTriesToGetCustomerThrowsAuthorisationException() {
            //then
            assertThrows(AuthorisationException.class, () -> service.getCustomer("555", "wrong id"));
        }

        /*@Test
        void whenRepositoryIsEmptyGetCustomerThrowsException() {
            //then
            assertThrows(EmptyCollectionException.class, () -> service.getCustomer("555", "666"));
        }*/

        @Test
        void whenUserDoesNotExistInOurDatabaseGetCustomerThrowsException() {
            //given
            Address address = new Address("SomeStreet", "25", "45896", "SomeCity");
            Customer customer = new Customer().setFirstName("Tom")
                    .setLastName("B")
                    .setEmail("a@b.cd")
                    .setAddress(address)
                    .setPhoneNumber("12345");
            //when
            repo.add(customer);
            //then
            assertThrows(IllegalArgumentException.class, () -> service.getCustomer("123", "666"));
        }

        @Test
        void whenACustomerIsStoredInOurDatabaseGetCustomerReturnsThatUser() {
            //given
            Address address = new Address("SomeStreet", "25", "45896", "SomeCity");
            Customer customer = new Customer().setFirstName("Tom")
                    .setLastName("B")
                    .setEmail("a@b.cd")
                    .setAddress(address)
                    .setPhoneNumber("12345");
            //when
            repo.add(customer);
            //then
            assertEquals(customer, service.getCustomer(customer.getId(), "666"));
        }
    }
}