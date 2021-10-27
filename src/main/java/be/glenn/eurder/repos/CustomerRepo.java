package be.glenn.eurder.repos;

import be.glenn.eurder.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CustomerRepo {
    private final Map<String, Customer> repo;

    @Autowired
    public CustomerRepo() {
        this.repo = new ConcurrentHashMap<>();
    }

    public void add(Customer customer) {
        repo.put(customer.getId(), customer);
    }

    public boolean contains(Customer customer) {
        return repo.containsValue(customer);
    }
}
