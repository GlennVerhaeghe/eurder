package be.glenn.eurder.repos;

import be.glenn.eurder.domain.Address;
import be.glenn.eurder.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CustomerRepo {
    private final Map<String, Customer> repo;
    private final Customer dummy;

    @Autowired
    public CustomerRepo() {
        this.repo = new ConcurrentHashMap<>();
        dummy = new Customer().setFirstName("Dum").setLastName("My").setEmail("d@a.vv").setAddress(new Address("s","t","u","v")).setPhoneNumber("1245");
        repo.put(dummy.getId(), dummy);
    }

    public Customer add(Customer customer) {
        repo.put(customer.getId(), customer);
        return customer;
    }

    public boolean contains(Customer customer) {
        if(customer == null) {
            throw new IllegalArgumentException("Can't check for non-existing customers");
        }
        return repo.containsValue(customer);
    }

    public boolean containsKey(String customerId) {
        if(customerId == null) {
            throw new IllegalArgumentException("Can't check for non-existing customers");
        }
        return repo.containsKey(customerId);
    }

    public Customer get(String customerId) {
        return repo.get(customerId);
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(repo.values());
    }

    public Map<String, Customer> getRepo() {
        return repo;
    }
}
