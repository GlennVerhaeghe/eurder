package be.glenn.eurder.services;

import be.glenn.eurder.domain.Customer;
import be.glenn.eurder.domain.dtos.CreateCustomerDto;
import be.glenn.eurder.domain.dtos.CustomerDto;
import be.glenn.eurder.exceptions.AuthorisationException;
import be.glenn.eurder.exceptions.EmptyCollectionException;
import be.glenn.eurder.mappers.CustomerMapper;
import be.glenn.eurder.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;

    @Autowired
    public CustomerService(CustomerRepo repo, CustomerMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public CustomerDto createCustomer(CreateCustomerDto dto) {
        if(dto.allInputIsValid()) {
            return mapper.customerToDto(repo.add(mapper.createDtoToCustomer(dto)));
        }

        throw new IllegalArgumentException("Not all necessary information to create a new customer was provided");
    }

    public List<CustomerDto> getAllCustomers(String id) {
        if (!AdminValidator.isAdmin(id)) {
            throw new AuthorisationException("Only the admin can view customer details");
        }
        if(repo.getRepo().size() == 0) {
            throw new EmptyCollectionException("We don't have any customers yet");
        }
        return new ArrayList<>(mapper.customerListToCustomerDtoList(repo.getAllCustomers()));
    }

    public Customer getCustomer(String customerId, String validationId) {
        if(!AdminValidator.isAdmin(validationId)) {
            throw new AuthorisationException("Only the admin can view the details of a customer");
        }
        if (repo.getRepo().size() == 0) {
            throw new EmptyCollectionException("We don't have any customers yet");
        }
        if (!repo.getRepo().containsKey(customerId)) {
            throw new IllegalArgumentException("No customer with that ID is registered");
        }
        return repo.get(customerId);
    }
}
