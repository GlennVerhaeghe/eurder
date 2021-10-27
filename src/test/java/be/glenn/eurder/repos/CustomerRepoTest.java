package be.glenn.eurder.repos;

import be.glenn.eurder.domain.Address;
import be.glenn.eurder.domain.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerRepoTest {

    private CustomerRepo repo;
    private Address address;

    @BeforeAll
    void setup() {
        repo = new CustomerRepo();
        address = new Address("SomeStreet", "25", "45896", "SomeCity");
    }

    @Test
    void addMethodAndContainsMethodWork() {
        //given
        Customer customer = new Customer()
                .setFirstName("A")
                .setLastName("B")
                .setEmail("a@b.cd")
                .setAddress(address)
                .setPhoneNumber("12345");
        Customer customer2 = new Customer()
                .setFirstName("A")
                .setLastName("B")
                .setEmail("a@b.cd")
                .setAddress(address)
                .setPhoneNumber("123456");
        //when
        repo.add(customer);
        //then
        assertTrue(repo.contains(customer));
        assertFalse(repo.contains(customer2));
    }
}